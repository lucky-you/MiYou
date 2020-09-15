package com.zhowin.miyou.recommend.activity;


import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.base_library.utils.SizeUtils;
import com.zhowin.base_library.widget.GridSpacingItemDecoration;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityRoomSearchBinding;
import com.zhowin.miyou.mine.adapter.MyRoomListAdapter;
import com.zhowin.miyou.recommend.adapter.SearchHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 房间搜索
 */
public class RoomSearchActivity extends BaseBindActivity<ActivityRoomSearchBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_room_search;
    }

    @Override
    public void initView() {
        setOnClick(R.id.ivBackReturn, R.id.tvSearchText);
    }

    @Override
    public void initData() {
        List<String> recommendLists = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            recommendLists.add("");
        }
//        MyRoomListAdapter myRoomListAdapter = new MyRoomListAdapter(recommendLists);
//        mBinding.recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, SizeUtils.dp2px(10), false));
//        mBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
//        mBinding.recyclerView.setAdapter(myRoomListAdapter);

        SearchHistoryAdapter searchHistoryAdapter = new SearchHistoryAdapter(recommendLists);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(searchHistoryAdapter);
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
            case R.id.ivBackReturn:
                ActivityManager.getAppInstance().finishActivity();
                break;
            case R.id.tvSearchText:
                break;
        }
    }
}
