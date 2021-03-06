package com.zhowin.miyou.mine.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.model.UserLevelInfo;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.base_library.utils.SetDrawableHelper;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindFragment;
import com.zhowin.miyou.databinding.MineFragmentLayoutBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.main.utils.GenderHelper;
import com.zhowin.miyou.mine.activity.AttentionAndFansActivity;
import com.zhowin.miyou.mine.activity.HelpOrFeedbackActivity;
import com.zhowin.miyou.mine.activity.KnighthoodActivity;
import com.zhowin.miyou.mine.activity.MyRoomActivity;
import com.zhowin.miyou.mine.activity.MyWalletActivity;
import com.zhowin.miyou.mine.activity.OnlineServiceActivity;
import com.zhowin.miyou.mine.activity.PersonalizedDressActivity;
import com.zhowin.miyou.mine.activity.SettingActivity;
import com.zhowin.miyou.mine.activity.SignInDrawActivity;
import com.zhowin.miyou.mine.activity.UnionActivity;
import com.zhowin.miyou.mine.activity.UserVipActivity;
import com.zhowin.miyou.mine.activity.VerifiedActivity;
import com.zhowin.miyou.mine.activity.VerifiedSuccessActivity;
import com.zhowin.miyou.mine.activity.YouthModeActivity;
import com.zhowin.miyou.mine.adapter.MineIconListAdapter;
import com.zhowin.miyou.mine.model.MineIconList;
import com.zhowin.miyou.mine.model.VerifiedStatus;
import com.zhowin.miyou.recommend.activity.HomepageActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的
 */
public class MineFragment extends BaseBindFragment<MineFragmentLayoutBinding> implements BaseQuickAdapter.OnItemClickListener {


    @Override
    public int getLayoutId() {
        return R.layout.mine_fragment_layout;
    }

    @Override
    public void initView() {
        setOnClick(R.id.ivSetting,
                R.id.llAttentionLayout, R.id.ivUserAvatar, R.id.llFansLayout, R.id.llVisitorsLayout,
                R.id.llWalletLayout, R.id.llKnighthoodLayout, R.id.llVipLayout);
        mBinding.mineRecyclerView.setNestedScrollingEnabled(false);
        mBinding.mineRecyclerView.setFocusable(false);

    }

    @Override
    public void initData() {
        setMineIconList();
    }


