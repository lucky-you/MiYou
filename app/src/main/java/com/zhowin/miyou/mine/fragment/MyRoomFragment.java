package com.zhowin.miyou.mine.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.base_library.utils.EmptyViewUtils;
import com.zhowin.base_library.utils.SizeUtils;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.base_library.widget.GridSpacingItemDecoration;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.IncludeMyRoomFragmentBinding;
import com.zhowin.miyou.http.BaseResponse;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.mine.activity.CreateRoomActivity;
import com.zhowin.miyou.mine.activity.VerifiedActivity;
import com.zhowin.miyou.mine.adapter.MyRoomListAdapter;
import com.zhowin.miyou.mine.dialog.UnlockRoomDialog;
import com.zhowin.miyou.mine.dialog.UserVerifiedDialog;
import com.zhowin.miyou.mine.model.VerifiedStatus;
import com.zhowin.miyou.recommend.model.RecommendList;

import java.util.ArrayList;
import java.util.List;

/**
 * author : zho
 * date  ：2020/9/1
 * desc ： 我的房间
 */
public class MyRoomFragment extends BaseBindFragment<IncludeMyRoomFragmentBinding> implements BaseQuickAdapter.OnItemClickListener {


    private int fragmentIndex;
    private MyRoomListAdapter myRoomListAdapter;
    private List<RecommendList> roomDataList = new ArrayList<>();

    public static MyRoomFragment newInstance(int index) {
        MyRoomFragment fragment = new MyRoomFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_my_room_fragment;
    }

    @Override
    public void initView() {
        fragmentIndex = getArguments().getInt(ConstantValue.INDEX);
        mBinding.tvCreateRoom.setVisibility(0 == fragmentIndex ? View.VISIBLE : View.GONE);
        setOnClick(R.id.tvCreateRoom);
    }

    @Override
    public void initData() {
        myRoomListAdapter = new MyRoomListAdapter(roomDataList);
        mBinding.recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, SizeUtils.dp2px(10), false));
        mBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mBinding.recyclerView.setAdapter(myRoomListAdapter);
        myRoomListAdapter.setOnItemClickListener(this::onItemClick);
    }

    @Override
    public void onResume() {
        super.onResume();
        getMyCreateOrCollectionRoomList(0 == fragmentIndex);
    }

    /**
     * 获取我创建或者我收藏的房间
     *
     * @param isMyCreate 是否是我创建
     */
    private void getMyCreateOrCollectionRoomList(boolean isMyCreate) {
        showLoadDialog();
        HttpRequest.getMyCreateOrCollectionRoomList(this, isMyCreate, new HttpCallBack<BaseResponse<RecommendList>>() {
            @Override
            public void onSuccess(BaseResponse<RecommendList> roomList) {
                dismissLoadDialog();
                if (roomList != null && !roomList.getRecords().isEmpty()) {
                    myRoomListAdapter.setNewData(roomList.getRecords());
                } else {
                    EmptyViewUtils.bindEmptyView(mContext, myRoomListAdapter, R.drawable.empty_wusc_icon, isMyCreate ? "暂无房间" : "暂无收藏");
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);
            }
        });
    }


    @Override
    public void initListener() {
        mBinding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mBinding.refreshLayout.setRefreshing(false);
                getMyCreateOrCollectionRoomList(0 == fragmentIndex);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvCreateRoom:
                getVerifiedStatus();
                break;
        }
    }

    /**
     * 实名认证状态
     */
    private void getVerifiedStatus() {
        HttpRequest.getVerifiedStatus(this, new HttpCallBack<VerifiedStatus>() {
            @Override
            public void onSuccess(VerifiedStatus verifiedStatus) {
                if (verifiedStatus != null) {
                    startActivity(CreateRoomActivity.class);
                } else {
                    showCreateRoomDialog();
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                ToastUtils.showToast(errorMsg);
            }
        });
    }

    /**
     * 实名认证
     */
    private void showCreateRoomDialog() {
        UserVerifiedDialog userVerifiedDialog = new UserVerifiedDialog(mContext);
        userVerifiedDialog.show();
        userVerifiedDialog.setOnVerifiedButtonClickListener(new UserVerifiedDialog.OnVerifiedButtonClickListener() {
            @Override
            public void onGoToVerified() {
                startActivity(VerifiedActivity.class);
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        showUnLockRoomDialog();
    }

    private void showUnLockRoomDialog() {
        UnlockRoomDialog unlockRoomDialog = new UnlockRoomDialog(mContext);
        unlockRoomDialog.show();

    }
}
