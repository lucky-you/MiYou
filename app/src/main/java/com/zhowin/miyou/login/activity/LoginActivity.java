package com.zhowin.miyou.login.activity;


import android.graphics.Typeface;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.gyf.immersionbar.ImmersionBar;
import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.utils.PhoneUtils;
import com.zhowin.base_library.utils.SetDrawableResourceHelper;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityLoginBinding;

/**
 * 登录
 */
public class LoginActivity extends BaseBindActivity<ActivityLoginBinding> {


    private boolean isShowPassword;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        setOnClick(R.id.rlSMSLoginLayout, R.id.rlPasswordLayout, R.id.tvForgetPassword,
                R.id.tvGetVerificationCode, R.id.tvLogin, R.id.tvOneClickLogin,
                R.id.ivPasswordClose, R.id.ivWeChatLogin, R.id.ivQQLogin);
    }

    @Override
    public void initData() {
        changeUiOfClickStatus(true);
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
            case R.id.rlSMSLoginLayout:
                changeUiOfClickStatus(true);
                break;
            case R.id.rlPasswordLayout:
                changeUiOfClickStatus(false);
                break;
            case R.id.tvForgetPassword:
                startActivity(ForgetPasswordActivity.class);
                break;
            case R.id.tvGetVerificationCode:
                break;
            case R.id.tvLogin:
                clickLogin();
                break;
            case R.id.tvOneClickLogin:
                startActivity(EditNickNameActivity.class);
                break;
            case R.id.ivPasswordClose:
                isShowPassword = !isShowPassword;
                setShowPassword();
                break;
            case R.id.ivWeChatLogin:
                startActivity(BindPhoneActivity.class);
                break;
            case R.id.ivQQLogin:
                break;
        }
    }

    private void clickLogin() {
        String phoneNumber = mBinding.editMobileNumber.getText().toString().trim();
        if (!PhoneUtils.checkPhone(phoneNumber, true)) {
            return;
        }

    }


    private void setShowPassword() {
        if (isShowPassword) {
            mBinding.editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            mBinding.ivPasswordClose.setImageResource(R.mipmap.login_eyeopen_icon);
        } else {
            mBinding.editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            mBinding.ivPasswordClose.setImageResource(R.mipmap.login_eyeclose_icon);
        }
        mBinding.editPassword.setSelection(mBinding.editPassword.getText().toString().trim().length());
    }


    private void changeUiOfClickStatus(boolean isClickSMSLoginLayout) {
        if (isClickSMSLoginLayout) {
            mBinding.tvSMSLogin.setTextColor(getBaseColor(R.color.color_333));
            mBinding.ivSMSBottomView.setVisibility(View.VISIBLE);

            mBinding.tvPasswordLogin.setTextColor(getBaseColor(R.color.color_666));
            mBinding.ivPasswordBottomView.setVisibility(View.GONE);

            mBinding.llPhoneLayout.setVisibility(View.VISIBLE);
            mBinding.llPasswordLayout.setVisibility(View.GONE);
            mBinding.tvForgetPassword.setVisibility(View.INVISIBLE);

        } else {
            mBinding.tvPasswordLogin.setTextColor(getBaseColor(R.color.color_333));
            mBinding.ivPasswordBottomView.setVisibility(View.VISIBLE);

            mBinding.tvSMSLogin.setTextColor(getBaseColor(R.color.color_666));
            mBinding.ivSMSBottomView.setVisibility(View.GONE);

            mBinding.llPhoneLayout.setVisibility(View.GONE);
            mBinding.llPasswordLayout.setVisibility(View.VISIBLE);
            mBinding.tvForgetPassword.setVisibility(View.VISIBLE);

        }
    }

}
