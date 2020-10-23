package com.zhowin.miyou.mine.activity;


import androidx.fragment.app.Fragment;

import com.zhowin.base_library.adapter.HomePageAdapter;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindActivity;
import com.zhowin.miyou.databinding.ActivityMyRoomBinding;
import com.zhowin.miyou.mine.fragment.MyRoomFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的房间
 */
public class MyRoomActivity extends BaseBindActivity<ActivityMyRoomBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_room;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        String[] mTitle = {"我的收藏", "我的创建"};
        List<Fragment> mFragments = new ArrayList<>();
        for (int i = 0; i < mTitle.length; i++) {
            mFragments.add(MyRoomFragment.newInstance(i));
        }
        HomePageAdapter homePageAdapter = new HomePageAdapter(getSupportFragmentManager(), mFragments, mTitle);
        mBinding.noScrollViewPager.setAdapter(homePageAdapter);
        mBinding.noScrollViewPager.setScroll(true);
        mBinding.slidingTabLayout.setViewPager(mBinding.noScrollViewPager);

    }
}
