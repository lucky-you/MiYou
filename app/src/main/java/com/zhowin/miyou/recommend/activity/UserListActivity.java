package com.zhowin.miyou.recommend.activity;


import android.view.View;

import androidx.fragment.app.Fragment;

import com.gyf.immersionbar.ImmersionBar;
import com.zhowin.base_library.adapter.HomePageAdapter;
import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindActivity;
import com.zhowin.miyou.databinding.ActivityUserListBinding;
import com.zhowin.miyou.recommend.fragment.NobleListFragment;
import com.zhowin.miyou.recommend.fragment.ToadyListFragment;
import com.zhowin.miyou.recommend.fragment.TrueLoveListFragment;
import com.zhowin.miyou.recommend.fragment.GuardListFragment;
import com.zhowin.miyou.recommend.fragment.WeekStarListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户榜单
 */
public class UserListActivity extends BaseBindActivity<ActivityUserListBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_user_list;
    }

    @Override
    public void initView() {
        setOnClick(R.id.ivBackReturn);
    }

    @Override
    public void initData() {
        String[] mTitles = {"日榜", "真爱榜", "守护榜", "周星榜", "贵族榜"};
        List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(ToadyListFragment.newInstance());
        mFragments.add(TrueLoveListFragment.newInstance());
        mFragments.add(GuardListFragment.newInstance());
        mFragments.add(WeekStarListFragment.newInstance());
        mFragments.add(NobleListFragment.newInstance());
        HomePageAdapter homePageAdapter = new HomePageAdapter(getSupportFragmentManager(), mFragments, mTitles);
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
                .titleBar(mBinding.llTopView, false)
                .transparentBar()
                .init();
    }

}
