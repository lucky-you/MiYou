package com.zhowin.miyou.mine.dialog;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.zhowin.base_library.base.BaseDialogView;
import com.zhowin.miyou.R;

/**
 * author : zho
 * date  ：2020/9/3
 * desc ： 去实名认证的dialog
 */
public class UserVerifiedDialog extends BaseDialogView {
    public UserVerifiedDialog(@NonNull Context context) {
        super(context);
    }

    public interface OnVerifiedButtonClickListener {

        void onGoToVerified();
    }

    private OnVerifiedButtonClickListener onVerifiedButtonClickListener;

    public void setOnVerifiedButtonClickListener(OnVerifiedButtonClickListener onVerifiedButtonClickListener) {
        this.onVerifiedButtonClickListener = onVerifiedButtonClickListener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_user_verified_dailog;
    }

    @Override
    public void initView() {
        get(R.id.tvCancel).setOnClickListener(this::onViewClick);
        get(R.id.tvVerified).setOnClickListener(this::onViewClick);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tvCancel:
                break;
            case R.id.tvVerified:
                if (onVerifiedButtonClickListener != null) {
                    onVerifiedButtonClickListener.onGoToVerified();
                }
                break;
        }
        dismiss();
    }
}
