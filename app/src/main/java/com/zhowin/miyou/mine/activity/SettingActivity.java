package com.zhowin.miyou.mine.activity;

import android.app.Dialog;
import android.view.View;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.callback.OnCenterHitMessageListener;
import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.base_library.view.CenterHitMessageDialog;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivitySettingBinding;

/**
 * 设置
 */
public class SettingActivity extends BaseBindActivity<ActivitySettingBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        setOnClick(R.id.clAccountAndSecurity, R.id.clUserAgreement, R.id.clCommunityNorms,
                R.id.clContactUs, R.id.clBlackList, R.id.clPrivacySetting, R.id.clCheckVersion,
                R.id.clClearCache, R.id.clAboutUs, R.id.tvOutLogin
        );

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clAccountAndSecurity:
                startActivity(AccountSecurityActivity.class);
                break;
            case R.id.clUserAgreement:
                break;
            case R.id.clCommunityNorms:
                break;
            case R.id.clContactUs:
                startActivity(ContactUsActivity.class);
                break;
            case R.id.clBlackList:
                startActivity(BlackListActivity.class);
                break;
            case R.id.clPrivacySetting:
                startActivity(PrivacySettingActivity.class);
                break;
            case R.id.clCheckVersion:
                break;
            case R.id.clClearCache:
                break;
            case R.id.clAboutUs:
                break;
            case R.id.tvOutLogin:
                showOutLoginDialog();
                break;
        }
    }

    private void showOutLoginDialog() {
        String title = "确定要退出吗?";
        new CenterHitMessageDialog(mContext, title, new OnCenterHitMessageListener() {
            @Override
            public void onNegativeClick(Dialog dialog) {

            }

            @Override
            public void onPositiveClick(Dialog dialog) {
            }
        }).show();
    }
}