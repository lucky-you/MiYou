package com.zhowin.miyou.mine.activity;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivitySetPasswordBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.login.activity.ForgetPasswordActivity;

/**
 * 设置密码/修改密码
 */
public class SetPasswordActivity extends BaseBindActivity<ActivitySetPasswordBinding> {

    private int classType;
    private boolean isShowOriginalPassword, isShowPassword, isShowPasswordAgain;

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_password;
    }

    @Override
    public void initView() {
        classType = UserInfo.getUserInfo().getUndefinedPwd();
        mBinding.llOriginalPasswordLayout.setVisibility(0 == classType ? View.VISIBLE : View.GONE);
        setOnClick(R.id.tvSavePassword, R.id.ivOriginalPasswordClose, R.id.ivSetUpPasswordClose, R.id.ivConfirmPasswordClose);
        if (1 == classType) {
            mBinding.titleView.setTitle("设置密码");
        } else if (0 == classType) {
            mBinding.titleView.setTitle("修改密码");
            mBinding.titleView.setRightText("忘记密码");
            mBinding.titleView.setRightTxColor(getBaseColor(R.color.color_4AACFE));
            mBinding.titleView.setRightTextViewClick(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ForgetPasswordActivity.start(mContext,1);
                }
            });
        }
    }

    @Override
    public void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSavePassword:
                savePassword();
                break;
            case R.id.ivOriginalPasswordClose:
                isShowOriginalPassword = !isShowOriginalPassword;
                setShowOriginalPassword();
                break;
            case R.id.ivSetUpPasswordClose:
                isShowPassword = !isShowPassword;
                setShowPassword();
                break;
            case R.id.ivConfirmPasswordClose:
                isShowPasswordAgain = !isShowPasswordAgain;
                setShowPasswordAgain();
                break;
        }
    }

    private void setShowOriginalPassword() {
        mBinding.editOriginalPassword.setTransformationMethod(isShowOriginalPassword ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
        mBinding.ivOriginalPasswordClose.setImageResource(isShowOriginalPassword ? R.mipmap.login_eyeopen_icon : R.mipmap.login_eyeclose_icon);
        mBinding.editOriginalPassword.setSelection(mBinding.editOriginalPassword.getText().toString().trim().length());
    }

    private void setShowPassword() {
        mBinding.editSetUpPassword.setTransformationMethod(isShowPassword ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
        mBinding.ivSetUpPasswordClose.setImageResource(isShowPassword ? R.mipmap.login_eyeopen_icon : R.mipmap.login_eyeclose_icon);
        mBinding.editSetUpPassword.setSelection(mBinding.editSetUpPassword.getText().toString().trim().length());
    }

    private void setShowPasswordAgain() {
        mBinding.editConfirmPassword.setTransformationMethod(isShowPasswordAgain ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
        mBinding.ivConfirmPasswordClose.setImageResource(isShowPasswordAgain ? R.mipmap.login_eyeopen_icon : R.mipmap.login_eyeclose_icon);
        mBinding.editConfirmPassword.setSelection(mBinding.editConfirmPassword.getText().toString().trim().length());
    }


    private void savePassword() {
        String originalPassword = mBinding.editOriginalPassword.getText().toString().trim();
        String password = mBinding.editSetUpPassword.getText().toString().trim();
        String passwordAgain = mBinding.editConfirmPassword.getText().toString().trim();
        if (0 == classType)
            if (TextUtils.isEmpty(originalPassword)) {
                ToastUtils.showToast("请输入原密码");
                return;
            }
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showToast("请输入新密码");
            return;
        }
        if (password.length() < 6) {
            ToastUtils.showToast("新密码长度不能少于6位");
            return;
        }
        if (TextUtils.isEmpty(passwordAgain)) {
            ToastUtils.showToast("请再次输入新密码");
            return;
        }
        if (passwordAgain.length() < 6) {
            ToastUtils.showToast("新密码长度不能少于6位");
            return;
        }
        if (!TextUtils.equals(password, passwordAgain)) {
            ToastUtils.showToast("两次输入的密码不一致");
            return;
        }
        if (1 == classType) {
            setUserPassword(password);
        } else if (0 == classType) {
            changeUserPassword(originalPassword, password);
        }
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    private void setUserPassword(String password) {
        showLoadDialog();
        HttpRequest.setUserPassword(this, password, new HttpCallBack<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                dismissLoadDialog();
                ToastUtils.showToast("设置成功");
                UserInfo.getUserInfo().setUndefinedPwd(0);
                ActivityManager.getAppInstance().finishActivity();

            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);
            }
        });
    }

    /**
     * 修改密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    private void changeUserPassword(String oldPassword, String newPassword) {
        showLoadDialog();
        HttpRequest.changeUserPassword(this, oldPassword, newPassword, new HttpCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                dismissLoadDialog();
                ToastUtils.showToast("修改成功");
                ActivityManager.getAppInstance().finishActivity();
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);
            }
        });
    }
}
