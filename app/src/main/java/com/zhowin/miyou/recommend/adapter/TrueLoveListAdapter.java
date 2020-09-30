package com.zhowin.miyou.recommend.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.base_library.model.UserLevelInfo;
import com.zhowin.base_library.model.UserRankInfo;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.main.utils.GenderHelper;
import com.zhowin.miyou.recommend.model.ZABUserList;

/**
 * 真爱榜
 */
public class TrueLoveListAdapter extends BaseQuickAdapter<ZABUserList, BaseViewHolder> {
    public TrueLoveListAdapter() {
        super(R.layout.include_true_love_item_view);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ZABUserList item) {
        ZABUserList.GiverBean itemGiverInfo = item.getGiver();//守护人
        if (itemGiverInfo != null) {
            GlideUtils.loadUserPhotoForLogin(mContext, itemGiverInfo.getProfilePictureKey(), helper.getView(R.id.civInitiatorHead));
            helper.setImageResource(R.id.ivAccepterSexImage, GenderHelper.getSexResource(itemGiverInfo.getGender()))
                    .setText(R.id.tvUserNickName, itemGiverInfo.getAvatar());

            UserLevelInfo userLevelInfo = itemGiverInfo.getLevelObj();
            if (userLevelInfo != null) {
                helper.setVisible(R.id.tvUserLevel, 0 != userLevelInfo.getLevel())
                        .setText(R.id.tvUserLevel, "V" + userLevelInfo.getLevel());
            }
            UserRankInfo userRankInfo = itemGiverInfo.getRank();
            if (userRankInfo != null) {
                helper.setGone(R.id.tvUserKnighthood, true)
                        .setText(R.id.tvUserKnighthood, userRankInfo.getRankName());
            } else {
                helper.setGone(R.id.tvUserKnighthood, false);
            }
        }
        ZABUserList.AccepterBean itemAccepterInfo = item.getAccepter();//被守护者
        if (itemAccepterInfo != null) {
            GlideUtils.loadUserPhotoForLogin(mContext, itemAccepterInfo.getProfilePictureKey(), helper.getView(R.id.civAccepterHead));
            helper.setImageResource(R.id.ivAccepterSexImage, GenderHelper.getSexResource(itemAccepterInfo.getGender()));
        }
        ZABUserList.GiftBean itemGift = item.getGift();
        if (itemGift != null) {
            GlideUtils.loadObjectImage(mContext, itemGift.getGiftPictureKey(), helper.getView(R.id.ivGiftImage));
            helper.setText(R.id.tvGiftNumber, "x" + item.getNumber())
                    .setText(R.id.tvCreateTime, "7分钟前");
        }
    }
}
