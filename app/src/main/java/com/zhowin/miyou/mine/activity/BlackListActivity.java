package com.zhowin.miyou.mine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityBlackListBinding;
import com.zhowin.miyou.mine.adapter.BlackListAdapter;
import com.zhowin.miyou.mine.model.BlackList;
import com.zhowin.miyou.recommend.adapter.TodayListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 黑名单
 */
public class BlackListActivity extends BaseBindActivity<ActivityBlackListBinding> {

    private BlackListAdapter blackListAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_black_list;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        List<BlackList> blackLists = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            blackLists.add(new BlackList());
        }
        blackListAdapter = new BlackListAdapter(blackLists);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(blackListAdapter);
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
