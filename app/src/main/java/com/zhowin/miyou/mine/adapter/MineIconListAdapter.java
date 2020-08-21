package com.zhowin.miyou.mine.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;
import com.zhowin.miyou.mine.model.MineIconList;

import java.util.List;

/**
 * 我的列表
 */
public class MineIconListAdapter extends BaseQuickAdapter<MineIconList, BaseViewHolder> {
    public MineIconListAdapter(@Nullable List<MineIconList> data) {
        super(R.layout.include_mine_icon_item_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MineIconList item) {
        helper.setImageResource(R.id.ivLeftDrawable,item.getLeftDrawable())
                .setText(R.id.tvLeftTitle,item.getLeftTitle());

    }
}
