package com.zhowin.miyou.message.fragment;

import android.net.Uri;
import android.view.View;

import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.base.BaseApplication;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindFragment;
import com.zhowin.miyou.databinding.MessageFragmentLayoutBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.message.activity.SystemMessageActivity;
import com.zhowin.miyou.message.adapter.ConversationListAdapterEx;
import com.zhowin.miyou.recommend.activity.RoomSearchActivity;
import com.zhowin.miyou.rongIM.IMManager;

import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * 消息
 */
public class MessageFragment extends BaseBindFragment<MessageFragmentLayoutBinding> {


    private boolean isNotDisturb; //免打扰

    @Override
    public int getLayoutId() {
        return R.layout.message_fragment_layout;
    }

    @Override
    public void initView() {
        setOnClick(R.id.tvSearchFriend, R.id.ivCheckCloseMessage, R.id.llSystemLayout, R.id.llAnnouncementLayout, R.id.llGuildMessageLayout);

    }

    @Override
    public void initData() {
        loadIMConversation();
    }

    @Override
    public void initListener() {
        mBinding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mBinding.refreshLayout.setRefreshing(false);
            }
        });
    }

    private void loadIMConversation() {
        ConversationListFragment conversationListFragment = new ConversationListFragment();
        ConversationListAdapterEx chatMessageListAdapter = new ConversationListAdapterEx(mContext);
        Uri IMRongUri = Uri.parse("rong://" + BaseApplication.getInstance().getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")
                .build();
        conversationListFragment.setAdapter(chatMessageListAdapter);
        conversationListFragment.setUri(IMRongUri);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.conversationlist, conversationListFragment);
        transaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        getConversationList();
    }

    /**
     * 获取会话列表
     */
    private void getConversationList() {
        RongIM.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
            @Override
            public void onSuccess(List<Conversation> conversations) {
                if (conversations != null && conversations.size() > 0) {
                    for (Conversation conversation : conversations) {
                        getOtherUserInfoMessage(conversation.getTargetId());
                    }
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {

            }
        }, Conversation.ConversationType.PRIVATE);
    }

    /**
     * 根据融云id反查用户信息，刷新用户数据
     */
    private void getOtherUserInfoMessage(String userID) {
        HttpRequest.getOtherUserInfoMessage(this, Integer.parseInt(userID), new HttpCallBack<UserInfo>() {
            @Override
            public void onSuccess(UserInfo userInfo) {
                if (userInfo != null) {
                    IMManager.getInstance().updateUserInfoCache(String.valueOf(userInfo.getUserId()), userInfo.getAvatar(), Uri.parse(userInfo.getProfilePictureKey()));
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSearchFriend:
                RoomSearchActivity.start(mContext, 2);
                break;
            case R.id.ivCheckCloseMessage:
                setNotDisturb();
                break;
            case R.id.llSystemLayout:
                SystemMessageActivity.start(mContext, 1);
                break;
            case R.id.llAnnouncementLayout:
                SystemMessageActivity.start(mContext, 2);
                break;
            case R.id.llGuildMessageLayout:
                SystemMessageActivity.start(mContext, 3);
                break;
        }
    }

    /**
     * 设置免打扰
     */
    private void setNotDisturb() {
        if (isNotDisturb) {
            IMManager.getInstance().removeNotificationQuietHours();
            mBinding.ivCheckCloseMessage.setImageResource(R.drawable.message_switch1_box);
            isNotDisturb = !isNotDisturb;
        } else {
            IMManager.getInstance().setRemindStatus(true);
            mBinding.ivCheckCloseMessage.setImageResource(R.drawable.message_switch2_box);
            isNotDisturb = !isNotDisturb;
        }
    }

}
