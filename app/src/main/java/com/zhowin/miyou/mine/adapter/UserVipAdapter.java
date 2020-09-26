package com.zhowin.miyou.mine.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;

/**
 * 用户vip
 */
public class UserVipAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public UserVipAdapter() {
        super(R.layout.include_user_vip_item_view);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {

    }
}
