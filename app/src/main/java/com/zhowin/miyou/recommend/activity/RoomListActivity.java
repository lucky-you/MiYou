package com.zhowin.miyou.recommend.activity;


import androidx.fragment.app.Fragment;

import com.zhowin.base_library.adapter.HomePageAdapter;
import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityRoomListBinding;
import com.zhowin.miyou.recommend.fragment.CharmListFragment;
import com.zhowin.miyou.recommend.fragment.KickOutRoomFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 房间榜单
 */
public class RoomListActivity extends BaseBindActivity<ActivityRoomListBinding> {

    private String[] mTitle = {"日榜", "周榜"};

    @Override
    public int getLayoutId() {
        return R.layout.activity_room_list;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        List<Fragment> mFragments = new ArrayList<>();
        for (int i = 0; i < mTitle.length; i++) {
            mFragments.add(CharmListFragment.newInstance(i));
        }
        HomePageAdapter homePageAdapter = new HomePageAdapter(getSupportFragmentManager(), mFragments, mTitle);
        mBinding.noScrollViewPager.setAdapter(homePageAdapter);
        mBinding.noScrollViewPager.setScroll(true);
        mBinding.slidingTabLayout.setViewPager(mBinding.noScrollViewPager);
    }
}
