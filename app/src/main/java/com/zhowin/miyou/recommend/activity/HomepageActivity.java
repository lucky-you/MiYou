package com.zhowin.miyou.recommend.activity;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.gyf.immersionbar.ImmersionBar;
import com.zhowin.base_library.banner.BannerImageAdapter;
import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.model.GiftAndCarList;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.model.UserInterestList;
import com.zhowin.base_library.model.UserLevelInfo;
import com.zhowin.base_library.model.UserRankInfo;
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
import com.zhowin.miyou.recommend.callback.OnHitCenterClickListener;
import com.zhowin.miyou.recommend.callback.OnHomePageMoreGiftListener;
import com.zhowin.miyou.recommend.callback.OnReportAndAttentionListener;
import com.zhowin.miyou.recommend.callback.OnTopicTagClickListener;
import com.zhowin.miyou.recommend.dialog.HitCenterDialog;
import com.zhowin.miyou.recommend.dialog.ReportAndAttentionDialog;
import com.zhowin.miyou.recommend.dialog.ShareItemDialog;
import com.zhowin.miyou.recommend.model.HomePageCategoryList;
import com.zhowin.miyou.recommend.model.ReportUserOrRoom;
import com.zhowin.miyou.recommend.widget.SetTagsToViewHelper;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

/**
 * 个人主页 自己和别人共用
 */
public class HomepageActivity extends BaseBindActivity<ActivityHomepageBinding> implements OnHomePageMoreGiftListener {


    private boolean isMine;
    private int userId;
    private HomePagerAdapter homePagerAdapter;
    private List<HomePageCategoryList> homePageCategoryLists = new ArrayList<>();
    private String userNickName, userIDCode;
    private ReportUserOrRoom reportUserOrRoom = new ReportUserOrRoom();

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
        setOnClick(R.id.ivBackReturn, R.id.ivEditPersonal, R.id.tvAttention, R.id.tvChatWith);
        mBinding.ivEditPersonal.setImageResource(isMine ? R.drawable.personal_add_icon : R.drawable.personal_more_icon);
        mBinding.llBottomAttentionLayout.setVisibility(isMine ? View.GONE : View.VISIBLE);
        mBinding.tvIsLiving.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        homePagerAdapter = new HomePagerAdapter(homePageCategoryLists);
        mBinding.homePageRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.homePageRecyclerView.setAdapter(homePagerAdapter);
        homePagerAdapter.setOnHomePageMoreGiftListener(this::onSeeMoreItemClick);
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
        userNickName = userInfo.getAvatar();
        userIDCode = userInfo.getUsername();
        mBinding.tvUserNickName.setText(userNickName);
        mBinding.tvUserMuNumber.setText("ID号:" + userIDCode);
        mBinding.tvUserSex.setText(String.valueOf(userInfo.getAge()));
        mBinding.tvUserSex.setBackgroundResource(GenderHelper.getSexBackground(userInfo.getGender()));
        int drawable = GenderHelper.getSexDrawable(userInfo.getGender());
        SetDrawableHelper.setLeftDrawable(mContext, mBinding.tvUserSex, true, 2, drawable, drawable);
        if (!TextUtils.isEmpty(userInfo.getStatus()))
            mBinding.tvUserSignText.setText("签名：" + userInfo.getStatus());
        String onLineStatus = "离线  " + userInfo.getFollowNum() + "关注" + "  •  " + userInfo.getFansNum() + "粉丝";
        mBinding.tvUserOnlineStatus.setText(onLineStatus);
        reportUserOrRoom.setUserId(userId);
        reportUserOrRoom.setUserNickName(userNickName);
        reportUserOrRoom.setUserHeadPhoto(userInfo.getProfilePictureKey());
        reportUserOrRoom.setUserMUNumber("ID:" + userIDCode);
        reportUserOrRoom.setUserGender(userInfo.getGender());
        reportUserOrRoom.setUserAge(userInfo.getAge());
        UserLevelInfo userLevelInfo = userInfo.getLevelObj();
        if (userLevelInfo != null) {
            mBinding.ivUserLevel.setVisibility(0 == userLevelInfo.getLevel() ? View.GONE : View.VISIBLE);
        }
        UserRankInfo userRankInfo = userInfo.getRank();
        if (userRankInfo != null) {
            mBinding.ivUserKnight.setVisibility(View.VISIBLE);
            GlideUtils.loadObjectImage(mContext, userRankInfo.getRankPictureKey(), mBinding.ivUserKnight);
        } else {
            mBinding.ivUserKnight.setVisibility(View.GONE);
        }
        setUserBannerData(userInfo.getBackgroundPictureKeys());
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

