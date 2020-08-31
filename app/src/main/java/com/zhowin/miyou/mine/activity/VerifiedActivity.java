package com.zhowin.miyou.mine.activity;


import android.view.View;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.utils.SpanUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityVerifiedBinding;

/**
 * 实名认证
 */
public class VerifiedActivity extends BaseBindActivity<ActivityVerifiedBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_verified;
    }

    @Override
    public void initView() {
        setOnClick(R.id.tvStartVerified);
        SpanUtils.with(mBinding.tvHitMessage)
                .appendLine("温馨提示:").setFontSize(16, true)
                .appendLine()
                .append("1.根据相关法律法规，在使用直播、提现服务之前，您需要填写身份信息进行实名认证；")
                .appendLine()
                .append("2.请您填写准确，真实的身份信息。请您明确，实名信息将被作为身份识别，判定您的账号使用归属的依据；")
                .appendLine()
                .append("3.您所提交的身份信息将被严格保密，不会被用于其他用途。")
                .create();
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvStartVerified:
                startActivity(EditVerifiedActivity.class);
                break;
        }
    }
}
