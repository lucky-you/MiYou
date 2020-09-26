package com.zhowin.miyou.recommend.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.main.utils.GenderHelper;
import com.zhowin.miyou.recommend.model.GuardUserList;

/**
 * 守护
 */
public class GuardListAdapter extends BaseQuickAdapter<GuardUserList, BaseViewHolder> {
    public GuardListAdapter() {
        super(R.layout.include_guard_list_item_view);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GuardUserList item) {

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

        GuardUserList.GiverBean itemGiverInfo = item.getGiver();//守护人
        if (itemGiverInfo != null) {
            GlideUtils.loadUserPhotoForLogin(mContext, itemGiverInfo.getProfilePictureKey(), helper.getView(R.id.civInitiatorHead));
            helper.setImageResource(R.id.ivAccepterSexImage, GenderHelper.getSexResource(itemGiverInfo.getGender()))
                    .setText(R.id.tvInitiatorNickName, itemGiverInfo.getAvatar())
                    .setText(R.id.tvInitiatorMLValue, item.getScore() + "");

            GuardUserList.GiverBean.LevelObjBean userLevelInfo = itemGiverInfo.getLevelObj();
            if (userLevelInfo != null) {
                helper.setText(R.id.tvInitiatorLevel, "V" + userLevelInfo.getLevel());
            }
            GuardUserList.GiverBean.RankBean userRankInfo = itemGiverInfo.getRank();
            if (userRankInfo != null) {
                helper.setGone(R.id.tvInitiatorKnighthood, true)
                        .setText(R.id.tvInitiatorKnighthood, userRankInfo.getRankName());
            } else {
                helper.setGone(R.id.tvInitiatorKnighthood, false);
            }

        }

        GuardUserList.AccepterBean itemAccepterInfo = item.getAccepter();//被守护者
        if (itemAccepterInfo != null) {
            GlideUtils.loadUserPhotoForLogin(mContext, itemAccepterInfo.getProfilePictureKey(), helper.getView(R.id.civAccepterHead));
            helper.setImageResource(R.id.ivAccepterSexImage, GenderHelper.getSexResource(itemAccepterInfo.getGender()));
        }

    }
}
