package com.zhowin.miyou.recommend.dialog;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.googlecode.mp4parser.authoring.Edit;
import com.zhowin.base_library.base.BaseDialogView;
import com.zhowin.base_library.callback.OnTextChangedListener;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.recommend.callback.OnSetRoomPasswordListener;

/**
 * author : zho
 * date  ：2020/10/26
 * desc ： 设置房间密码
 */
public class SetRoomPasswordDialog extends BaseDialogView {


    private EditText editReportReason;
    private TextView tvReasonText;
    private int MAX_NUMBER = 4;
    private String passwordText;
    private OnSetRoomPasswordListener onSetRoomPasswordListener;


    public SetRoomPasswordDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_set_room_password_layout;
    }

    public void setOnSetRoomPasswordListener(OnSetRoomPasswordListener onSetRoomPasswordListener) {
        this.onSetRoomPasswordListener = onSetRoomPasswordListener;
    }

    @Override
    public void initView() {
        editReportReason = get(R.id.editReportReason);
        tvReasonText = get(R.id.tvReasonText);
        get(R.id.tvCancel).setOnClickListener(this::onViewClick);
        get(R.id.tvDetermine).setOnClickListener(this::onViewClick);
    }

    @Override
    public void initData() {
        tvReasonText.setText("0/" + MAX_NUMBER);
        editReportReason.addTextChangedListener(new OnTextChangedListener() {
            @Override
            public void afterTextChanged(Editable editable) {
                passwordText = editable.toString();
                if (!TextUtils.isEmpty(passwordText))
                    if (passwordText.length() <= MAX_NUMBER) {
                        tvReasonText.setText(passwordText.length() + "/" + MAX_NUMBER);
                    }
            }
        });
    }

    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tvCancel:
                dismiss();
                break;
            case R.id.tvDetermine:
                if (TextUtils.isEmpty(passwordText)) {
                    ToastUtils.showToast("请输入房间密码");
                    return;
                }
                if (onSetRoomPasswordListener != null) {
                    onSetRoomPasswordListener.onSetRoomPassword(passwordText);
                    dismiss();
                }
                break;
        }
    }
}
