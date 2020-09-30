package com.zhowin.miyou.recommend.adapter;

import android.text.TextUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.model.UserLevelInfo;
import com.zhowin.base_library.model.UserRankInfo;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.base_library.utils.SetDrawableHelper;
import com.zhowin.miyou.R;
import com.zhowin.miyou.main.utils.GenderHelper;



/**
 * author : zho
 * date  ：2020/9/30
 * desc ：搜索用户结果
 */
public class SearchUserResultAdapter extends BaseQuickAdapter<UserInfo, BaseViewHolder> {
    public SearchUserResultAdapter() {
        super(R.layout.include_search_user_result_item_view);
    }


    @Override
    protected void convert(@NonNull BaseViewHolder helper, UserInfo item) {
        helper.setText(R.id.tvUserNickName, item.getAvatar())
                .setBackgroundRes(R.id.tvUserSex, GenderHelper.getSexBackground(item.getGender()))
                .setText(R.id.tvUserSex, String.valueOf(item.getAge()));
        TextView tvUserSex = helper.getView(R.id.tvUserSex);
        SetDrawableHelper.setLeftDrawable(mContext, tvUserSex, TextUtils.equals("男", item.getGender()), 2, R.drawable.man_icon, R.drawable.girl_icon);
        GlideUtils.loadUserPhotoForLogin(mContext, item.getProfilePictureKey(), helper.getView(R.id.civUserHeadPhoto));

        UserLevelInfo userItemLevelInf = item.getLevelObj();
        if (userItemLevelInf != null) {
            helper.setGone(R.id.tvUserLevel, 0 != userItemLevelInf.getLevel())
                    .setText(R.id.tvUserLevel, "v" + userItemLevelInf.getLevel());
        }
        UserRankInfo itemUserRank = item.getRank();
        if (itemUserRank != null) {
            helper.setGone(R.id.tvKnighthood, true)
                    .setText(R.id.tvKnighthood, itemUserRank.getRankName());
        } else {
            helper.setGone(R.id.tvKnighthood, false);
        }
    }
}
