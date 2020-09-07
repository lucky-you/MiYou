package com.zhowin.miyou.recommend.activity;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.gyf.immersionbar.ImmersionBar;
import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityHomepageBinding;
import com.zhowin.miyou.mine.activity.PersonalInfoActivity;
import com.zhowin.miyou.recommend.adapter.HomePagerAdapter;
import com.zhowin.miyou.recommend.model.HomePageCategoryList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 个人主页 自己和别人共用
 */
public class HomepageActivity extends BaseBindActivity<ActivityHomepageBinding> {


    private boolean isMine;
    private int userId;

    private HomePagerAdapter homePagerAdapter;

    public static void start(Context context, boolean isMine, int userId) {
        Intent intent = new Intent(context, HomepageActivity.class);
        intent.putExtra(ConstantValue.TYPE, isMine);
        intent.putExtra(ConstantValue.ID, userId);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_homepage;
    }

    @Override
    public void initView() {
        isMine = getIntent().getBooleanExtra(ConstantValue.TYPE, false);
        userId = getIntent().getIntExtra(ConstantValue.TYPE, -1);

        setOnClick(R.id.ivBackReturn, R.id.ivEditPersonal);
        mBinding.llBottomAttentionLayout.setVisibility(isMine ? View.GONE : View.VISIBLE);
    }

    @Override
    public void initData() {
        setAdapterData();
    }


    private void setAdapterData() {

        List<HomePageCategoryList> homePageCategoryLists = new ArrayList<>();
        homePageCategoryLists.add(new HomePageCategoryList("收到的礼物", Arrays.asList("告白气球", "告白气球", "告白气球", "告白气球", "告白气球")));
        homePageCategoryLists.add(new HomePageCategoryList("头像框", Arrays.asList("熊熊框", "熊熊框", "熊熊框", "熊熊框", "熊熊框")));
        homePageCategoryLists.add(new HomePageCategoryList("座驾", Arrays.asList("三轮宝马", "三轮宝马", "三轮宝马", "三轮宝马", "三轮宝马")));
        homePagerAdapter = new HomePagerAdapter(homePageCategoryLists);
        mBinding.homePageRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.homePageRecyclerView.setAdapter(homePagerAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBackReturn:
                ActivityManager.getAppInstance().finishActivity();
                break;
            case R.id.ivEditPersonal:
                if (isMine)
                    startActivity(PersonalInfoActivity.class);
                break;
        }
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .titleBar(mBinding.clTopView, false)
                .transparentBar()
                .init();
    }
}
