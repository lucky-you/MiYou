package com.zhowin.miyou.recommend.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;

import java.util.List;

/**
 * 礼物列表的adapter
 */
public class GiftListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public GiftListAdapter(@Nullable List<String> data) {
        super(R.layout.include_gift_list_item_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {

    }
}
