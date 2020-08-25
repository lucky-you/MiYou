package com.zhowin.miyou.mine.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;
import com.zhowin.miyou.mine.model.MasonryDetailsList;

import java.util.List;

public class MasonryDetailsListAdapter extends BaseQuickAdapter<MasonryDetailsList, BaseViewHolder> {


    public MasonryDetailsListAdapter(@Nullable List<MasonryDetailsList> data) {
        super(R.layout.include_masonry_details_item_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MasonryDetailsList item) {

    }
}
