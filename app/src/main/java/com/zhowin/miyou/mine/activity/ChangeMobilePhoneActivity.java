package com.zhowin.miyou.mine.activity;


import android.text.TextUtils;
import android.view.View;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.base_library.utils.PhoneUtils;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityChangeMobilePhoneBinding;
import com.zhowin.miyou.http.HttpRequest;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * 更换绑定手机号码
 */
public class ChangeMobilePhoneActivity extends BaseBindActivity<ActivityChangeMobilePhoneBinding> {


    private Disposable mdDisposable;
    private boolean isDetermineBinding;
    private String verifyCode;

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_mobile_phone;
    }

    @Override
    public void initView() {
        setOnClick(R.id.tvGetVerificationCode, R.id.tvNextStep);

    }

    @Override
    public void initData() {
        UserInfo userInfo = UserInfo.getUserInfo();
        if (!TextUtils.isEmpty(userInfo.getMobileNum())) {
            mBinding.tvOriginalMobile.setText("当前绑定手机号：" + PhoneUtils.hitCenterMobilNumber(userInfo.getMobileNum()));
        }
        setViewDataOfStatus();
    }

    private void setViewDataOfStatus() {
        mBinding.tvHitMessageText.setText(isDetermineBinding ? "更换后，下次必须使用新的绑定手机号进行登录" : "请先使用绑定手机号进行验证后，再进行换绑");
        mBinding.editMobilePhone.setHint(isDetermineBinding ? "请输入新手机号" : "请输入手机号");
        mBinding.tvNextStep.setText(isDetermineBinding ? "更换绑定" : "下一步");
        mBinding.editMobilePhone.setText("");
        mBinding.editVerificationCode.setText("");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvGetVerificationCode:
                getMobileCode();
                break;
            case R.id.tvNextStep:
                if (isDetermineBinding) {
                    String phoneNumber = mBinding.editMobilePhone.getText().toString().trim();
                    if (!PhoneUtils.checkPhone(phoneNumber, true)) {
                        return;
                    }
                    String captchaCode = mBinding.editVerificationCode.getText().toString().trim();
                    if (TextUtils.isEmpty(captchaCode)) {
                        ToastUtils.showToast("请输入验证码");
                        return;
                    }
                    verifyChangeMobileNumber(captchaCode, phoneNumber);
                } else {
                    String phoneNumber = mBinding.editMobilePhone.getText().toString().trim();
                    if (!PhoneUtils.checkPhone(phoneNumber, true)) {
                        return;
                    }
                    String captchaCode = mBinding.editVerificationCode.getText().toString().trim();
                    if (TextUtils.isEmpty(captchaCode)) {
                        ToastUtils.showToast("请输入验证码");
                        return;
                    }
                    //验证手机号+验证码
                    verifyMobile(phoneNumber, captchaCode);
                }
                break;
        }
    }

    /**
     * 验证手机号码
     */
    private void verifyMobile(String phoneNumber, String captchaCode) {
        showLoadDialog();
        HttpRequest.verifyMobileNumber(this, phoneNumber, captchaCode, new HttpCallBack<String>() {
            @Override
            public void onSuccess(String code) {
                dismissLoadDialog();
                if (!TextUtils.isEmpty(code)) {
                    verifyCode = code;
                    isDetermineBinding = true;
                    setViewDataOfStatus();
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);
            }
        });
    }

    /**
     * 换绑手机号码
     */
    private void verifyChangeMobileNumber(String phoneNumber, String captchaCode) {
        showLoadDialog();
        HttpRequest.verifyChangeMobileNumber(this, captchaCode, phoneNumber, verifyCode, new HttpCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                dismissLoadDialog();
                getUserInfo();
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);
            }
        });
    }

    private void getUserInfo() {
        HttpRequest.getUserInfoMessage(this, new HttpCallBack<UserInfo>() {
            @Override
            public void onSuccess(UserInfo userInfo) {
                if (userInfo != null) {
                    UserInfo.setUserInfo(userInfo);
                    ActivityManager.getAppInstance().finishActivity();
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                ToastUtils.showToast(errorMsg);
            }
        });
    }


    /**
     * 获取短信验证码
     */
    private void getMobileCode() {
        String phoneNumber = mBinding.editMobilePhone.getText().toString().trim();
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
        HttpRequest.getVerificationCode(this, isDetermineBinding ? 4 : 3, phoneNumber, new HttpCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                dismissLoadDialog();
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);
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
                        String showText = countdownNumber + "s后重发";
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
