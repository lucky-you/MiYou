package com.zhowin.miyou.recommend.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;
import com.zhowin.miyou.recommend.model.LiveRoomSet;

import java.util.List;

/**
 * 房间设置的dialog
 */
public class LiveRoomSetAdapter extends BaseQuickAdapter<LiveRoomSet, BaseViewHolder> {
    public LiveRoomSetAdapter(@Nullable List<LiveRoomSet> data) {
        super(R.layout.include_live_room_set_item_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, LiveRoomSet item) {

        helper.setText(R.id.tvSetTitle, item.getItemTitle())
                .setGone(R.id.divideLine, helper.getAdapterPosition() != mData.size());

    }
}
