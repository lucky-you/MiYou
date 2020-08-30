package com.zhowin.miyou.recommend.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.IncludeTrueLoveListFragmentBinding;
import com.zhowin.miyou.recommend.adapter.TrueLoveListAdapter;
import com.zhowin.miyou.recommend.model.UserList;

import java.util.ArrayList;
import java.util.List;

/**
 * 真爱榜单
 */
public class TrueLoveListFragment extends BaseBindFragment<IncludeTrueLoveListFragmentBinding> {

    private TrueLoveListAdapter trueLoveListAdapter;

    public static TrueLoveListFragment newInstance(int index) {
        TrueLoveListFragment fragment = new TrueLoveListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_true_love_list_fragment;
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
        trueLoveListAdapter = new TrueLoveListAdapter(userLists);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(trueLoveListAdapter);

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
