package com.zhowin.miyou.recommend.activity;



import androidx.fragment.app.Fragment;

import com.zhowin.base_library.adapter.HomePageAdapter;
import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityKickOutTheRoomBinding;
import com.zhowin.miyou.mine.fragment.PropsFragment;
import com.zhowin.miyou.recommend.fragment.KickOutRoomFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 提出房间
 */
public class KickOutTheRoomActivity extends BaseBindActivity<ActivityKickOutTheRoomBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_kick_out_the_room;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        String[] mTitle = {"踢出房间", "踢出记录", "道具"};
        List<Fragment> mFragments = new ArrayList<>();
        for (int i = 0; i < mTitle.length; i++) {
            mFragments.add(KickOutRoomFragment.newInstance(i));
        }
        HomePageAdapter homePageAdapter = new HomePageAdapter(getSupportFragmentManager(), mFragments, mTitle);
        mBinding.noScrollViewPager.setAdapter(homePageAdapter);
        mBinding.noScrollViewPager.setScroll(true);
        mBinding.slidingTabLayout.setViewPager(mBinding.noScrollViewPager);
    }
}
