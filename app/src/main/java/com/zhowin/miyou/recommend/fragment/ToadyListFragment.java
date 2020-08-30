package com.zhowin.miyou.recommend.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.IncludeTodayFragmentBinding;
import com.zhowin.miyou.recommend.adapter.TodayListAdapter;
import com.zhowin.miyou.recommend.model.UserList;

import java.util.ArrayList;
import java.util.List;

/**
 * 日榜
 */
public class ToadyListFragment extends BaseBindFragment<IncludeTodayFragmentBinding> {
    private int fragmentIndex;

    private TodayListAdapter userListAdapter;

    public static ToadyListFragment newInstance(int index) {
        ToadyListFragment fragment = new ToadyListFragment();
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
    }


    @Override
    public void initData() {
        List<UserList> userLists = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userLists.add(new UserList());
        }
        userListAdapter = new TodayListAdapter(userLists);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(userListAdapter);
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
