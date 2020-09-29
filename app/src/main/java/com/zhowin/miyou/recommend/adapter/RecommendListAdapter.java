package com.zhowin.miyou.recommend.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.recommend.model.RecommendList;

import java.util.List;

/**
 * author : zho
 * date  ：2020/8/24
 * desc ：首页房间列表
 */
public class RecommendListAdapter extends BaseQuickAdapter<RecommendList, BaseViewHolder> {
    public RecommendListAdapter() {
        super(R.layout.include_recommens_list_item_view);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RecommendList item) {
        GlideUtils.loadObjectImage(mContext, item.getCoverPictureKey(), helper.getView(R.id.ivRoomBackGround));
        helper.setText(R.id.tvRoomType, item.getTypeName())
                .setText(R.id.tvRoomDescription, item.getTitle());

    }
}
