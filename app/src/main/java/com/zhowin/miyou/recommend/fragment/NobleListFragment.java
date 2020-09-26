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
import com.zhowin.miyou.databinding.IncludeNobleListFragmentBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.recommend.adapter.NobleListAdapter;
import com.zhowin.miyou.recommend.model.GZBUserList;

import java.util.List;

/**
 * 贵族榜单
 */
public class NobleListFragment extends BaseBindFragment<IncludeNobleListFragmentBinding> {


    private NobleListAdapter nobleListAdapter;
    private boolean isRefreshing;// 是否刷新


    public static NobleListFragment newInstance() {
        NobleListFragment fragment = new NobleListFragment();
        Bundle bundle = new Bundle();
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
        nobleListAdapter = new NobleListAdapter();
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(nobleListAdapter);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        getGZBUserList();
    }

    @Override
    public void initListener() {
        mBinding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefreshing = true;
                getGZBUserList();
                mBinding.refreshLayout.setRefreshing(false);
            }
        });
    }


    /**
     * 获取贵族帮
     */
    private void getGZBUserList() {
        if (isRefreshing) {
            showLoadDialog();
        }
        HttpRequest.getGZBUserList(this, new HttpCallBack<List<GZBUserList>>() {
            @Override
            public void onSuccess(List<GZBUserList> toadyUserLists) {
                dismissLoadDialog();
                isRefreshing = false;
                if (toadyUserLists != null && !toadyUserLists.isEmpty()) {
                    nobleListAdapter.setNewData(toadyUserLists);
                } else {
                    EmptyViewUtils.bindEmptyView(mContext, nobleListAdapter);
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
