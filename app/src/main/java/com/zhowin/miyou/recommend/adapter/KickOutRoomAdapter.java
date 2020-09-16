package com.zhowin.miyou.recommend.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;

import java.util.List;

/**
 * author : zho
 * date  ：2020/9/16
 * desc ： 踢出房间用户的adapter
 */
public class KickOutRoomAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private int classType, indexType;


    public KickOutRoomAdapter(@Nullable List<String> data) {
        super(R.layout.include_kick_out_room_item_view, data);
    }

    public void setClassType(int classType, int indexType) {
        this.classType = classType;
        this.indexType = indexType;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setGone(R.id.llUserInfoLayout, 0 == indexType)
                .setGone(R.id.tvCreateTime, 1 == indexType)
                .setText(R.id.tvCancelKick, 1 == classType ? "踢出" : "禁麦");


    }
}
