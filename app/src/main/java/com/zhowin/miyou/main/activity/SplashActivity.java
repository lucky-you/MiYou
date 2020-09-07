package com.zhowin.miyou.main.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.base_library.utils.SPUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivitySplashBinding;
import com.zhowin.miyou.login.activity.LoginActivity;

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
        mBinding.ivSplashImage.postDelayed(new Runnable() {
            @Override
            public void run() {
//                if (!SPUtils.getBoolean(ConstantValue.MAIN, false)) {
//                    startActivity(GuidePageActivity.class);
//                    finish();
//                    return;
//                }
                UserInfo userInfo = UserInfo.getUserInfo();
                if (TextUtils.isEmpty(userInfo.getToken())) {
                    startActivity(LoginActivity.class);
                } else {
                    startActivity(MainActivity.class);
                }
                finish();
            }
        }, 2000);
    }

    @Override
    public void initData() {

    }
}
