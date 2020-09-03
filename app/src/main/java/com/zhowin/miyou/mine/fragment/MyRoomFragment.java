package com.zhowin.miyou.mine.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.base_library.utils.SizeUtils;
import com.zhowin.base_library.widget.GridSpacingItemDecoration;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.IncludeMyRoomFragmentBinding;
import com.zhowin.miyou.mine.activity.CreateRoomActivity;
import com.zhowin.miyou.mine.activity.VerifiedActivity;
import com.zhowin.miyou.mine.adapter.MyRoomListAdapter;
import com.zhowin.miyou.mine.dialog.UserVerifiedDialog;
import com.zhowin.miyou.recommend.adapter.RecommendListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author : zho
 * date  ：2020/9/1
 * desc ： 我的房间
 */
public class MyRoomFragment extends BaseBindFragment<IncludeMyRoomFragmentBinding> {


    private int fragmentIndex;
    private MyRoomListAdapter myRoomListAdapter;

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
        List<String> recommendLists = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            recommendLists.add("");
        }
        myRoomListAdapter = new MyRoomListAdapter(recommendLists);
        mBinding.recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, SizeUtils.dp2px(10), false));
        mBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mBinding.recyclerView.setAdapter(myRoomListAdapter);
    }


    @Override
    public void initListener() {
        mBinding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mBinding.refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvCreateRoom:
                showCreateRoomDialog();
                break;
        }
    }

    private void showCreateRoomDialog() {
        UserVerifiedDialog userVerifiedDialog = new UserVerifiedDialog(mContext);
        userVerifiedDialog.show();
        userVerifiedDialog.setOnVerifiedButtonClickListener(new UserVerifiedDialog.OnVerifiedButtonClickListener() {
            @Override
            public void onGoToVerified() {
                startActivity(CreateRoomActivity.class);
            }
        });

    }
}
