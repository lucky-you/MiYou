package com.zhowin.miyou.recommend.activity;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.base_library.utils.EmptyViewUtils;
import com.zhowin.base_library.utils.KeyboardUtils;
import com.zhowin.base_library.utils.SizeUtils;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.base_library.widget.GridSpacingItemDecoration;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityRoomSearchBinding;
import com.zhowin.miyou.db.manager.DaoManagerUtils;
import com.zhowin.miyou.db.model.SearchUserHistory;
import com.zhowin.miyou.http.BaseResponse;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.mine.adapter.MyRoomListAdapter;
import com.zhowin.miyou.mine.dialog.UnlockRoomDialog;
import com.zhowin.miyou.recommend.adapter.SearchHistoryAdapter;
import com.zhowin.miyou.recommend.adapter.SearchUserResultAdapter;
import com.zhowin.miyou.recommend.callback.OnHitCenterClickListener;
import com.zhowin.miyou.recommend.dialog.HitCenterDialog;
import com.zhowin.miyou.recommend.model.RecommendList;

import java.util.ArrayList;
import java.util.List;

/**
 * 房间/ 好友 搜索
 * <p>
 * type: 1 房间  2 好友
 */
public class RoomSearchActivity extends BaseBindActivity<ActivityRoomSearchBinding> implements BaseQuickAdapter.OnItemClickListener {

    private int classType;
    private MyRoomListAdapter roomSearchAdapter;
    private SearchHistoryAdapter searchHistoryAdapter;
    private SearchUserResultAdapter searchUserResultAdapter;

    public static void start(Context context, int type) {
        Intent intent = new Intent(context, RoomSearchActivity.class);
        intent.putExtra(ConstantValue.TYPE, type);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_room_search;
    }

    @Override
    public void initView() {
        setOnClick(R.id.ivBackReturn, R.id.tvSearchText);
        classType = getIntent().getIntExtra(ConstantValue.TYPE, 1);
    }

