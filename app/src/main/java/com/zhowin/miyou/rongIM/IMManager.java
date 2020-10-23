package com.zhowin.miyou.rongIM;

import android.content.Context;
import android.net.Uri;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bumptech.glide.load.engine.Resource;
import com.google.gson.Gson;
import com.zhowin.miyou.BuildConfig;
import com.zhowin.miyou.message.message.GroupApplyMessage;
import com.zhowin.miyou.message.message.HandOverHostMessage;
import com.zhowin.miyou.message.message.KickMemberMessage;
import com.zhowin.miyou.message.message.RoomMemberChangedMessage;
import com.zhowin.miyou.message.message.SendGiftMessage;
import com.zhowin.miyou.message.message.TakeOverHostMessage;
import com.zhowin.miyou.recommend.activity.HomepageActivity;
import com.zhowin.miyou.rongIM.message.SealContactNotificationMessage;
import com.zhowin.miyou.rongIM.model.ChatRoomAction;
import com.zhowin.miyou.rongIM.model.ContactNotificationMessageData;
import com.zhowin.miyou.rongIM.model.ConversationRecord;
import com.zhowin.miyou.rongIM.model.QuietHours;
import com.zhowin.miyou.rongIM.model.UserCacheInfo;
import com.zhowin.miyou.rongIM.provider.ContactNotificationMessageProvider;
import com.zhowin.miyou.rongIM.sp.UserCache;
import com.zhowin.miyou.rongIM.sp.UserConfigCache;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imkit.manager.SendImageManager;
import io.rong.imkit.mention.IMentionedInputListener;
import io.rong.imkit.mention.RongMentionManager;
import io.rong.imkit.model.GroupNotificationMessageData;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.notification.MessageNotificationManager;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.widget.provider.RealTimeLocationMessageProvider;
import io.rong.imlib.CustomServiceConfig;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.cs.CustomServiceManager;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Discussion;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.UserInfo;
import io.rong.message.ContactNotificationMessage;
import io.rong.message.GroupNotificationMessage;
import io.rong.message.ImageMessage;

public class IMManager {
    private static volatile IMManager instance;

    private MutableLiveData<ChatRoomAction> chatRoomActionLiveData = new MutableLiveData<>();
    private Context context;

    private UserConfigCache configCache;
    private UserCache userCache;

    private MutableLiveData<Message> messageRouter = new MutableLiveData<>();
    private MutableLiveData<Boolean> kickedOffline = new MutableLiveData<>();
    private ConversationRecord lastConversationRecord;


    /**
     * 接收消息的监听器列表
     */
    private final List<RongIMClient.OnReceiveMessageListener> onReceiveMessageListeners = new ArrayList<>();

    private IMManager() {
    }

    public static IMManager getInstance() {
        if (instance == null) {
            synchronized (IMManager.class) {
                if (instance == null) {
                    instance = new IMManager();
                }
            }
        }
        return instance;
    }

    /**
     * @param context
     */
    public void init(Context context) {
        this.context = context.getApplicationContext();

        // 初始化 IM 相关缓存
        initIMCache();

        // 调用 RongIM 初始化
        initRongIM(context);

        // 初始化自定义消息和消息模版
        initMessageAndTemplate();

        // 初始化扩展模块
//        initExtensionModules(context);

        // 初始化已读回执类型
        initReadReceiptConversation();

        // 初始化会话界面相关内容
        initConversation();

        // 初始化会话列表界面相关内容
        initConversationList();

        // 初始化连接状态变化监听
        initConnectStateChangeListener();

        // 初始化消息监听
        initOnReceiveMessage(context);

        // 初始化聊天室监听
        initChatRoomActionListener();
    }


    public void evaluateCustomService(String targetId, int stars, CustomServiceConfig.CSEvaSolveStatus resolveStatus, String lables, String suggestion, String dialogId) {
        RongIMClient.getInstance().evaluateCustomService(targetId, stars, resolveStatus, lables,
                suggestion, dialogId, null);
    }


    /**
     * 设置人工评价监听
     * 当人工评价有标签等配置时，在回调中返回配置
     *
     * @param listener
     */
    public void setCustomServiceHumanEvaluateListener(CustomServiceManager.OnHumanEvaluateListener listener) {
        RongIMClient.getInstance().setCustomServiceHumanEvaluateListener(listener);
    }

