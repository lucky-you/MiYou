package com.zhowin.miyou.mine.activity;


import android.text.TextUtils;
import android.view.View;

import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.base_library.utils.PhoneUtils;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindActivity;
import com.zhowin.miyou.databinding.ActivityLogoutAccountBinding;
import com.zhowin.miyou.http.HttpRequest;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * 永久注销账号
 */
public class LogoutAccountActivity extends BaseBindActivity<ActivityLogoutAccountBinding> {


    private Disposable mdDisposable;
    private String userMobile;

    @Override
    public int getLayoutId() {
        return R.layout.activity_logout_account;
    }

    @Override
    public void initView() {
        setOnClick(R.id.tvGetVerificationCode, R.id.tvApplyLogout);

    }

    @Override
    public void initData() {
        UserInfo userInfo = UserInfo.getUserInfo();
        if (!TextUtils.isEmpty(userInfo.getMobileNum())) {
            userMobile = userInfo.getMobileNum();
            mBinding.tvOriginalMobile.setText("当前绑定手机号：" + PhoneUtils.hitCenterMobilNumber(userMobile));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvGetVerificationCode:
                getVerificationCode(userMobile);
                countdownTime();
                break;

            case R.id.tvApplyLogout:
                String captchaCode = mBinding.editVerificationCode.getText().toString().trim();
                if (TextUtils.isEmpty(captchaCode)) {
                    ToastUtils.showToast("请输入验证码");
                    return;
                }
                longTimeOutLogin(captchaCode);
                break;
        }
    }

    /**
     * 注销
     */
    private void longTimeOutLogin(String captchaCode) {
        showLoadDialog();
        HttpRequest.longTimeOutLogin(this, userMobile, captchaCode, new HttpCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                dismissLoadDialog();
                ActivityManager.getAppInstance().AppExit(mContext);
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);
            }
        });

    }

    /**
     * 获取验证码
     */
    private void getVerificationCode(String phoneNumber) {
        showLoadDialog();
        HttpRequest.getVerificationCode(this, 5, phoneNumber, new HttpCallBack<Object>() {
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
