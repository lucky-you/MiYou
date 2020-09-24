package com.zhowin.miyou.recommend.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;
import com.zhowin.miyou.recommend.model.GiftList;

/**
 * 礼物列表的adapter
 */
public class GiftListAdapter extends BaseQuickAdapter<GiftList, BaseViewHolder> {

    public GiftListAdapter() {
        super(R.layout.include_gift_list_item_view);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GiftList item) {

    }
}
