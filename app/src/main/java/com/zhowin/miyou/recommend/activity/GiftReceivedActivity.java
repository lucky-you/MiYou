package com.zhowin.miyou.recommend.activity;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindActivity;
import com.zhowin.miyou.databinding.ActivityGiftReceivedBinding;
import com.zhowin.miyou.recommend.adapter.GiftReceivedAdapter;
import com.zhowin.miyou.recommend.model.GiftList;

import java.util.ArrayList;
import java.util.List;

/**
 * 收到的礼物
 */
public class GiftReceivedActivity extends BaseBindActivity<ActivityGiftReceivedBinding> {

    private GiftReceivedAdapter giftReceivedAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_gift_received;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        List<GiftList> giftLists = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            giftLists.add(new GiftList());
        }
        giftReceivedAdapter = new GiftReceivedAdapter(giftLists);
        mBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        mBinding.recyclerView.setAdapter(giftReceivedAdapter);
    }

    @Override
    public void initListener() {
        mBinding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mBinding.refreshLayout.setEnabled(false);
            }
        });
    }
}
