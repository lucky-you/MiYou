package com.zhowin.miyou.recommend.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.model.UserLevelInfo;
import com.zhowin.base_library.model.UserRankInfo;
import com.zhowin.base_library.utils.EmptyViewUtils;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindFragment;
import com.zhowin.miyou.databinding.IncludeTodayFragmentBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.recommend.adapter.TodayListAdapter;
import com.zhowin.miyou.recommend.model.ToadyUserList;

import java.util.List;

/**
 * 日榜
 */
public class ToadyListFragment extends BaseBindFragment<IncludeTodayFragmentBinding> {

    private TodayListAdapter userListAdapter;
    private boolean is_MLB = true;//默认魅力榜
    private boolean isRefreshing;// 是否刷新

    public static ToadyListFragment newInstance() {
        ToadyListFragment fragment = new ToadyListFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.include_today_fragment;
    }

    @Override
    public void initView() {
        setOnClick(R.id.rbCharmList, R.id.rbContributionList);
        setButtonStatusView();
    }


    @Override
    public void initData() {
        userListAdapter = new TodayListAdapter();
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(userListAdapter);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        getTodayUserList();
    }

    @Override
    public void initListener() {
        mBinding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefreshing = true;
                getTodayUserList();
                mBinding.refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void initImmersionBar() {

    }

    private void getTodayUserList() {
        if (isRefreshing) {
            showLoadDialog();
        }
        HttpRequest.getTodayUserList(this, is_MLB, new HttpCallBack<List<ToadyUserList>>() {
            @Override
            public void onSuccess(List<ToadyUserList> toadyUserLists) {
                dismissLoadDialog();
                isRefreshing = false;
                if (toadyUserLists != null && !toadyUserLists.isEmpty()) {
                    mBinding.clTopNumberLayout.setVisibility(View.VISIBLE);
                    setTopUserDataToView(toadyUserLists);
                    if (toadyUserLists.size() > 3) {
                        List<ToadyUserList> spiltUserList = toadyUserLists.subList(3, toadyUserLists.size() - 1);
                        userListAdapter.setNewData(spiltUserList);
                    } else {
                        EmptyViewUtils.bindEmptyView(mContext, userListAdapter, R.drawable.empty_wusc_icon, "没有榜单数据");
                    }
                } else {
                    mBinding.clTopNumberLayout.setVisibility(View.GONE);
                    EmptyViewUtils.bindEmptyView(mContext, userListAdapter, R.drawable.empty_wusc_icon, "没有榜单数据");
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

    private void setTopUserDataToView(List<ToadyUserList> toadyUserLists) {
        if (1 == toadyUserLists.size()) {
            setNumberOneDate(toadyUserLists.get(0));
        } else if (2 == toadyUserLists.size()) {
            setNumberOneDate(toadyUserLists.get(0));
            setNumberTwoDate(toadyUserLists.get(1));
        } else if (3 >= toadyUserLists.size()) {
            setNumberOneDate(toadyUserLists.get(0));
            setNumberTwoDate(toadyUserLists.get(1));
            setNumberThreeDate(toadyUserLists.get(2));
        }
    }


    private void setNumberOneDate(ToadyUserList toadyUserList) {
        ToadyUserList.UserInfoBean userInfoBean = toadyUserList.getUserInfo();
        if (userInfoBean != null) {
            GlideUtils.loadUserPhotoForLogin(mContext, userInfoBean.getProfilePictureKey(), mBinding.civNumberOneHead);
            mBinding.tvNumberOneName.setText(userInfoBean.getAvatar());
            UserLevelInfo userLevelInfo = userInfoBean.getLevelObj();
            if (userLevelInfo != null) {
                mBinding.tvNumberOneLevel.setText("V" + userLevelInfo.getLevel());
            }
            UserRankInfo userRankInfo = userInfoBean.getRank();
            if (userRankInfo != null) {
                mBinding.tvNumberOneKnighthood.setVisibility(View.VISIBLE);
                mBinding.tvNumberOneKnighthood.setText(userRankInfo.getRankName());
            } else {
                mBinding.tvNumberOneKnighthood.setVisibility(View.GONE);
            }
            mBinding.tvNumberOneCharmValue.setText("魅力值：" + toadyUserList.getScore());
        }
    }

    private void setNumberTwoDate(ToadyUserList toadyUserList) {
        ToadyUserList.UserInfoBean userInfoBean = toadyUserList.getUserInfo();
        if (userInfoBean != null) {
            GlideUtils.loadUserPhotoForLogin(mContext, userInfoBean.getProfilePictureKey(), mBinding.civNumberTwoHead);
            mBinding.tvNumberTwoName.setText(userInfoBean.getAvatar());
            UserLevelInfo userLevelInfo = userInfoBean.getLevelObj();
            if (userLevelInfo != null) {
                mBinding.tvNumberTwoLevel.setText("V" + userLevelInfo.getLevel());
            }
            UserRankInfo userRankInfo = userInfoBean.getRank();
            if (userRankInfo != null) {
                mBinding.tvNumberTwoKnighthood.setVisibility(View.VISIBLE);
                mBinding.tvNumberTwoKnighthood.setText(userRankInfo.getRankName());
            } else {
                mBinding.tvNumberTwoKnighthood.setVisibility(View.GONE);
            }
            mBinding.tvNumberTwoCharmValue.setText("魅力值：" + toadyUserList.getScore());
        }
    }

    private void setNumberThreeDate(ToadyUserList toadyUserList) {
        ToadyUserList.UserInfoBean userInfoBean = toadyUserList.getUserInfo();
        if (userInfoBean != null) {
            GlideUtils.loadUserPhotoForLogin(mContext, userInfoBean.getProfilePictureKey(), mBinding.civNumberThreeHead);
            mBinding.tvNumberThreeName.setText(userInfoBean.getAvatar());
            UserLevelInfo userLevelInfo = userInfoBean.getLevelObj();
            if (userLevelInfo != null) {
                mBinding.tvNumberThreeLevel.setText("V" + userLevelInfo.getLevel());
            }
            UserRankInfo userRankInfo = userInfoBean.getRank();
            if (userRankInfo != null) {
                mBinding.tvNumberThreeKnighthood.setVisibility(View.VISIBLE);
                mBinding.tvNumberThreeKnighthood.setText(userRankInfo.getRankName());
            } else {
                mBinding.tvNumberThreeKnighthood.setVisibility(View.GONE);
            }
            mBinding.tvNumberThreeCharmValue.setText("魅力值：" + toadyUserList.getScore());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rbCharmList:
                //魅力榜
                is_MLB = true;
                break;
            case R.id.rbContributionList:
                //贡献榜
                is_MLB = false;
                break;
        }
        isRefreshing = true;
        setButtonStatusView();
        getTodayUserList();
    }


    private void setButtonStatusView() {
        if (is_MLB) {
            mBinding.rbCharmList.setBackground(mContext.getResources().getDrawable(R.drawable.shape_today_select_bg));
            mBinding.rbContributionList.setBackground(null);
            mBinding.rbCharmList.setTextColor(getBaseColor(R.color.color_13A1FB));
            mBinding.rbContributionList.setTextColor(getBaseColor(R.color.white));
            mBinding.rbCharmList.getPaint().setFakeBoldText(true);
            mBinding.rbContributionList.getPaint().setFakeBoldText(false);
        } else {
            mBinding.rbContributionList.setBackground(mContext.getResources().getDrawable(R.drawable.shape_today_select_bg));
            mBinding.rbCharmList.setBackground(null);
            mBinding.rbContributionList.setTextColor(getBaseColor(R.color.color_13A1FB));
            mBinding.rbCharmList.setTextColor(getBaseColor(R.color.white));
            mBinding.rbContributionList.getPaint().setFakeBoldText(true);
            mBinding.rbCharmList.getPaint().setFakeBoldText(false);
        }
    }
}
