package com.zhowin.miyou.recommend.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.IncludeNobleListFragmentBinding;
import com.zhowin.miyou.recommend.adapter.NobleListAdapter;
import com.zhowin.miyou.recommend.model.UserList;

import java.util.ArrayList;
import java.util.List;

/**
 * 贵族榜单
 */
public class NobleListFragment extends BaseBindFragment<IncludeNobleListFragmentBinding> {


    private NobleListAdapter nobleListAdapter;

    public static NobleListFragment newInstance(int index) {
        NobleListFragment fragment = new NobleListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_noble_list_fragment;
    }


    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        List<UserList> userLists = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            userLists.add(new UserList());
        }
        nobleListAdapter = new NobleListAdapter(userLists);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(nobleListAdapter);
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
