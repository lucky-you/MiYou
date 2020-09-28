package com.zhowin.miyou.recommend.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.model.UserLevelInfo;
import com.zhowin.base_library.model.UserRankInfo;
import com.zhowin.base_library.utils.EmptyViewUtils;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.IncludeTrueLoveListFragmentBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.main.utils.GenderHelper;
import com.zhowin.miyou.recommend.adapter.TrueLoveListAdapter;
import com.zhowin.miyou.recommend.model.ZABUserList;

import java.util.List;

/**
 * 真爱榜单
 */
public class TrueLoveListFragment extends BaseBindFragment<IncludeTrueLoveListFragmentBinding> {

    private TrueLoveListAdapter trueLoveListAdapter;
    private boolean isRefreshing;// 是否刷新

    public static TrueLoveListFragment newInstance() {
        TrueLoveListFragment fragment = new TrueLoveListFragment();
        Bundle bundle = new Bundle();
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
        trueLoveListAdapter = new TrueLoveListAdapter();
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(trueLoveListAdapter);

    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        getZABUserList();
    }

    @Override
    public void initListener() {
        mBinding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefreshing = true;
                getZABUserList();
                mBinding.refreshLayout.setRefreshing(false);
            }
        });
    }


    private void getZABUserList() {
        if (isRefreshing) {
            showLoadDialog();
        }
        HttpRequest.getZABUserList(this, new HttpCallBack<List<ZABUserList>>() {
            @Override
            public void onSuccess(List<ZABUserList> zabUserLists) {
                dismissLoadDialog();
                isRefreshing = false;
                if (zabUserLists != null && !zabUserLists.isEmpty()) {
                    setDateToViews(zabUserLists.get(0));
                    trueLoveListAdapter.setNewData(zabUserLists);
                } else {
                    EmptyViewUtils.bindEmptyView(mContext, trueLoveListAdapter);
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


    private void setDateToViews(ZABUserList zabUserLists) {
        ZABUserList.GiverBean itemGiverInfo = zabUserLists.getGiver();//守护人
        if (itemGiverInfo != null) {
            GlideUtils.loadUserPhotoForLogin(mContext, itemGiverInfo.getProfilePictureKey(), mBinding.civInitiatorHead);
            mBinding.ivInitiatorSexImage.setImageResource(GenderHelper.getSexResource(itemGiverInfo.getGender()));
            mBinding.tvInitiatorUserNickName.setText(itemGiverInfo.getAvatar());
            UserLevelInfo itemInitiatorLevelInfo = itemGiverInfo.getLevelObj();
            if (itemInitiatorLevelInfo != null) {
                mBinding.tvInitiatorUserLevel.setText("v" + itemInitiatorLevelInfo.getLevel());
            }
            UserRankInfo itemInitiatorRankInfo = itemGiverInfo.getRank();
            if (itemInitiatorRankInfo != null) {
                mBinding.tvInitiatorUserKnighthood.setVisibility(View.VISIBLE);
                mBinding.tvAccepterUserKnighthood.setText(itemInitiatorRankInfo.getRankName());
            } else {
                mBinding.tvInitiatorUserKnighthood.setVisibility(View.GONE);
            }
        }
        ZABUserList.AccepterBean itemAccepterInfo = zabUserLists.getAccepter();//被守护者
        if (itemAccepterInfo != null) {
            GlideUtils.loadUserPhotoForLogin(mContext, itemAccepterInfo.getProfilePictureKey(), mBinding.civAccepterHead);
            mBinding.ivAccepterSexImage.setImageResource(GenderHelper.getSexResource(itemAccepterInfo.getGender()));
            mBinding.tvAccepterNickName.setText(itemAccepterInfo.getAvatar());

            UserLevelInfo itemAccepterUserLevelInfo = itemAccepterInfo.getLevelObj();
            if (itemAccepterUserLevelInfo != null) {
                mBinding.tvAccepterUserLevel.setText("v" + itemAccepterUserLevelInfo.getLevel());
            }
            UserRankInfo itemAccepterUserRankInfo = itemAccepterInfo.getRank();
            if (itemAccepterUserRankInfo != null) {
                mBinding.tvAccepterUserKnighthood.setVisibility(View.VISIBLE);
                mBinding.tvAccepterUserKnighthood.setText(itemAccepterUserRankInfo.getRankName());
            } else {
                mBinding.tvAccepterUserKnighthood.setVisibility(View.GONE);
            }
        }
        ZABUserList.GiftBean itemGift = zabUserLists.getGift();
        if (itemGift != null) {
            GlideUtils.loadObjectImage(mContext, itemGift.getGiftPictureKey(), mBinding.ivGiftPhoto);
            mBinding.tvGiftNumber.setText("x" + zabUserLists.getNumber());
            mBinding.tvCreateTime.setText("6分钟前");
        }
    }

    @Override
    public void initImmersionBar() {

    }

}
