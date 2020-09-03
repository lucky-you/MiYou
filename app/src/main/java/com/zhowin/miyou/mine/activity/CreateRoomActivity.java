package com.zhowin.miyou.mine.activity;


import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityCreateRoomBinding;
import com.zhowin.miyou.mine.adapter.RoomBackgroundAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建房间
 */
public class CreateRoomActivity extends BaseBindActivity<ActivityCreateRoomBinding> {


    private RoomBackgroundAdapter roomBackgroundAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_room;
    }

    @Override
    public void initView() {
        setOnClick(R.id.tvCreateRoom);

    }

    @Override
    public void initData() {
        List<String> bgList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            bgList.add("http://gank.io/images/dc75cbde1d98448183e2f9514b4d1320");
        }
        roomBackgroundAdapter = new RoomBackgroundAdapter(mContext, bgList);
        mBinding.roomBgRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mBinding.roomBgRecyclerView.setAdapter(roomBackgroundAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvCreateRoom:
                ToastUtils.showCustomToast(mContext, "创建成功");
                break;
        }
    }
}
