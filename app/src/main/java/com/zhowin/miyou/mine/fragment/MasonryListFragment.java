package com.zhowin.miyou.mine.fragment;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.zhowin.base_library.adapter.HomePageAdapter;
import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.IncludeMasonryListFragmentBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 充值明细
 */
public class MasonryListFragment extends BaseBindFragment<IncludeMasonryListFragmentBinding> {

    private String[] titles;
    private List<Fragment> mFragments = new ArrayList<>();
    private int fragmentIndex;

    public static MasonryListFragment newInstance(int index) {
        MasonryListFragment fragment = new MasonryListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_masonry_list_fragment;
    }

    @Override
    public void initView() {
        fragmentIndex = getArguments().getInt(ConstantValue.INDEX);
        Log.e("xy", "fragmentIndex:" + fragmentIndex);
    }

    @Override
    public void initData() {
        switch (fragmentIndex) {
            case 0:
                titles = new String[]{"系统充值", "兑换收入"};
                break;
            case 1:
                titles = new String[]{"礼物赠送", "守护支付"};
                break;
        }
        for (int i = 0; i < titles.length; i++) {
            mFragments.add(MasonryDetailsFragment.newInstance(fragmentIndex, i));
        }
        HomePageAdapter homePageAdapter = new HomePageAdapter(getChildFragmentManager(), mFragments, titles);
        mBinding.noScrollViewPager.setAdapter(homePageAdapter);
        mBinding.noScrollViewPager.setScroll(true);
        mBinding.segmentTabLayout.setTabData(titles);
    }
}
