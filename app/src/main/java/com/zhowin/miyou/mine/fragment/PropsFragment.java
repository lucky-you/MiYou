package com.zhowin.miyou.mine.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.base_library.utils.EmptyViewUtils;
import com.zhowin.base_library.utils.SizeUtils;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.base_library.widget.GridSpacingItemDecoration;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.IncludeProsFragmentLayoutBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.mine.adapter.PropsListAdapter;
import com.zhowin.miyou.mine.model.ShopMallPropsList;

import java.util.List;

/**
 * 道具的fragment
 */
public class PropsFragment extends BaseBindFragment<IncludeProsFragmentLayoutBinding> {


    private int fragmentIndex;
    private PropsListAdapter propsListAdapter;
    private boolean isRefreshIng;


    public static PropsFragment newInstance(int index) {
        PropsFragment fragment = new PropsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_pros_fragment_layout;
    }

    @Override
    public void initView() {
        fragmentIndex = getArguments().getInt(ConstantValue.INDEX);
    }

    @Override
    public void initData() {
        propsListAdapter = new PropsListAdapter();
        propsListAdapter.setFragmentIndex(fragmentIndex);
        mBinding.recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, SizeUtils.dp2px(6), false));
        mBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        mBinding.recyclerView.setAdapter(propsListAdapter);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        getShopMallPropsList();
    }

    @Override
    public void initListener() {
        mBinding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefreshIng = true;
                getShopMallPropsList();
                mBinding.refreshLayout.setRefreshing(false);
            }
        });
    }


    private void getShopMallPropsList() {
        if (isRefreshIng) {
            showLoadDialog();
        }
        HttpRequest.getShopMallPropsList(this, fragmentIndex, new HttpCallBack<List<ShopMallPropsList>>() {
            @Override
            public void onSuccess(List<ShopMallPropsList> shopMallPropsLists) {
                dismissLoadDialog();
                isRefreshIng = false;
                if (shopMallPropsLists != null && !shopMallPropsLists.isEmpty()) {
                    propsListAdapter.setNewData(shopMallPropsLists);
                } else {
                    EmptyViewUtils.bindEmptyView(mContext, propsListAdapter, R.drawable.empty_wufs_icon, "暂无数据");
                }

            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                isRefreshIng = false;
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);
            }
        });
    }

    @Override
    public void initImmersionBar() {

    }
}
