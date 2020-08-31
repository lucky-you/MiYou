package com.zhowin.miyou.mine.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;
import com.zhowin.miyou.mine.model.BlackList;

import java.util.List;

/**
 * author : zho
 * date  ：2020/8/31
 * desc ： 黑名单
 */
public class BlackListAdapter extends BaseQuickAdapter<BlackList, BaseViewHolder> {
    public BlackListAdapter(@Nullable List<BlackList> data) {
        super( R.layout.include_black_list_item_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, BlackList item) {

    }
}
