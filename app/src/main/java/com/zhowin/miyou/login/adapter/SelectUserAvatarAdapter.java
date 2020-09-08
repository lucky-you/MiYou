package com.zhowin.miyou.login.adapter;


import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.base_library.utils.SizeUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.login.callback.OnUserAvatarClickListener;
import com.zhowin.miyou.login.model.DefaultImageList;
import com.zhowin.miyou.login.model.LabelList;
import com.zhowin.miyou.mine.model.RechargeList;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * 选择用户图像
 */
public class SelectUserAvatarAdapter extends BaseQuickAdapter<DefaultImageList, BaseViewHolder> {
    public SelectUserAvatarAdapter(@Nullable List<DefaultImageList> data) {
        super(R.layout.include_select_user_avatar_item_view, data);
    }

    private OnUserAvatarClickListener onUserAvatarClickListener;

    private int currentPosition;

    public void setOnUserAvatarClickListener(OnUserAvatarClickListener onUserAvatarClickListener) {
        this.onUserAvatarClickListener = onUserAvatarClickListener;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DefaultImageList item) {
        CircleImageView ivUserAvatar = helper.getView(R.id.ivUserAvatar);
        if (!item.isAlbumAdd()) {
            GlideUtils.loadUserPhotoForLogin(mContext, item.getPictureKey(), ivUserAvatar);
        } else {
            ivUserAvatar.setImageResource(R.mipmap.gender_head_icon);
        }
        if (item.isSelect()) {
            ivUserAvatar.setBorderWidth(SizeUtils.dp2px(1));
            ivUserAvatar.setBorderColor(getItemColor(R.color.color_8E9DFD));
        } else {
            ivUserAvatar.setBorderWidth(0);
        }
        ivUserAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!item.isAlbumAdd()) {
                    for (DefaultImageList itemInfo : mData) {
                        if (TextUtils.equals(item.getPictureKey(), itemInfo.getPictureKey())) {
                            item.setSelect(itemInfo.isSelect());
                        } else {
                            itemInfo.setSelect(false);
                        }
                    }
                    item.setSelect(!item.isSelect());
                    if (onUserAvatarClickListener != null) {
                        onUserAvatarClickListener.onAvatarClick(false, item.isSelect(), item.getPictureKey());
                    }
                    notifyDataSetChanged();
                } else {
                    if (onUserAvatarClickListener != null) {
                        onUserAvatarClickListener.onAvatarClick(true, item.isSelect(), item.getPictureKey());
                    }
                }
            }
        });
    }

    private int getItemColor(int color) {
        return mContext.getResources().getColor(color);
    }
}
