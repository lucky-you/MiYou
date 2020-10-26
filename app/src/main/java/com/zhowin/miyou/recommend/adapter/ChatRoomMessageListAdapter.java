package com.zhowin.miyou.recommend.adapter;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;
import com.zhowin.miyou.recommend.callback.OnChatMessageItemClickListener;
import com.zhowin.miyou.rongIM.message.SendGiftMessage;

import java.util.ArrayList;
import java.util.List;

import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.message.TextMessage;

/**
 * author : zho
 * date  ：2020/10/24
 * desc ：聊天室消息列表adapter
 */
public class ChatRoomMessageListAdapter extends BaseQuickAdapter<Message, BaseViewHolder> {


    public ChatRoomMessageListAdapter(@Nullable List<Message> data) {
        super(R.layout.include_chat_room_message_item_view, data);
    }

    private OnChatMessageItemClickListener onChatMessageItemClickListener;

    public void setOnChatMessageItemClickListener(OnChatMessageItemClickListener onChatMessageItemClickListener) {
        this.onChatMessageItemClickListener = onChatMessageItemClickListener;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Message item) {
        String name = "";
        String contentTv = "";
        //是否显示背景
        boolean isShowBackground = false;
        MessageContent content = item.getContent();
        String objectName = item.getObjectName();
        if (content instanceof SendGiftMessage) {
            SendGiftMessage sendGiftMessage = (SendGiftMessage) content;
            name = sendGiftMessage.getUserInfo().getName();
            contentTv = sendGiftMessage.getContent();
            isShowBackground = false;
        }
        if (content instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) content;
            name = textMessage.getUserInfo().getName();
            contentTv = textMessage.getContent();
            if (contentTv.equals(mContext.getResources().getString(R.string.join_room_success))) {
                isShowBackground = true;
            } else {
                isShowBackground = true;
            }
        }
        if (TextUtils.isEmpty(name)) {
            helper.setGone(R.id.tvLeftTitle, false)
                    .setGone(R.id.tvMiddleTitle, false);
        } else {
            //是否展示背景
            if (isShowBackground) {
                helper.setBackgroundRes(R.id.clContactItemLayout, R.drawable.shape_chat_room_message_item_bg);
            } else {
                helper.setBackgroundRes(R.id.clContactItemLayout, R.drawable.shape_chat_room_message_item_gone_bg);
            }
            helper.setText(R.id.tvLeftTitle, name + ": ")
                    .setText(R.id.tvMiddleTitle, contentTv);
            if (objectName != null) {
                if (objectName.equals(mContext.getResources().getString(R.string.object_name))) {
                    helper.setText(R.id.tvLeftTitle, name)
                            .setTextColor(R.id.tvLeftTitle, Color.parseColor("#F8E71C"))
                            .setTextColor(R.id.tvMiddleTitle, Color.parseColor("#F8E71C"));
                } else {
                    helper.setTextColor(R.id.tvLeftTitle, Color.parseColor("#CFCFCF"))
                            .setTextColor(R.id.tvMiddleTitle, Color.parseColor("#FFFFFF"));
                }
            } else {
                helper.setTextColor(R.id.tvLeftTitle, Color.parseColor("#CFCFCF"))
                        .setTextColor(R.id.tvMiddleTitle, Color.parseColor("#FFFFFF"));
            }
        }
        helper.getView(R.id.clContactItemLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onChatMessageItemClickListener != null) {
                    onChatMessageItemClickListener.onMessageItemClick(helper.getAdapterPosition(), item);
                }
            }
        });
    }

    public void removeMessage(int messageId) {
        for (int i = 0; i < mData.size(); i++) {
            Message msg = mData.get(i);
            if (msg.getMessageId() == messageId) {
                mData.remove(i);
                notifyDataSetChanged();
                break;
            }
        }
    }
}
