package com.zhowin.miyou.mine.activity;


import android.view.View;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityMyWalletBinding;

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
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvRecharge:
                startActivity(MyDiamondActivity.class);
                break;
            case R.id.tvExchange:
                break;

        }
    }
}
