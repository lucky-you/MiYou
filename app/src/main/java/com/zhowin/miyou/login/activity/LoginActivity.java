package com.zhowin.miyou.login.activity;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.base_library.utils.KeyboardUtils;
import com.zhowin.base_library.utils.PhoneUtils;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindActivity;
import com.zhowin.miyou.databinding.ActivityLoginBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.main.activity.MainActivity;
import com.zhowin.miyou.rongIM.IMManager;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * 登录
 */
public class LoginActivity extends BaseBindActivity<ActivityLoginBinding> {


    private Disposable mdDisposable;
    private boolean isShowPassword, isPhoneAndPasswordLogin;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

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
        ActivityManager.getAppInstance().finishAllButCurrentActivity(LoginActivity.class);
        changeUiOfClickStatus();
    }


    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .titleBar(mBinding.titleView)
                .init();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlSMSLoginLayout:
                isPhoneAndPasswordLogin = false;
                changeUiOfClickStatus();
                break;
            case R.id.rlPasswordLayout:
                isPhoneAndPasswordLogin = true;
                changeUiOfClickStatus();
                break;
            case R.id.tvForgetPassword:
                ForgetPasswordActivity.start(mContext, 1);
                break;
            case R.id.tvGetVerificationCode:
                clickLoginOrGetCode(false);
                break;
            case R.id.tvLogin:
                if (!isPhoneAndPasswordLogin) {
                    clickLoginOrGetCode(true);
                    KeyboardUtils.hideSoftInput(mContext);
                } else {
                    loginMobileAndPassword();
                }
                break;
            case R.id.tvOneClickLogin:
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


    /**
     * 账号 + 密码 登录
     */
    private void loginMobileAndPassword() {
        String userNameId = mBinding.editPhoneNumber.getText().toString().trim();
        if (TextUtils.isEmpty(userNameId)) {
            ToastUtils.showToast("请输入您的账号");
            return;
        }
        String password = mBinding.editPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showToast("请输入密码");
            return;
        }
        if (password.length() < 6) {
            ToastUtils.showToast("密码长度不能少于6位");
            return;
        }
        showLoadDialog();
        HttpRequest.nameAndPasswordLogin(this, userNameId, password, new HttpCallBack<UserInfo>() {
            @Override
            public void onSuccess(UserInfo userInfo) {
                dismissLoadDialog();
                if (userInfo != null) {
                    UserInfo.setUserInfo(userInfo);
                    if (userInfo.isCompleted()) {
                        connectIM(userInfo);
//                        startActivity(MainActivity.class);
                    } else {
                        startActivity(EditNickNameActivity.class);
                    }
                }
                ActivityManager.getAppInstance().finishActivity();
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);
            }
        });
    }


    /**
     * 手机号 + 验证码 登录
     *
     * @param isgLogin 是否是登录
     */
    private void clickLoginOrGetCode(boolean isgLogin) {
        String phoneNumber = mBinding.editMobileNumber.getText().toString().trim();
        if (!PhoneUtils.checkPhone(phoneNumber, true)) {
            return;
        }
        if (!isgLogin) {
            getVerificationCode(phoneNumber);
            countdownTime();
        } else {
            String captchaCode = mBinding.editVerificationCode.getText().toString().trim();
            if (TextUtils.isEmpty(captchaCode)) {
                ToastUtils.showToast("请输入验证码");
                return;
            }
            showLoadDialog();
            HttpRequest.mobileVerificationCodeLogin(this, phoneNumber, captchaCode, new HttpCallBack<UserInfo>() {
                @Override
                public void onSuccess(UserInfo userInfo) {
                    dismissLoadDialog();
                    if (userInfo != null) {
                        UserInfo.setUserInfo(userInfo);
                        if (userInfo.isCompleted()) {
                            connectIM(userInfo);
//                            startActivity(MainActivity.class);
                        } else {
                            startActivity(EditNickNameActivity.class);
                            ActivityManager.getAppInstance().finishActivity();
                        }
                    }
                }

                @Override
                public void onFail(int errorCode, String errorMsg) {
                    ToastUtils.showToast(errorMsg);
                    dismissLoadDialog();
                }
            });
        }
    }

    /**
     * 链接登录融云
     *
     * @param userInfo 用户信息
     */
    private void connectIM(UserInfo userInfo) {
        RongIM.getInstance().disconnect();
        String imToken = userInfo.getRongToken();
        if (TextUtils.isEmpty(imToken)) return;
        RongIM.connect(imToken, 0, new RongIMClient.ConnectCallback() {
            @Override
            public void onSuccess(String s) {
                //保存用户信息
                IMManager.getInstance().setUserCache(s, imToken, userInfo.getAvatar(), userInfo.getProfilePictureKey());
                ToastUtils.showToast(getString(R.string.login_success));
                Log.e("xy", "连接IM Success,userId:" + s);
                startActivity(MainActivity.class);
                ActivityManager.getAppInstance().finishActivity();
            }

            @Override
            public void onError(RongIMClient.ConnectionErrorCode errorCode) {
                Log.e("xy", "连接IM Error:" + errorCode.getValue());
                startActivity(MainActivity.class);
                ActivityManager.getAppInstance().finishActivity();
            }

            @Override
            public void onDatabaseOpened(RongIMClient.DatabaseOpenStatus databaseOpenStatus) {
//                Log.e("xy", "连接IM DatabaseOpened");

            }
        });
    }

    /**
     * 获取验证码
     */
    private void getVerificationCode(String phoneNumber) {
        showLoadDialog();
        HttpRequest.getVerificationCode(this, 0, phoneNumber, new HttpCallBack<Object>() {
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
     * 切换不同登录方式的UI
     */
    private void changeUiOfClickStatus() {
        if (!isPhoneAndPasswordLogin) {
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
//                        SpannableString colorNumberText = SplitUtils.getTextColor(showText, 0, String.valueOf(countdownNumber).length(), getBaseColor(R.color.color_8c86fa));
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
