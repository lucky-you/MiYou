package com.zhowin.miyou.mine.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;

import java.util.List;

/**
 * 成员流水  和 公会成员 公用的adapter
 */
public class GuildFlowAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private int adapterType;

    public GuildFlowAdapter(@Nullable List<String> data) {
        super(R.layout.include_guild_flow_item_view, data);
    }

    public void setAdapterType(int adapterType) {
        this.adapterType = adapterType;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {


        helper.setGone(R.id.tvGuilderValue, 1 == adapterType)
                .setGone(R.id.tvGuilderLeader, 2 == adapterType);
    }
}
