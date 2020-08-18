package com.zhowin.miyou.login.adapter;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.login.model.LabelList;

import java.util.List;


/**
 * 选择用户图像
 */
public class SelectUserAvatarAdapter extends BaseQuickAdapter<LabelList, BaseViewHolder> {
    public SelectUserAvatarAdapter(@Nullable List<LabelList> data) {
        super(R.layout.include_select_user_avatar_item_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, LabelList item) {
        GlideUtils.loadUserPhotoForLogin(mContext, item.getLabelTitle(), helper.getView(R.id.ivUserAvatar));
    }
}
