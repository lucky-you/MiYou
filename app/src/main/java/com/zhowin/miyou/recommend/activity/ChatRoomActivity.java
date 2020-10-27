package com.zhowin.miyou.recommend.activity;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

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
import com.zhowin.miyou.http.BaseResponse;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.mine.activity.CreateRoomActivity;
import com.zhowin.miyou.recommend.adapter.AudienceListAdapter;
import com.zhowin.miyou.recommend.adapter.ChatRoomMessageListAdapter;
import com.zhowin.miyou.recommend.callback.OnChatMessageItemClickListener;
import com.zhowin.miyou.recommend.callback.OnLiveRoomSettingItemListener;
import com.zhowin.miyou.recommend.callback.OnReportAndAttentionListener;
import com.zhowin.miyou.recommend.callback.OnRoomMemberItemClickListener;
import com.zhowin.miyou.recommend.callback.OnRowWheatClickListener;
import com.zhowin.miyou.recommend.callback.OnSendGiftListener;
import com.zhowin.miyou.recommend.callback.OnSetRoomPasswordListener;
import com.zhowin.miyou.recommend.dialog.LiveRoomSettingDialog;
import com.zhowin.miyou.recommend.dialog.RoomUserCommentDialog;
import com.zhowin.miyou.recommend.dialog.RowWheatListDialog;
import com.zhowin.miyou.recommend.dialog.SendGiftDialogFragment;
import com.zhowin.miyou.recommend.dialog.SetRoomPasswordDialog;
import com.zhowin.miyou.recommend.dialog.ShareItemDialog;
import com.zhowin.miyou.recommend.model.ReportUserOrRoom;
import com.zhowin.miyou.recommend.model.RoomDataInfo;
import com.zhowin.miyou.rongIM.IMManager;
import com.zhowin.miyou.rongIM.callback.OnDialogButtonListClickListener;
import com.zhowin.miyou.rongIM.callback.SealMicResultCallback;
import com.zhowin.miyou.rongIM.constant.MicState;
import com.zhowin.miyou.rongIM.constant.RoomMemberStatus;
import com.zhowin.miyou.rongIM.constant.UserRoleType;
import com.zhowin.miyou.rongIM.dialog.MicAudienceFactory;
import com.zhowin.miyou.rongIM.dialog.MicConnectDialogFactory;
import com.zhowin.miyou.rongIM.dialog.MicConnectTakeOverDialogFactory;
import com.zhowin.miyou.rongIM.dialog.MicDialogFactory;
import com.zhowin.miyou.rongIM.dialog.MicEnqueueDialogFactory;
import com.zhowin.miyou.rongIM.dialog.MicSettingDialogFactory;
import com.zhowin.miyou.rongIM.lifecycle.RoomObserver;
import com.zhowin.miyou.rongIM.manager.CacheManager;
import com.zhowin.miyou.rongIM.manager.ThreadManager;
import com.zhowin.miyou.rongIM.model.MicBean;
import com.zhowin.miyou.rongIM.model.SpeakBean;
import com.zhowin.miyou.rongIM.repo.NetResult;
import com.zhowin.miyou.rongIM.repo.RoomMemberRepo;
import com.zhowin.miyou.rongIM.rtc.RTCClient;
import com.zhowin.miyou.rongIM.util.ButtonDelayUtil;

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
    private String roomTitle, roomType; //房间title
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
                R.id.ivRoomSetting, R.id.ivGiftPhoto, R.id.ivComment);
        getLifecycle().addObserver(new RoomObserver());
    }

    @Override
    public void initData() {
        getRoomDataMessage();

        chatRoomMessageListAdapter = new ChatRoomMessageListAdapter(messageList);
        mBinding.chatMessageRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.chatMessageRecyclerView.setAdapter(chatRoomMessageListAdapter);

        for (int i = 0; i < 8; i++) {
            roomMemberList.add(new RoomMemberRepo.MemberBean(i));
        }
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
            roomType = roomInfo.getTypeName();
            roomTitle = roomInfo.getTitle();
            GlideUtils.loadObjectImage(mContext, roomInfo.getCoverPictureKey(), mBinding.civUserHeadPhoto);
            mBinding.tvFounderNickName.setText("[" + roomType + "] " + roomTitle);
            mBinding.tvRoomNumber.setText("房间ID: " + roomInfo.getRoomId());
            mBinding.tvPopularityValue.setText("人气: " + roomInfo.getHeatValue());
            mBinding.tvAttention.setText(roomDataInfo.isCollection() ? "已关注" : "关注");
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
        IMManager.getInstance().onlineNumber(new SealMicResultCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                if (aBoolean) {
                    IMManager.getInstance().getChatRoomInfo(String.valueOf(roomId), new RongIMClient.ResultCallback<ChatRoomInfo>() {
                        @Override
                        public void onSuccess(ChatRoomInfo chatRoomInfo) {
//                            int onlineNumber = chatRoomInfo.getTotalMemberCount();
//                            String onlineNumberString = mContext.getResources().getString(R.string.online_number);
                        }

                        @Override
                        public void onError(RongIMClient.ErrorCode errorCode) {
                            Log.e("xy", "轮询人数失败：" + errorCode);

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
                userIdList = new ArrayList<>();
                IMManager.getInstance().transMicBean(stringStringMap, new SealMicResultCallback<MicBean>() {
                    @Override
                    public void onSuccess(MicBean micBean) {
                        localMicBeanMap.put(micBean.getPosition(), micBean);
                        userIdList.add(micBean.getUserId());
                    }

                    @Override
                    public void onFail(int errorCode) {
                        Log.e("xy", "获取初始化麦位信息失败: " + errorCode);
                    }
                });
                queryUserMessageList(GsonUtils.toJson(userIdList));
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e("xy", "获取全部麦位的KV信息失败，错误码为: " + errorCode);
            }
        });
    }

    /**
     * 根据id批量查询用户信息
     *
     * @param userIDs
     */
    private void queryUserMessageList(String userIDs) {
        HttpRequest.queryUserMessageList(this, userIDs, new HttpCallBack<BaseResponse<UserInfo>>() {
            @Override
            public void onSuccess(BaseResponse<UserInfo> userInfoBaseResponse) {
                Log.e("xy", "查询成功：" + userInfoBaseResponse.toString());
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                Log.e("xy", "查询失败~");
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


    private void roomMemberRow() {

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
                    micAbsentHost(clickMicBean);
                } else if (UserRoleType.CONNECT_MIC.getValue() == CacheManager.getInstance().getUserRoleType()) {
                    //连麦者
                    micAbsentConnect(clickMicBean);
                } else if (UserRoleType.AUDIENCE.getValue() == CacheManager.getInstance().getUserRoleType()) {
                    //观众
                    micAbsentAudience(clickMicBean);
                }
            } else {
                //麦位上有人
                if (UserRoleType.HOST.getValue() == CacheManager.getInstance().getUserRoleType()) {
                    //主持人
                    micPresentHost(clickMicBean);
                } else if (UserRoleType.CONNECT_MIC.getValue() == CacheManager.getInstance().getUserRoleType()) {
                    //连麦者
                    micPresentConnect(clickMicBean);
                } else if (UserRoleType.AUDIENCE.getValue() == CacheManager.getInstance().getUserRoleType()) {
                    //观众
                    micPresentAudience(clickMicBean);
                }
            }
        }
    }

    /**
     * 用户角色为主持人时，空麦位时对应的操作
     */
    public void micAbsentHost(final MicBean micBean) {
        //麦位上没人
        ThreadManager.getInstance().runOnUIThread(new Runnable() {
            @Override
            public void run() {
                MicSettingDialogFactory micSettingDialogFactory = new MicSettingDialogFactory();
                final Dialog dialog = micSettingDialogFactory.buildDialog(mContext, micBean);
                micSettingDialogFactory.setOnDialogButtonListClickListener(new OnDialogButtonListClickListener() {
                    @Override
                    public void onClick(String content) {
                        if (getResources().getString(R.string.invite_mic).equals(content)) {
                            //判断状态是否为锁定状态
                            if (micBean.getState() == MicState.LOCK.getState()) {
                                ToastUtils.showToast("麦位已锁定");
                                dialog.cancel();
                                return;
                            }
                            //点击邀请连麦
                            dialog.cancel();
                        }
                        if (getResources().getString(R.string.lock_mic).equals(content)) {
                            //点击麦位状态操作
                            if (micBean.getState() == MicState.NORMAL.getState()) {
                                //如果麦位为正常，则锁定
                            }
                        }
                        if (getResources().getString(R.string.unlock_all_mic).equals(content)) {
                            if (micBean.getState() == MicState.LOCK.getState()) {
                                //如果麦位为锁定，则解锁

                            }
                        }
                    }
                });
                dialog.show();
                micSettingDialogFactory.setWheetContent("麦位管理-" + micBean.getPosition() + "号麦");
            }
        });
    }

    /**
     * 用户角色为主持人时，麦位上有人对应的操作
     */
    public void micPresentHost(final MicBean micBean) {

        //麦位上有人
        //主持人自己点自己
        if (micBean.getPosition() == 0 && CacheManager.getInstance().getUserId().equals(micBean.getUserId())) {
            ThreadManager.getInstance().runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    final MicConnectDialogFactory micConnectFactory = new MicConnectDialogFactory();
                    final Dialog dialog = micConnectFactory.buildDialog(mContext);
                    micConnectFactory.setCurrentUser(true);
                    micConnectFactory.setOnDialogButtonListClickListener(new OnDialogButtonListClickListener() {
                        @Override
                        public void onClick(String content) {
                            //连麦者下麦

                        }
                    });
                    List<String> ids = new ArrayList<>();
                    ids.add(micBean.getUserId());

                    micConnectFactory.setMicPosition("主持人");
                    dialog.show();
                }
            });
        }

        //主持人点击别的主播
        if (micBean.getPosition() != 0 && !"".equals(micBean.getUserId())) {
//            micUserName = "";
            ThreadManager.getInstance().runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    final MicDialogFactory micDialogFactory = new MicDialogFactory();
                    final Dialog micDialog = micDialogFactory.buildDialog(mContext, micBean);
                    List<String> ids = new ArrayList<>();
                    ids.add(micBean.getUserId());
                    // 获取点击目标的name,

                    micDialogFactory.setMicPosition(String.valueOf(micBean.getPosition()) + "号麦");
                    micDialogFactory.setOnDialogButtonListClickListener(new OnDialogButtonListClickListener() {
                        @Override
                        public void onClick(String content) {
                            if (getResources().getString(R.string.close_mic).equals(content)) {
                                //点击闭麦

                            }
                            if (getResources().getString(R.string.open_mic).equals(content)) {
                                //点击开麦

                            }
                            if (getResources().getString(R.string.down_mic).equals(content)) {
                                //点击下麦

                            }
                            if (getResources().getString(R.string.hand_over_host).equals(content)) {
                                //点击转让主持人

                            }
                            if (getResources().getString(R.string.send_message).equals(content)) {
                                //点击发送消息，此处弹出键盘发消息
                            }
                            if (getResources().getString(R.string.send_gift_item).equals(content)) {
                                //点击送礼，此处弹出送礼的弹窗

                            }
                            if (getResources().getString(R.string.go_out_room).equals(content)) {
                                //点击移除房间

                            }
                        }
                    });
                    micDialog.show();
                }
            });
        }

    }

    /**
     * 用户角色为观众时，空麦位时对应的操作
     */
    public void micAbsentAudience(final MicBean micBean) {
        if (micBean.getPosition() == 0) {
            //作为观众，面对主持人空麦位的情况下，弹出接管主持人的弹窗
            ThreadManager.getInstance().runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    final MicConnectTakeOverDialogFactory micConnectTakeOverDialogFactory = new MicConnectTakeOverDialogFactory();
                    micConnectTakeOverDialogFactory.setShowMessageButton(false);
                    final Dialog dialog = micConnectTakeOverDialogFactory.buildDialog(mContext);
                    micConnectTakeOverDialogFactory.setCurrentType(false);
                    micConnectTakeOverDialogFactory.setUserName(getResources().getString(R.string.host_location));
                    micConnectTakeOverDialogFactory.setOnDialogButtonListClickListener(new OnDialogButtonListClickListener() {
                        @Override
                        public void onClick(String content) {
                            if (getResources().getString(R.string.send_message).equals(content)) {
                                //点击发消息
                                //此处弹窗不显示发送消息按钮，自然不用处理
                            }
                            if (getResources().getString(R.string.take_over_host).equals(content)) {
                                //接管主持

                            }
                        }
                    });
                    dialog.show();
                }
            });
        } else {
            //作为观众，面对其他主播空麦位的情况下，弹出排麦的弹窗
            ThreadManager.getInstance().runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    MicEnqueueDialogFactory micEnqueueDialogFactory = new MicEnqueueDialogFactory();
                    final Dialog dialog = micEnqueueDialogFactory.buildDialog(mContext, micBean);
                    micEnqueueDialogFactory.setCallClick(new MicEnqueueDialogFactory.CallClick() {
                        @Override
                        public void onClick(String content) {
                            if (ButtonDelayUtil.isNormalClick()) {
//                                SLog.i("asdff", content);
                                if (getResources().getString(R.string.enqueue_mic).equals(content)) {
                                    //麦位状态如果为正常，请求排麦
                                    if (micBean.getState() == MicState.NORMAL.getState() || micBean.getState() == MicState.CLOSE.getState()) {
                                        //判断当前是否在排麦列表
                                        for (int i = 0; i < localMicBeanMap.size(); i++) {
                                            if (localMicBeanMap.get(i).getUserId().equals(CacheManager.getInstance().getUserId())) {
//                                                ToastUtil.showToast(getResources().getString(R.string.already_at_miclilst));
                                                dialog.cancel();
                                                return;
                                            }
                                        }
                                        //调用请求排麦接口
                                    } else {
                                        ToastUtils.showToast("麦位已锁定");
                                        dialog.cancel();
                                    }
                                }
                            }
                            dialog.cancel();
                        }
                    });
                    dialog.show();
                }
            });
        }
    }

    /**
     * 用户角色为观众时，麦位上有人对应的操作
     */
    public void micPresentAudience(final MicBean micBean) {
        ThreadManager.getInstance().runOnUIThread(new Runnable() {
            @Override
            public void run() {
                final MicAudienceFactory micAudienceFactory = new MicAudienceFactory();
                final Dialog dialog = micAudienceFactory.buildDialog(mContext);
                if (micBean.getPosition() == 0) {
                    micAudienceFactory.setMicPosition("主持人");
                } else {
                    micAudienceFactory.setMicPosition(micBean.getPosition() + "号麦");
                }
                List<String> ids = new ArrayList<>();
                ids.add(micBean.getUserId());
                //

                micAudienceFactory.setOnDialogButtonListClickListener(new OnDialogButtonListClickListener() {
                    @Override
                    public void onClick(String content) {
                        if (getResources().getString(R.string.send_message).equals(content)) {
                            //发消息
                        }

                        if (getResources().getString(R.string.send_gift_item).equals(content)) {
                            //送礼
                            ThreadManager.getInstance().runOnUIThread(new Runnable() {
                                @Override
                                public void run() {

                                }
                            });
                        }
//                        if (getResources().getString(R.string.mic_apply).equals(content)) {
//                            //申请排麦
//                            if (micBean.getState() == MicState.NORMAL.getState() || micBean.getState() == MicState.LOCK.getState()) {
//                                final NetStateLiveData<NetResult<Void>> result = roomMemberViewModel.micApply();
//                                result.getNetStateMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
//                                    @Override
//                                    public void onChanged(Integer integer) {
//                                        if (result.isSuccess()) {
//                                            ToastUtil.showToast("观众申请排麦");
//                                            dialog.cancel();
//                                        }
//                                    }
//                                });
//                            }
//                        }
                    }
                });
                dialog.show();
            }
        });
    }


    /**
     * 用户角色为连麦者时，麦位上有人对应的操作
     */
    public void micPresentConnect(final MicBean micBean) {
        if (micBean.getPosition() == 0 && !"".equals(micBean.getUserId())) {
            //当点击的是主持人时，弹出接管主持人dialog
            ThreadManager.getInstance().runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    final MicConnectTakeOverDialogFactory micConnectTakeOverDialogFactory = new MicConnectTakeOverDialogFactory();
                    final Dialog dialog = micConnectTakeOverDialogFactory.buildDialog(mContext);
                    micConnectTakeOverDialogFactory.setCurrentType(true);
                    micConnectTakeOverDialogFactory.setOnDialogButtonListClickListener(new OnDialogButtonListClickListener() {
                        @Override
                        public void onClick(String content) {
                            if (getResources().getString(R.string.send_message).equals(content)) {
                                //点击发消息
                            }
                            if (getResources().getString(R.string.take_over_host).equals(content)) {
                                //接管主持

                            }
                        }
                    });
                    List<String> ids = new ArrayList<>();
                    ids.add(micBean.getUserId());

                    micConnectTakeOverDialogFactory.setUserName("主持人麦位");
                    dialog.show();

                }
            });
        }
        if (micBean.getPosition() != 0 && micBean.getUserId().equals(CacheManager.getInstance().getUserId())) {
            //当连麦者点击的是自己时，弹出下麦的dialog
            ThreadManager.getInstance().runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    final MicConnectDialogFactory micConnectFactory = new MicConnectDialogFactory();
                    final Dialog dialog = micConnectFactory.buildDialog(mContext);
                    micConnectFactory.setCurrentUser(false);
                    micConnectFactory.setMicPosition(micBean.getPosition() + "号麦");
                    micConnectFactory.setOnDialogButtonListClickListener(new OnDialogButtonListClickListener() {
                        @Override
                        public void onClick(String content) {
                            //连麦者下麦

                        }
                    });
                    List<String> ids = new ArrayList<>();
                    ids.add(micBean.getUserId());
                    //TODO:
                    dialog.show();
                }
            });
        }
        if (micBean.getPosition() != 0 && !micBean.getUserId().equals(CacheManager.getInstance().getUserId())) {
            //当点击的不是自己而是别人时，弹出对应的人的用户资料卡
            micPresentAudience(micBean);
        }
    }

    /**
     * 连麦
     *
     * @param micBean
     */
    public void micAbsentConnect(final MicBean micBean) {
        //当其他连麦者面对主持人麦位为空的情况下，弹出接管主持的弹窗
        if (micBean.getPosition() == 0) {
            ThreadManager.getInstance().runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    final MicConnectTakeOverDialogFactory micConnectTakeOverDialogFactory = new MicConnectTakeOverDialogFactory();
                    micConnectTakeOverDialogFactory.setShowMessageButton(false);
                    final Dialog dialog = micConnectTakeOverDialogFactory.buildDialog(mContext);
                    micConnectTakeOverDialogFactory.setCurrentType(false);
                    micConnectTakeOverDialogFactory.setOnDialogButtonListClickListener(new OnDialogButtonListClickListener() {
                        @Override
                        public void onClick(String content) {
                            if (getResources().getString(R.string.send_message).equals(content)) {
                                //点击发消息
                                //此处弹窗不显示发送消息按钮，自然不用处理
                            }
                            if (getResources().getString(R.string.take_over_host).equals(content)) {
                                //接管主持

                            }
                        }
                    });
                    micConnectTakeOverDialogFactory.setUserName("主持人麦位");
                    dialog.show();
                }
            });
        }
    }

    /**
     * 链接麦序
     */
    private void connectRowList() {
        RowWheatListDialog rowWheatListDialog = new RowWheatListDialog();
        rowWheatListDialog.show(getSupportFragmentManager(), "show");
        rowWheatListDialog.setOnRowWheatClickListener(new OnRowWheatClickListener() {
            @Override
            public void onRowWheatItemSelect() {

            }

            @Override
            public void onClearRowWheat() {
                roomOperating(1);
            }
        });
    }

    /**
     * 清空麦序
     */
    private void roomOperating(int type) {
        showLoadDialog();
        HttpRequest.roomItemOperating(this, type, roomId, new HttpCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                dismissLoadDialog();

            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBackReturn:
                dropOutRoom();
                break;
            case R.id.llReleaseBroadcastLayout:
                startActivity(BroadcastDatingActivity.class);
                break;
            case R.id.ivUserList:
                RoomListActivity.start(mContext, roomId);
                break;
            case R.id.ivRoomSetting:
                showRoomSettingDialog();
                break;
            case R.id.ivGiftPhoto:
                showSendGiftDialog();
                break;
            case R.id.ivComment:
                //评论
                showRoomUserCommentDialog();
                break;
        }
    }

    /**
     * 聊天对话
     */
    private void showRoomUserCommentDialog() {
        RoomUserCommentDialog roomUserCommentDialog = RoomUserCommentDialog.newInstance(roomId, roomTitle);
        roomUserCommentDialog.show(getSupportFragmentManager(), "comment");
    }

    /**
     * 退出房间，依然要判断是主播，还是观众
     */
    private void dropOutRoom() {
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
                switch (itemID) {
                    case 1://房间流水
                        RoomWaterActivity.start(mContext, roomId);
                        break;
                    case 2://房间资料
//                        CreateRoomActivity.start(mContext,2);
                        break;
                    case 3://房间加密
                        showSetRoomPasswordDialog();
                        break;
                    case 4://关闭公屏
                        break;
                    case 5://清除公屏
                        break;
                    case 6://魅力值统计
                        break;
                    case 7://魅力值清零
                        break;
                    case 8://设置管理员
                        startActivity(SetUpAdministratorActivity.class);
                        break;
                    case 9://禁言管理
                        KickOutTheRoomActivity.start(mContext, 2);
                        break;
                    case 10://禁麦管理
                        KickOutTheRoomActivity.start(mContext, 3);
                        break;
                    case 11://踢出房间
                        KickOutTheRoomActivity.start(mContext, 1);
                        break;
                    case 12://关注房间
                        break;
                    case 13://分享房间
                        showShareRoomDialog();
                        break;
                    case 14://举报房间
                        ReportUserOrRoom reportUserOrRoom = new ReportUserOrRoom();
                        reportUserOrRoom.setRoomId(roomId);
                        reportUserOrRoom.setRoomTitle(roomTitle);
                        reportUserOrRoom.setRoomType(roomType);
                        ReportRoomActivity.start(mContext, 1, reportUserOrRoom);
                        break;
                    case 15://退出房间
                        dropOutRoom();
                        break;
                }
            }
        });
    }

    /**
     * 设置密码
     */
    private void showSetRoomPasswordDialog() {
        SetRoomPasswordDialog passwordDialog = new SetRoomPasswordDialog(mContext);
        passwordDialog.show();
        passwordDialog.setOnSetRoomPasswordListener(new OnSetRoomPasswordListener() {
            @Override
            public void onSetRoomPassword(String password) {
                HttpRequest.setRoomPassword(mContext, password, roomId, new HttpCallBack<Object>() {
                    @Override
                    public void onSuccess(Object o) {
                        ToastUtils.showCustomToast(mContext, "设置成功");
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        ToastUtils.showToast(errorMsg);

                    }
                });
            }
        });
    }

    /**
     * 分享
     */
    private void showShareRoomDialog() {
        ShareItemDialog shareItemDialog = new ShareItemDialog();
        shareItemDialog.show(getSupportFragmentManager(), "share");
        shareItemDialog.setOnReportAndAttentionListener(new OnReportAndAttentionListener() {
            @Override
            public void onItemClick(int itemType) {

            }
        });
    }


    /**
     * 送礼物
     */
    private void showSendGiftDialog() {
        SendGiftDialogFragment sendGiftDialogFragment = new SendGiftDialogFragment();
        sendGiftDialogFragment.show(getSupportFragmentManager(), "gift");
        sendGiftDialogFragment.setOnSendGiftListener(new OnSendGiftListener() {
            @Override
            public void onSendGift(int giftNumber, int giftId) {

            }
        });
    }


    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .statusBarColor(R.color.color_3A37C4)
                .keyboardEnable(true)
                .statusBarDarkFont(true, 0f)
                .init();
    }
}
