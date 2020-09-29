package com.zhowin.miyou.recommend.activity;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityChatRoomBinding;
import com.zhowin.miyou.recommend.dialog.SendGiftDialogFragment;

/**
 * 聊天室
 */
public class ChatRoomActivity extends BaseBindActivity<ActivityChatRoomBinding> {

    private int roomId;

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
        roomId=getIntent().getIntExtra(ConstantValue.ID,-1);
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
