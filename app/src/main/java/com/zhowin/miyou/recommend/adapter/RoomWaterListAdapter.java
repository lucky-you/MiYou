package com.zhowin.miyou.recommend.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;
import com.zhowin.miyou.recommend.model.RoomWaterList;

import java.util.List;

/**
 * author : zho
 * date  ：2020/9/17
 * desc ： 房间流水
 */
public class RoomWaterListAdapter extends BaseQuickAdapter<RoomWaterList, BaseViewHolder> {


    public RoomWaterListAdapter(@Nullable List<RoomWaterList> data) {
        super(R.layout.include_room_water_item_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RoomWaterList item) {
        helper.setText(R.id.tvLeftTime, item.getDate())
                .setText(R.id.tvRightValue, String.valueOf(item.getAmount()));

    }
}
