package com.zhowin.miyou.recommend.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;

import java.util.List;

/**
 * author : zho
 * date  ：2020/9/19
 * desc ： 贡献榜单 / 魅力榜单  的 信息
 */
public class CharmInformationAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public CharmInformationAdapter(@Nullable List<String> data) {
        super( R.layout.include_charm_information_item_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {

    }
}
