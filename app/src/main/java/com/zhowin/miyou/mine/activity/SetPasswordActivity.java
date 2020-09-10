package com.zhowin.miyou.mine.activity;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivitySetPasswordBinding;

/**
 * 设置密码/修改密码
 */
public class SetPasswordActivity extends BaseBindActivity<ActivitySetPasswordBinding> {


    private int classType;

    public static void start(Context context, int type) {
        Intent intent = new Intent(context, SetPasswordActivity.class);
        intent.putExtra(ConstantValue.TYPE, type);
        context.startActivity(intent);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_password;
    }

    @Override
    public void initView() {
        classType = UserInfo.getUserInfo().getUndefinedPwd();
        mBinding.titleView.setTitle(1 == classType ? "设置密码" : "修改密码");
        mBinding.llOriginalPasswordLayout.setVisibility(0 == classType ? View.VISIBLE : View.GONE);

    }

    @Override
    public void initData() {

    }
}
