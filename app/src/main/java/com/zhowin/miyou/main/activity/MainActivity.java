package com.zhowin.miyou.main.activity;

import androidx.fragment.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.zhowin.base_library.adapter.HomePageAdapter;
import com.zhowin.base_library.base.BaseLibActivity;
import com.zhowin.base_library.view.NoScrollViewPager;
import com.zhowin.miyou.R;
import com.zhowin.miyou.main.entity.TabEntity;
import com.zhowin.miyou.message.fragment.MessageFragment;
import com.zhowin.miyou.mine.fragment.MineFragment;
import com.zhowin.miyou.recommend.fragment.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseLibActivity implements OnTabSelectListener {

    private NoScrollViewPager noScrollViewPager;
    private CommonTabLayout commonTabLayout;

    private String[] mTitles = {"房间", "消息", "我的"};
    private List<Fragment> mFragments = new ArrayList<>();

    private int[] mIconSelectIds = {
            R.drawable.bottom_home_click_icon, R.drawable.bottom_message_click_icon, R.drawable.bottom_mine_click_icon};

    private int[] mIconUnSelectIds = {
            R.drawable.bottom_home_icon, R.drawable.bottom_message_icon, R.drawable.bottom_mine_icon};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        noScrollViewPager = get(R.id.noScrollViewPager);
        commonTabLayout = get(R.id.commonTabLayout);
    }

    @Override
    public void initData() {


        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnSelectIds[i]));
        }
        mFragments.add(new RecommendFragment());
        mFragments.add(new MessageFragment());
        mFragments.add(new MineFragment());

        HomePageAdapter homePageAdapter = new HomePageAdapter(getSupportFragmentManager(), mFragments, mTitles);
        noScrollViewPager.setAdapter(homePageAdapter);

        commonTabLayout.setTabData(mTabEntities);

        commonTabLayout.setOnTabSelectListener(this);
    }

    @Override
    public void onTabSelect(int position) {
        noScrollViewPager.setCurrentItem(position);
    }

    @Override
    public void onTabReselect(int position) {

    }
}
