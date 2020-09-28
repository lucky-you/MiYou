package com.zhowin.miyou.mine.activity;

import android.text.TextUtils;
import android.view.View;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityYouthModeSetPasswordBinding;
import com.zhowin.miyou.main.view.VerificationCodeView;

/**
 * 青少年模式设置密码
 */
public class YouthModeSetPasswordActivity extends BaseBindActivity<ActivityYouthModeSetPasswordBinding> {

    private boolean isConfirmPassword;
    private String youthModePassword;

    @Override
    public int getLayoutId() {
        return R.layout.activity_youth_mode_set_password;
    }

    @Override
    public void initView() {
        setOnClick(R.id.tvNextStepSet);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvNextStepSet:
                if (!isConfirmPassword) {
                    if (TextUtils.isEmpty(youthModePassword)) {
                        ToastUtils.showToast("请先设置密码哦");
                        return;
                    }
                    isConfirmPassword = true;
                    setPasswordView();
                } else {

                }
                break;
        }
    }

    private void setPasswordView() {
        mBinding.tvSetHitMessage.setVisibility(isConfirmPassword ? View.INVISIBLE : View.GONE);
        mBinding.tvSetPassword.setText(isConfirmPassword ? "确认密码" : "设置密码");
        mBinding.tvNextStepSet.setText(isConfirmPassword ? "确认开启" : "下一步");

    }

    @Override
    public void initListener() {
        mBinding.verificationCodeView.setInputCompleteListener(new VerificationCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                youthModePassword = mBinding.verificationCodeView.getInputContent();
            }

            @Override
            public void deleteContent() {

            }
        });
    }
}