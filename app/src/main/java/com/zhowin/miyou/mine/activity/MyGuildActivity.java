package com.zhowin.miyou.mine.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityMyGuildBinding;

/**
 * 我的公会
 */
public class MyGuildActivity extends BaseBindActivity<ActivityMyGuildBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_my_guild;
    }

    @Override
    public void initView() {
        setOnClick(R.id.ivBackReturn);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBackReturn:
                ActivityManager.getAppInstance().finishActivity();
                break;
        }
    }
}