    private void setUserBannerData(List<String> bannerList) {
        if (bannerList != null && !bannerList.isEmpty()) {
            mBinding.userHomeBanner.setAdapter(new BannerImageAdapter(bannerList, 1))
                    .isAutoLoop(false)
                    .start();
        } else {
            mBinding.userHomeBanner.setBackground(getBaseResources().getDrawable(R.drawable.mine_top_bg));
        }
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
            case R.id.tvAttention:

                break;
            case R.id.tvChatWith:
                RongIM.getInstance().startConversation(mContext, Conversation.ConversationType.PRIVATE, String.valueOf(userId), userNickName);
                break;
        }
    }

    private void showShareAndAttentionDialog() {
        String userNickNameAndId = userNickName + "(ID:" + userIDCode + ")";
        ReportAndAttentionDialog reportAndAttentionDialog = ReportAndAttentionDialog.newInstance(userNickNameAndId);
        reportAndAttentionDialog.show(getSupportFragmentManager(), "attention");
        reportAndAttentionDialog.setOnReportAndAttentionListener(new OnReportAndAttentionListener() {
            @Override
            public void onItemClick(int itemType) {
                switch (itemType) {
                    case 1://举报
                        ReportRoomActivity.start(mContext, 2, reportUserOrRoom);
                        break;
                    case 2://关注
                        addAttentionOrBlackList(1, userId);
                        break;
                    case 3://拉黑
                        showAddUserToBlackListDialog(userId);
                        break;
                    case 4://分享
                        showShareDialog();
                        break;
                }
            }
        });
    }

    /**
     * 拉黑
     */
    private void showAddUserToBlackListDialog(int userId) {
        String hitTitle = "确定要将" + userNickName + "加入黑名单吗?";
        HitCenterDialog hitCenterDialog = new HitCenterDialog(mContext);
        hitCenterDialog.setDialogTitle(hitTitle);
        hitCenterDialog.show();
        hitCenterDialog.setOnHitCenterClickListener(new OnHitCenterClickListener() {
            @Override
            public void onCancelClick() {

            }

            @Override
            public void onDetermineClick() {
                addAttentionOrBlackList(3, userId);
            }
        });
    }

    /**
     * 关注或者拉黑
     */
    private void addAttentionOrBlackList(int type, int userId) {
        showLoadDialog();
        HttpRequest.addAttentionOrBlackList(this, type, userId, new HttpCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                dismissLoadDialog();
                switch (type) {
                    case 1:
                        ToastUtils.showCustomToast(mContext, "关注成功");
                        break;
                    case 3:
                        ToastUtils.showCustomToast(mContext, "添加到黑名单成功");
                        break;
                }

            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);
            }
        });
    }

    /**
     * 分享
     */
    private void showShareDialog() {
        ShareItemDialog shareItemDialog = new ShareItemDialog();
        shareItemDialog.show(getSupportFragmentManager(), "share");
        shareItemDialog.setOnReportAndAttentionListener(new OnReportAndAttentionListener() {
            @Override
            public void onItemClick(int itemType) {

            }
        });
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarView(R.id.topView)
                .navigationBarColor(R.color.black)
                .fullScreen(true)
                .addTag("PicAndColor")
                .init();

    }

    @Override
    public void onSeeMoreItemClick(int position) {
        switch (position) {
            case 0:
                startActivity(GiftReceivedActivity.class);
                break;
            case 1:
                break;
            case 2:
                break;
        }
    }
}
