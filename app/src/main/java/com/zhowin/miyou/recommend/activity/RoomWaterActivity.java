package com.zhowin.miyou.recommend.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindActivity;
import com.zhowin.miyou.databinding.ActivityRoomWaterBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.recommend.adapter.RoomWaterListAdapter;
import com.zhowin.miyou.recommend.model.RoomWaterBean;
import com.zhowin.miyou.recommend.model.RoomWaterList;

import java.util.ArrayList;
import java.util.List;

/**
 * 房间流水
 */
public class RoomWaterActivity extends BaseBindActivity<ActivityRoomWaterBinding> {


    private RoomWaterListAdapter roomWaterListAdapter;
    private View headerView;
    private int roomId;
    private List<RoomWaterList> roomWaterList = new ArrayList<>();
    private boolean isTodayWater = true;
    private TextView tvLeftWaterTitle, tvLeftWaterNumber, tvRightWaterTitle, tvRightWaterNumber, tvThisWeekWater;


    public static void start(Context context, int roomID) {
        Intent intent = new Intent(context, RoomWaterActivity.class);
        intent.putExtra(ConstantValue.ID, roomID);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_room_water;
    }

    @Override
    public void initView() {
        roomId = getIntent().getIntExtra(ConstantValue.ID, roomId);
        headerView = View.inflate(mContext, R.layout.include_room_water_header_layout, null);
        tvLeftWaterTitle = headerView.findViewById(R.id.tvLeftWaterTitle);
        tvLeftWaterNumber = headerView.findViewById(R.id.tvLeftWaterNumber);
        tvRightWaterTitle = headerView.findViewById(R.id.tvRightWaterTitle);
        tvRightWaterNumber = headerView.findViewById(R.id.tvRightWaterNumber);
        tvThisWeekWater = headerView.findViewById(R.id.tvThisWeekWater);
        tvThisWeekWater.setOnClickListener(this::onClick);
    }

    @Override
    public void initData() {

        getDayRoomWaterList();

        roomWaterListAdapter = new RoomWaterListAdapter(roomWaterList);
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
                getDayRoomWaterList();
            }
        });
    }

    private void getDayRoomWaterList() {
        showLoadDialog();
        HttpRequest.getRoomWaterList(this, isTodayWater, roomId, new HttpCallBack<RoomWaterBean>() {
            @Override
            public void onSuccess(RoomWaterBean roomWaterBean) {
                dismissLoadDialog();
                if (roomWaterBean != null) {
                    roomWaterList = roomWaterBean.getBills();
                    roomWaterListAdapter.setNewData(roomWaterList);
                    if (isTodayWater) {
                        tvLeftWaterNumber.setText(String.valueOf(roomWaterBean.getTotal()));
                    } else {
                        tvLeftWaterNumber.setText(String.valueOf(roomWaterBean.getCurrentWeekTotal()));
                        tvRightWaterNumber.setText(String.valueOf(roomWaterBean.getLastWeekTotal()));
                    }
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);

            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvThisWeekWater:
                isTodayWater = false;
                tvThisWeekWater.setVisibility(View.GONE);
                tvRightWaterTitle.setVisibility(View.VISIBLE);
                tvRightWaterNumber.setVisibility(View.VISIBLE);
                tvLeftWaterTitle.setText("本周流水(钻石)");
                getDayRoomWaterList();
                break;
        }
    }
}