    /**
     * 设置正在输入消息状态
     *
     * @param listener
     */
    public void setTypingStatusListener(RongIMClient.TypingStatusListener listener) {
        RongIMClient.setTypingStatusListener(listener);
    }


    /**
     * 获取讨论组信息
     *
     * @param targetId
     * @param callback
     */
    public void getDiscussion(String targetId, RongIMClient.ResultCallback<Discussion> callback) {
        RongIM.getInstance().getDiscussion(targetId, callback);
    }

    /**
     * 获取从公众号信息
     *
     * @param type
     * @param targetId
     * @param callback
     */
    public void getPublicServiceProfile(Conversation.PublicServiceType type, String targetId, RongIMClient.ResultCallback<PublicServiceProfile> callback) {
        RongIM.getInstance().getPublicServiceProfile(type, targetId, callback);
    }


    /**
     * 初始化会话相关
     */
    private void initConversation() {
        // 启用会话界面新消息提示
        RongIM.getInstance().enableNewComingMessageIcon(true);
        // 启用会话界面未读信息提示
        RongIM.getInstance().enableUnreadMessageIcon(true);
        // 添加会话界面点击事件
        RongIM.setConversationClickListener(new RongIM.ConversationClickListener() {
            @Override
            public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo, String s) {
                if (conversationType == Conversation.ConversationType.PRIVATE) {
                    Log.e("xy", "userID：" + s + "userInfoId:" + userInfo.getUserId());
                    int userID = com.zhowin.base_library.model.UserInfo.getUserInfo().getUserId();
                    boolean isMime = TextUtils.equals(String.valueOf(userID), userInfo.getUserId());
                    HomepageActivity.start(context, isMime, Integer.parseInt(userInfo.getUserId()));
                }
                return true;
            }

            @Override
            public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo, String s) {
                if (conversationType == Conversation.ConversationType.GROUP) {
                    // 当在群组时长按用户在输入框中加入 @ 信息
                    return true;
                }
                return false;
            }

            @Override
            public boolean onMessageClick(Context context, View view, Message message) {
                if (message.getContent() instanceof ImageMessage) {
//                    Intent intent = new Intent(view.getContext(), SealPicturePagerActivity.class);
//                    intent.setPackage(view.getContext().getPackageName());
//                    intent.putExtra("message", message);
//                    view.getContext().startActivity(intent);
                    return true;
                }
                return false;
            }

            @Override
            public boolean onMessageLinkClick(Context context, String s, Message message) {
                return false;
            }

            @Override
            public boolean onMessageLongClick(Context context, View view, Message message) {
                return false;
            }
        });
    }

    /**
     * 初始化 IM 相关缓存
     */
    private void initIMCache() {
        // 用户设置缓存 sp
        configCache = new UserConfigCache(context.getApplicationContext());
        userCache = new UserCache(context.getApplicationContext());
    }

    /**
     * 设置用户信息
     */
    public void setUserCache(String userId, String userToken, String userName, String userAvatar) {
        UserCacheInfo userCacheInfo = new UserCacheInfo(userId, userName, Uri.parse(userAvatar));
        userCacheInfo.setUserToken(userToken);
        userCacheInfo.setUserAvatar(userAvatar);
        userCache.saveUserCache(userCacheInfo);
    }


    public String getUserIMToken() {
        String token = null;
        if (userCache != null) {
            UserCacheInfo userCacheInfo = userCache.getUserCache();
            if (userCacheInfo != null)
                token = userCacheInfo.getUserToken();
        }
        return token;
    }

    /**
     * 初始化会话列表相关事件
     */
    private void initConversationList() {
        // 设置会话列表行为监听
        RongIM.setConversationListBehaviorListener(new RongIM.ConversationListBehaviorListener() {
            @Override
            public boolean onConversationPortraitClick(Context context, Conversation.ConversationType conversationType, String s) {
                //如果是群通知，点击头像进入群通知页面
                if (s.equals("__group_apply__")) {
//                    Intent noticeListIntent = new Intent(context, GroupNoticeListActivity.class);
//                    context.startActivity(noticeListIntent);
                    return true;
                }
                return false;
            }

            @Override
            public boolean onConversationPortraitLongClick(Context context, Conversation.ConversationType conversationType, String s) {
                return false;
            }

            @Override
            public boolean onConversationLongClick(Context context, View view, UIConversation uiConversation) {
                return false;
            }

            @Override
            public boolean onConversationClick(Context context, View view, UIConversation uiConversation) {
                /*
                 * 当点击会话列表中通知添加好友消息时，判断是否已成为好友
                 * 已成为好友时，跳转到私聊界面
                 * 非好友时跳转到新的朋友界面查看添加好友状态
                 */
                MessageContent messageContent = uiConversation.getMessageContent();
                if (messageContent instanceof ContactNotificationMessage) {
                    ContactNotificationMessage contactNotificationMessage = (ContactNotificationMessage) messageContent;
                    if (contactNotificationMessage.getOperation().equals("AcceptResponse")) {
                        // 被加方同意请求后
                        if (contactNotificationMessage.getExtra() != null) {
                            ContactNotificationMessageData bean = null;
                            try {
                                Gson gson = new Gson();
                                bean = gson.fromJson(contactNotificationMessage.getExtra(), ContactNotificationMessageData.class);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            RongIM.getInstance().startPrivateChat(context, uiConversation.getConversationSenderId(), bean.getSourceUserNickname());
                        }
                    } else {
//                        context.startActivity(new Intent(context, NewFriendListActivity.class));
                    }
                    return true;
                } else if (messageContent instanceof GroupApplyMessage) {
//                    Intent noticeListIntent = new Intent(context, GroupNoticeListActivity.class);
//                    context.startActivity(noticeListIntent);
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 更新 IMKit 显示用用户信息
     *
     * @param userId
     * @param userName
     * @param portraitUri
     */
    public void updateUserInfoCache(String userId, String userName, Uri portraitUri) {
        UserInfo oldUserInfo = RongUserInfoManager.getInstance().getUserInfo(userId);
        if (oldUserInfo == null
                || (
                !oldUserInfo.getName().equals(userName)
                        || oldUserInfo.getPortraitUri() == null
                        || !oldUserInfo.getPortraitUri().equals(portraitUri)
        )) {
            UserInfo userInfo = new UserInfo(userId, userName, portraitUri);
            RongIM.getInstance().refreshUserInfoCache(userInfo);
        }
    }

    /**
     * 获取当前用户 id
     *
     * @return
     */
    public String getCurrentId() {
        return RongIM.getInstance().getCurrentUserId();
    }


    /**
     * 清除会话及消息
     *
     * @param targetId
     * @param conversationType
     */
    public void clearConversationAndMessage(String targetId, Conversation.ConversationType conversationType) {
        RongIM.getInstance().getConversation(conversationType, targetId, new RongIMClient.ResultCallback<Conversation>() {
            @Override
            public void onSuccess(Conversation conversation) {
                RongIM.getInstance().clearMessages(conversationType, targetId, new RongIMClient.ResultCallback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        RongIM.getInstance().removeConversation(conversationType, targetId, null);
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode e) {

                    }
                });
            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {
            }
        });
    }


    /**
     * 注册消息及消息模版
     */
    private void initMessageAndTemplate() {
        RongIM.registerMessageType(SealContactNotificationMessage.class);

        RongIM.registerMessageTemplate(new ContactNotificationMessageProvider());
        RongIM.registerMessageTemplate(new RealTimeLocationMessageProvider());
        RongIM.registerMessageType(GroupApplyMessage.class);

        //语音房间
        RongIM.registerMessageType(RoomMemberChangedMessage.class);
        RongIM.registerMessageType(SendGiftMessage.class);
        RongIM.registerMessageType(KickMemberMessage.class);
        RongIM.registerMessageType(HandOverHostMessage.class);
        RongIM.registerMessageType(TakeOverHostMessage.class);

        // 开启高清语音
        RongIM.getInstance().setVoiceMessageType(RongIM.VoiceMessageType.HighQuality);
    }

    /**
     * 初始化扩展模块
     *
     * @param context
     */
    private void initExtensionModules(Context context) {
        /**
         * 因为 SealExtensionModule 继承与融云默认 DefaultExtensionModule，
         * 需要先移除掉默认的扩展后再进行注册
         * 继承并覆盖默认的扩展模块可在自己需要的时机控制各默认模块的展示与隐藏
         */
        List<IExtensionModule> moduleList = RongExtensionManager.getInstance().getExtensionModules();
        IExtensionModule defaultModule = null;
        if (moduleList != null) {
            for (IExtensionModule module : moduleList) {
                if (module instanceof DefaultExtensionModule) {
                    defaultModule = module;
                    break;
                }
            }
            if (defaultModule != null) {
                RongExtensionManager.getInstance().unregisterExtensionModule(defaultModule);
            }
        }

//        RongExtensionManager.getInstance().registerExtensionModule(new SealExtensionModule(context));


    }

    /**
     * 调用初始化 RongIM
     *
     * @param context
     */
    private void initRongIM(Context context) {
        /*
         * 如果是连接到私有云需要在此配置服务器地址
         * 如果是公有云则不需要调用此方法
         */
        //RongIM.setServerInfo(BuildConfig.SEALTALK_NAVI_SERVER, BuildConfig.SEALTALK_FILE_SERVER);

        /*
         * 初始化 SDK，在整个应用程序全局，只需要调用一次。建议在 Application 继承类中调用。
         */

        /* 若直接调用init方法请在 IMLib 模块中的 AndroidManifest.xml 中, 找到 <meta-data> 中 android:name 为 RONG_CLOUD_APP_KEY的标签，
         * 将 android:value 替换为融云 IM 申请的APP KEY
         */
        //RongIM.init(this);

        // 可在初始 SDK 时直接带入融云 IM 申请的APP KEY
        RongIM.init(context, BuildConfig.Rong_key, true);
    }


    /**
     * 初始化已读回执类型
     */
    private void initReadReceiptConversation() {
        // 将私聊，群组加入消息已读回执
        Conversation.ConversationType[] types = new Conversation.ConversationType[]{
                Conversation.ConversationType.PRIVATE,
                Conversation.ConversationType.GROUP,
                Conversation.ConversationType.ENCRYPTED
        };
        RongIM.getInstance().setReadReceiptConversationTypeList(types);
    }

    /**
     * 初始化连接状态监听
     */
    private void initConnectStateChangeListener() {
        RongIM.setConnectionStatusListener(new RongIMClient.ConnectionStatusListener() {
            @Override
            public void onChanged(ConnectionStatus connectionStatus) {
                Log.d("xy", "ConnectionStatus onChanged = " + connectionStatus.getMessage());
                if (connectionStatus.equals(ConnectionStatus.KICKED_OFFLINE_BY_OTHER_CLIENT)) {
                    //被其他踢出时，需要返回登录界面
                    kickedOffline.postValue(true);
                } else if (connectionStatus == ConnectionStatus.TOKEN_INCORRECT) {
                    //TODO token 错误时，重新登录
                }
            }
        });
    }

    /**
     * 初始化消息监听
     */
    private void initOnReceiveMessage(Context context) {
        RongIM.setOnReceiveMessageListener(new RongIMClient.OnReceiveMessageListener() {
            @Override
            public boolean onReceived(Message message, int i) {
                messageRouter.postValue(message);
                MessageContent messageContent = message.getContent();
                String targetId = message.getTargetId();
                if (messageContent instanceof ContactNotificationMessage) { // 添加好友状态信息
                    ContactNotificationMessage contactNotificationMessage = (ContactNotificationMessage) messageContent;
                    if (contactNotificationMessage.getOperation().equals("Request")) {

                    } else if (contactNotificationMessage.getOperation().equals("AcceptResponse")) {
                        // 根据好友 id 进行获取好友信息并刷新
                        String sourceUserId = contactNotificationMessage.getSourceUserId();
//                        imInfoProvider.updateFriendInfo(sourceUserId);
                    }
                } else if (messageContent instanceof GroupNotificationMessage) {    // 群组通知消息
                    GroupNotificationMessage groupNotificationMessage = (GroupNotificationMessage) messageContent;
                    Log.d("xy", "onReceived GroupNotificationMessage:" + groupNotificationMessage.getMessage());

                    String groupID = targetId;
                    GroupNotificationMessageData data = null;
                    try {
                        String currentID = RongIM.getInstance().getCurrentUserId();
                        try {
                            Gson gson = new Gson();
                            data = gson.fromJson(groupNotificationMessage.getData(), GroupNotificationMessageData.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (groupNotificationMessage.getOperation().equals("Create")) {
                            // 创建群组,获取群组信息
//                            imInfoProvider.updateGroupInfo(groupID);
//                            imInfoProvider.updateGroupMember(groupID);
                        } else if (groupNotificationMessage.getOperation().equals("Dismiss")) {

                        } else if (groupNotificationMessage.getOperation().equals("Kicked")) {
                            //群组踢人
                            boolean isKicked = false;
                            if (data != null) {
                                List<String> memberIdList = data.getTargetUserIds();
                                if (memberIdList != null) {
                                    for (String userId : memberIdList) {
                                        if (currentID.equals(userId)) {
                                            // 被踢出群组，删除群组会话和消息
                                            clearConversationAndMessage(groupID, Conversation.ConversationType.GROUP);
//                                            imInfoProvider.deleteGroupInfoInDb(groupID);
                                            isKicked = true;
                                            break;
                                        }
                                    }
                                }
                            }
                            // 如果未被提出，则更新群组信息和群成员
                            if (!isKicked) {
//                                imInfoProvider.updateGroupInfo(groupID);
//                                imInfoProvider.updateGroupMember(groupID);
                            }
                        } else if (groupNotificationMessage.getOperation().equals("Add")) {
                            // 群组添加人员
//                            imInfoProvider.updateGroupInfo(groupID);
//                            imInfoProvider.updateGroupMember(groupID);
                        } else if (groupNotificationMessage.getOperation().equals("Quit")) {
                            //刷新退群列表
//                            imInfoProvider.refreshGroupExitedInfo(groupID);
                            // 退出群组，当非自己退出室刷新群组信息
                            if (!currentID.equals(groupNotificationMessage.getOperatorUserId())) {
//                                imInfoProvider.updateGroupInfo(groupID);
//                                imInfoProvider.updateGroupMember(groupID);
                            }
                        } else if (groupNotificationMessage.getOperation().equals("Rename")) {
                            // 群组重命名,更新群信息
                            if (data != null) {
                                // 更新数据库中群组名称
                                String targetGroupName = data.getTargetGroupName();
//                                imInfoProvider.updateGroupNameInDb(groupID, targetGroupName);
                            }
                        } else if (groupNotificationMessage.getOperation().equals("Transfer")) {
                            // 转移群主，获取群组信息
//                            imInfoProvider.updateGroupInfo(groupID);
//                            imInfoProvider.updateGroupMember(groupID);
                        } else if (groupNotificationMessage.getOperation().equals("SetManager")) {
                            // 设置管理员，获取群组信息
//                            imInfoProvider.updateGroupInfo(groupID);
//                            imInfoProvider.updateGroupMember(groupID);
                        } else if (groupNotificationMessage.getOperation().equals("RemoveManager")) {
                            // 移除管理员，获取群组信息
//                            imInfoProvider.updateGroupInfo(groupID);
//                            imInfoProvider.updateGroupMember(groupID);
                        }
                    } catch (Exception e) {
                        Log.d("xy", "onReceived process GroupNotificationMessage catch exception:" + e.getMessage());
                        e.printStackTrace();
                    }
                    return true;
                } else if (messageContent instanceof GroupApplyMessage) {
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 设置通知消息免打扰
     *
     * @param startTime
     * @param spanMinutes
     * @return
     */
    public MutableLiveData<Resource<QuietHours>> setNotificationQuietHours(String startTime, int spanMinutes, boolean isCache) {
        MutableLiveData<Resource<QuietHours>> result = new MutableLiveData<>();
        RongIMClient.getInstance().setNotificationQuietHours(startTime, spanMinutes, new RongIMClient.OperationCallback() {
            @Override
            public void onSuccess() {
                MessageNotificationManager.getInstance().setNotificationQuietHours(startTime, spanMinutes);
                // 设置用户消息免打扰缓存状态
                if (isCache) {
                    configCache.setNotifiDonotDistrabStatus(getCurrentId(), true);
                    configCache.setNotifiQuietHours(getCurrentId(), startTime, spanMinutes);
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
            }
        });
        return result;
    }

    /**
     * 清理通知免打扰
     */
    public MutableLiveData<Resource<Boolean>> removeNotificationQuietHours() {
        MutableLiveData<Resource<Boolean>> result = new MutableLiveData<>();
        RongIM.getInstance().removeNotificationQuietHours(new RongIMClient.OperationCallback() {
            @Override
            public void onSuccess() {
                // 设置用户消息免打扰缓存状态
                configCache.setNotifiDonotDistrabStatus(getCurrentId(), false);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
            }
        });
        return result;
    }

    /**
     * 获取通知时间
     *
     * @return
     */
    public MutableLiveData<Resource<QuietHours>> getNotificationQuietHours() {
        MutableLiveData<Resource<QuietHours>> result = new MutableLiveData<>();
        RongIM.getInstance().getNotificationQuietHours(new RongIMClient.GetNotificationQuietHoursCallback() {
            @Override
            public void onSuccess(String s, int i) {
                QuietHours quietHours = new QuietHours();
                quietHours.startTime = s;
                quietHours.spanMinutes = i;
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
            }
        });
        return result;
    }

    /**
     * 新消息通知设置
     *
     * @param status
     */
    public void setRemindStatus(boolean status) {
        if (status) {
            boolean donotDistrabStatus = configCache.getNotifiDonotDistrabStatus(getCurrentId());
            if (!donotDistrabStatus) {
                removeNotificationQuietHours();
            }
        } else {
            setNotificationQuietHours("00:00:00", 1439, false);
        }
        configCache.setNewMessageRemind(getCurrentId(), status);
    }


    /**
     * 获取消息通知设置
     *
     * @return
     */
    public boolean getRemindStatus() {
        return configCache.getNewMessageRemind(getCurrentId());
    }

    /**
     * 获取通知设置消息
     *
     * @return
     */
    public QuietHours getNotifiQuietHours() {
        return configCache.getNotifiQUietHours(getCurrentId());
    }

    /**
     * 初始化聊天室监听
     */
    private void initChatRoomActionListener() {
        RongIMClient.setChatRoomActionListener(new RongIMClient.ChatRoomActionListener() {
            @Override
            public void onJoining(String roomId) {
                chatRoomActionLiveData.postValue(ChatRoomAction.joining(roomId));
            }

            @Override
            public void onJoined(String roomId) {
                chatRoomActionLiveData.postValue(ChatRoomAction.joined(roomId));
            }

            @Override
            public void onQuited(String roomId) {
                chatRoomActionLiveData.postValue(ChatRoomAction.quited(roomId));
            }

            @Override
            public void onError(String roomId, RongIMClient.ErrorCode errorCode) {
                chatRoomActionLiveData.postValue(ChatRoomAction.error(roomId));
            }
        });
    }

    /**
     * 获取聊天室行为状态
     *
     * @return
     */
    public LiveData<ChatRoomAction> getChatRoomAction() {
        return chatRoomActionLiveData;
    }

    /**
     * 监听未读消息状态
     */
    public void addUnReadMessageCountChangedObserver(IUnReadMessageObserver observer, Conversation.ConversationType[] conversationTypes) {
        RongIM.getInstance().addUnReadMessageCountChangedObserver(observer, conversationTypes);
    }


    /**
     * 移除未读消息监听
     *
     * @param observer
     */
    public void removeUnReadMessageCountChangedObserver(IUnReadMessageObserver observer) {
        RongIM.getInstance().removeUnReadMessageCountChangedObserver(observer);
    }

    /**
     * 清理未读消息状态
     *
     * @param conversationTypes 指定清理的会话类型
     */
    public void clearMessageUnreadStatus(Conversation.ConversationType[] conversationTypes) {
        RongIM.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
            @Override
            public void onSuccess(List<Conversation> conversations) {
                if (conversations != null && conversations.size() > 0) {
                    for (Conversation c : conversations) {
                        RongIM.getInstance().clearMessagesUnreadStatus(c.getConversationType(), c.getTargetId(), null);
                        if (c.getConversationType() == Conversation.ConversationType.PRIVATE || c.getConversationType() == Conversation.ConversationType.ENCRYPTED) {
                            RongIMClient.getInstance().sendReadReceiptMessage(c.getConversationType(), c.getTargetId(), c.getSentTime(), new IRongCallback.ISendMessageCallback() {

                                @Override
                                public void onAttached(Message message) {

                                }

                                @Override
                                public void onSuccess(Message message) {

                                }

                                @Override
                                public void onError(Message message, RongIMClient.ErrorCode errorCode) {

                                }
                            });
                        }
                        RongIMClient.getInstance().syncConversationReadStatus(c.getConversationType(), c.getTargetId(), c.getSentTime(), null);
                    }
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {

            }
        }, conversationTypes);
    }


    /**
     * 获取群成员列表
     *
     * @param targetId
     */
    public LiveData<List<UserInfo>> getGroupMembers(String targetId) {
        MutableLiveData<List<UserInfo>> result = new MutableLiveData<>();
        RongIM.IGroupMembersProvider groupMembersProvider = RongMentionManager.getInstance().getGroupMembersProvider();
        if (groupMembersProvider == null) {
            result.postValue(null);
        } else {
            groupMembersProvider.getGroupMembers(targetId, new RongIM.IGroupMemberCallback() {
                @Override
                public void onGetGroupMembersResult(final List<UserInfo> members) {
                    result.postValue(members);
                }
            });
        }

        return result;
    }

    /**
     * 获取讨论组中的人员
     *
     * @param targetId
     */
    public LiveData<List<UserInfo>> getDiscussionMembers(String targetId) {
        MutableLiveData<List<UserInfo>> result = new MutableLiveData<>();
        RongIMClient.getInstance().getDiscussion(targetId, new RongIMClient.ResultCallback<Discussion>() {
            @Override
            public void onSuccess(Discussion discussion) {
                List<String> memeberIds = discussion.getMemberIdList();
                if (memeberIds == null || memeberIds.size() <= 0) {
                    result.postValue(null);
                    return;
                }
                List<UserInfo> userInfos = new ArrayList<>();
                for (String id : memeberIds) {
                    UserInfo userInfo = RongUserInfoManager.getInstance().getUserInfo(id);
                    if (userInfo != null) {
                        userInfos.add(userInfo);
                    }
                }
                result.postValue(userInfos);

            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {
                result.postValue(null);
            }
        });

        return result;
    }

    public MutableLiveData<Message> getMessageRouter() {
        return messageRouter;
    }


    /**
     * 退出
     */
    public void logout() {
        RongIM.getInstance().logout();
    }


    /**
     * 获取连接状态
     *
     * @return
     */
    public RongIMClient.ConnectionStatusListener.ConnectionStatus getConnectStatus() {
        return RongIM.getInstance().getCurrentConnectionStatus();
    }

    /**
     * 被踢监听, true 为当前为被提出状态， false 为不需要处理踢出状态
     *
     * @return
     */
    public LiveData<Boolean> getKickedOffline() {
        return kickedOffline;
    }

    /**
     * 重置被提出状态为 false
     */
    public void resetKickedOfflineState() {
        if (Looper.getMainLooper().getThread().equals(Thread.currentThread())) {
            kickedOffline.setValue(false);
        } else {
            kickedOffline.postValue(false);
        }
    }

    /**
     * 发送图片消息
     *
     * @param conversationType
     * @param targetId
     * @param imageList
     * @param origin
     */
    public void sendImageMessage(Conversation.ConversationType conversationType, String targetId, List<Uri> imageList, boolean origin) {
        SendImageManager.getInstance().sendImages(conversationType, targetId, imageList, origin);
    }


    /**
     * 记录最新的会话信息
     *
     * @param targetId
     * @param conversationType
     */
    public void setLastConversationRecord(String targetId, Conversation.ConversationType conversationType) {
        ConversationRecord record = new ConversationRecord();
        record.targetId = targetId;
        record.conversationType = conversationType;
        lastConversationRecord = record;
    }

    /**
     * 清除最后的会话信息
     */
    public void clearConversationRecord(String targetId) {
        if (lastConversationRecord != null && lastConversationRecord.targetId.equals(targetId)) {
            lastConversationRecord = null;
        }
    }

    /**
     * 获取最后的会话信息
     *
     * @return
     */
    public ConversationRecord getLastConversationRecord() {
        return lastConversationRecord;
    }

}


