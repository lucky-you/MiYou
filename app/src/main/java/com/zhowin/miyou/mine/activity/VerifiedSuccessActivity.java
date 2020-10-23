package com.zhowin.miyou.mine.activity;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindActivity;
import com.zhowin.miyou.databinding.ActivityVerifiedSuccessBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.mine.model.VerifiedStatus;

/**
 * 认证中/认证成功/失败
 * type : 0：审核中、1：审核通过、2：审核失败
 */
public class VerifiedSuccessActivity extends BaseBindActivity<ActivityVerifiedSuccessBinding> {


    private int classType;

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
        classType = getIntent().getIntExtra(ConstantValue.TYPE, 0);
        switch (classType) {
            case 0:
                mBinding.ivVerifiedImage.setImageResource(R.drawable.authen_ing_icon);
                mBinding.tvVerifiedStatus.setText("认证中");
                mBinding.tvVerifiedAgain.setVisibility(View.GONE);
                break;
            case 1:
                mBinding.ivVerifiedImage.setImageResource(R.drawable.authen_succes_icon);
                mBinding.tvVerifiedStatus.setText("认证成功");
                mBinding.tvVerifiedAgain.setVisibility(View.GONE);
                break;
            case 2:
                mBinding.ivVerifiedImage.setImageResource(R.drawable.authen_fail_icon);
                mBinding.tvVerifiedStatus.setText("认证失败");
                mBinding.tvVerifiedAgain.setVisibility(View.VISIBLE);
                break;
        }
        getVerifiedStatus();
    }

    @Override
    public void initData() {

    }


    /**
     * 实名认证状态
     */
    private void getVerifiedStatus() {
        HttpRequest.getVerifiedStatus(this, new HttpCallBack<VerifiedStatus>() {
            @Override
            public void onSuccess(VerifiedStatus verifiedStatus) {
                if (verifiedStatus != null) {
                    //状态 0：审核中、1：审核通过、2：审核失败
                    setDateToViews(verifiedStatus);
                } else {
                    startActivity(VerifiedActivity.class);
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                ToastUtils.showToast(errorMsg);
            }
        });
    }

    private void setDateToViews(VerifiedStatus verifiedStatus) {
        mBinding.tvUserRealName.setText("真实姓名：" + verifiedStatus.getRealName());
        mBinding.tvUserIDCardNumber.setText("身份证号：" + verifiedStatus.getIdCardNo());
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
