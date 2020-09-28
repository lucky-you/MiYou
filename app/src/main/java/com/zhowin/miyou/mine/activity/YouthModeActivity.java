package com.zhowin.miyou.mine.activity;

import android.view.View;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityYouthModeBindingImpl;

/**
 * 青少年模式
 */
public class YouthModeActivity extends BaseBindActivity<ActivityYouthModeBindingImpl> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_youth_mode;
    }

    @Override
    public void initView() {
        setOnClick(R.id.tvTurnOnYouthMode);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvTurnOnYouthMode:
                startActivity(YouthModeSetPasswordActivity.class);
                break;
        }
    }
}
