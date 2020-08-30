package com.zhowin.miyou.recommend.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.IncludeWeekStarFragmentBinding;
import com.zhowin.miyou.main.entity.TabEntity;

import java.util.ArrayList;

/**
 * 周星榜
 */
public class WeekStarListFragment extends BaseBindFragment<IncludeWeekStarFragmentBinding> {


    private String[] mTitles = {"高跟鞋", "小熊"};
    private int[] mIconUnSelectIds = {
            R.mipmap.ic_def_image, R.mipmap.ic_def_image};
    private int[] mIconSelectIds = {
            R.mipmap.ic_def_image, R.mipmap.ic_def_image};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public static WeekStarListFragment newInstance(int index) {
        WeekStarListFragment fragment = new WeekStarListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_week_star_fragment;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnSelectIds[i]));
            mFragments.add(WeekStarGiftFragment.newInstance(i));
        }
        mBinding.commonTabLayout.setTabData(mTabEntities, mContext, R.id.flWeekStarFragment, mFragments);
    }

    @Override
    public void initListener() {
        mBinding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mBinding.refreshLayout.setRefreshing(false);
            }
        });
    }
    @Override
    public void initImmersionBar() {

    }
}
