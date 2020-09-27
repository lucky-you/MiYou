package com.zhowin.miyou.login.activity;


import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.base_library.utils.PhoneUtils;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityForgetPasswordBinding;
import com.zhowin.miyou.http.HttpRequest;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * 忘记密码
 */
public class ForgetPasswordActivity extends BaseBindActivity<ActivityForgetPasswordBinding> {


    private Disposable mdDisposable;
    private boolean isShowPassword;

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget_password;
    }

    @Override
    public void initView() {
        setOnClick(R.id.tvGetVerificationCode, R.id.ivPasswordClose, R.id.tvDetermine);
    }

    @Override
    public void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvGetVerificationCode:
                getMobileCode();
                break;
            case R.id.ivPasswordClose:
                isShowPassword = !isShowPassword;
                setShowPassword();
                break;
            case R.id.tvDetermine:
                resetPassword();
                break;
        }
    }

    /**
     * 充值密码
     */
    private void resetPassword() {
        String phoneNumber = mBinding.editMobileNumber.getText().toString().trim();
        if (!PhoneUtils.checkPhone(phoneNumber, true)) {
            return;
        }
        String captchaCode = mBinding.editVerificationCode.getText().toString().trim();
        if (TextUtils.isEmpty(captchaCode)) {
            ToastUtils.showToast("请输入验证码");
            return;
        }
        String password = mBinding.editPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showToast("请输入新密码");
            return;
        }
        if (password.length() < 6) {
            ToastUtils.showToast("新密码长度不能少于6位");
            return;
        }
        showLoadDialog();
        HttpRequest.forgetPassword(this, phoneNumber, captchaCode, password, new HttpCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                dismissLoadDialog();
                startActivity(LoginActivity.class);
                ActivityManager.getAppInstance().finishActivity();
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
            }
        });
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

    /**
     * 获取短信验证码
     */
    private void getMobileCode() {
        String phoneNumber = mBinding.editMobileNumber.getText().toString().trim();
        if (!PhoneUtils.checkPhone(phoneNumber, true)) {
            return;
        }
        getVerificationCode(phoneNumber);
        countdownTime();
    }

    /**
     * 获取验证码
     */
    private void getVerificationCode(String phoneNumber) {
        showLoadDialog();
        HttpRequest.getVerificationCode(this, phoneNumber, new HttpCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                dismissLoadDialog();
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
            }
        });
    }

    /**
     * 倒计时
     */
    private void countdownTime() {
        final int count = 60;//倒计时60秒
        mdDisposable = Flowable.intervalRange(0, count, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mBinding.tvGetVerificationCode.setEnabled(false);
                        int countdownNumber = (int) (count - aLong);
                        String showText = countdownNumber + "s";
                        mBinding.tvGetVerificationCode.setText(showText);
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        //倒计时完毕置为可点击状态
                        mBinding.tvGetVerificationCode.setEnabled(true);
                        mBinding.tvGetVerificationCode.setText("再次获取");
                    }
                })
                .subscribe();
    }


    @Override
    protected void onDestroy() {
        if (mdDisposable != null) {
            mdDisposable.dispose();
        }
        super.onDestroy();
    }
}
