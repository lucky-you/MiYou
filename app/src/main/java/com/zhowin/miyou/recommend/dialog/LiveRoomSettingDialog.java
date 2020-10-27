package com.zhowin.miyou.recommend.dialog;


import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhowin.base_library.base.BaseBottomSheetFragment;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.recommend.adapter.LiveRoomSetAdapter;
import com.zhowin.miyou.recommend.callback.OnLiveRoomSettingItemListener;
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
    private OnLiveRoomSettingItemListener onLiveRoomSettingItemListener;
    private int userIdentity;//用户身份

    /**
     * @param userIdentity 1:房主 2:主持人 3：接待管理 4：普通管理 5：普通用户
     */
    public static LiveRoomSettingDialog newInstance(int userIdentity) {
        LiveRoomSettingDialog settingDialog = new LiveRoomSettingDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.TYPE, userIdentity);
        settingDialog.setArguments(bundle);
        return settingDialog;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.include_live_room_set_layout;
    }

    @Override
    protected boolean isFixedHeight() {
        return false;
    }


    public void setOnLiveRoomSettingItemListener(OnLiveRoomSettingItemListener onLiveRoomSettingItemListener) {
        this.onLiveRoomSettingItemListener = onLiveRoomSettingItemListener;
    }

    @Override
    public void initView() {
        userIdentity = getArguments().getInt(ConstantValue.TYPE);
        liveRoomSetRecyclerView = get(R.id.liveRoomSetRecyclerView);
        get(R.id.tvCancel).setOnClickListener(this::onViewClick);
        liveRoomSetRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        liveRoomSetAdapter = new LiveRoomSetAdapter(addSetDataList());
        liveRoomSetRecyclerView.setAdapter(liveRoomSetAdapter);
        liveRoomSetAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int itemID = liveRoomSetAdapter.getItem(position).getItemId();
                String itemText = liveRoomSetAdapter.getItem(position).getItemTitle();
                if (onLiveRoomSettingItemListener != null) {
                    onLiveRoomSettingItemListener.onLiveRoomItemClick(itemID, itemText);
                }
            }
        });
    }

    /**
     * 根据身份设置数据
     *
     * @return
     */
    private List<LiveRoomSet> addSetDataList() {
        List<LiveRoomSet> liveRoomSetList = new ArrayList<>();
        if (!liveRoomSetList.isEmpty()) liveRoomSetList.clear();
        switch (userIdentity) {
            case 1:
                liveRoomSetList.add(new LiveRoomSet(1, "房间流水"));
                liveRoomSetList.add(new LiveRoomSet(2, "房间资料"));
                liveRoomSetList.add(new LiveRoomSet(3, "房间加密"));
                liveRoomSetList.add(new LiveRoomSet(4, "关闭公屏"));
                liveRoomSetList.add(new LiveRoomSet(5, "清除公屏"));
                liveRoomSetList.add(new LiveRoomSet(6, "开启魅力值统计"));
                liveRoomSetList.add(new LiveRoomSet(7, "魅力值清零"));
                liveRoomSetList.add(new LiveRoomSet(8, "设置管理员"));
                liveRoomSetList.add(new LiveRoomSet(9, "禁言管理"));
                liveRoomSetList.add(new LiveRoomSet(10, "禁麦管理"));
                liveRoomSetList.add(new LiveRoomSet(11, "踢出房间"));
                liveRoomSetList.add(new LiveRoomSet(12, "收藏房间"));
                liveRoomSetList.add(new LiveRoomSet(13, "分享房间"));
                liveRoomSetList.add(new LiveRoomSet(15, "退出房间"));
                break;
            case 2:
                liveRoomSetList.add(new LiveRoomSet(1, "房间流水"));
                liveRoomSetList.add(new LiveRoomSet(2, "房间资料"));
                liveRoomSetList.add(new LiveRoomSet(3, "房间加密"));
                liveRoomSetList.add(new LiveRoomSet(4, "关闭公屏"));
                liveRoomSetList.add(new LiveRoomSet(5, "清除公屏"));
                liveRoomSetList.add(new LiveRoomSet(6, "开启魅力值统计"));
                liveRoomSetList.add(new LiveRoomSet(7, "魅力值清零"));
                liveRoomSetList.add(new LiveRoomSet(9, "禁言管理"));
                liveRoomSetList.add(new LiveRoomSet(10, "禁麦管理"));
                liveRoomSetList.add(new LiveRoomSet(11, "踢出房间"));
                liveRoomSetList.add(new LiveRoomSet(12, "收藏房间"));
                liveRoomSetList.add(new LiveRoomSet(13, "分享房间"));
                liveRoomSetList.add(new LiveRoomSet(14, "举报房间"));
                liveRoomSetList.add(new LiveRoomSet(15, "退出房间"));
                break;
            case 3:
                liveRoomSetList.add(new LiveRoomSet(2, "房间资料"));
                liveRoomSetList.add(new LiveRoomSet(9, "禁言管理"));
                liveRoomSetList.add(new LiveRoomSet(10, "禁麦管理"));
                liveRoomSetList.add(new LiveRoomSet(11, "踢出房间"));
                liveRoomSetList.add(new LiveRoomSet(12, "收藏房间"));
                liveRoomSetList.add(new LiveRoomSet(13, "分享房间"));
                liveRoomSetList.add(new LiveRoomSet(14, "举报房间"));
                liveRoomSetList.add(new LiveRoomSet(15, "退出房间"));
                break;
            case 4:
            case 5:
                liveRoomSetList.add(new LiveRoomSet(12, "收藏房间"));
                liveRoomSetList.add(new LiveRoomSet(13, "分享房间"));
                liveRoomSetList.add(new LiveRoomSet(14, "举报房间"));
                liveRoomSetList.add(new LiveRoomSet(15, "退出房间"));
                break;
        }
        return liveRoomSetList;
    }

    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tvCancel:
                dismiss();
                break;
        }
    }
}
