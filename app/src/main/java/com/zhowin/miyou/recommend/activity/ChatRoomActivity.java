package com.zhowin.miyou.recommend.activity;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.base_library.utils.GsonUtils;
import com.zhowin.base_library.utils.KeyboardUtils;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindActivity;
import com.zhowin.miyou.databinding.ActivityChatRoomBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.recommend.adapter.AudienceListAdapter;
import com.zhowin.miyou.recommend.adapter.ChatRoomMessageListAdapter;
import com.zhowin.miyou.recommend.callback.OnChatMessageItemClickListener;
import com.zhowin.miyou.recommend.callback.OnLiveRoomSettingItemListener;
import com.zhowin.miyou.recommend.callback.OnRoomMemberItemClickListener;
import com.zhowin.miyou.recommend.dialog.LiveRoomSettingDialog;
import com.zhowin.miyou.recommend.dialog.SendGiftDialogFragment;
import com.zhowin.miyou.recommend.model.RoomDataInfo;
import com.zhowin.miyou.rongIM.IMManager;
import com.zhowin.miyou.rongIM.callback.SealMicResultCallback;
import com.zhowin.miyou.rongIM.constant.MicState;
import com.zhowin.miyou.rongIM.constant.UserRoleType;
import com.zhowin.miyou.rongIM.lifecycle.RoomObserver;
import com.zhowin.miyou.rongIM.manager.CacheManager;
import com.zhowin.miyou.rongIM.manager.ThreadManager;
import com.zhowin.miyou.rongIM.model.Event;
import com.zhowin.miyou.rongIM.model.MicBean;
import com.zhowin.miyou.rongIM.model.SpeakBean;
import com.zhowin.miyou.rongIM.repo.NetResult;
import com.zhowin.miyou.rongIM.repo.RoomMemberRepo;
import com.zhowin.miyou.rongIM.rtc.RTCClient;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.ChatRoomInfo;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;

/**
 * 聊天室
 */
public class ChatRoomActivity extends BaseBindActivity<ActivityChatRoomBinding> {

    private int roomId, roomOwnerId;//房间id / 拥有者 id
    /**
     * 本地维护一个kv列表
     */
    private Map<Integer, MicBean> localMicBeanMap = new HashMap<>();
    private List<String> userIdList = new ArrayList<>();

    //聊天信息的adapter
    private ChatRoomMessageListAdapter chatRoomMessageListAdapter;
    private List<Message> messageList = new ArrayList<>();

    //排麦的adapter
    private AudienceListAdapter audienceListAdapter;
    private List<RoomMemberRepo.MemberBean> roomMemberList = new ArrayList<>();

    public static void start(Context context, int roomId) {
        Intent intent = new Intent(context, ChatRoomActivity.class);
        intent.putExtra(ConstantValue.ID, roomId);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_chat_room;
    }

    @Override
    public void initView() {
        roomId = getIntent().getIntExtra(ConstantValue.ID, -1);
        Log.e("xy", "roomId:" + roomId);
        CacheManager.getInstance().cacheRoomId(String.valueOf(roomId));
        setOnClick(R.id.ivBackReturn, R.id.llReleaseBroadcastLayout, R.id.ivUserList,
                R.id.ivRoomSetting, R.id.ivGiftPhoto);
        getLifecycle().addObserver(new RoomObserver());
    }

    @Override
    public void initData() {
        getRoomDataMessage();

//        initChatMessage();


        chatRoomMessageListAdapter = new ChatRoomMessageListAdapter(messageList);
        mBinding.chatMessageRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.chatMessageRecyclerView.setAdapter(chatRoomMessageListAdapter);

        audienceListAdapter = new AudienceListAdapter(roomMemberList);
        mBinding.AudienceRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        mBinding.AudienceRecyclerView.setAdapter(audienceListAdapter);
    }

