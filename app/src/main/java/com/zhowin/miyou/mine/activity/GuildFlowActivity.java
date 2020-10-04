package com.zhowin.miyou.mine.activity;


import androidx.recyclerview.widget.LinearLayoutManager;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityGuildFlowBinding;
import com.zhowin.miyou.mine.adapter.GuildFlowAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 公会流水
 */
public class GuildFlowActivity extends BaseBindActivity<ActivityGuildFlowBinding> {

    private GuildFlowAdapter guildFlowAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_guild_flow;
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
}