package com.zhowin.miyou.mine.activity;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityVerifiedSuccessBinding;

/**
 * 认证成功/失败
 */
public class VerifiedSuccessActivity extends BaseBindActivity<ActivityVerifiedSuccessBinding> {

    public static void start(Context context, int type) {
        Intent intent = new Intent(context, VerifiedSuccessActivity.class);
        intent.putExtra(ConstantValue.TYPE, type);
        context.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_verified_success;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


    @Override
    public void initListener() {
        mBinding.tvVerifiedAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(EditVerifiedActivity.class);
            }
        });
    }
}
