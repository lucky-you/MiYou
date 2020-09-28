package com.zhowin.miyou.mine.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.base_library.utils.EmptyViewUtils;
import com.zhowin.base_library.utils.SizeUtils;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.base_library.view.RefreshLayout;
import com.zhowin.base_library.widget.GridSpacingItemDecoration;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.IncludeProsFragmentLayoutBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.mine.adapter.PropsListAdapter;
import com.zhowin.miyou.mine.callback.OnShopMallPropsClickListener;
import com.zhowin.miyou.mine.model.ShopMallPropsList;
import com.zhowin.miyou.recommend.callback.OnHitCenterClickListener;
import com.zhowin.miyou.recommend.dialog.HitCenterDialog;

import java.util.List;

/**
 * 道具的fragment
 */
public class PropsFragment extends BaseBindFragment<IncludeProsFragmentLayoutBinding> implements OnShopMallPropsClickListener {


    private int fragmentIndex;
    private PropsListAdapter propsListAdapter;
    private boolean isRefreshIng;
    private String propsName, propsId;


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
        mBinding.refreshLayout.setEnabled(false);
        setOnClick(R.id.clBottomBuyLayout);
    }

    @Override
    public void initData() {
        propsListAdapter = new PropsListAdapter();
        propsListAdapter.setFragmentIndex(fragmentIndex);
        propsListAdapter.setOnShopMallPropsClickListener(this::onPropsItemClick);
        switch (fragmentIndex) {
            case 0:
            case 2:
                mBinding.recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, SizeUtils.dp2px(6), false));
                mBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
                break;
            case 1:
                mBinding.recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, SizeUtils.dp2px(6), false));
                mBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
                break;
        }
        mBinding.recyclerView.setAdapter(propsListAdapter);
    }

    public RefreshLayout getRefreshView() {

        return mBinding.refreshLayout;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clBottomBuyLayout:
                showBuyPropsDialog();
                break;
        }
    }

    private void showBuyPropsDialog() {
        HitCenterDialog hitCenterDialog = new HitCenterDialog(mContext);
        hitCenterDialog.setDialogTitle("确定要购买" + propsName + "吗?");
        hitCenterDialog.show();
        hitCenterDialog.setOnHitCenterClickListener(new OnHitCenterClickListener() {
            @Override
            public void onCancelClick() {

            }

            @Override
            public void onDetermineClick() {
                userBuyProps();
            }
        });
    }

    private void userBuyProps() {
        showLoadDialog();
        HttpRequest.userBuyProps(this, propsId, 1, new HttpCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                dismissLoadDialog();
                ToastUtils.showCustomToast(mContext, "购买成功");
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);
            }
        });
    }

    private void getShopMallPropsList() {
        if (isRefreshIng) {
            showLoadDialog();
            mBinding.clBottomBuyLayout.setVisibility(View.GONE);
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

    @Override
    public void onPropsItemClick(boolean isSelect, int position, ShopMallPropsList itemPropsInfo) {
        if (isSelect) {
            mBinding.clBottomBuyLayout.setVisibility(View.VISIBLE);
            List<ShopMallPropsList.GoodsTimesBean> itemPropsList = itemPropsInfo.getGoodsTimes();
            if (itemPropsList != null && !itemPropsList.isEmpty()) {
                propsName = itemPropsInfo.getName();
                ShopMallPropsList.GoodsTimesBean itemGood = itemPropsList.get(0);
                int propsPriceValue = itemGood.getPrice();
                propsId = itemGood.getId();
                String propsEndTime;
                switch (itemGood.getTimeType()) {//物品期限 0：七天、1：一个月、2：三个月、3：一年、4：无限期
                    case 0:
                        propsEndTime = "7天";
                        break;
                    case 1:
                        propsEndTime = "一个月";
                        break;
                    case 2:
                        propsEndTime = "三个月";
                        break;
                    case 3:
                        propsEndTime = "一年";
                        break;
                    case 4:
                        propsEndTime = "无限期";
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + itemGood.getTimeType());
                }
                mBinding.tvEndTime.setText("(" + propsPriceValue + "钻石/" + propsEndTime + ")");
            }
        } else {
            mBinding.clBottomBuyLayout.setVisibility(View.GONE);
        }
    }
}
