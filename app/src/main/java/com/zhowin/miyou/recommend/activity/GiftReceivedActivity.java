package com.zhowin.miyou.recommend.activity;


import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityGiftReceivedBinding;
import com.zhowin.miyou.recommend.adapter.GiftReceivedAdapter;

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
