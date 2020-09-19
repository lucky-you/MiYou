package com.zhowin.miyou.recommend.fragment;

import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.IncludeCharmInformationFragmentBinding;
import com.zhowin.miyou.recommend.adapter.CharmInformationAdapter;

import java.util.Arrays;


/**
 * author : zho
 * date  ：2020/9/17
 * desc ：  贡献榜单 / 魅力榜单  的 信息
 */
public class CharmInformationFragment extends BaseBindFragment<IncludeCharmInformationFragmentBinding> {


    private int fragmentType, fragmentIndex;

    private CharmInformationAdapter charmInformationAdapter;

    public static CharmInformationFragment newInstance(int type, int index) {
        CharmInformationFragment fragment = new CharmInformationFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.TYPE, index);
        bundle.putInt(ConstantValue.INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_charm_information_fragment;
    }

    @Override
    public void initView() {
        fragmentType = getArguments().getInt(ConstantValue.TYPE);
        fragmentIndex = getArguments().getInt(ConstantValue.INDEX);
        Log.e("xy", "fragmentType:" + fragmentType + "---fragmentIndex:" + fragmentIndex);
    }

    @Override
    public void initData() {
        charmInformationAdapter = new CharmInformationAdapter(Arrays.asList("212", "14545", "ff", "dfd", "dfd"));
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(charmInformationAdapter);
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
}
