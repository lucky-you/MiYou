package com.zhowin.miyou.main.activity;

import android.text.TextUtils;
import android.util.Log;

import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindActivity;
import com.zhowin.miyou.databinding.ActivitySplashBinding;
import com.zhowin.miyou.login.activity.LoginActivity;
import com.zhowin.miyou.rongIM.IMManager;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * 引导页
 */
public class SplashActivity extends BaseBindActivity<ActivitySplashBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        UserInfo userInfo = UserInfo.getUserInfo();
        if (TextUtils.isEmpty(userInfo.getToken())) {
            startActivity(LoginActivity.class);
            ActivityManager.getAppInstance().finishActivity();
        } else {
            connectIM(userInfo);
        }
    }

    @Override
    public void initData() {

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

            }
        });
    }

}
