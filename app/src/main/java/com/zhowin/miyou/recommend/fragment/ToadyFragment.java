package com.zhowin.miyou.recommend.fragment;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.zhowin.base_library.adapter.HomePageAdapter;
import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.IncludeTodayFragmentBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 日榜
 */
public class ToadyFragment extends BaseBindFragment<IncludeTodayFragmentBinding> {
    private String[] titles;
    private List<Fragment> mFragments = new ArrayList<>();
    private int fragmentIndex;

    public static ToadyFragment newInstance(int index) {
        ToadyFragment fragment = new ToadyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.include_today_fragment;
    }

    @Override
    public void initView() {
        fragmentIndex = getArguments().getInt(ConstantValue.INDEX);
        Log.e("xy", "fragmentIndex:" + fragmentIndex);
    }

    @Override
    public void initData() {
        titles = new String[]{"魅力榜", "贡献榜"};
        for (int i = 0; i < titles.length; i++) {
            mFragments.add(TodayListFragment.newInstance(i));
        }
        HomePageAdapter homePageAdapter = new HomePageAdapter(getChildFragmentManager(), mFragments, titles);
        mBinding.noScrollViewPager.setAdapter(homePageAdapter);
        mBinding.noScrollViewPager.setScroll(true);
        mBinding.segmentTabLayout.setTabData(titles);
    }
}
