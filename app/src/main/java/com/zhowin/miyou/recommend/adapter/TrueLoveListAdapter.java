package com.zhowin.miyou.recommend.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;
import com.zhowin.miyou.recommend.model.UserList;

import java.util.List;

/**
 * 真爱榜
 */
public class TrueLoveListAdapter extends BaseQuickAdapter<UserList, BaseViewHolder> {
    public TrueLoveListAdapter(@Nullable List<UserList> data) {
        super(R.layout.include_true_love_item_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, UserList item) {

    }
}
