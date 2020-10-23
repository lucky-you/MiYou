package com.zhowin.miyou.mine.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.model.UserRankInfo;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindFragment;
import com.zhowin.miyou.databinding.IncludeKnighthoodFragmentBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.mine.adapter.KnighthoodPrivilegeAdapter;
import com.zhowin.miyou.mine.model.KnighthoodList;
import com.zhowin.miyou.mine.model.KnighthoodMessageInfo;

import java.util.List;

/**
 * 爵位
 */
public class KnighthoodFragment extends BaseBindFragment<IncludeKnighthoodFragmentBinding> {


    private KnighthoodPrivilegeAdapter knighthoodPrivilegeAdapter;
    private int fragmentIndex, rankKnighthoodID;
    private boolean isRefreshIng;//刷新

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
        fragmentIndex = getArguments().getInt(ConstantValue.INDEX);
        setOnClick(R.id.tvRenewNow);
        getKnighthoodMessageInfo();
    }

    @Override
    public void initData() {

        knighthoodPrivilegeAdapter = new KnighthoodPrivilegeAdapter();
        mBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        mBinding.recyclerView.setAdapter(knighthoodPrivilegeAdapter);
    }

    private void getKnighthoodMessageInfo() {
        if (isRefreshIng) showLoadDialog();
        HttpRequest.getKnighthoodMessageInfo(this, new HttpCallBack<KnighthoodMessageInfo>() {
            @Override
            public void onSuccess(KnighthoodMessageInfo knighthoodMessageInfo) {
                dismissLoadDialog();
                isRefreshIng = false;
                if (knighthoodMessageInfo != null) {
                    List<KnighthoodMessageInfo.RankInfosBean> knighthoodInfoList = knighthoodMessageInfo.getRankInfos();
                    UserRankInfo rankInfo = knighthoodMessageInfo.getRank();
                    setRankBottomView(rankInfo);
                    if (knighthoodInfoList != null && !knighthoodInfoList.isEmpty()) {
                        List<KnighthoodList> knighthoodList = knighthoodInfoList.get(fragmentIndex).getRankPerms();
                        rankKnighthoodID = knighthoodInfoList.get(fragmentIndex).getRank().getRankId();
                        if (knighthoodList != null && !knighthoodList.isEmpty()) {
                            knighthoodPrivilegeAdapter.setNewData(knighthoodList);
                        }
                    }
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                isRefreshIng = false;
                ToastUtils.showToast(errorMsg);
            }
        });
    }

    private void setRankBottomView(UserRankInfo rankInfo) {
        if (rankInfo != null) {
            mBinding.tvRenewNow.setText("立即续费");
        } else {
            mBinding.tvRenewNow.setText("立即开通");
        }
    }

    @Override
    public void initListener() {
        mBinding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefreshIng = true;
                mBinding.refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvRenewNow:
                openKnighthoodLevel();
                break;
        }
    }

    private void openKnighthoodLevel() {
        showLoadDialog();
        HttpRequest.openKnighthoodLevel(this, rankKnighthoodID, new HttpCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                dismissLoadDialog();
                ToastUtils.showCustomToast(mContext, "成功");
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);
            }
        });
    }

    @Override
    public void initImmersionBar() {

    }
}