    @Override
    public void onResume() {
        super.onResume();
        getUserInfoMessage();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivSetting:
                startActivity(SettingActivity.class);
                break;
            case R.id.ivUserAvatar:
                HomepageActivity.start(mContext, true, UserInfo.getUserInfo().getUserId());
                break;
            case R.id.llAttentionLayout:
                AttentionAndFansActivity.start(mContext, 1);
                break;
            case R.id.llFansLayout:
                AttentionAndFansActivity.start(mContext, 2);
                break;
            case R.id.llVisitorsLayout:
                AttentionAndFansActivity.start(mContext, 3);
                break;
            case R.id.llWalletLayout:
                startActivity(MyWalletActivity.class);
                break;
            case R.id.llKnighthoodLayout:
                startActivity(KnighthoodActivity.class);
                break;
            case R.id.llVipLayout:
                startActivity(UserVipActivity.class);
                break;
        }
    }

    private void getUserInfoMessage() {
        HttpRequest.getUserInfoMessage(this, new HttpCallBack<UserInfo>() {
            @Override
            public void onSuccess(UserInfo userInfo) {
                if (userInfo != null) {
                    UserInfo.setUserInfo(userInfo);
                    setDataToViews(userInfo);
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {

            }
        });
    }

    private void setDataToViews(UserInfo userInfo) {
        GlideUtils.loadUserPhotoForLogin(mContext, userInfo.getProfilePictureKey(), mBinding.ivUserAvatar);
        mBinding.tvUserNickName.setText(userInfo.getAvatar());
        mBinding.tvMUNumber.setText("觅U号:" + userInfo.getUsername());
        mBinding.tvUserSex.setText(String.valueOf(userInfo.getAge()));
        mBinding.tvAttentionNumber.setText(String.valueOf(userInfo.getFollowNum()));
        mBinding.tvFansNumber.setText(String.valueOf(userInfo.getFansNum()));
        mBinding.tvVisitorNumber.setText(String.valueOf(userInfo.getVisitNum()));
        mBinding.tvUserSex.setBackgroundResource(GenderHelper.getSexBackground(userInfo.getGender()));
        int drawable = GenderHelper.getSexDrawable(userInfo.getGender());
        SetDrawableHelper.setLeftDrawable(mContext, mBinding.tvUserSex, true, 2, drawable, drawable);
        UserLevelInfo userLevel = userInfo.getLevelObj();
        if (userLevel != null) {
            mBinding.ivUserLevel.setVisibility(0 == userLevel.getLevel() ? View.GONE : View.VISIBLE);
            mBinding.ivUserLevel.setText("V" + userLevel.getLevel());
        }
    }


    private void setMineIconList() {
        List<MineIconList> mineIconList = new ArrayList<>();
        mineIconList.add(new MineIconList(R.drawable.mine_team_icon, "公会"));
        mineIconList.add(new MineIconList(R.drawable.mine_room_icon, "我的房间"));
        mineIconList.add(new MineIconList(R.drawable.mine_authen_icon, "实名认证"));
        mineIconList.add(new MineIconList(R.drawable.mine_shop_icon, "商城"));
        mineIconList.add(new MineIconList(R.drawable.mine_dressup_icon, "个性装扮"));
        mineIconList.add(new MineIconList(R.drawable.mine_in_icon, "签到抽奖"));
        mineIconList.add(new MineIconList(R.drawable.mine_sever_icon, "客服(在线)"));
        mineIconList.add(new MineIconList(R.drawable.mine_help_icon, "帮助与反馈"));
        mineIconList.add(new MineIconList(R.drawable.mine_young_icon, "青少年模式"));
        MineIconListAdapter mineIconListAdapter = new MineIconListAdapter(mineIconList);
        mBinding.mineRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.mineRecyclerView.setAdapter(mineIconListAdapter);
        mineIconListAdapter.setOnItemClickListener(this::onItemClick);

    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .statusBarColor(R.color.color_8E9DFD)
                .statusBarDarkFont(true, 0f)
                .init();
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position) {
            case 0:
                startActivity(UnionActivity.class);
                break;
            case 1:
                startActivity(MyRoomActivity.class);
                break;
            case 2:
                getVerifiedStatus();
                break;
            case 3:
                PersonalizedDressActivity.start(mContext, 1);
                break;
            case 4:
                PersonalizedDressActivity.start(mContext, 2);
                break;
            case 5:
                startActivity(SignInDrawActivity.class);
                break;
            case 6:
                startActivity(OnlineServiceActivity.class);
                break;
            case 7:
                startActivity(HelpOrFeedbackActivity.class);
                break;
            case 8:
//                checkYouthMode();
                YouthModeActivity.start(mContext, false);
                break;
        }
    }

    /**
     * 查询青少年模式
     */
    private void checkYouthMode() {
        showLoadDialog();
        HttpRequest.checkYouthMode(this, new HttpCallBack<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                dismissLoadDialog();
                YouthModeActivity.start(mContext, aBoolean);
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();

            }
        });
    }

    /**
     * 实名认证状态
     */
    private void getVerifiedStatus() {
        HttpRequest.getVerifiedStatus(this, new HttpCallBack<VerifiedStatus>() {
            @Override
            public void onSuccess(VerifiedStatus verifiedStatus) {
                if (verifiedStatus != null) {
                    //状态 0：审核中、1：审核通过、2：审核失败
                    VerifiedSuccessActivity.start(mContext, verifiedStatus.getStatus());
                } else {
                    startActivity(VerifiedActivity.class);
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                ToastUtils.showToast(errorMsg);
            }
        });
    }

}
