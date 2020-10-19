package com.zhowin.miyou.message.activity;


import android.net.Uri;
import android.util.Log;

import androidx.fragment.app.FragmentTransaction;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityIMChatBinding;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

/**
 * 聊天的activity
 */
public class IMChatActivity extends BaseBindActivity<ActivityIMChatBinding> implements RongIM.UserInfoProvider,
        RongIMClient.ConnectionStatusListener {

    private String userName, mTargetId;


    @Override
    public int getLayoutId() {
        return R.layout.activity_i_m_chat;
    }

    @Override
    public void initView() {

        RongIM.setUserInfoProvider(this, true);
        RongIM.setConnectionStatusListener(this); //链接状态的监听
        userName = getIntent().getData().getQueryParameter("title");
        mTargetId = getIntent().getData().getQueryParameter("targetId");
//        Log.d("my", "userName=" + userName + "\n" + "mTargetId=" + mTargetId + "\n" + "classType=" + classType);
    }

    @Override
    public void initData() {
        ConversationListFragment chatConversationFragment = new ConversationListFragment();
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation")
                .appendPath(Conversation.ConversationType.PRIVATE.getName().toLowerCase())
                .appendQueryParameter("targetId", mTargetId)
                .build();
        chatConversationFragment.setUri(uri);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.rong_content, chatConversationFragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public UserInfo getUserInfo(String s) {
        return null;
    }

    @Override
    public void onChanged(ConnectionStatus connectionStatus) {

    }
}
