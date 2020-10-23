package com.zhowin.miyou.mine.activity;


import android.view.View;

import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.utils.PhoneUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindActivity;
import com.zhowin.miyou.databinding.ActivityAccountSecurityBinding;

/**
 * 账号与安全
 */
public class AccountSecurityActivity extends BaseBindActivity<ActivityAccountSecurityBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_account_security;
    }

    @Override
    public void initView() {
        setOnClick(R.id.clChangePhoneNumber, R.id.clPasswordManagement, R.id.clAccountLogout);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mBinding.tvUserMobile.setText(PhoneUtils.hitCenterMobilNumber(UserInfo.getUserInfo().getMobileNum()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clChangePhoneNumber:
                startActivity(ChangeMobilePhoneActivity.class);
                break;
            case R.id.clPasswordManagement:
                startActivity(SetPasswordActivity.class);
                break;
            case R.id.clAccountLogout:
                startActivity(PermanentLogoutActivity.class);
                break;

        }
    }
}
