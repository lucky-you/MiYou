package com.zhowin.miyou.message.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.base_library.utils.DateHelpUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.message.model.SystemMessage;

import java.util.List;

/**
 * author : zho
 * date  ：2020/10/20
 * desc ： 系统消息
 */
public class SystemMessageAdapter extends BaseQuickAdapter<SystemMessage, BaseViewHolder> {
    public SystemMessageAdapter(@Nullable List<SystemMessage> data) {
        super(R.layout.include_syatem_message_item_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SystemMessage item) {
        helper.setText(R.id.tvCreateTime, DateHelpUtils.getCurrentDayNotYear(item.getSendTime()))
                .setText(R.id.tvMessageContent, item.getContent());

    }
}
