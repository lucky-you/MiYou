package com.zhowin.miyou.mine.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;

import java.util.List;

/**
 * author : zho
 * date  ：2020/9/14
 * desc ： 公会列表
 */
public class GuildListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public GuildListAdapter(@Nullable List<String> data) {
        super(R.layout.include_guild_list_item_view,  data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {

    }


}
