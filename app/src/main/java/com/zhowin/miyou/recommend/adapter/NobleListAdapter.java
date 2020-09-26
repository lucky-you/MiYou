package com.zhowin.miyou.recommend.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.main.utils.GenderHelper;
import com.zhowin.miyou.recommend.model.GZBUserList;

/**
 * 贵族榜单
 */
public class NobleListAdapter extends BaseQuickAdapter<GZBUserList, BaseViewHolder> {

    public NobleListAdapter() {
        super(R.layout.include_noble_list_item_view);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GZBUserList item) {
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
            helper.setText(R.id.tvLevelText, helper.getAdapterPosition() + "")
                    .setBackgroundRes(R.id.tvLevelText, 0);
        }
        helper.setText(R.id.tvUserNickName, item.getAvatar())
                .setImageResource(R.id.ivUserSexImage, GenderHelper.getSexResource(item.getGender()));
        GlideUtils.loadUserPhotoForLogin(mContext, item.getProfilePictureKey(), helper.getView(R.id.civUserHeadPhoto));
        GZBUserList.LevelObjBean userLevelInfo = item.getLevelObj();
        if (userLevelInfo != null) {
            helper.setText(R.id.tvUserLevel, "V" + userLevelInfo.getLevel());
        }
        GZBUserList.RankBean userRankInfo = item.getRank();
        if (userRankInfo != null) {
            helper.setGone(R.id.ivUserKnightImage, true);
            GlideUtils.loadObjectImage(mContext, userRankInfo.getRankPictureKey(), helper.getView(R.id.ivUserKnightImage));
        } else {
            helper.setGone(R.id.ivUserKnightImage, false);
        }
    }
}
