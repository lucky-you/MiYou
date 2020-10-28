package com.zhowin.miyou.recommend.activity;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

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
import com.zhowin.miyou.recommend.callback.OnHitCenterClickListener;
import com.zhowin.miyou.recommend.callback.OnLiveRoomBottomSetListener;
import com.zhowin.miyou.recommend.callback.OnLiveRoomSettingItemListener;
import com.zhowin.miyou.recommend.callback.OnReportAndAttentionListener;
import com.zhowin.miyou.recommend.callback.OnRoomMemberItemClickListener;
import com.zhowin.miyou.recommend.callback.OnRowWheatClickListener;
import com.zhowin.miyou.recommend.callback.OnSendGiftListener;
import com.zhowin.miyou.recommend.callback.OnSetRoomPasswordListener;
import com.zhowin.miyou.recommend.dialog.HitCenterDialog;
import com.zhowin.miyou.recommend.dialog.LiveRoomBottomSetDialog;
import com.zhowin.miyou.recommend.dialog.LiveRoomSettingDialog;
import com.zhowin.miyou.recommend.dialog.RoomUserCommentDialog;
import com.zhowin.miyou.recommend.dialog.RowWheatListDialog;
import com.zhowin.miyou.recommend.dialog.SendGiftDialogFragment;
import com.zhowin.miyou.recommend.dialog.SetRoomPasswordDialog;
import com.zhowin.miyou.recommend.dialog.ShareItemDialog;
import com.zhowin.miyou.recommend.model.ReportUserOrRoom;
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
import com.zhowin.miyou.rongIM.rtc.RTCClient;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
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
public class ChatRoomActivity extends BaseBindActivity<ActivityChatRoomBinding> implements
        OnRoomMemberItemClickListener, OnChatMessageItemClickListener {

    private int userId, roomId, roomOwnerId;// 自己的id/ 房间id / 房间拥有者(房主)的 id
    private String roomTitle, roomType; //房间title 、类型
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
    private List<MicBean> roomMemberMicroList = new ArrayList<>();
    private boolean isAllowMicFree, isCloseScreen;//是否允许自由上下麦/是否关闭公屏

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
        setOnClick(R.id.ivBackReturn, R.id.civHostUserHeadImage, R.id.civGuestImage,
                R.id.llReleaseBroadcastLayout, R.id.ivUserList,
                R.id.ivRoomSetting, R.id.ivGiftPhoto, R.id.ivComment);
        getLifecycle().addObserver(new RoomObserver());
    }

    @Override
    public void initData() {
        userId = UserInfo.getUserInfo().getUserId();

        getRoomDataMessage();

        chatRoomMessageListAdapter = new ChatRoomMessageListAdapter(messageList);
        mBinding.chatMessageRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.chatMessageRecyclerView.setAdapter(chatRoomMessageListAdapter);
        chatRoomMessageListAdapter.setOnChatMessageItemClickListener(this::onMessageItemClick);

        audienceListAdapter = new AudienceListAdapter(roomMemberMicroList);
        mBinding.AudienceRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        mBinding.AudienceRecyclerView.setAdapter(audienceListAdapter);
        audienceListAdapter.setOnRoomMemberItemClickListener(this::onMemberItemClick);
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
        RoomDataInfo.OwnerBean roomOwnerInfo = roomDataInfo.getOwner();
        if (roomOwnerInfo != null) {
            roomOwnerId = roomOwnerInfo.getUserId();
            GlideUtils.loadObjectImage(mContext, roomOwnerInfo.getProfilePictureKey(), mBinding.civUserHeadPhoto);
        }
        RoomDataInfo.RoomBean roomInfo = roomDataInfo.getRoom();
        if (roomInfo != null) {
            roomType = roomInfo.getTypeName();
            roomTitle = roomInfo.getTitle();
            isAllowMicFree = 1 == roomInfo.getAllowMicFree();//1 是允许自由山下麦
            isCloseScreen = 1 == roomInfo.getCloseScreen();//1 关闭公屏
            mBinding.tvFounderNickName.setText("[" + roomType + "] " + roomTitle);
            mBinding.tvRoomNumber.setText("房间ID: " + roomInfo.getRoomId());
            mBinding.tvPopularityValue.setText("人气: " + roomInfo.getHeatValue());
            mBinding.tvAttention.setVisibility(roomDataInfo.isCollection() ? View.GONE : View.VISIBLE);
        }

        /**
         * 判断用户身份
         * 在这里判断房间是否是自己创建的，如果是自己，以房主的身份进入房间，
         * 如果不是自己则以观众的身份进入房间
         */
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
                            if (chatRoomInfo != null) {
                                int onlineNumber = chatRoomInfo.getTotalMemberCount();
                                Log.e("xy", "房间人数:" + onlineNumber);
//                            String onlineNumberString = mContext.getResources().getString(R.string.online_number);
                            }
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
                Log.e("xy", "麦位KV的Map:" + stringStringMap.size());
                //根据KV判断，如果对应的麦位上有人，则用对应的信息填充麦位
                userIdList = new ArrayList<>();
                IMManager.getInstance().transMicBean(stringStringMap, new SealMicResultCallback<MicBean>() {
                    @Override
                    public void onSuccess(MicBean micBean) {
                        Log.e("xy", "micBean:" + micBean);
                        localMicBeanMap.put(micBean.getPosition(), micBean);
                        userIdList.add(micBean.getUserId());
                        roomMemberMicroList.add(micBean);
                        Collections.sort(roomMemberMicroList);
                        if (userIdList.size() == stringStringMap.size()) {
                            //说明循环完成了，更新麦位数据
                            // TODO:麦位说明：融云返回的麦位顺序的是0-10 ，0号麦是房主，
                            // TODO:在项目中 0号麦是房主 ，1号麦是主持人 ，2号麦是嘉宾位，3号麦是老板位 ,4-10号麦对应界面上的1-7号麦
                            if (roomMemberMicroList.size() >= 10) {
                                List<MicBean> newAudienceList = roomMemberMicroList.subList(3, 11);//所以这里截取3-10
                                Log.e("xy", "newAudienceList:" + newAudienceList);
                                audienceListAdapter.setNewData(newAudienceList);
                            }
                        }
                        EventBus.getDefault().postSticky(new Event.EventMicBean(micBean));
                    }

                    @Override
                    public void onFail(int errorCode) {
                        Log.e("xy", "获取初始化麦位信息失败: " + errorCode);
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
     * 居中提示的dialog
     *
     * @param hitTitle 提示信息
     */
    private void showCenterDialog(String hitTitle) {
        HitCenterDialog hitCenterDialog = new HitCenterDialog(mContext);
        hitCenterDialog.setDialogTitle(hitTitle);
        hitCenterDialog.setOnHitCenterClickListener(new OnHitCenterClickListener() {
            @Override
            public void onCancelClick() {

            }

            @Override
            public void onDetermineClick() {

            }
        });
        hitCenterDialog.show();
    }

    /**
     * 根据id查询用户信息
     *
     * @param userId
     */
    private void queryUserMessageList(String userId, MicBean micBean) {
        HttpRequest.getOtherUserInfoMessage(this, Integer.parseInt(userId), new HttpCallBack<UserInfo>() {
            @Override
            public void onSuccess(UserInfo userInfo) {
                Log.e("xy", "查询成功：" + userInfo.getUserId());
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


    @Override
    public void onMessageItemClick(int position, Message message) {
        final String currentUserId = CacheManager.getInstance().getUserId();
        int userRoleType = CacheManager.getInstance().getUserRoleType();
    }


    @Override
    public void onMemberItemClick(int itemUiPosition, int itemIMPosition, MicBean audienceList) {
        onRoomMemberItemClick(itemUiPosition, itemIMPosition);
    }

    /**
     * 对于麦位点击事件的判断处理
     */
    private void onRoomMemberItemClick(int itemUiPosition, int itemIMPosition) {
        if (isAllowMicFree) { //允许自由上下麦
            MicBean micBean = localMicBeanMap.get(itemIMPosition);
            if (micBean != null) {
                switch (itemIMPosition) {
                    case 1: //主持人
                    case 2: //嘉宾位
                        LockOrUnLockMicro(micBean);
                        break;

                    case 3://老板位
                        break;
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10: //其他麦位
                        break;
                }
            }

        } else {
            //不允许自由上下麦

        }
    }

    /**
     * 禁麦 、解麦
     *
     * @param micBean
     */
    private void LockOrUnLockMicro(MicBean micBean) {
        if (userId == roomOwnerId) {
            //自己是房主
            if (micBean.getState() == MicState.LOCK.getState()) {
                showLockMicroDialog(false);
            } else if (micBean.getState() == MicState.NORMAL.getState()) {
                showLockMicroDialog(true);
            }
        } else {
            //不是房主,还要判断是否是主持人，是否是接待管理，是否是普通管理，最后才是观众


//            ToastUtils.showCustomToast(mContext, "您没有权限哦");
        }
    }

    /**
     * 锁麦/解锁
     */
    private void showLockMicroDialog(boolean isLockMicro) {
        LiveRoomBottomSetDialog liveRoomBottomSetDialog = LiveRoomBottomSetDialog.newInstance(true, isLockMicro ? "锁麦" : "解麦", "取消");
        liveRoomBottomSetDialog.show(getSupportFragmentManager(), "lock");
        liveRoomBottomSetDialog.setOnLiveRoomBottomSetListener(new OnLiveRoomBottomSetListener() {
            @Override
            public void onTitleOneClick(int typeOne) {
                lockOrUnLockMicro(isLockMicro);
            }

            @Override
            public void onTitleTwoClick(int typeTwo) {

            }
        });
    }

    private void lockOrUnLockMicro(boolean isLockMicro) {
        HttpRequest.lockOrUnLockMicro(this, isLockMicro, roomId, 34, new HttpCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onFail(int errorCode, String errorMsg) {

            }
        });

    }

    /**
     * 排麦列表
     */
    private void showRowWheatListDialog() {
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
            case R.id.civHostUserHeadImage:
                //主持位置
                onRoomMemberItemClick(1, 1);
                break;
            case R.id.civGuestImage:
                //嘉宾位
                onRoomMemberItemClick(2, 2);
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
     * 房间设置 ：需要判断用户身份，不同的身份显示不同的选项
     */
    private void showRoomSettingDialog() {
        LiveRoomSettingDialog roomSettingDialog = LiveRoomSettingDialog.newInstance(2);
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
