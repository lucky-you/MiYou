package com.zhowin.miyou.mine.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.googlecode.mp4parser.authoring.Edit;
import com.zhowin.base_library.base.BaseDialogView;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;

/**
 * author : zho
 * date  ：2020/9/17
 * desc ： 解锁房间的dialog
 */
public class UnlockRoomDialog extends BaseDialogView {

    private OnUnLockRoomListener onUnLockRoomListener;
    private EditText editRoomPassword;

    public UnlockRoomDialog(@NonNull Context context) {
        super(context);
    }

    public void setOnUnLockRoomListener(OnUnLockRoomListener onUnLockRoomListener) {
        this.onUnLockRoomListener = onUnLockRoomListener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_unlock_room_dialog;
    }

    @Override
    public void initView() {
        editRoomPassword = get(R.id.editRoomPassword);
        get(R.id.tvCancel).setOnClickListener(this::onViewClick);
        get(R.id.tvDetermine).setOnClickListener(this::onViewClick);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tvCancel:
                break;
            case R.id.tvDetermine:
                String password = editRoomPassword.getText().toString().trim();
                if (TextUtils.isEmpty(password)) {
                    ToastUtils.showToast("房间密码不能为空哦");
                    return;
                }
                if (onUnLockRoomListener != null) {
                    onUnLockRoomListener.onUnLockRoom(password);
                }
                break;
        }
        dismiss();
    }


    public interface OnUnLockRoomListener {

        void onUnLockRoom(String password);
    }
}
