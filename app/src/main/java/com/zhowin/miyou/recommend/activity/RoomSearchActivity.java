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
import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.base_library.utils.EmptyViewUtils;
import com.zhowin.base_library.utils.KeyboardUtils;
import com.zhowin.base_library.utils.SizeUtils;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.base_library.widget.GridSpacingItemDecoration;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityRoomSearchBinding;
import com.zhowin.miyou.http.BaseResponse;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.mine.adapter.MyRoomListAdapter;
import com.zhowin.miyou.mine.dialog.UnlockRoomDialog;
import com.zhowin.miyou.recommend.adapter.SearchHistoryAdapter;
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
                List<String> searchHistoryLists = new ArrayList<>();
                searchHistoryLists.add("周树人");
                searchHistoryLists.add("树人教育");
                searchHistoryLists.add("hello树先生");
                SearchHistoryAdapter searchHistoryAdapter = new SearchHistoryAdapter(searchHistoryLists);
                mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                mBinding.recyclerView.setAdapter(searchHistoryAdapter);
                break;
        }
    }

    @Override
    public void initListener() {
        mBinding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 0;
                if (1 == classType) {
                    startSearchRoom();
                } else {
                    startSearchFriend();
                }
                mBinding.refreshLayout.setRefreshing(false);
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
        boolean roomIsLock = roomSearchAdapter.getItem(position).isExistPwd();
        if (roomIsLock) {
            showUnLockRoomDialog();
        } else {

        }
    }

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
            }
        });

    }

    /**
     * 搜索好友
     */
    private void startSearchFriend() {
    }
}
