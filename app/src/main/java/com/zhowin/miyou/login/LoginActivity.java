package com.zhowin.miyou.login;


import android.view.View;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseBindActivity<ActivityLoginBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        setOnClick(R.id.tvForgetPassword);
    }

    @Override
    public void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvForgetPassword:
                startActivity(ForgetPasswordActivity.class);
                break;
        }
    }
}
