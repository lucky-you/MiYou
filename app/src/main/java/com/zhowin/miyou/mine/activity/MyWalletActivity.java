package com.zhowin.miyou.mine.activity;


import android.view.View;

import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindActivity;
import com.zhowin.miyou.databinding.ActivityMyWalletBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.mine.model.MyWalletBalance;

/**
 * 我的钱包
 */
public class MyWalletActivity extends BaseBindActivity<ActivityMyWalletBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_my_wallet;
    }

    @Override
    public void initView() {
        setOnClick(R.id.tvRecharge, R.id.tvExchange);
        getMyWalletBalance();
    }

    @Override
    public void initData() {

    }

    private void getMyWalletBalance() {
        showLoadDialog();
        HttpRequest.getMyWalletBalance(this, new HttpCallBack<MyWalletBalance>() {
            @Override
            public void onSuccess(MyWalletBalance myWalletBalance) {
                dismissLoadDialog();
                if (myWalletBalance != null) {
                    mBinding.tvRechargeNumber.setText(myWalletBalance.getDiamondNum() + "");
                    mBinding.tvExchangeNumber.setText(myWalletBalance.getCharmValue() + "");
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvRecharge:
                MyDiamondActivity.start(mContext, 1);
                break;
            case R.id.tvExchange:
                MyDiamondActivity.start(mContext, 2);
                break;

        }
    }
}
