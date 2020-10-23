package com.zhowin.miyou.recommend.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.utils.EmptyViewUtils;
import com.zhowin.base_library.utils.SetDrawableHelper;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindFragment;
import com.zhowin.miyou.databinding.IncludeWeekStarFragmentBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.recommend.adapter.WeekStarGiftAdapter;
import com.zhowin.miyou.recommend.model.WeekStarUserList;

import java.util.List;

/**
 * 周星榜
 */
public class WeekStarListFragment extends BaseBindFragment<IncludeWeekStarFragmentBinding> {


    private boolean is_MLB = true;//默认魅力榜
    private boolean isRefreshing;// 是否刷新
    private boolean is_LeftGiftItem = true;//显示左侧礼物数据
    private WeekStarGiftAdapter weekStarGiftAdapter;
    private List<WeekStarUserList.GiftARankingBean> leftGiftList; //左侧数据
    private List<WeekStarUserList.GiftARankingBean> rightGiftList;//右侧数据

    public static WeekStarListFragment newInstance() {
        WeekStarListFragment fragment = new WeekStarListFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_week_star_fragment;
    }

    @Override
    public void initView() {
        setOnClick(R.id.rbCharmList, R.id.rbContributionList, R.id.llLeftWeekLayout, R.id.llRightWeekLayout);
        setTopButtonStatusView();
        setBottomGiftItemLayoutView();
        getWeekStarUserList();
    }

    @Override
    public void initData() {

        weekStarGiftAdapter = new WeekStarGiftAdapter();
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(weekStarGiftAdapter);
    }

    @Override
    public void initListener() {
        mBinding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefreshing = true;
                getWeekStarUserList();
                mBinding.refreshLayout.setRefreshing(false);
            }
        });
    }

    /**
     * 获取周星榜
     */
    private void getWeekStarUserList() {
        if (isRefreshing) {
            showLoadDialog();
        }
        HttpRequest.getWeekStarUserList(this, is_MLB, new HttpCallBack<WeekStarUserList>() {
            @Override
            public void onSuccess(WeekStarUserList weekStarUserList) {
                dismissLoadDialog();
                isRefreshing = false;
                if (weekStarUserList != null) {
                    setDataToViews(weekStarUserList);
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

    private void setDataToViews(WeekStarUserList weekStarUserList) {
        mBinding.tvLeftWeekText.setText(weekStarUserList.getCuurentWeekGiftAName());
        mBinding.tvRightWeekText.setText(weekStarUserList.getCuurentWeekGiftBName());
        leftGiftList = weekStarUserList.getGiftARanking();
        rightGiftList = weekStarUserList.getGiftBRanking();
        setAdapterDataToView();

    }

    private void setAdapterDataToView() {
        if (is_LeftGiftItem) {
            if (leftGiftList != null && !leftGiftList.isEmpty()) {
                weekStarGiftAdapter.setNewData(leftGiftList);
            } else {
                EmptyViewUtils.bindEmptyView(mContext, weekStarGiftAdapter, R.drawable.empty_wusc_icon, "没有榜单数据");
            }
        } else {
            if (rightGiftList != null && !rightGiftList.isEmpty()) {
                weekStarGiftAdapter.setNewData(rightGiftList);
            } else {
                EmptyViewUtils.bindEmptyView(mContext, weekStarGiftAdapter, R.drawable.empty_wusc_icon, "没有榜单数据");
            }
        }

    }


    @Override
    public void initImmersionBar() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rbCharmList:
                //魅力榜
                is_MLB = true;
                isRefreshing = true;
                setTopButtonStatusView();
                getWeekStarUserList();
                break;
            case R.id.rbContributionList:
                //贡献榜
                is_MLB = false;
                isRefreshing = true;
                setTopButtonStatusView();
                getWeekStarUserList();
                break;
            case R.id.llLeftWeekLayout:
                is_LeftGiftItem = true;
                setBottomGiftItemLayoutView();
                setAdapterDataToView();
                break;
            case R.id.llRightWeekLayout:
                is_LeftGiftItem = false;
                setBottomGiftItemLayoutView();
                setAdapterDataToView();
                break;
        }


    }

    /**
     * 顶部的魅力榜和贡献榜切换
     */
    private void setTopButtonStatusView() {
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

    /**
     * 改变底部礼物的布局
     */
    private void setBottomGiftItemLayoutView() {
        if (is_LeftGiftItem) {
            mBinding.tvLeftWeekText.getPaint().setFakeBoldText(true);
            mBinding.tvLeftWeekText.setTextColor(getBaseColor(R.color.color_343434));
            SetDrawableHelper.setBottomDrawable(mContext, mBinding.tvLeftWeekText, true, 2, R.drawable.shape_week_star_bottom_check_bg, R.drawable.shape_week_star_bottom_uncheck_bg);

            mBinding.tvRightWeekText.getPaint().setFakeBoldText(false);
            mBinding.tvRightWeekText.setTextColor(getBaseColor(R.color.color_666));
            SetDrawableHelper.setBottomDrawable(mContext, mBinding.tvRightWeekText, false, 2, R.drawable.shape_week_star_bottom_check_bg, R.drawable.shape_week_star_bottom_uncheck_bg);

        } else {
            mBinding.tvLeftWeekText.getPaint().setFakeBoldText(false);
            mBinding.tvLeftWeekText.setTextColor(getBaseColor(R.color.color_666));
            SetDrawableHelper.setBottomDrawable(mContext, mBinding.tvLeftWeekText, false, 2, R.drawable.shape_week_star_bottom_check_bg, R.drawable.shape_week_star_bottom_uncheck_bg);

            mBinding.tvRightWeekText.getPaint().setFakeBoldText(true);
            mBinding.tvRightWeekText.setTextColor(getBaseColor(R.color.color_343434));
            SetDrawableHelper.setBottomDrawable(mContext, mBinding.tvRightWeekText, true, 2, R.drawable.shape_week_star_bottom_check_bg, R.drawable.shape_week_star_bottom_uncheck_bg);
        }
    }
}
