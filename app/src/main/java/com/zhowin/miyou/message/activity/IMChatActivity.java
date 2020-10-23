package com.zhowin.miyou.message.activity;


import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.FragmentTransaction;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityIMChatBinding;
import com.zhowin.miyou.rongIM.IMManager;

import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.model.Conversation;

/**
 * 聊天的activity
 */
public class IMChatActivity extends BaseBindActivity<ActivityIMChatBinding> {

    private String userName, mTargetId;
    private Conversation.ConversationType mConversationType; //会话类型

    @Override
    public int getLayoutId() {
        return R.layout.activity_i_m_chat;
    }

    @Override
    public void initView() {
        // 没有intent 的则直接返回
        Intent intent = getIntent();
        if (intent == null || intent.getData() == null) {
            finish();
            return;
        }
        userName = getIntent().getData().getQueryParameter("title");
        mTargetId = getIntent().getData().getQueryParameter("targetId");
        mConversationType = Conversation.ConversationType.valueOf(intent.getData().getLastPathSegment().toUpperCase());
        Log.d("xy", "userName=" + userName + "\n" + "mTargetId=" + mTargetId);
        mBinding.titleView.setTitle(userName);
    }

    @Override
    public void initData() {
        ConversationFragment fragment = new ConversationFragment();
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation")
                .appendPath(mConversationType.getName().toLowerCase())
                .appendQueryParameter("targetId", mTargetId)
                .build();
        fragment.setUri(uri);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.conversation, fragment);
        transaction.commit();
    }

    @Override
    public void initListener() {
        mBinding.titleView.getRightImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
