package com.zhowin.miyou.mine.activity;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindActivity;
import com.zhowin.miyou.databinding.ActivityMyDiamondBinding;
import com.zhowin.miyou.mine.adapter.RechargeListAdapter;
import com.zhowin.miyou.mine.model.RechargeList;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的钻石 和 我的魅力值
 */
public class MyDiamondActivity extends BaseBindActivity<ActivityMyDiamondBinding> implements BaseQuickAdapter.OnItemClickListener {


    private int classType;
    private RechargeListAdapter rechargeLIstAdapter;


    public static void start(Context context, int type) {
        Intent intent = new Intent(context, MyDiamondActivity.class);
        intent.putExtra(ConstantValue.TYPE, type);
        context.startActivity(intent);

    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_my_diamond;
    }

    @Override
    public void initView() {
        classType = getIntent().getIntExtra(ConstantValue.TYPE, -1);
        mBinding.titleView.setTitle(1 == classType ? "我的钻石" : "我的魅力值");
        mBinding.tvDiamondText.setText(1 == classType ? "钻石余额" : "魅力值");
        mBinding.ivDiamondImage.setImageResource(1 == classType ? R.drawable.wallet_diamond_icon : R.drawable.wallet_heart_icon);
        mBinding.tvRechargeLeHitMessage.setText(1 == classType ? "（充值金额无法提现,1元=10钻石）" : "（1魅力值=1钻石）");
        mBinding.editRechargeMoney.setHint(1 == classType ? "请输入充值金额（元）" : "请输入兑换魅力值(1~100000之间）");
        mBinding.llRechargeLayout.setVisibility(1 == classType ? View.VISIBLE : View.GONE);
        mBinding.tvConfirmPayment.setText(1 == classType ? "确认支付（30元）" : "确认兑换（1000魅力值）");
        mBinding.tvRechargeConsultation.setText(1 == classType ? "充值咨询" : "兑换咨询");
    }

    @Override
    public void initData() {
        setRecyclerViewData();
    }


    @Override
    public void initListener() {
        mBinding.titleView.getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MasonryDetailsActivity.start(mContext, classType);
            }
        });
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
        rechargeLIstAdapter.setAdapterType(classType);
        rechargeLIstAdapter.setOnItemClickListener(this::onItemClick);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        rechargeLIstAdapter.setCurrentPosition(position);
    }
}