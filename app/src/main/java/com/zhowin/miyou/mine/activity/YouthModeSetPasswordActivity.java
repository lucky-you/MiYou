package com.zhowin.miyou.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.base_library.utils.KeyboardUtils;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindActivity;
import com.zhowin.miyou.databinding.ActivityYouthModeSetPasswordBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.login.activity.ForgetPasswordActivity;
import com.zhowin.miyou.main.view.VerificationCodeView;

/**
 * 青少年模式设置密码
 */
public class YouthModeSetPasswordActivity extends BaseBindActivity<ActivityYouthModeSetPasswordBinding> {

    private boolean isConfirmPassword;
    private String youthModePassword;
    private boolean isOpenYouthMode; //开启或者关闭青少年模式

    /**
     * @param isOpenMode false: 开启青少年模式设置密码  true: 关闭青少年模式输入密码
     */
    public static void start(Context context, boolean isOpenMode) {
        Intent intent = new Intent(context, YouthModeSetPasswordActivity.class);
        intent.putExtra(ConstantValue.TYPE, isOpenMode);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_youth_mode_set_password;
    }

    @Override
    public void initView() {
        setOnClick(R.id.tvForgetPassword, R.id.tvNextStepSet);
        isOpenYouthMode = getIntent().getBooleanExtra(ConstantValue.TYPE, false);
        mBinding.tvSetPassword.setText(isOpenYouthMode ? "关闭青少年模式" : "设置密码");
        mBinding.tvSetHitMessage.setText(isOpenYouthMode ? "输入密码确认" : "启动青少年模式，需先设置独立密码");
        mBinding.tvNextStepSet.setText(isOpenYouthMode ? "确认关闭" : "下一步");
        mBinding.tvForgetPassword.setVisibility(isOpenYouthMode ? View.VISIBLE : View.GONE);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvForgetPassword:
                ForgetPasswordActivity.start(mContext, 2);
                break;

            case R.id.tvNextStepSet:
                if (isOpenYouthMode) {
                    if (TextUtils.isEmpty(youthModePassword) || youthModePassword.length() < 4) {
                        ToastUtils.showToast("请先输入密码哦");
                        return;
                    }
                    openOrCloseYouthMode(false);
                    KeyboardUtils.hideSoftInput(mContext);
                } else {
                    if (!isConfirmPassword) {
                        if (TextUtils.isEmpty(youthModePassword) || youthModePassword.length() < 4) {
                            ToastUtils.showToast("请先设置密码哦");
                            return;
                        }
                        isConfirmPassword = true;
                        setPasswordView();
                    } else {
                        openOrCloseYouthMode(true);
                    }
                    KeyboardUtils.hideSoftInput(mContext);
                }
                break;
        }
    }

    /**
     * 开启或者关闭青少年模式
     *
     * @param isOpenYouthMode
     */
    private void openOrCloseYouthMode(boolean isOpenYouthMode) {
        showLoadDialog();
        HttpRequest.openOrCloseYouthMode(this, isOpenYouthMode, youthModePassword, new HttpCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                dismissLoadDialog();
                ToastUtils.showCustomToast(mContext, "操作成功");
                ActivityManager.getAppInstance().finishActivity();
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);
            }
        });
    }


    private void setPasswordView() {
        mBinding.tvSetHitMessage.setVisibility(isConfirmPassword ? View.INVISIBLE : View.GONE);
        mBinding.tvSetPassword.setText(isConfirmPassword ? "确认密码" : "设置密码");
        mBinding.tvNextStepSet.setText(isConfirmPassword ? "确认开启" : "下一步");
    }

    @Override
    public void initListener() {
        mBinding.verificationCodeView.setInputCompleteListener(new VerificationCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                youthModePassword = mBinding.verificationCodeView.getInputContent();
            }

            @Override
            public void deleteContent() {

            }
        });
    }
}