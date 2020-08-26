package com.zhowin.miyou.recommend.fragment;

import android.os.Bundle;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.base_library.base.BaseLibFragment;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.IncludeUserListFragmentLayoutBinding;

/**
 * author : zho
 * date  ：2020/8/26
 * desc ：
 */
public class UserListFragment extends BaseBindFragment<IncludeUserListFragmentLayoutBinding> {


    public static UserListFragment newInstance(int index) {
        UserListFragment fragment = new UserListFragment();
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
        mBinding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mBinding.refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void initData() {

    }
}
