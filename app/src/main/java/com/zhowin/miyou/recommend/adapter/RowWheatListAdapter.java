package com.zhowin.miyou.recommend.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;

import java.util.List;

/**
 * author : zho
 * date  ：2020/10/26
 * desc ：麦位列表的adapter
 */
public class RowWheatListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RowWheatListAdapter(@Nullable List<String> data) {
        super(R.layout.include_row_wheat_item_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {

    }
}
