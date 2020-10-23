package com.zhowin.miyou.mine.activity;

import android.view.View;

import com.zhowin.base_library.utils.SpanUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindActivity;
import com.zhowin.miyou.databinding.ActivityPermanentLogoutBinding;

/**
 * 永久注册
 */
public class PermanentLogoutActivity extends BaseBindActivity<ActivityPermanentLogoutBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_permanent_logout;
    }

    @Override
    public void initView() {
        SpanUtils.with(mBinding.tvHitMessage)
                .append("1.账号相关身份，信息，权益将清空无法恢复")
                .appendLine()
                .append("2.账号的好友消息，发布内容，互动将清空无法恢复")
                .appendLine()
                .append("3.账号内剩余钻石、魅力值将被清空，注销后产生的资产将视为放弃")
                .create();
        SpanUtils.with(mBinding.tvHitMessageTwo)
                .append("请确认所有的钻石数小于100")
                .appendLine()
                .append("请确认所有魅力值已提现完毕")
                .create();
        setOnClick(R.id.tvApplyLogout);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvApplyLogout:
                startActivity(LogoutAccountActivity.class);
                break;
        }
    }
}
