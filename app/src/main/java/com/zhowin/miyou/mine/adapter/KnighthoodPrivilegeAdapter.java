package com.zhowin.miyou.mine.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.mine.model.KnighthoodList;


/**
 * 爵位特权
 */
public class KnighthoodPrivilegeAdapter extends BaseQuickAdapter<KnighthoodList, BaseViewHolder> {
    public KnighthoodPrivilegeAdapter() {
        super(R.layout.include_knighthood_privile_item_view);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, KnighthoodList item) {
        GlideUtils.loadObjectImage(mContext, item.getHeightPicture(), helper.getView(R.id.ivKnighthoodPhoto));
        helper.setText(R.id.tvKnighthoodTitle, item.getName())
                .setText(R.id.tvKnighthoodContent, "");
    }
}
