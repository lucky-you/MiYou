package com.zhowin.miyou.mine.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;

import java.util.List;

/**
 * author : zho
 * date  ：2020/9/1
 * desc ：
 */
public class AttentionAndFansAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private int adapterType = 1;


    public AttentionAndFansAdapter(@Nullable List<String> data) {
        super(R.layout.include_attention_and_fans_item_view, data);
    }

    public void setAdapterType(int adapterType) {
        this.adapterType = adapterType;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper
                .setGone(R.id.tvCreateTime, 3 == adapterType)
                .setGone(R.id.tvMutualAttention, 3 != adapterType);
    }
}
