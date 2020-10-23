package com.zhowin.miyou.mine.activity;


import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindActivity;
import com.zhowin.miyou.databinding.ActivityUnionBinding;
import com.zhowin.miyou.mine.adapter.GuildListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 工会
 */
public class UnionActivity extends BaseBindActivity<ActivityUnionBinding> {


    private GuildListAdapter guildListAdapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_union;
    }

    @Override
    public void initView() {
        setOnClick(R.id.tvSearch,R.id.tvMyGuild);
    }

    @Override
    public void initData() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            stringList.add("");
        }
        guildListAdapter = new GuildListAdapter(stringList);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(guildListAdapter);

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

            case R.id.tvSearch:
                startActivity(GuildSearchActivity.class);
                break;
            case R.id.tvMyGuild:
                startActivity(MyGuildActivity.class);
                break;
        }
    }

}
