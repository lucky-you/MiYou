package com.zhowin.miyou.recommend.fragment;

import androidx.fragment.app.Fragment;

import com.zhowin.base_library.adapter.HomePageAdapter;
import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.RecommendFragmentLayoutBinding;
import com.zhowin.miyou.main.banner.BannerHelperUtils;
import com.zhowin.miyou.main.model.BannerList;

import java.util.ArrayList;
import java.util.List;

/**
 * 推荐
 */
public class RecommendFragment extends BaseBindFragment<RecommendFragmentLayoutBinding> {



    @Override
    public int getLayoutId() {
        return R.layout.recommend_fragment_layout;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

        String[] mTitles = {"热门", "女神", "男神", "娱乐", "FM"};
        List<Fragment> mFragments = new ArrayList<>();

        for (int i = 0; i < mTitles.length; i++) {
            mFragments.add(RecommendListFragment.newInstance(i));
        }
        HomePageAdapter homePageAdapter = new HomePageAdapter(getChildFragmentManager(), mFragments, mTitles);
        mBinding.noScrollViewPager.setAdapter(homePageAdapter);
        mBinding.noScrollViewPager.setScroll(true);
        mBinding.slidingTabLayout.setViewPager(mBinding.noScrollViewPager);

        setBannerData();

    }

    private void setBannerData() {
        List<BannerList> bannerLists = new ArrayList<>();
        bannerLists.add(new BannerList("https://img.zcool.cn/community/013de756fb63036ac7257948747896.jpg"));
        bannerLists.add(new BannerList("https://img.zcool.cn/community/01639a56fb62ff6ac725794891960d.jpg"));
        bannerLists.add(new BannerList("https://img.zcool.cn/community/01270156fb62fd6ac72579485aa893.jpg"));
        BannerHelperUtils.showHomeFragmentBanner(mContext, mBinding.banner, bannerLists);
    }
}
