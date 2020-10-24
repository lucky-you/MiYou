package com.zhowin.miyou.recommend.callback;

import io.rong.imlib.model.Message;

/**
 * author : zho
 * date  ：2020/10/24
 * desc ： 聊天消息的点击事件
 */
public interface OnChatMessageItemClickListener {


    void onMessageItemClick(int position, Message message);
}
