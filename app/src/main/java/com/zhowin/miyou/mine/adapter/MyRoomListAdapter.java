package com.zhowin.miyou.mine.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;
import com.zhowin.miyou.recommend.model.RecommendList;

import java.util.List;

/**
 * author : zho
 * date  ：2020/9/2
 * desc ：我的房间
 */
public class MyRoomListAdapter extends BaseQuickAdapter<RecommendList, BaseViewHolder> {
    public MyRoomListAdapter(@Nullable List<RecommendList> data) {
        super(R.layout.include_my_room_list_item_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RecommendList item) {

        helper.setText(R.id.tvRoomDescription, item.getTitle());
//                .setText(R.id.tvRoomType, item.getTypeName())

    }
}
