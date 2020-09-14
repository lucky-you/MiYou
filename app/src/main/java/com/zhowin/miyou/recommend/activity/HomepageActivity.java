package com.zhowin.miyou.recommend.activity;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.model.GiftAndCarList;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.model.UserInterestList;
import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.base_library.utils.SetDrawableHelper;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityHomepageBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.main.utils.GenderHelper;
import com.zhowin.miyou.mine.activity.PersonalInfoActivity;
import com.zhowin.miyou.recommend.adapter.HomePagerAdapter;
import com.zhowin.miyou.recommend.callback.OnReportAndAttentionListener;
import com.zhowin.miyou.recommend.callback.OnTopicTagClickListener;
import com.zhowin.miyou.recommend.dialog.ReportAndAttentionDialog;
import com.zhowin.miyou.recommend.dialog.ShareItemDialog;
import com.zhowin.miyou.recommend.model.HomePageCategoryList;
import com.zhowin.miyou.recommend.widget.SetTagsToViewHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人主页 自己和别人共用
 */
public class HomepageActivity extends BaseBindActivity<ActivityHomepageBinding> {


    private boolean isMine;
    private int userId;
    private HomePagerAdapter homePagerAdapter;
    private List<HomePageCategoryList> homePageCategoryLists = new ArrayList<>();

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
        userId = getIntent().getIntExtra(ConstantValue.ID, -1);
        mBinding.homePageRecyclerView.setFocusable(false);
        setOnClick(R.id.ivBackReturn, R.id.ivEditPersonal);
        mBinding.ivEditPersonal.setImageResource(isMine ? R.drawable.personal_add_icon : R.drawable.personal_more_icon);
        mBinding.llBottomAttentionLayout.setVisibility(isMine ? View.GONE : View.VISIBLE);
    }

    @Override
    public void initData() {
        homePagerAdapter = new HomePagerAdapter(homePageCategoryLists);
        mBinding.homePageRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.homePageRecyclerView.setAdapter(homePagerAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserInfoMessage();

    }


    private void getUserInfoMessage() {
        showLoadDialog();
        HttpRequest.getOtherUserInfoMessage(this, userId, new HttpCallBack<UserInfo>() {
            @Override
            public void onSuccess(UserInfo userInfo) {
                dismissLoadDialog();
                if (userInfo != null) {
                    setDataToViews(userInfo);
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);

            }
        });
    }

    private void setDataToViews(UserInfo userInfo) {
        GlideUtils.loadUserPhotoForLogin(mContext, userInfo.getProfilePictureKey(), mBinding.civUserHeadPhoto);
        mBinding.tvUserNickName.setText(userInfo.getAvatar());
        mBinding.tvUserMuNumber.setText("ID号:" + userInfo.getUsername());
        mBinding.tvUserSex.setText(String.valueOf(userInfo.getAge()));
        mBinding.tvUserSex.setBackgroundResource(GenderHelper.getSexBackground(userInfo.getGender()));
        int drawable = GenderHelper.getSexDrawable(userInfo.getGender());
        SetDrawableHelper.setLeftDrawable(mContext, mBinding.tvUserSex, true, 2, drawable, drawable);
        mBinding.tvUserSignText.setText("签名：" + userInfo.getStatus());
        String onLineStatus = "离线  " + userInfo.getFollowNum() + "关注" + "  •  " + userInfo.getFansNum() + "粉丝";
        mBinding.tvUserOnlineStatus.setText(onLineStatus);

        List<UserInterestList> userInterestList = userInfo.getLabelList();
        if (userInterestList != null && !userInterestList.isEmpty()) {
            SetTagsToViewHelper.setInterestListTagToView(mContext, mBinding.flInterestTagsLayout, userInterestList, new OnTopicTagClickListener() {
                @Override
                public void onTagsClick(boolean isTagsData, int tagId, String nickName) {

                }
            });
        }
        if (!homePageCategoryLists.isEmpty()) homePageCategoryLists.clear();
        List<GiftAndCarList> giftList = userInfo.getGifts();
        if (giftList != null && !giftList.isEmpty()) {
            homePageCategoryLists.add(new HomePageCategoryList("收到的礼物", giftList));
        }
        List<GiftAndCarList> userHeadList = userInfo.getDecorations();
        if (userHeadList != null && !userHeadList.isEmpty()) {
            homePageCategoryLists.add(new HomePageCategoryList("头像框", userHeadList));
        }
        List<GiftAndCarList> userCarList = userInfo.getCars();
        if (userCarList != null && !userCarList.isEmpty()) {
            homePageCategoryLists.add(new HomePageCategoryList("座驾", userCarList));
        }
        homePagerAdapter.setNewData(homePageCategoryLists);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBackReturn:
                ActivityManager.getAppInstance().finishActivity();
                break;
            case R.id.ivEditPersonal:
                if (isMine) {
                    startActivity(PersonalInfoActivity.class);
                } else {
                    showShareAndAttentionDialog();
                }
                break;
        }
    }

    private void showShareAndAttentionDialog() {
        ReportAndAttentionDialog reportAndAttentionDialog = new ReportAndAttentionDialog();
        reportAndAttentionDialog.show(getSupportFragmentManager(), "attention");
        reportAndAttentionDialog.setOnReportAndAttentionListener(new OnReportAndAttentionListener() {
            @Override
            public void onItemClick(int itemType) {
                if (4 == itemType) {
                    showShareDialog();
                }
            }
        });
    }

    private void showShareDialog() {
        ShareItemDialog shareItemDialog = new ShareItemDialog();
        shareItemDialog.show(getSupportFragmentManager(), "share");
        shareItemDialog.setOnReportAndAttentionListener(new OnReportAndAttentionListener() {
            @Override
            public void onItemClick(int itemType) {

            }
        });
    }

//    @Override
//    public void initImmersionBar() {
//        ImmersionBar.with(this)
//                .titleBar(mBinding.clTopView, false)
//                .transparentBar()
//                .init();
//    }
}
