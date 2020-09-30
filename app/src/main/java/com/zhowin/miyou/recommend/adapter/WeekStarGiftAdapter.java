package com.zhowin.miyou.recommend.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.base_library.model.UserLevelInfo;
import com.zhowin.base_library.model.UserRankInfo;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.main.utils.GenderHelper;
import com.zhowin.miyou.recommend.model.WeekStarUserList;

/**
 * 周星榜单 礼物
 */
public class WeekStarGiftAdapter extends BaseQuickAdapter<WeekStarUserList.GiftARankingBean, BaseViewHolder> {


    public WeekStarGiftAdapter() {
        super(R.layout.include_week_star_gift_item_view);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, WeekStarUserList.GiftARankingBean item) {
        if (0 == helper.getAdapterPosition()) {
            helper.setBackgroundRes(R.id.tvLevelText, R.drawable.list_shouhu1_iocn)
                    .setText(R.id.tvLevelText, "");
        } else if (1 == helper.getAdapterPosition()) {
            helper.setBackgroundRes(R.id.tvLevelText, R.drawable.list_shouhu2_iocn)
                    .setText(R.id.tvLevelText, "");
        } else if (2 == helper.getAdapterPosition()) {
            helper.setBackgroundRes(R.id.tvLevelText, R.drawable.list_shouhu3_iocn)
                    .setText(R.id.tvLevelText, "");
        } else {
            helper.setText(R.id.tvLevelText, String.valueOf(helper.getAdapterPosition() + 1))
                    .setBackgroundRes(R.id.tvLevelText, 0);
        }
        WeekStarUserList.GiftARankingBean.UserInfoBean weekStarWeekUserInfo = item.getUserInfo();
        if (weekStarWeekUserInfo != null) {
            GlideUtils.loadUserPhotoForLogin(mContext, weekStarWeekUserInfo.getProfilePictureKey(), helper.getView(R.id.civUserHeadPhoto));
            helper.setText(R.id.tvUserNickName, weekStarWeekUserInfo.getAvatar())
                    .setImageResource(R.id.ivUserSexImage, GenderHelper.getSexResource(weekStarWeekUserInfo.getGender()))
                    .setGone(R.id.tvFollow, false)
                    .setText(R.id.tvGiftName, "送出 " + item.getScore());
            UserLevelInfo userLevel = weekStarWeekUserInfo.getLevelObj();
            if (userLevel != null) {
                helper.setGone(R.id.tvUserLevel, 0 != userLevel.getLevel())
                        .setText(R.id.tvUserLevel, "V" + userLevel.getLevel());
            }
            UserRankInfo userRank = weekStarWeekUserInfo.getRank();
            if (userRank != null) {
                helper.setGone(R.id.tvUserKnighthood, true)
                        .setText(R.id.tvUserKnighthood, userRank.getRankName());
            } else {
                helper.setGone(R.id.tvUserKnighthood, false);
            }
        }
    }
}
