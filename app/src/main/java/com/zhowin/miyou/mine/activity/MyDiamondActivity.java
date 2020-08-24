package com.zhowin.miyou.mine.activity;


import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityMyDiamondBinding;
import com.zhowin.miyou.mine.adapter.RechargeListAdapter;
import com.zhowin.miyou.mine.model.RechargeList;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的钻石
 */
public class MyDiamondActivity extends BaseBindActivity<ActivityMyDiamondBinding> implements BaseQuickAdapter.OnItemClickListener {


    private RechargeListAdapter rechargeLIstAdapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_my_diamond;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

        setRecyclerViewData();
    }

    private void setRecyclerViewData() {
        List<RechargeList> rechargeLists = new ArrayList<>();
        rechargeLists.add(new RechargeList("50", "5"));
        rechargeLists.add(new RechargeList("100", "10"));
        rechargeLists.add(new RechargeList("300", "30"));
        rechargeLists.add(new RechargeList("500", "50"));
        rechargeLists.add(new RechargeList("1080", "108"));
        rechargeLists.add(new RechargeList("2080", "208"));
        mBinding.rechargeRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        rechargeLIstAdapter = new RechargeListAdapter(rechargeLists);
        mBinding.rechargeRecyclerView.setAdapter(rechargeLIstAdapter);
        rechargeLIstAdapter.setOnItemClickListener(this::onItemClick);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        rechargeLIstAdapter.setCurrentPosition(position);
    }
}