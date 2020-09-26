package com.zhowin.miyou.mine.adapter;

import android.text.TextUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.base_library.utils.SetDrawableHelper;
import com.zhowin.miyou.R;
import com.zhowin.miyou.main.utils.GenderHelper;
import com.zhowin.miyou.mine.model.AttentionUserList;

/**
 * author : zho
 * date  ：2020/9/1
 * desc ：
 */
public class AttentionAndFansAdapter extends BaseQuickAdapter<AttentionUserList, BaseViewHolder> {

    private int adapterType = 1;

    public AttentionAndFansAdapter() {
        super(R.layout.include_attention_and_fans_item_view);
    }

    public void setAdapterType(int adapterType) {
        this.adapterType = adapterType;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, AttentionUserList item) {
        helper
                .setGone(R.id.tvCreateTime, 3 == adapterType)
                .setGone(R.id.tvMutualAttention, 3 != adapterType);
        GlideUtils.loadUserPhotoForLogin(mContext, item.getProfilePictureKey(), helper.getView(R.id.civUserHeadPhoto));
        helper.setText(R.id.tvUserNickName, item.getAvatar())
                .setBackgroundRes(R.id.tvUserSex, GenderHelper.getSexBackground(item.getGender()))
                .setText(R.id.tvUserSex, item.getAge() + "");
        TextView tvUserSex = helper.getView(R.id.tvUserSex);
        SetDrawableHelper.setLeftDrawable(mContext, tvUserSex, TextUtils.equals("男", item.getGender()), 2, R.drawable.man_icon, R.drawable.girl_icon);

        AttentionUserList.LevelObjBean userItemLevelInf = item.getLevelObj();
        if (userItemLevelInf != null) {
            helper.setText(R.id.tvUserLevel, "v" + userItemLevelInf.getLevel());
        }
        AttentionUserList.RankBean itemUserRank = item.getRank();
        if (itemUserRank != null) {
            helper.setGone(R.id.tvKnighthood, true)
                    .setText(R.id.tvKnighthood, itemUserRank.getRankName());
        } else {
            helper.setGone(R.id.tvKnighthood, false);
        }
    }
}
