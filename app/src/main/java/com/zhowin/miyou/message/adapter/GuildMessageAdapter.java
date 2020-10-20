package com.zhowin.miyou.message.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;
import com.zhowin.miyou.message.model.SystemMessage;

import java.util.List;

/**
 * 公会消息
 */
public class GuildMessageAdapter extends BaseQuickAdapter<SystemMessage, BaseViewHolder> {
    public GuildMessageAdapter(@Nullable List<SystemMessage> data) {
        super(R.layout.include_guild_message_item_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SystemMessage item) {

    }
}
