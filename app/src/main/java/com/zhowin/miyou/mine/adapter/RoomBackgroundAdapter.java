package com.zhowin.miyou.mine.adapter;


import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.base_library.utils.SizeUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.mine.model.RoomBackgroundList;

import java.util.List;

/**
 * author : zho
 * date  ：2020/9/3
 * desc ： 房间背景
 */
public class RoomBackgroundAdapter extends BaseQuickAdapter<RoomBackgroundList, BaseViewHolder> {

    private int currentPosition;

    public RoomBackgroundAdapter(@Nullable List<RoomBackgroundList> data) {
        super(R.layout.include_room_background_item_view, data);
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RoomBackgroundList item) {
        RoundedImageView roundedImageView = helper.getView(R.id.ivBackground);
        GlideUtils.loadObjectImage(mContext, item.getBackgroundImage(), roundedImageView);
        if (currentPosition == helper.getAdapterPosition()) {
            helper.setGone(R.id.viewBackground, true);
        } else {
            helper.setGone(R.id.viewBackground, false);
        }
    }

    private int getItemColor(int color) {
        return mContext.getResources().getColor(color);
    }
}
