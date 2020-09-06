package com.zhowin.miyou.mine.activity;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityYouthModeSetPasswordBinding;
import com.zhowin.miyou.main.view.VerificationCodeView;

/**
 * 青少年模式设置密码
 */
public class YouthModeSetPasswordActivity extends BaseBindActivity<ActivityYouthModeSetPasswordBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_youth_mode_set_password;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mBinding.verificationCodeView.setInputCompleteListener(new VerificationCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {

            }

            @Override
            public void deleteContent() {

            }
        });
    }
}