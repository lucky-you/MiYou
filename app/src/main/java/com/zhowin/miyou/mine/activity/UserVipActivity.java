package com.zhowin.miyou.mine.activity;


import androidx.recyclerview.widget.GridLayoutManager;

import com.gyf.immersionbar.ImmersionBar;
import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityUserVipBinding;
import com.zhowin.miyou.mine.adapter.UserVipAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户vip
 */
public class UserVipActivity extends BaseBindActivity<ActivityUserVipBinding> {


    private UserVipAdapter userVipAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_vip;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        List<String> strLis = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            strLis.add("");
        }
        userVipAdapter = new UserVipAdapter();
        mBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        mBinding.recyclerView.setAdapter(userVipAdapter);
        userVipAdapter.setNewData(strLis);
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .statusBarColor(R.color.color_2E2E30)
                .keyboardEnable(true)
                .statusBarDarkFont(true, 0f)
                .init();
    }
}