package com.zhowin.miyou.mine.activity;


import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.zhowin.base_library.adapter.HomePageAdapter;
import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityMasonryDetailsBinding;
import com.zhowin.miyou.mine.fragment.MasonryDetailsFragment;
import com.zhowin.miyou.mine.fragment.MasonryListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 钻石明细
 */
public class MasonryDetailsActivity extends BaseBindActivity<ActivityMasonryDetailsBinding> {

    private int classType;

    public static void start(Context context, int type) {
        Intent intent = new Intent(context, MasonryDetailsActivity.class);
        intent.putExtra(ConstantValue.TYPE, type);
        context.startActivity(intent);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_masonry_details;
    }

    @Override
    public void initView() {
        classType = getIntent().getIntExtra(ConstantValue.TYPE, -1);
    }

    @Override
    public void initData() {
        String[] mTitle = {"收入明细", "支出明细"};
        List<Fragment> mFragment = new ArrayList<>();
        for (int i = 0; i < mTitle.length; i++) {
            switch (classType){
                case 1: //钻石
                    mFragment.add(MasonryListFragment.newInstance(i));
                    break;
                case 2://魅力值
                    mFragment.add(MasonryDetailsFragment.newInstance(2, i));
                    break;
            }

        }
        HomePageAdapter homePageAdapter = new HomePageAdapter(getSupportFragmentManager(), mFragment, mTitle);
        mBinding.viewPager.setAdapter(homePageAdapter);
        mBinding.viewPager.setScroll(true);
        mBinding.slidingTabLayout.setViewPager(mBinding.viewPager);

    }
}