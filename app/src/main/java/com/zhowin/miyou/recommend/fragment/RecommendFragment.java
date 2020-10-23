package com.zhowin.miyou.recommend.fragment;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.zhowin.base_library.adapter.HomePageAdapter;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindFragment;
import com.zhowin.miyou.databinding.RecommendFragmentLayoutBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.main.banner.BannerHelperUtils;
import com.zhowin.miyou.main.model.BannerList;
import com.zhowin.miyou.recommend.activity.RoomSearchActivity;
import com.zhowin.miyou.recommend.activity.UserListActivity;
import com.zhowin.miyou.recommend.model.HomeCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * 推荐
 */
public class RecommendFragment extends BaseBindFragment<RecommendFragmentLayoutBinding> {


    private List<Fragment> mFragments = new ArrayList<>();

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
        loadHomeBannerList();
        getHomeNoticeMessageList();
        getHomeCategoryList();
    }


    /**
     * 获取首页banner
     */
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
                ToastUtils.showToast(errorMsg);
            }
        });
    }

    /**
     * 获取首页滚动信息
     */
    private void getHomeNoticeMessageList() {
        HttpRequest.getHomeNoticeMessageList(this, new HttpCallBack<List<String>>() {
            @Override
            public void onSuccess(List<String> stringList) {
                if (stringList != null && !stringList.isEmpty()) {
                    mBinding.marqueeView.startWithList(stringList);
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {

            }
        });
    }

    /**
     * 获取分类
     */
    private void getHomeCategoryList() {
        HttpRequest.getHomeCategoryList(this, new HttpCallBack<List<HomeCategory>>() {
            @Override
            public void onSuccess(List<HomeCategory> homeCategories) {
                if (homeCategories != null && !homeCategories.isEmpty()) {
                    List<String> mTitles = new ArrayList<>();
                    if (!mFragments.isEmpty()) mFragments.clear();
                    for (int i = 0; i < homeCategories.size(); i++) {
                        mTitles.add(homeCategories.get(i).getTypeName());
                        mFragments.add(RecommendListFragment.newInstance(i, homeCategories.get(i).getTypeId()));
                    }
                    HomePageAdapter homePageAdapter = new HomePageAdapter(getChildFragmentManager(), mFragments, mTitles);
                    mBinding.noScrollViewPager.setAdapter(homePageAdapter);
                    mBinding.noScrollViewPager.setScroll(true);
                    mBinding.slidingTabLayout.setViewPager(mBinding.noScrollViewPager);
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
                RoomSearchActivity.start(mContext, 1);
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
                loadHomeBannerList();
                RecommendListFragment recommendListFragment = (RecommendListFragment) mFragments.get(mBinding.slidingTabLayout.getCurrentTab());
                recommendListFragment.setRefreshData();
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
