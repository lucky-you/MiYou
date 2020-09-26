package com.zhowin.miyou.recommend.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.main.utils.GenderHelper;
import com.zhowin.miyou.recommend.model.ToadyUserList;

/**
 * 日榜
 */
public class TodayListAdapter extends BaseQuickAdapter<ToadyUserList, BaseViewHolder> {
    public TodayListAdapter() {
        super(R.layout.include_today_list_item_view);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ToadyUserList item) {
        ToadyUserList.UserInfoBean itemUserInfo = item.getUserInfo();
        if (itemUserInfo != null) {
            helper.setText(R.id.tvLevelNumber, helper.getAdapterPosition() + "")
                    .setText(R.id.tvUserNickName, itemUserInfo.getAvatar())
                    .setText(R.id.tvUserCharmValue, "魅力值：" + item.getScore())
                    .setImageResource(R.id.ivUserSexImage, GenderHelper.getSexResource(itemUserInfo.getGender()))
                    .setGone(R.id.tvFollow, false);
            GlideUtils.loadUserPhotoForLogin(mContext, itemUserInfo.getProfilePictureKey(), helper.getView(R.id.civUserHeadPhoto));
            ToadyUserList.UserInfoBean.LevelObjBean userLevelInfo = itemUserInfo.getLevelObj();
            if (userLevelInfo != null) {
                helper.setText(R.id.tvUserLevel, "V" + userLevelInfo.getLevel());
            }
            ToadyUserList.UserInfoBean.RankBean userRankInfo = itemUserInfo.getRank();
            if (userRankInfo != null) {
                helper.setGone(R.id.tvUserKnighthood, true)
                        .setText(R.id.tvUserKnighthood, userRankInfo.getRankName());
            } else {
                helper.setGone(R.id.tvUserKnighthood, false);
            }
        }
    }
}
