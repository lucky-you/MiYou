package com.zhowin.miyou.mine.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.base_library.utils.SizeUtils;
import com.zhowin.base_library.widget.GridSpacingItemDecoration;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.IncludeProsFragmentLayoutBinding;
import com.zhowin.miyou.mine.adapter.PropsListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 道具的fragment
 */
public class PropsFragment extends BaseBindFragment<IncludeProsFragmentLayoutBinding> {


    private int fragmentIndex;
    private PropsListAdapter propsListAdapter;


    public static PropsFragment newInstance(int index) {
        PropsFragment fragment = new PropsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_pros_fragment_layout;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        List<String> recommendLists = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            recommendLists.add("");
        }
        propsListAdapter = new PropsListAdapter(recommendLists);
        mBinding.recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, SizeUtils.dp2px(6), false));
        mBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        mBinding.recyclerView.setAdapter(propsListAdapter);
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
