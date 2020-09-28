package com.zhowin.miyou.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.zhowin.base_library.adapter.HomePageAdapter;
import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.base_library.utils.BarUtils;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.base_library.utils.SizeUtils;
import com.zhowin.base_library.widget.BlurTransformation;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityPersonalizedDressBinding;
import com.zhowin.miyou.mine.fragment.PropsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 商城 和 个性装扮 共用
 * type  1:商城  2：个性装扮
 */
public class PersonalizedDressActivity extends BaseBindActivity<ActivityPersonalizedDressBinding> {


    private int classType;//类型
    private boolean isScrollBottom;//是否滑动到顶部

    public static void start(Context mContext, int type) {
        Intent intent = new Intent(mContext, PersonalizedDressActivity.class);
        intent.putExtra(ConstantValue.TYPE, type);
        mContext.startActivity(intent);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_personalized_dress;
    }

    @Override
    public void initView() {
        classType = getIntent().getIntExtra(ConstantValue.TYPE, -1);
        setOnClick(R.id.ivBackReturn, R.id.tvShoppingMall);
        mBinding.tvTopTitle.setText(1 == classType ? "商城" : "个性装扮");
        mBinding.tvShoppingMall.setText(1 == classType ? "装扮" : "商城");
        UserInfo userInfo = UserInfo.getUserInfo();
        String userAvatar = userInfo.getProfilePictureKey();
        if (!TextUtils.isEmpty(userAvatar)) {
            GlideUtils.loadUserPhotoForLogin(mContext, userAvatar, mBinding.civUserHeadPhoto);
            loadShopMallBackground(mContext, userAvatar, mBinding.ivUserHeadBackground);
        }
    }

    /**
     * 对图片做高斯模糊处理
     */
    public void loadShopMallBackground(Context context, Object photoUrl, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.shop_bg)
                .error(R.drawable.shop_bg)
                .transform(new BlurTransformation(context, 25))
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(context)
                .load(photoUrl)
                .apply(options)
                .into(imageView);
    }

    @Override
    public void initData() {
        String[] mTitle = {"头像框", "座驾", "道具"};
        List<Fragment> mFragments = new ArrayList<>();
        for (int i = 0; i < mTitle.length; i++) {
            mFragments.add(PropsFragment.newInstance(i));
        }
        HomePageAdapter homePageAdapter = new HomePageAdapter(getSupportFragmentManager(), mFragments, mTitle);
        mBinding.noScrollViewPager.setAdapter(homePageAdapter);
        mBinding.noScrollViewPager.setScroll(true);
        mBinding.slidingTabLayout.setViewPager(mBinding.noScrollViewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBackReturn:
                ActivityManager.getAppInstance().finishActivity();
                break;
            case R.id.tvShoppingMall:
                switch (classType) {
                    case 1://商城
                        PersonalizedDressActivity.start(mContext, 2);
                        break;
                    case 2://个性装扮
                        PersonalizedDressActivity.start(mContext, 1);
                        break;
                }
                break;

        }
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .titleBar(mBinding.clTopView)
                .init();
    }

    @Override
    public void initListener() {
        mBinding.appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (appBarLayout.getTotalScrollRange() == -verticalOffset) {
                    isScrollBottom = true;
                } else {
                    isScrollBottom = false;
                }
                if (isScrollBottom) {
                    setStatusBar(get(R.id.tabTopView), true);
                } else {
                    setStatusBar(get(R.id.tabTopView), false);
                }
            }
        });
    }

    public void setStatusBar(View view, boolean isShow) {
        if (view == null) return;
        BarUtils.setStatusBarLightMode(this, isShow);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                isShow ? BarUtils.getStatusBarHeight() : SizeUtils.dp2px(1));
        view.setLayoutParams(params);

    }
}