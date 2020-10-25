package com.zhowin.miyou.recommend.dialog;


import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhowin.base_library.base.BaseBottomSheetFragment;
import com.zhowin.miyou.R;
import com.zhowin.miyou.recommend.adapter.LiveRoomSetAdapter;
import com.zhowin.miyou.recommend.model.LiveRoomSet;

import java.util.ArrayList;
import java.util.List;

/**
 * 直播间设置的dialog
 */
public class LiveRoomSettingDialog extends BaseBottomSheetFragment {


    private RecyclerView liveRoomSetRecyclerView;

    private LiveRoomSetAdapter liveRoomSetAdapter;
    private List<LiveRoomSet> liveRoomSetList = new ArrayList<>();


    @Override
    public int getLayoutResId() {
        return R.layout.include_live_room_set_layout;
    }

    @Override
    protected boolean isFixedHeight() {
        return true;
    }

    @Override
    public void initView() {
        liveRoomSetRecyclerView = get(R.id.liveRoomSetRecyclerView);
        get(R.id.tvCancel).setOnClickListener(this::onViewClick);
        addSetDataList();
        liveRoomSetRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        liveRoomSetAdapter = new LiveRoomSetAdapter(liveRoomSetList);
        liveRoomSetRecyclerView.setAdapter(liveRoomSetAdapter);
        liveRoomSetAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }


    private void addSetDataList() {
        liveRoomSetList.add(new LiveRoomSet(1, "房间流水"));
        liveRoomSetList.add(new LiveRoomSet(2, "房间资料"));
        liveRoomSetList.add(new LiveRoomSet(3, "房间加密"));
        liveRoomSetList.add(new LiveRoomSet(4, "关闭公屏"));
        liveRoomSetList.add(new LiveRoomSet(5, "清除公屏"));
        liveRoomSetList.add(new LiveRoomSet(6, "魅力值统计"));
        liveRoomSetList.add(new LiveRoomSet(7, "魅力值清零"));
        liveRoomSetList.add(new LiveRoomSet(8, "设置管理员"));
        liveRoomSetList.add(new LiveRoomSet(9, "禁言管理"));
        liveRoomSetList.add(new LiveRoomSet(10, "禁麦管理"));
        liveRoomSetList.add(new LiveRoomSet(11, "踢出房间"));
        liveRoomSetList.add(new LiveRoomSet(12, "关注房间"));
        liveRoomSetList.add(new LiveRoomSet(13, "分享房间"));
        liveRoomSetList.add(new LiveRoomSet(14, "退出房间"));
    }

    @Override
    public void onViewClick(View view) {

    }

}
