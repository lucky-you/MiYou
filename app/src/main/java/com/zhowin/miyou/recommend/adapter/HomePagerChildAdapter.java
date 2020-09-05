package com.zhowin.miyou.recommend.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;

import java.util.List;

/**
 * author : zho
 * date  ：2020/9/5
 * desc ： 子分类的adapter
 */
public class HomePagerChildAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public HomePagerChildAdapter(@Nullable List<String> data) {
        super(R.layout.include_home_pager_child_item_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.tvItemName, item);
    }
}