    @Override
    public void initListener() {
        chatRoomMessageListAdapter.setOnChatMessageItemClickListener(new OnChatMessageItemClickListener() {
            @Override
            public void onMessageItemClick(int position, Message message) {
                final String currentUserId = CacheManager.getInstance().getUserId();
                int userRoleType = CacheManager.getInstance().getUserRoleType();
            }
        });
        audienceListAdapter.setOnRoomMemberItemClickListener(new OnRoomMemberItemClickListener() {
            @Override
            public void onMemberItemClick(int position, String userId, String userName) {
                clickMemberMic(position);
            }
        });
    }

    /**
     * 聊天信息
     */
    private void initChatMessage() {
        RTCClient.getInstance().setSpeakerEnable(true);
        //扬声器是否播放，true为是
        boolean isSpeakerphoneOn = RTCClient.getInstance().isSpeakerphoneOn(mContext);
        Log.e("xy", "扬声器是否打开: " + isSpeakerphoneOn);
        RTCClient.getInstance().setLocalMicEnable(true);
        //加入房间后给集合里面添加一条默认消息
        TextMessage currentUserTextMessage = new TextMessage(getResources().getString(R.string.join_room_success));
        currentUserTextMessage.setUserInfo(new io.rong.imlib.model.UserInfo(CacheManager.getInstance().getUserId(),
                CacheManager.getInstance().getUserName(),
                Uri.parse(CacheManager.getInstance().getUserPortrait())));
        RongIMClient.getInstance().sendMessage(Conversation.ConversationType.CHATROOM, String.valueOf(roomId), currentUserTextMessage, null, null, new IRongCallback.ISendMessageCallback() {
            @Override
            public void onAttached(Message message) {

            }

            @Override
            public void onSuccess(Message message) {
                messageList.add(message);
                chatRoomMessageListAdapter.setNewData(messageList);
            }

            @Override
            public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                Log.e("xy", "发送消息失败：" + errorCode);
            }
        });
    }


    /**
     * 获取直播间信息
     */
    private void getRoomDataMessage() {
        showLoadDialog();
        HttpRequest.getRoomDataInfo(this, roomId, new HttpCallBack<RoomDataInfo>() {
            @Override
            public void onSuccess(RoomDataInfo roomDataInfo) {
                dismissLoadDialog();
                if (roomDataInfo != null) {
                    setDataToViews(roomDataInfo);
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);
            }
        });
    }

    private void setDataToViews(RoomDataInfo roomDataInfo) {
        roomOwnerId = roomDataInfo.getOwner().getUserId();
        RoomDataInfo.RoomBean roomInfo = roomDataInfo.getRoom();
        if (roomInfo != null) {
            GlideUtils.loadObjectImage(mContext, roomInfo.getCoverPictureKey(), mBinding.civUserHeadPhoto);
            mBinding.tvFounderNickName.setText("[" + roomInfo.getTypeName() + "] " + roomInfo.getTitle());
            mBinding.tvRoomNumber.setText("房间ID: " + roomInfo.getRoomId());
            mBinding.tvPopularityValue.setText("人气: " + roomInfo.getHeatValue());
        }


        /**
         * 融云默认创建房间者为主持人，
         * 在这里判断房主是否是自己，如果是自己，则以主播的身份进入房间，
         * 如果不是自己则以观众的身份进入房间
         *
         */
        int userId = UserInfo.getUserInfo().getUserId();
        if (userId == roomOwnerId) {
            //是自己创建的
            AnchorJoinChatRoom();
        } else {
            //不是自己创建的
            AudienceJsonChatRoom();
        }

        //加入成功发一条默认消息
        initChatMessage();

        //轮询请求房间在线人数
        IMManager.getInstance().onlineNumber(String.valueOf(roomId), new SealMicResultCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                if (aBoolean) {
                    IMManager.getInstance().getChatRoomInfo(String.valueOf(roomId), new RongIMClient.ResultCallback<ChatRoomInfo>() {
                        @Override
                        public void onSuccess(ChatRoomInfo chatRoomInfo) {
                            int onlineNumber = chatRoomInfo.getTotalMemberCount();
                            String onlineNumberString = mContext.getResources().getString(R.string.online_number);
                        }

                        @Override
                        public void onError(RongIMClient.ErrorCode errorCode) {

                        }
                    });
                }

            }

            @Override
            public void onFail(int errorCode) {

            }
        });
    }

    /**
     * 主播加入房间
     */
    private void AnchorJoinChatRoom() {
        IMManager.getInstance().micJoinRoom(String.valueOf(roomId), new RongIMClient.ResultCallback<String>() {
            @Override
            public void onSuccess(String roomId) {
                Log.e("xy", "以主持人的身份加入房间");
                //创建房间之后，保存用户角色，关键盘，跳走
                CacheManager.getInstance().cacheUserRoleType(UserRoleType.HOST.getValue());
                KeyboardUtils.hideSoftInput(mContext);
                initMic();
                //房间加入成功，初始化音频
                initSpeak();
                //设置扬声器播放的图标
                boolean speakerphoneOn = RTCClient.getInstance().isSpeakerphoneOn(mContext);
                RTCClient.getInstance().setSpeakerEnable(speakerphoneOn);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e("xy", "以主持人的身份加入房间:" + errorCode);
            }
        });
    }

    /**
     * 观众加入房间
     */
    private void AudienceJsonChatRoom() {
        IMManager.getInstance().audienceJoinRoom(String.valueOf(roomId), new SealMicResultCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                Log.e("xy", "以观众的身份加入房间 Success");
                initMic();
                initSpeak();
                //房间加入成功，初始化音频
                boolean speakerphoneOn = RTCClient.getInstance().isSpeakerphoneOn(mContext);
                RTCClient.getInstance().setSpeakerEnable(speakerphoneOn);
            }

            @Override
            public void onFail(int errorCode) {
                Log.e("xy", "以观众的身份加入房间 Error :" + errorCode);
            }
        });
    }


    /**
     * 获取全部麦位的KV
     */
    private void initMic() {

        //获取全部麦位的KV
        IMManager.getInstance().getAllChatRoomMic(String.valueOf(roomId), new RongIMClient.ResultCallback<Map<String, String>>() {
            @Override
            public void onSuccess(Map<String, String> stringStringMap) {
                Log.e("xy", "麦位KV的Map:" + stringStringMap.toString());
                //根据KV判断，如果对应的麦位上有人，则用对应的信息填充麦位
                ThreadManager.getInstance().runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        userIdList = new ArrayList<>();
                        for (String key : stringStringMap.keySet()) {
                            final MicBean micBean = new Gson().fromJson(stringStringMap.get(key), MicBean.class);
                            Log.e("xy", "micBean:" + micBean.toString());
                            //初始化麦位时本地保存一份麦位map
                            localMicBeanMap.put(micBean.getPosition(), micBean);
                            userIdList.add(micBean.getUserId());
                            RoomMemberRepo.MemberBean roomMember = new RoomMemberRepo.MemberBean();
                            roomMember.setUserId(micBean.getUserId());
                            roomMember.setUserName("");
                            roomMember.setPortrait("");
                            roomMember.setPosition(micBean.getPosition());
                            roomMember.setCharmValue(micBean.getCharmValue());
                            roomMemberList.add(roomMember);
                        }
                        audienceListAdapter.setNewData(roomMemberList);
                    }
                });
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e("xy", "获取全部麦位的KV信息失败，错误码为: " + errorCode);
            }
        });
    }


    /**
     * 获取全部说话状态
     */
    public void initSpeak() {
        IMManager.getInstance().getAllChatRoomSpeaking(String.valueOf(roomId), new RongIMClient.ResultCallback<Map<String, String>>() {
            @Override
            public void onSuccess(Map<String, String> stringStringMap) {
                Log.e("xy", "获取说话的map:" + stringStringMap.size());
                for (String key : stringStringMap.keySet()) {
                    final SpeakBean speakBean = GsonUtils.fromJson(stringStringMap.get(key), SpeakBean.class);
                    ThreadManager.getInstance().runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            if (1 == speakBean.getSpeaking()) {
//                                dynamicAvatarViewList.get(speakBean.getPosition()).startSpeak();
                            } else {
//                                dynamicAvatarViewList.get(speakBean.getPosition()).stopSpeak();
                            }
                        }
                    });
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e("xy", "获取正在讲话的KV信息失败，错误码为: " + errorCode);
            }
        });

    }

    /**
     * 点击麦位的操作
     *
     * @param position
     */
    public void clickMemberMic(final int position) {
        //获取点击的麦位信息
        MicBean clickMicBean = localMicBeanMap.get(position);
        if (clickMicBean != null) {
            if (TextUtils.isEmpty(clickMicBean.getUserId())) {
                //空麦位
                if (UserRoleType.HOST.getValue() == CacheManager.getInstance().getUserRoleType()) {
                    //主持人
//                    micAbsentHost(clickMicBean);
                } else if (UserRoleType.CONNECT_MIC.getValue() == CacheManager.getInstance().getUserRoleType()) {
                    //连麦者
//                    micAbsentConnect(clickMicBean);
                } else if (UserRoleType.AUDIENCE.getValue() == CacheManager.getInstance().getUserRoleType()) {
                    //观众
//                    micAbsentAudience(clickMicBean);
                }
            } else {
                //麦位上有人
                if (UserRoleType.HOST.getValue() == CacheManager.getInstance().getUserRoleType()) {
                    //主持人
//                    micPresentHost(clickMicBean);
                } else if (UserRoleType.CONNECT_MIC.getValue() == CacheManager.getInstance().getUserRoleType()) {
                    //连麦者
//                    micPresentConnect(clickMicBean);
                } else if (UserRoleType.AUDIENCE.getValue() == CacheManager.getInstance().getUserRoleType()) {
                    //观众
//                    micPresentAudience(clickMicBean);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBackReturn:
                //退出房间，依然要判断是主播，还是观众
                int userId = UserInfo.getUserInfo().getUserId();
                if (userId == roomOwnerId) {
                    //主播退出房间
                    IMManager.getInstance().micQuitRoom(String.valueOf(roomId), new RongIMClient.ResultCallback<String>() {
                        @Override
                        public void onSuccess(String s) {
                            ActivityManager.getAppInstance().finishActivity();
                        }

                        @Override
                        public void onError(RongIMClient.ErrorCode errorCode) {

                        }
                    });
                } else {
                    //观众退出房间
                    IMManager.getInstance().audienceQuitRoom(String.valueOf(roomId), new RongIMClient.ResultCallback<String>() {
                        @Override
                        public void onSuccess(String s) {
                            ActivityManager.getAppInstance().finishActivity();
                        }

                        @Override
                        public void onError(RongIMClient.ErrorCode errorCode) {

                        }
                    });
                }
                break;
            case R.id.llReleaseBroadcastLayout:
                startActivity(BroadcastDatingActivity.class);
                break;
            case R.id.ivUserList:
                startActivity(RoomListActivity.class);
                break;

            case R.id.ivRoomSetting:
                showRoomSettingDialog();
                break;
            case R.id.ivGiftPhoto:
                showSendGiftDialog();
                break;
        }
    }

    /**
     * 房价设置
     */
    private void showRoomSettingDialog() {
        LiveRoomSettingDialog roomSettingDialog = new LiveRoomSettingDialog();
        roomSettingDialog.show(getSupportFragmentManager(), "setting");
        roomSettingDialog.setOnLiveRoomSettingItemListener(new OnLiveRoomSettingItemListener() {
            @Override
            public void onLiveRoomItemClick(int itemID, String itemText) {

            }
        });
    }


    /**
     * 送礼物
     */
    private void showSendGiftDialog() {
        SendGiftDialogFragment sendGiftDialogFragment = new SendGiftDialogFragment();
        sendGiftDialogFragment.show(getSupportFragmentManager(), "gift");
    }


    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .statusBarColor(R.color.color_8E9DFD)
                .keyboardEnable(true)
                .statusBarDarkFont(true, 0f)
                .init();
    }
}
