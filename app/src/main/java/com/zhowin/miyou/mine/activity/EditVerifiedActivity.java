package com.zhowin.miyou.mine.activity;


import android.view.View;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityEditVerifiedBinding;

/**
 * 实名认证
 */
public class EditVerifiedActivity extends BaseBindActivity<ActivityEditVerifiedBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_verified;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mBinding.tvSubmitVerified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerifiedSuccessActivity.start(mContext, 1);
            }
        });
    }
}
