package com.zhowin.miyou.recommend.activity;


import android.view.View;

import androidx.fragment.app.Fragment;

import com.zhowin.base_library.adapter.HomePageAdapter;
import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityUserListBinding;
import com.zhowin.miyou.recommend.fragment.ToadyFragment;
import com.zhowin.miyou.recommend.fragment.UserListFragment;

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
        mFragments.add(ToadyFragment.newInstance(1));
        mFragments.add(UserListFragment.newInstance(2));
        mFragments.add(UserListFragment.newInstance(3));
        mFragments.add(UserListFragment.newInstance(4));
        mFragments.add(UserListFragment.newInstance(5));
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
}
