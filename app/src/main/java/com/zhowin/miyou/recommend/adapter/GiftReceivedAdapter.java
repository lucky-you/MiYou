package com.zhowin.miyou.recommend.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;
import com.zhowin.miyou.recommend.model.GiftList;

import java.util.List;

/**
 * author : zho
 * date  ：2020/9/28
 * desc ：收到的礼物
 */
public class GiftReceivedAdapter extends BaseQuickAdapter<GiftList, BaseViewHolder> {
    public GiftReceivedAdapter(@Nullable List<GiftList> data) {
        super(R.layout.include_gift_received_item_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GiftList item) {

    }
}
