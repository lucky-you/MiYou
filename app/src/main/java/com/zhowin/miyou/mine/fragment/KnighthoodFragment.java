package com.zhowin.miyou.mine.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.IncludeKnighthoodFragmentBinding;
import com.zhowin.miyou.mine.adapter.KnighthoodPrivilegeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 爵位
 */
public class KnighthoodFragment extends BaseBindFragment<IncludeKnighthoodFragmentBinding> {


    private KnighthoodPrivilegeAdapter knighthoodPrivilegeAdapter;

    public static KnighthoodFragment newInstance(int index) {
        KnighthoodFragment fragment = new KnighthoodFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.include_knighthood_fragment;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        List<String> strList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            strList.add("1");
        }
        knighthoodPrivilegeAdapter=new KnighthoodPrivilegeAdapter();
        knighthoodPrivilegeAdapter.setNewData(strList);
        mBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        mBinding.recyclerView.setAdapter(knighthoodPrivilegeAdapter);
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
