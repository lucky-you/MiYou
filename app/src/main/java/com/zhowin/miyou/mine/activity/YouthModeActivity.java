package com.zhowin.miyou.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindActivity;
import com.zhowin.miyou.databinding.ActivityYouthModeBindingImpl;
import com.zhowin.miyou.http.HttpRequest;

/**
 * 青少年模式 开启和 关闭通用
 */
public class YouthModeActivity extends BaseBindActivity<ActivityYouthModeBindingImpl> {


    private boolean isOpenYouthMode;

    public static void start(Context context, boolean isOpenMode) {
        Intent intent = new Intent(context, YouthModeActivity.class);
        intent.putExtra(ConstantValue.TYPE, isOpenMode);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_youth_mode;
    }

    @Override
    public void initView() {
        setOnClick(R.id.tvTurnOnYouthMode);
        isOpenYouthMode = getIntent().getBooleanExtra(ConstantValue.TYPE, false);
    }

    private void setDataViewFromStatus() {
        mBinding.tvModeStatus.setText(isOpenYouthMode ? "已开启" : "未开启");
        mBinding.ivModeStatusImage.setImageResource(isOpenYouthMode ? R.drawable.young_open_icon : R.drawable.young_close_icon);
        mBinding.tvTurnOnYouthMode.setText(isOpenYouthMode ? "关闭青少年模式" : "开启青少年模式");
        mBinding.tvTurnOnYouthMode.setTextColor(isOpenYouthMode ? getBaseColor(R.color.color_56B2FE) : getBaseColor(R.color.white));
        mBinding.tvTurnOnYouthMode.setBackgroundResource(isOpenYouthMode ? R.drawable.young_close_btn : R.drawable.authen_start_btn);
        mBinding.tvChangePassword.setVisibility(isOpenYouthMode ? View.GONE : View.GONE);
    }

    @Override
    public void initData() {


    }

    @Override
    protected void onResume() {
        super.onResume();
        checkYouthMode();
    }

    /**
     * 查询青少年模式
     */
    private void checkYouthMode() {
        showLoadDialog();
        HttpRequest.checkYouthMode(this, new HttpCallBack<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                dismissLoadDialog();
                isOpenYouthMode = aBoolean;
                setDataViewFromStatus();
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvTurnOnYouthMode:
                YouthModeSetPasswordActivity.start(mContext, isOpenYouthMode);
                break;
        }
    }
}
