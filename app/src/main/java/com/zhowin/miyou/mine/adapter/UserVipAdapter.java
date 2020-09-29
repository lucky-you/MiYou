package com.zhowin.miyou.mine.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.model.UserLevelInfo;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.mine.model.VipPrivilegeList;

/**
 * 用户vip
 */
public class UserVipAdapter extends BaseQuickAdapter<VipPrivilegeList, BaseViewHolder> {
    public UserVipAdapter() {
        super(R.layout.include_user_vip_item_view);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, VipPrivilegeList item) {
        UserLevelInfo userLevel = UserInfo.getUserInfo().getLevelObj();
        if (userLevel != null) {
            if (userLevel.getLevel() >= item.getLevel()) {
                GlideUtils.loadObjectImage(mContext, item.getHeightPicture(), helper.getView(R.id.ivLevelImage));
            } else {
                GlideUtils.loadObjectImage(mContext, item.getGreyPicture(), helper.getView(R.id.ivLevelImage));
            }
        }
        helper.setText(R.id.tvPrivilegeName, item.getName())
                .setText(R.id.tvEndLevel, "VIP" + item.getLevel() + "解锁");
    }
}
