package com.zhowin.miyou.recommend.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;

import java.util.List;

/**
 * author : zho
 * date  ：2020/9/21
 * desc ： 麦位
 */
public class ServeWheatListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ServeWheatListAdapter(@Nullable List<String> data) {
        super(R.layout.include_wheat_item_view,  data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {

    }
}
