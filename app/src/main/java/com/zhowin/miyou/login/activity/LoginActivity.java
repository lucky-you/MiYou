package com.zhowin.miyou.login.activity;


import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.utils.BarUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseBindActivity<ActivityLoginBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        setOnClick(R.id.tvForgetPassword, R.id.tvGetVerificationCode, R.id.tvLogin, R.id.tvOneClickLogin);
    }

    @Override
    public void initData() {

    }


    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .titleBar(mBinding.titleView, false)
                .transparentBar()
                .init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvForgetPassword:
//                startActivity(ForgetPasswordActivity.class);
                break;
            case R.id.tvGetVerificationCode:
                startActivity(BindPhoneActivity.class);
                break;
            case R.id.tvLogin:
                startActivity(ForgetPasswordActivity.class);
                break;
            case R.id.tvOneClickLogin:
                startActivity(EditNickNameActivity.class);
                break;
        }
    }
}
