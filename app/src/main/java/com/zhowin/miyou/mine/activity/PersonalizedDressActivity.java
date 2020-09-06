package com.zhowin.miyou.mine.activity;

import android.view.View;

import androidx.fragment.app.Fragment;

import com.gyf.immersionbar.ImmersionBar;
import com.zhowin.base_library.adapter.HomePageAdapter;
import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityPersonalizedDressBinding;
import com.zhowin.miyou.mine.fragment.PropsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 个性装扮
 */
public class PersonalizedDressActivity extends BaseBindActivity<ActivityPersonalizedDressBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_personalized_dress;
    }

    @Override
    public void initView() {
        setOnClick(R.id.ivBackReturn);
    }

    @Override
    public void initData() {
        String[] mTitle = {"头像框", "座驾", "道具"};
        List<Fragment> mFragments = new ArrayList<>();
        for (int i = 0; i < mTitle.length; i++) {
            mFragments.add(PropsFragment.newInstance(i));
        }
        HomePageAdapter homePageAdapter = new HomePageAdapter(getSupportFragmentManager(), mFragments, mTitle);
        mBinding.noScrollViewPager.setAdapter(homePageAdapter);
        mBinding.noScrollViewPager.setScroll(true);
        mBinding.slidingTabLayout.setViewPager(mBinding.noScrollViewPager);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBackReturn:
                ActivityManager.getAppInstance().finishActivity();
                break;

        }
    }
    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .titleBar(mBinding.clTopView, false)
                .transparentBar()
                .init();
    }
}