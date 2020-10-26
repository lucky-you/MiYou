package com.zhowin.miyou.rongIM.lifecycle;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.google.gson.Gson;
import com.zhowin.miyou.rongIM.IMManager;
import com.zhowin.miyou.rongIM.constant.UserRoleType;
import com.zhowin.miyou.rongIM.manager.CacheManager;
import com.zhowin.miyou.rongIM.message.HandOverHostMessage;
import com.zhowin.miyou.rongIM.message.KickMemberMessage;
import com.zhowin.miyou.rongIM.message.SendBroadcastGiftMessage;
import com.zhowin.miyou.rongIM.message.SendGiftMessage;
import com.zhowin.miyou.rongIM.message.TakeOverHostMessage;
import com.zhowin.miyou.rongIM.model.Event;
import com.zhowin.miyou.rongIM.model.MicBean;
import com.zhowin.miyou.rongIM.rtc.RTCClient;

import org.greenrobot.eventbus.EventBus;


import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.message.ChatRoomKVNotiMessage;
import io.rong.message.RecallNotificationMessage;
import io.rong.message.TextMessage;

/**
 * 聊天室观察者
 */
public class RoomObserver implements LifecycleObserver {

    private RongIMClient.OnReceiveMessageListener onReceiveMessageListener;
    private RongIMClient.OnRecallMessageListener onRecallMessageListener;

    private void obtainIMReceiveMessageListener() {
        onReceiveMessageListener = new RongIMClient.OnReceiveMessageListener() {
            @Override
            public boolean onReceived(Message message, int i) {
                MessageContent messageContent = message.getContent();
                //加入消息(包括欢迎某个人来到房间的消息)
                if (messageContent instanceof TextMessage) {
                    EventBus.getDefault().post(new Event.EventImList(message));
                }
                //麦位状态更新(锁麦和解除锁麦)的通知消息
                if (messageContent instanceof ChatRoomKVNotiMessage) {
                    ChatRoomKVNotiMessage chatRoomKvNotiMessage = (ChatRoomKVNotiMessage) messageContent;
                    Log.e("Demo server下发的KV信息: ", chatRoomKvNotiMessage.getValue());
                    if (chatRoomKvNotiMessage.getKey().contains(IMManager.KV_POSITION)) {
                        //更新麦位锁定状态
                        MicBean micBean = new Gson().fromJson(chatRoomKvNotiMessage.getValue(), MicBean.class);
                        EventBus.getDefault().post(new Event.EventLockMic(micBean.getState()));
                    }
                    if (chatRoomKvNotiMessage.getKey().contains(IMManager.KV_SPEAK)) {
                        //说话
                        EventBus.getDefault().post(new Event.EventKvMessage(chatRoomKvNotiMessage));
                    }
                    //更新麦位状态
                    EventBus.getDefault().post(new Event.EventMicKVMessage(chatRoomKvNotiMessage));
                }
                //送出礼物时的自定义消息
                if (messageContent instanceof SendGiftMessage) {
                    SendGiftMessage giftMessage = (SendGiftMessage) messageContent;
                    EventBus.getDefault().post(new Event.EventGiftMessage(giftMessage, message));
                }
                //房间内成员变更的自定义消息
                if (messageContent instanceof KickMemberMessage) {
                    KickMemberMessage roomMemberChangeMessage = (KickMemberMessage) messageContent;
                    EventBus.getDefault().post(new Event.EventMemberChangeMessage(roomMemberChangeMessage));
                }
                //转让主持人的自定义消息
                if (messageContent instanceof HandOverHostMessage) {
                    HandOverHostMessage handOverHostMessage = (HandOverHostMessage) messageContent;
                    EventBus.getDefault().post(new Event.EventHandOverHostMessage(handOverHostMessage));
                }
                //接管主持人的自定义消息
                if (messageContent instanceof TakeOverHostMessage) {
                    TakeOverHostMessage takeOverHostMessage = (TakeOverHostMessage) messageContent;
                    EventBus.getDefault().post(new Event.EventTakeOverHostMessage(takeOverHostMessage));
                }
                //全局礼物消息
                if (messageContent instanceof SendBroadcastGiftMessage) {
                    SendBroadcastGiftMessage sendBroadcastGiftMessage = (SendBroadcastGiftMessage) messageContent;
                    EventBus.getDefault().post(new Event.EventBroadcastGiftMessage(sendBroadcastGiftMessage));
                }
                return false;
            }
        };
    }

    private void obtainIMRecallMessageListener() {
        onRecallMessageListener = new RongIMClient.OnRecallMessageListener() {
            @Override
            public boolean onMessageRecalled(Message message, RecallNotificationMessage recallNotificationMessage) {
                MessageContent messageContent = message.getContent();
                //消息撤回，用撤回逻辑实现删除
                if (messageContent instanceof RecallNotificationMessage) {
                    EventBus.getDefault().post(new Event.EventBroadcastRecallMessage(message));
                }
                return false;
            }
        };
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void addMessageReceiveListener() {
        Log.d(RoomObserver.class.getSimpleName(), "ON_CREATE");
        obtainIMReceiveMessageListener();
        IMManager.getInstance().addMessageReceiveListener(onReceiveMessageListener);
        obtainIMRecallMessageListener();
        IMManager.getInstance().addMessageRecallListener(onRecallMessageListener);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void removeMessageReceiveListener() {
        Log.d(RoomObserver.class.getSimpleName(), "ON_DESTROY");
        //移除消息接收监听器
        IMManager.getInstance().removeMessageReceiveListener(onReceiveMessageListener);
        //移除消息撤回监听器
        IMManager.getInstance().removeMessageRecallListener(onRecallMessageListener);
        //退出聊天室
        //退房时如果是观众，退订RTC + 退出IM
        if (CacheManager.getInstance().getUserRoleType() == UserRoleType.AUDIENCE.getValue()) {
            IMManager.getInstance().audienceQuitRoom(CacheManager.getInstance().getRoomId(), new RongIMClient.ResultCallback<String>() {
                @Override
                public void onSuccess(String s) {
                    Log.e("xy", "观众退房");
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.e("xy", "观众退房失败: " + errorCode.getValue());
                }
            });
        }
        //退房时如果是主播，退出RTC + 退出IM + 调用demo server主播下麦接口
        if (CacheManager.getInstance().getUserRoleType() == UserRoleType.HOST.getValue()
                || CacheManager.getInstance().getUserRoleType() == UserRoleType.CONNECT_MIC.getValue()) {
            IMManager.getInstance().micQuitRoom(CacheManager.getInstance().getRoomId(), new RongIMClient.ResultCallback<String>() {
                @Override
                public void onSuccess(String s) {
                    Log.e("xy", "连麦者退房");
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.e("xy", "连麦者退房失败: " + errorCode.getValue());
                }
            });
        }
        RTCClient.getInstance().stopMix();
        CacheManager.getInstance().cacheBgmBean("", "");
        //离开房间时将当前的麦位信息清空
        CacheManager.getInstance().cacheMicBean(null);
    }
}
