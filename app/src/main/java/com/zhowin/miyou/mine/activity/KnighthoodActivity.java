package com.zhowin.miyou.mine.activity;

import androidx.fragment.app.Fragment;

import com.gyf.immersionbar.ImmersionBar;
import com.zhowin.base_library.adapter.HomePageAdapter;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.model.UserRankInfo;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindActivity;
import com.zhowin.miyou.databinding.ActivityKnighthoodBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.mine.fragment.KnighthoodFragment;
import com.zhowin.miyou.mine.model.KnighthoodMessageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 爵位
 */
public class KnighthoodActivity extends BaseBindActivity<ActivityKnighthoodBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_knighthood;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        getKnighthoodMessageInfo();
    }

    private void getKnighthoodMessageInfo() {
        HttpRequest.getKnighthoodMessageInfo(this, new HttpCallBack<KnighthoodMessageInfo>() {
            @Override
            public void onSuccess(KnighthoodMessageInfo knighthoodMessageInfo) {
                if (knighthoodMessageInfo != null) {
                    List<String> mTitle = new ArrayList<>();
                    List<Fragment> mFragment = new ArrayList<>();
                    List<KnighthoodMessageInfo.RankInfosBean> knighthoodList = knighthoodMessageInfo.getRankInfos();
                    if (knighthoodList != null && !knighthoodList.isEmpty()) {
                        for (int i = 0; i < knighthoodList.size(); i++) {
                            UserRankInfo rankInfo = knighthoodList.get(i).getRank();
                            if (rankInfo != null) {
                                mTitle.add(rankInfo.getRankName());
                            }
                            mFragment.add(KnighthoodFragment.newInstance(i));
                        }
                        HomePageAdapter homePageAdapter = new HomePageAdapter(getSupportFragmentManager(), mFragment, mTitle);
                        mBinding.noScrollViewPager.setAdapter(homePageAdapter);
                        mBinding.noScrollViewPager.setScroll(true);
                        mBinding.slidingTabLayout.setViewPager(mBinding.noScrollViewPager);

                    }
                }

            }

            @Override
            public void onFail(int errorCode, String errorMsg) {

            }
        });
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .statusBarColor(R.color.color_2E2E30)
                .keyboardEnable(true)
                .statusBarDarkFont(true, 0f)
                .init();
    }
}