    @Override
    public void initData() {
        switch (classType) {
            case 1:
                mBinding.editSearch.setHint("请输入房间ID/名称关键字");
                roomSearchAdapter = new MyRoomListAdapter();
                mBinding.recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, SizeUtils.dp2px(10), false));
                mBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
                mBinding.recyclerView.setAdapter(roomSearchAdapter);
                roomSearchAdapter.setOnItemClickListener(this::onItemClick);
                break;
            case 2:
                mBinding.editSearch.setHint("请输入好友ID/昵称关键字");
                mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                searchHistoryAdapter = new SearchHistoryAdapter();
                searchHistoryAdapter.setOnItemClickListener(this::onItemClick);
                searchUserResultAdapter = new SearchUserResultAdapter();
                searchUserResultAdapter.setOnItemClickListener(this::onItemClick);
                queryHistoryData();
                break;
        }
    }

    /**
     * 查询数据
     */
    private void queryHistoryData() {
        List<SearchUserHistory> searchHistoryList = DaoManagerUtils.getHistoryData();
        if (searchHistoryList != null && !searchHistoryList.isEmpty()) {
            mBinding.recyclerView.setAdapter(searchHistoryAdapter);
            searchHistoryAdapter.setNewData(searchHistoryList);
        } else {
            EmptyViewUtils.bindEmptyView(mContext, searchHistoryAdapter, R.drawable.empty_wufj_icon, "没有历史记录");
        }
    }

    @Override
    public void initListener() {
        mBinding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mBinding.refreshLayout.setRefreshing(false);
                String keyWord = mBinding.editSearch.getText().toString().trim();
                if (TextUtils.isEmpty(keyWord)) return;
                currentPage = 0;
                if (1 == classType) {
                    startSearchRoom();
                } else {
                    startSearchFriend();
                }
            }
        });
        mBinding.editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (1 == classType) {
                        startSearchRoom();
                    } else {
                        startSearchFriend();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (adapter instanceof MyRoomListAdapter) {
            boolean roomIsLock = roomSearchAdapter.getItem(position).isExistPwd();
            if (roomIsLock) {
                showUnLockRoomDialog();
            } else {

            }
        } else if (adapter instanceof SearchHistoryAdapter) {
            SearchUserHistory searchUserHistory = searchHistoryAdapter.getItem(position);
            if (searchUserHistory != null) {
                showDeleteUserDialog(searchUserHistory);
            }
        } else if (adapter instanceof SearchUserResultAdapter) {
            int userId = searchUserResultAdapter.getItem(position).getUserId();
            HomepageActivity.start(mContext, userId == UserInfo.getUserInfo().getUserId(), userId);
        }
    }

    private void showDeleteUserDialog(SearchUserHistory searchUserHistory) {
        HitCenterDialog hitCenterDialog = new HitCenterDialog(mContext);
        hitCenterDialog.setDialogTitle("确定要删除吗?");
        hitCenterDialog.show();
        hitCenterDialog.setOnHitCenterClickListener(new OnHitCenterClickListener() {
            @Override
            public void onCancelClick() {

            }

            @Override
            public void onDetermineClick() {
                DaoManagerUtils.deleteData(searchUserHistory);
                queryHistoryData();
            }
        });

    }

    /**
     * 解锁房间
     */
    private void showUnLockRoomDialog() {
        UnlockRoomDialog unlockRoomDialog = new UnlockRoomDialog(mContext);
        unlockRoomDialog.show();
        unlockRoomDialog.setOnUnLockRoomListener(new UnlockRoomDialog.OnUnLockRoomListener() {
            @Override
            public void onUnLockRoom(String password) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBackReturn:
                ActivityManager.getAppInstance().finishActivity();
                break;
            case R.id.tvSearchText:
                if (1 == classType) {
                    startSearchRoom();
                } else {
                    startSearchFriend();
                }
                KeyboardUtils.hideSoftInput(mContext);
                break;
        }
    }

    /**
     * 搜索房间
     */
    private void startSearchRoom() {
        String keyWord = mBinding.editSearch.getText().toString().trim();
        if (TextUtils.isEmpty(keyWord)) {
            ToastUtils.showToast("关键字不能为空哦");
            return;
        }
        showLoadDialog();
        HttpRequest.searchRoomResultList(this, keyWord, currentPage, pageNumber, new HttpCallBack<BaseResponse<RecommendList>>() {
            @Override
            public void onSuccess(BaseResponse<RecommendList> recommendListBaseResponse) {
                dismissLoadDialog();
                if (recommendListBaseResponse != null) {
                    List<RecommendList> roomList = recommendListBaseResponse.getRecords();
                    if (roomList != null && !roomList.isEmpty()) {
                        mBinding.recyclerView.setAdapter(roomSearchAdapter);
                        roomSearchAdapter.setNewData(roomList);
                    } else {
                        EmptyViewUtils.bindEmptyView(mContext, roomSearchAdapter, R.drawable.empty_wufj_icon, "没有搜索到房间");
                        ToastUtils.showToast("没有符合条件的房间哦");
                    }
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);
            }
        });

    }

    /**
     * 搜索好友
     */
    private void startSearchFriend() {
        String keyWord = mBinding.editSearch.getText().toString().trim();
        if (TextUtils.isEmpty(keyWord)) {
            ToastUtils.showToast("关键字不能为空哦");
            return;
        }
        showLoadDialog();
        HttpRequest.searchUserResultList(this, keyWord, currentPage, pageNumber, new HttpCallBack<BaseResponse<UserInfo>>() {
            @Override
            public void onSuccess(BaseResponse<UserInfo> userInfoBaseResponse) {
                dismissLoadDialog();
                DaoManagerUtils.insertHistoryDao(keyWord);
                if (userInfoBaseResponse != null) {
                    List<UserInfo> userInfoList = userInfoBaseResponse.getRecords();
                    if (userInfoList != null && !userInfoList.isEmpty()) {
                        mBinding.recyclerView.setAdapter(searchUserResultAdapter);
                        searchUserResultAdapter.setNewData(userInfoList);
                    } else {
                        mBinding.recyclerView.setAdapter(searchUserResultAdapter);
                        searchUserResultAdapter.setNewData(userInfoList);
                        EmptyViewUtils.bindEmptyView(mContext, searchUserResultAdapter, R.drawable.empty_wufj_icon, "没有搜索到好友");
                        ToastUtils.showToast("没有符合条件的好友哦");
                    }
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);
            }
        });
    }
}
