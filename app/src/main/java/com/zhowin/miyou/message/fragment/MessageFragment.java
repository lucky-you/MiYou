package com.zhowin.miyou.message.fragment;

import android.net.Uri;
import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.zhowin.base_library.base.BaseApplication;
import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.MessageFragmentLayoutBinding;
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
        setOnClick(R.id.tvSearchFriend);
//        loadIMConversation();
    }

    @Override
    public void initData() {
        loadIMFragment();
    }

    private void loadIMFragment() {
        ConversationListFragment conversationListFragment = new ConversationListFragment();
        // 此处设置 Uri. 通过 appendQueryParameter 去设置所要支持的会话类型. 例如
        // .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(),"false")
        // 表示支持单聊会话, false 表示不聚合显示, true 则为聚合显示
        Uri uri = Uri.parse("rong://" +
                getActivity().getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                .build();

        conversationListFragment.setUri(uri);
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.rong_content, conversationListFragment);
        transaction.commit();

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
        transaction.add(R.id.rong_content, conversationListFragment);
        transaction.commitAllowingStateLoss();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSearchFriend:
                RoomSearchActivity.start(mContext, 2);
                break;
        }
    }
}
