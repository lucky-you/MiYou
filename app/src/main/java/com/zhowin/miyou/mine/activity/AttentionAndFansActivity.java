package com.zhowin.miyou.mine.activity;


import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityAttentionAndFansBinding;
import com.zhowin.miyou.mine.adapter.AttentionAndFansAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 关注、粉丝,访客
 */
public class AttentionAndFansActivity extends BaseBindActivity<ActivityAttentionAndFansBinding> {


    private AttentionAndFansAdapter attentionAndFansAdapter;

    private int classType;

    public static void start(Context context, int type) {
        Intent intent = new Intent(context, AttentionAndFansActivity.class);
        intent.putExtra(ConstantValue.TYPE, type);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_attention_and_fans;
    }

    @Override
    public void initView() {
        classType = getIntent().getIntExtra(ConstantValue.TYPE, -1);
        switch (classType) {
            case 1:
                mBinding.titleView.setTitle("关注");
                mBinding.tvFansNumber.setText("关注20人");
                break;
            case 2:
                mBinding.titleView.setTitle("粉丝");
                mBinding.tvFansNumber.setText("粉丝20人");
                break;
            case 3:
                mBinding.titleView.setTitle("访客");
                mBinding.tvFansNumber.setText("访客20人");
                break;
        }
    }

    @Override
    public void initData() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            stringList.add("");
        }
        attentionAndFansAdapter = new AttentionAndFansAdapter(stringList);
        attentionAndFansAdapter.setAdapterType(classType);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(attentionAndFansAdapter);


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
}
