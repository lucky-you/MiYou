package com.zhowin.miyou.recommend.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;
import com.zhowin.miyou.recommend.model.UserList;

import java.util.List;

/**
 * 周星榜单 礼物
 */
public class WeekStarGiftAdapter extends BaseQuickAdapter<UserList, BaseViewHolder> {
    public WeekStarGiftAdapter(@Nullable List<UserList> data) {
        super( R.layout.include_week_star_gift_item_view,  data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, UserList item) {

    }
}
