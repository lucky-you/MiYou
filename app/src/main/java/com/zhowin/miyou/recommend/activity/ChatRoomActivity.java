package com.zhowin.miyou.recommend.activity;


import android.view.View;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityChatRoomBinding;
import com.zhowin.miyou.recommend.dialog.SendGiftDialogFragment;

/**
 * 聊天室
 */
public class ChatRoomActivity extends BaseBindActivity<ActivityChatRoomBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_chat_room;
    }

    @Override
    public void initView() {
        setOnClick(R.id.ivBackReturn, R.id.llReleaseBroadcastLayout, R.id.ivUserList,
                R.id.ivGiftPhoto);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBackReturn:
                ActivityManager.getAppInstance().finishActivity();
                break;
            case R.id.llReleaseBroadcastLayout:
                startActivity(BroadcastDatingActivity.class);
                break;
            case R.id.ivUserList:
                startActivity(RoomListActivity.class);
                break;
            case R.id.ivGiftPhoto:
                showSendGiftDialog();
                break;
        }
    }

    private void showSendGiftDialog() {
        SendGiftDialogFragment sendGiftDialogFragment = new SendGiftDialogFragment();
        sendGiftDialogFragment.show(getSupportFragmentManager(), "gift");
    }
}
