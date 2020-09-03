package com.zhowin.miyou.recommend.activity;


import android.content.Context;
import android.content.Intent;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityHomepageBinding;

/**
 * 个人主页 自己和别人共用
 */
public class HomepageActivity extends BaseBindActivity<ActivityHomepageBinding> {


    private boolean isMine;
    private int userId;

    public static void start(Context context, boolean isMine, int userId) {
        Intent intent = new Intent(context, HomepageActivity.class);
        intent.putExtra(ConstantValue.TYPE, isMine);
        intent.putExtra(ConstantValue.ID, userId);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_homepage;
    }

    @Override
    public void initView() {
        isMine = getIntent().getBooleanExtra(ConstantValue.TYPE, false);
        userId = getIntent().getIntExtra(ConstantValue.TYPE, -1);
    }

    @Override
    public void initData() {

    }
}
