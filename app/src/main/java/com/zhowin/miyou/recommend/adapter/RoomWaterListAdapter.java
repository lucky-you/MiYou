package com.zhowin.miyou.recommend.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;

import java.util.List;

/**
 * author : zho
 * date  ：2020/9/17
 * desc ： 房间流水
 */
public class RoomWaterListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public RoomWaterListAdapter(@Nullable List<String> data) {
        super(R.layout.include_room_water_item_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {

    }
}
