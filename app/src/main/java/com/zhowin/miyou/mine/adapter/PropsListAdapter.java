package com.zhowin.miyou.mine.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;

import java.util.List;

/**
 * 道具
 */
public class PropsListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public PropsListAdapter(@Nullable List<String> data) {
        super( R.layout.include_pro_list_item_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {

    }
}
