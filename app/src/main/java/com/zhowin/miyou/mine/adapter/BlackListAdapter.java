package com.zhowin.miyou.mine.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.base_library.utils.DateHelpUtils;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.base_library.utils.SetDrawableHelper;
import com.zhowin.miyou.R;
import com.zhowin.miyou.main.utils.GenderHelper;
import com.zhowin.miyou.mine.callback.OnBlackListItemClickListener;
import com.zhowin.miyou.mine.model.AttentionUserList;

/**
 * author : zho
 * date  ：2020/8/31
 * desc ： 黑名单
 */
public class BlackListAdapter extends BaseQuickAdapter<AttentionUserList, BaseViewHolder> {


    private OnBlackListItemClickListener onBlackListItemClickListener;

    public void setOnBlackListItemClickListener(OnBlackListItemClickListener onBlackListItemClickListener) {
        this.onBlackListItemClickListener = onBlackListItemClickListener;
    }

    public BlackListAdapter() {
        super(R.layout.include_black_list_item_view);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, AttentionUserList item) {
        GlideUtils.loadUserPhotoForLogin(mContext, item.getProfilePictureKey(), helper.getView(R.id.civUserHeadPhoto));
        helper.setText(R.id.tvUserNickName, item.getAvatar())
                .setBackgroundRes(R.id.tvUserAge, GenderHelper.getSexBackground(item.getGender()))
                .setText(R.id.tvUserAge, String.valueOf(item.getAge()))
                .setText(R.id.tvCreateTime, DateHelpUtils.getStrTimeNotSeconds(item.getOperateTime()))
                .getView(R.id.tvRemove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBlackListItemClickListener != null) {
                    onBlackListItemClickListener.onRemoveBlackList(helper.getAdapterPosition(), item.getUserId());
                }
            }
        });
        TextView tvUserAge = helper.getView(R.id.tvUserAge);
        SetDrawableHelper.setLeftDrawable(mContext, tvUserAge, TextUtils.equals("男", item.getGender()), 2, R.drawable.man_icon, R.drawable.girl_icon);

    }
}
