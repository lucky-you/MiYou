package com.zhowin.miyou.recommend.fragment;

import androidx.fragment.app.Fragment;

import com.zhowin.base_library.adapter.HomePageAdapter;
import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.RecommendFragmentLayoutBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 推荐
 */
public class RecommendFragment extends BaseBindFragment<RecommendFragmentLayoutBinding> {


    private String[] mTitles = {"热门", "女神", "男神", "娱乐", "FM"};
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.recommend_fragment_layout;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        for (int i = 0; i < mTitles.length; i++) {
            mFragments.add(RecommendListFragment.newInstance(i));
        }
        HomePageAdapter homePageAdapter = new HomePageAdapter(getChildFragmentManager(), mFragments, mTitles);
        mBinding.noScrollViewPager.setAdapter(homePageAdapter);
        mBinding.noScrollViewPager.setScroll(true);
        mBinding.slidingTabLayout.setViewPager(mBinding.noScrollViewPager);

    }
}
