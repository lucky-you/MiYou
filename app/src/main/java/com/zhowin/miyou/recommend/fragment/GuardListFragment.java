package com.zhowin.miyou.recommend.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.IncludeUserListFragmentLayoutBinding;
import com.zhowin.miyou.recommend.adapter.GuardListAdapter;
import com.zhowin.miyou.recommend.model.UserList;

import java.util.ArrayList;
import java.util.List;

/**
 * desc ：守护榜单
 */
public class GuardListFragment extends BaseBindFragment<IncludeUserListFragmentLayoutBinding> {

    private GuardListAdapter guardListAdapter;


    public static GuardListFragment newInstance(int index) {
        GuardListFragment fragment = new GuardListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.include_user_list_fragment_layout;
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
        guardListAdapter = new GuardListAdapter(userLists);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(guardListAdapter);
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
