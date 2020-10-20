package com.zhowin.miyou.message.fragment;

import android.net.Uri;
import android.view.View;

import androidx.fragment.app.FragmentTransaction;

import com.zhowin.base_library.base.BaseApplication;
import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.MessageFragmentLayoutBinding;
import com.zhowin.miyou.message.activity.SystemMessageActivity;
import com.zhowin.miyou.message.adapter.ConversationListAdapterEx;
import com.zhowin.miyou.recommend.activity.RoomSearchActivity;

import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

/**
 * 消息
 */
public class MessageFragment extends BaseBindFragment<MessageFragmentLayoutBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.message_fragment_layout;
    }

    @Override
    public void initView() {
        setOnClick(R.id.tvSearchFriend, R.id.llSystemLayout, R.id.llAnnouncementLayout, R.id.llGuildMessageLayout);

    }

    @Override
    public void initData() {
//        loadIMConversation();
    }

    private void loadIMConversation() {
        ConversationListFragment conversationListFragment = new ConversationListFragment();
        ConversationListAdapterEx chatMessageListAdapter = new ConversationListAdapterEx(mContext);
        Uri IMRongUri = Uri.parse("rong://" + BaseApplication.getInstance().getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                .build();
        conversationListFragment.setAdapter(chatMessageListAdapter);
        conversationListFragment.setUri(IMRongUri);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.conversationlist, conversationListFragment);
        transaction.commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSearchFriend:
                RoomSearchActivity.start(mContext, 2);
                break;
            case R.id.llSystemLayout:
                SystemMessageActivity.start(mContext,1);
                break;
            case R.id.llAnnouncementLayout:
                SystemMessageActivity.start(mContext,2);
                break;
            case R.id.llGuildMessageLayout:
                SystemMessageActivity.start(mContext,3);
                break;
        }
    }
}
