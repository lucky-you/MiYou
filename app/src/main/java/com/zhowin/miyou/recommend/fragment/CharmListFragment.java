package com.zhowin.miyou.recommend.fragment;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.zhowin.base_library.adapter.HomePageAdapter;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindFragment;
import com.zhowin.miyou.databinding.IncludeCharmListFragmentBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * author : zho
 * date  ：2020/9/17
 * desc ： 贡献榜单 / 魅力榜单
 */
public class CharmListFragment extends BaseBindFragment<IncludeCharmListFragmentBinding> {


    private String[] titles = {"贡献榜", "魅力榜"};
    private List<Fragment> mFragments = new ArrayList<>();
    private int fragmentIndex;

    public static CharmListFragment newInstance(int index) {
        CharmListFragment fragment = new CharmListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.include_charm_list_fragment;
    }

    @Override
    public void initView() {
        fragmentIndex = getArguments().getInt(ConstantValue.INDEX);
        Log.e("xy", "fragmentIndex:" + fragmentIndex);
    }

    @Override
    public void initData() {
        for (int i = 0; i < titles.length; i++) {
            mFragments.add(CharmInformationFragment.newInstance(fragmentIndex, i));
        }
        HomePageAdapter homePageAdapter = new HomePageAdapter(getChildFragmentManager(), mFragments, titles);
        mBinding.noScrollViewPager.setAdapter(homePageAdapter);
        mBinding.noScrollViewPager.setScroll(true);
        mBinding.segmentTabLayout.setTabData(titles);
    }
}
