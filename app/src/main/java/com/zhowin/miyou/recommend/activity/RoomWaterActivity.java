package com.zhowin.miyou.recommend.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.View;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityRoomWaterBinding;
import com.zhowin.miyou.recommend.adapter.RoomWaterListAdapter;

import java.util.Arrays;
import java.util.List;

import io.rong.imageloader.utils.L;

/**
 * 房间流水
 */
public class RoomWaterActivity extends BaseBindActivity<ActivityRoomWaterBinding> {


    private RoomWaterListAdapter roomWaterListAdapter;
    private View headerView;


    @Override
    public int getLayoutId() {
        return R.layout.activity_room_water;
    }

    @Override
    public void initView() {
        headerView = View.inflate(mContext, R.layout.include_room_water_header_layout, null);
    }

    @Override
    public void initData() {
        List<String> stringList = Arrays.asList("2", "3", "34", "56", "46", "dsf", "cd4");
        roomWaterListAdapter = new RoomWaterListAdapter(stringList);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(roomWaterListAdapter);
        roomWaterListAdapter.addHeaderView(headerView);
        roomWaterListAdapter.setHeaderAndEmpty(true);
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
