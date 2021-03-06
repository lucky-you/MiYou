package com.zhowin.miyou.recommend.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindFragment;
import com.zhowin.miyou.databinding.IncludeWeekStartGiftFragmentBinding;
import com.zhowin.miyou.recommend.adapter.WeekStarGiftAdapter;

/**
 * 周星榜 礼物
 */
public class WeekStarGiftFragment extends BaseBindFragment<IncludeWeekStartGiftFragmentBinding> {


    private WeekStarGiftAdapter weekStarGiftAdapter;


    public static WeekStarGiftFragment newInstance(int index) {
        WeekStarGiftFragment fragment = new WeekStarGiftFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.include_week_start_gift_fragment;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        weekStarGiftAdapter = new WeekStarGiftAdapter();
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(weekStarGiftAdapter);
    }

    @Override
    public void initImmersionBar() {

    }
}
