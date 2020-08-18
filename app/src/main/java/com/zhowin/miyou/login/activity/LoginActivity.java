package com.zhowin.miyou.login.activity;


import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.gyf.immersionbar.ImmersionBar;
import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.utils.SetDrawableResourceHelper;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityLoginBinding;

/**
 * 登录
 */
public class LoginActivity extends BaseBindActivity<ActivityLoginBinding> implements RadioGroup.OnCheckedChangeListener {


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        setOnClick(R.id.tvForgetPassword, R.id.tvGetVerificationCode, R.id.tvLogin, R.id.tvOneClickLogin);
        mBinding.rgLoginButton.setOnCheckedChangeListener(this::onCheckedChanged);
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
                startActivity(ForgetPasswordActivity.class);
                break;
            case R.id.tvGetVerificationCode:
                startActivity(BindPhoneActivity.class);
                break;
            case R.id.tvLogin:
//                startActivity(ForgetPasswordActivity.class);
                break;
            case R.id.tvOneClickLogin:
                startActivity(EditNickNameActivity.class);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rbPhoneLogin:
                break;
            case R.id.rbPasswordLogin:
                break;
        }
    }

    private void setBottomStatusUi(boolean isSelect, RadioButton radioButton) {

//        SetDrawableResourceHelper.setBottomDrawable(mContext,radioButton, isSelect, 0, 0);

    }

}
