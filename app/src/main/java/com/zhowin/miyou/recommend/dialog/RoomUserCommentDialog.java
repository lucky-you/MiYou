package com.zhowin.miyou.recommend.dialog;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;

import com.zhowin.base_library.base.BaseApplication;
import com.zhowin.base_library.base.BaseDialogFragment;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;

import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.model.Conversation;

/**
 * author : zho
 * date  ：2020/10/27
 * desc ：房间用户评论
 */
public class RoomUserCommentDialog extends BaseDialogFragment {

    private String userName;
    private int mTargetId;
    private TextView tvUserNickName;

    public static RoomUserCommentDialog newInstance(int userID, String userName) {
        RoomUserCommentDialog userCommentDialog = new RoomUserCommentDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.ID, userID);
        bundle.putString(ConstantValue.NAME, userName);
        userCommentDialog.setArguments(bundle);
        return userCommentDialog;
    }


    @Override
    public int getLayoutId() {
        return R.layout.include_room_user_comment_dialog_layuout;
    }

    @Override
    public void initView() {
        get(R.id.ivCloseDialog).setOnClickListener(this::onViewClick);
        tvUserNickName = get(R.id.tvUserNickName);

    }

    @Override
    public void initData() {
        userName = getArguments().getString(ConstantValue.NAME);
        mTargetId = getArguments().getInt(ConstantValue.ID);
        Log.d("xy", "userName=" + userName + "\n" + "mTargetId=" + mTargetId);
        tvUserNickName.setText(userName);

    }

    @Override
    public void onResume() {
        super.onResume();
//        ConversationFragment fragment = new ConversationFragment();
//        Uri uri = Uri.parse("rong://" + BaseApplication.getInstance().getPackageName()).buildUpon()
//                .appendPath("conversation")
//                .appendPath(Conversation.ConversationType.CHATROOM.getName().toLowerCase())
//                .appendQueryParameter("targetId", String.valueOf(mTargetId))
//                .build();
//        fragment.setUri(uri);
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        transaction.add(R.id.conversation, fragment);
//        transaction.commit();
    }

    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ivCloseDialog:
                dismiss();
                break;
        }
    }
}
