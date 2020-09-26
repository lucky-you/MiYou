package com.zhowin.miyou.recommend.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.utils.EmptyViewUtils;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.IncludeUserListFragmentLayoutBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.recommend.adapter.GuardListAdapter;
import com.zhowin.miyou.recommend.model.GuardUserList;

import java.util.List;

/**
 * desc ：守护榜单
 */
public class GuardListFragment extends BaseBindFragment<IncludeUserListFragmentLayoutBinding> {


    private GuardListAdapter guardListAdapter;
    private boolean isRefreshing;// 是否刷新

    public static GuardListFragment newInstance() {
        GuardListFragment fragment = new GuardListFragment();
        Bundle bundle = new Bundle();
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
        guardListAdapter = new GuardListAdapter();
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(guardListAdapter);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        getGuardUserList();
    }

    @Override
    public void initListener() {
        mBinding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefreshing = true;
                getGuardUserList();
                mBinding.refreshLayout.setRefreshing(false);
            }
        });
    }

    /**
     * 获取贵族帮
     */
    private void getGuardUserList() {
        if (isRefreshing) {
            showLoadDialog();
        }
        HttpRequest.getGuardUserList(this, new HttpCallBack<List<GuardUserList>>() {
            @Override
            public void onSuccess(List<GuardUserList> toadyUserLists) {
                dismissLoadDialog();
                isRefreshing = false;
                if (toadyUserLists != null && !toadyUserLists.isEmpty()) {
                    guardListAdapter.setNewData(toadyUserLists);
                } else {
                    EmptyViewUtils.bindEmptyView(mContext, guardListAdapter);
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                isRefreshing = false;
                ToastUtils.showToast(errorMsg);
            }
        });
    }


    @Override
    public void initImmersionBar() {

    }
}
