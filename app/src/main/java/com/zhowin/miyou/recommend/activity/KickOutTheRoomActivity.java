package com.zhowin.miyou.recommend.activity;


import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.zhowin.base_library.adapter.HomePageAdapter;
import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityKickOutTheRoomBinding;
import com.zhowin.miyou.mine.fragment.PropsFragment;
import com.zhowin.miyou.recommend.fragment.KickOutRoomFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 踢出房间 ，和  禁言共用
 * <p>
 * type   1:踢出  2: 禁言
 */
public class KickOutTheRoomActivity extends BaseBindActivity<ActivityKickOutTheRoomBinding> {


    private int classType;
    private String[] mTitle;


    public static void start(Context context, int type) {
        Intent intent = new Intent(context, KickOutTheRoomActivity.class);
        intent.putExtra(ConstantValue.TYPE, type);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_kick_out_the_room;
    }

    @Override
    public void initView() {
        classType = getIntent().getIntExtra(ConstantValue.TYPE, -1);
    }

    @Override
    public void initData() {
        switch (classType) {
            case 1:
                mTitle = new String[]{"踢出房间", "踢出记录",};
                break;
            case 2:
                mTitle = new String[]{"禁言", "禁言记录",};
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + classType);
        }

        List<Fragment> mFragments = new ArrayList<>();
        for (int i = 0; i < mTitle.length; i++) {
            mFragments.add(KickOutRoomFragment.newInstance(classType, i));
        }
        HomePageAdapter homePageAdapter = new HomePageAdapter(getSupportFragmentManager(), mFragments, mTitle);
        mBinding.noScrollViewPager.setAdapter(homePageAdapter);
        mBinding.noScrollViewPager.setScroll(true);
        mBinding.slidingTabLayout.setViewPager(mBinding.noScrollViewPager);
    }
}
