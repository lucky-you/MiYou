package com.zhowin.miyou.mine.activity;


import android.view.View;

import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindActivity;
import com.zhowin.miyou.databinding.ActivityHelpOrFeedbackBinding;

/**
 * 帮助与反馈
 */
public class HelpOrFeedbackActivity extends BaseBindActivity<ActivityHelpOrFeedbackBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_help_or_feedback;
    }

    @Override
    public void initView() {
        setOnClick(R.id.clCZGMLayout, R.id.clTXXZLayout, R.id.clGHJSLayout,
                R.id.clSQGZLayout, R.id.clXXFBLayout, R.id.clXWGFLayout);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clCZGMLayout:
                break;
            case R.id.clTXXZLayout:
                break;
            case R.id.clGHJSLayout:
                break;
            case R.id.clSQGZLayout:
                break;
            case R.id.clXXFBLayout:
                break;
            case R.id.clXWGFLayout:
                break;
        }
    }
}
