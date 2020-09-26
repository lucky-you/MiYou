package com.zhowin.miyou.mine.activity;

import androidx.fragment.app.Fragment;

import com.gyf.immersionbar.ImmersionBar;
import com.zhowin.base_library.adapter.HomePageAdapter;
import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityKnighthoodBinding;
import com.zhowin.miyou.mine.fragment.KnighthoodFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 爵位
 */
public class KnighthoodActivity extends BaseBindActivity<ActivityKnighthoodBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_knighthood;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        String[] mTitle = {"男爵", "子爵", "伯爵", "侯爵", "公爵", "国王", "皇帝"};
        List<Fragment> mFragment = new ArrayList<>();
        for (int i = 0; i < mTitle.length; i++) {
            mFragment.add(KnighthoodFragment.newInstance(i));
        }
        HomePageAdapter homePageAdapter = new HomePageAdapter(getSupportFragmentManager(), mFragment, mTitle);
        mBinding.noScrollViewPager.setAdapter(homePageAdapter);
        mBinding.noScrollViewPager.setScroll(true);
        mBinding.slidingTabLayout.setViewPager(mBinding.noScrollViewPager);
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