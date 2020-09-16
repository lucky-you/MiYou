package com.zhowin.miyou.recommend.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;

import java.util.List;

/**
 * author : zho
 * date  ：2020/9/16
 * desc ：广播交友
 */
public class BroadcastDatingAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public BroadcastDatingAdapter(@Nullable List<String> data) {
        super(R.layout.include_broadcast_dating_item_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {

    }
}
