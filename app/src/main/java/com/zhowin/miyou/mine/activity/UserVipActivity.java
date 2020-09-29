package com.zhowin.miyou.mine.activity;


import androidx.recyclerview.widget.GridLayoutManager;

import com.gyf.immersionbar.ImmersionBar;
import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.model.UserLevelInfo;
import com.zhowin.base_library.utils.EmptyViewUtils;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityUserVipBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.mine.adapter.UserVipAdapter;
import com.zhowin.miyou.mine.model.VipMessageInfo;
import com.zhowin.miyou.mine.model.VipPrivilegeList;

import java.util.List;

/**
 * 用户vip
 */
public class UserVipActivity extends BaseBindActivity<ActivityUserVipBinding> {


    private UserVipAdapter userVipAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_vip;
    }

    @Override
    public void initView() {
        getVipMessageInfo();
        UserInfo userInfo = UserInfo.getUserInfo();
        GlideUtils.loadUserPhotoForLogin(mContext, userInfo.getProfilePictureKey(), mBinding.civUserHeadPhoto);
    }

    @Override
    public void initData() {
        userVipAdapter = new UserVipAdapter();
        mBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        mBinding.recyclerView.setAdapter(userVipAdapter);

    }

    private void getVipMessageInfo() {
        showLoadDialog();
        HttpRequest.getVipMessageInfo(this, new HttpCallBack<VipMessageInfo>() {
            @Override
            public void onSuccess(VipMessageInfo vipMessageInfo) {
                dismissLoadDialog();
                if (vipMessageInfo != null) {
                    UserLevelInfo userLevel = vipMessageInfo.getLevel();
                    if (userLevel != null) {
                        mBinding.tvNextStepValue.setText("距离升到VIP" + (userLevel.getLevel() + 1) + "还需要" + (userLevel.getEndExpValue() - vipMessageInfo.getExp()) + "经验值");
                        mBinding.tvVipLeftValue.setText("VIP" + userLevel.getLevel());
                        mBinding.tvVipRightValue.setText("VIP" + (userLevel.getLevel() + 1));
                    }
                    List<VipPrivilegeList> privilegeList = vipMessageInfo.getPerms();
                    if (privilegeList != null && !privilegeList.isEmpty()) {
                        userVipAdapter.setNewData(privilegeList);
                    } else {
                        EmptyViewUtils.bindEmptyView(mContext, userVipAdapter, R.drawable.empty_wusc_icon, "暂无数据");
                    }
                }
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
        ImmersionBar.with(this)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .statusBarColor(R.color.color_2E2E30)
                .keyboardEnable(true)
                .statusBarDarkFont(true, 0f)
                .init();
    }
}