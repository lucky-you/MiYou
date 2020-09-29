package com.zhowin.miyou.mine.adapter;

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
 * date  ：2020/9/2
 * desc ：我创建/收藏房间
 */
public class MyRoomListAdapter extends BaseQuickAdapter<RecommendList, BaseViewHolder> {
    public MyRoomListAdapter() {
        super(R.layout.include_my_room_list_item_view);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RecommendList item) {
        GlideUtils.loadObjectImage(mContext, item.getCoverPictureKey(), helper.getView(R.id.ivRoomBackGround));
        helper.setText(R.id.tvRoomDescription, item.getTitle())
                .setText(R.id.tvRoomType, item.getTypeName())
                .setGone(R.id.ivRoomLock, item.isExistPwd());
    }
}
