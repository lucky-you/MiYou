package com.zhowin.miyou.recommend.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;

import java.util.List;

/**
 * author : zho
 * date  ：2020/10/24
 * desc ： 聊天室排麦列表的adapter
 */
public class AudienceListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public AudienceListAdapter(@Nullable List<String> data) {
        super(R.layout.include_room_audience_item_view,  data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {

    }
}
