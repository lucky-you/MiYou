package com.zhowin.miyou.mine.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.miyou.R;


/**
 * 搜索公会用户的adapter
 */
public class GuildSearchUserAdapter extends BaseQuickAdapter<UserInfo, BaseViewHolder> {
    public GuildSearchUserAdapter() {
        super(R.layout.include_uild_search_user_item_view);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, UserInfo item) {

    }
}
