package com.zhowin.miyou.mine.fragment;

import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.IncludeMasonryDetailsFragmentBinding;
import com.zhowin.miyou.mine.adapter.MasonryDetailsListAdapter;
import com.zhowin.miyou.mine.model.MasonryDetailsList;

import java.util.ArrayList;
import java.util.List;

/**
 * 明细列表
 */
public class MasonryDetailsFragment extends BaseBindFragment<IncludeMasonryDetailsFragmentBinding> {


    private MasonryDetailsListAdapter masonryDetailsListAdapter;
    private int fragmentType, fragmentIndex;

    public static MasonryDetailsFragment newInstance(int type, int index) {
        MasonryDetailsFragment fragment = new MasonryDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.TYPE, index);
        bundle.putInt(ConstantValue.INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.include_masonry_details_fragment;
    }

    @Override
    public void initView() {
        fragmentType = getArguments().getInt(ConstantValue.TYPE);
        fragmentIndex = getArguments().getInt(ConstantValue.INDEX);
        Log.e("xy", "fragmentType:" + fragmentType + "---fragmentIndex:" + fragmentIndex);
    }

    @Override
    public void initData() {
        setRecyclerViewData();
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

    private void setRecyclerViewData() {
        List<MasonryDetailsList> masonryDetailsLists = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            masonryDetailsLists.add(new MasonryDetailsList());
        }
        masonryDetailsListAdapter = new MasonryDetailsListAdapter(masonryDetailsLists);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(masonryDetailsListAdapter);

    }
}
