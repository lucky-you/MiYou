package com.zhowin.miyou.mine.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindActivity;
import com.zhowin.miyou.databinding.ActivityGuildMemberBinding;
import com.zhowin.miyou.mine.adapter.GuildFlowAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 公会成员
 */
public class GuildMemberActivity extends BaseBindActivity<ActivityGuildMemberBinding> {
    private GuildFlowAdapter guildFlowAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_guild_member;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("");
        }
        guildFlowAdapter = new GuildFlowAdapter(strings);
        guildFlowAdapter.setAdapterType(1);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(guildFlowAdapter);
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