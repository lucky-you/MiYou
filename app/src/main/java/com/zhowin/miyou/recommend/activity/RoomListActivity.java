package com.zhowin.miyou.recommend.activity;


import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.gyf.immersionbar.ImmersionBar;
import com.zhowin.base_library.adapter.HomePageAdapter;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindActivity;
import com.zhowin.miyou.databinding.ActivityRoomListBinding;
import com.zhowin.miyou.recommend.fragment.CharmListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 房间榜单
 */
public class RoomListActivity extends BaseBindActivity<ActivityRoomListBinding> {

    private String[] mTitle = {"日榜", "周榜"};

    private int roomId;


    public static void start(Context context, int roomId) {
        Intent intent = new Intent(context, RoomListActivity.class);
        intent.putExtra(ConstantValue.ID, roomId);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_room_list;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        roomId = getIntent().getIntExtra(ConstantValue.ID, -1);
        List<Fragment> mFragments = new ArrayList<>();
        for (int i = 0; i < mTitle.length; i++) {
            mFragments.add(CharmListFragment.newInstance(roomId, i));
        }
        HomePageAdapter homePageAdapter = new HomePageAdapter(getSupportFragmentManager(), mFragments, mTitle);
        mBinding.noScrollViewPager.setAdapter(homePageAdapter);
        mBinding.noScrollViewPager.setScroll(true);
        mBinding.slidingTabLayout.setViewPager(mBinding.noScrollViewPager);
    }


    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .statusBarColor(R.color.color_8234FC)
                .keyboardEnable(true)
                .statusBarDarkFont(true, 0f)
                .init();
    }
}
