package com.zhowin.miyou.recommend.fragment;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.zhowin.base_library.adapter.HomePageAdapter;
import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.RecommendFragmentLayoutBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.main.banner.BannerHelperUtils;
import com.zhowin.miyou.main.model.BannerList;
import com.zhowin.miyou.recommend.activity.RoomSearchActivity;
import com.zhowin.miyou.recommend.activity.SetUpAdministratorActivity;
import com.zhowin.miyou.recommend.activity.UserListActivity;

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
        setOnClick(R.id.tvHomeSearch, R.id.ivUserList);
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

    private void loadHomeBannerList() {
        HttpRequest.getHomeBannerList(this, new HttpCallBack<List<BannerList>>() {
            @Override
            public void onSuccess(List<BannerList> bannerLists) {
                if (bannerLists != null && !bannerLists.isEmpty()) {
                    BannerHelperUtils.showHomeFragmentBanner(mContext, mBinding.banner, bannerLists);
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvHomeSearch:
                startActivity(RoomSearchActivity.class);
                break;
            case R.id.ivUserList:
                startActivity(UserListActivity.class);
                break;
        }
    }

    @Override
    public void initListener() {
        mBinding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mBinding.refreshLayout.setRefreshing(false);
            }
        });
        mBinding.appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if (i >= 0) {
                    mBinding.refreshLayout.setEnabled(true);
                } else {
                    mBinding.refreshLayout.setEnabled(false);
                }
            }
        });
    }
}
