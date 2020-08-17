package com.zhowin.miyou.login.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;
import com.zhowin.miyou.login.model.LabelList;

import java.util.List;

/**
 * 选择兴趣
 */
public class SelectInterestAdapter extends BaseQuickAdapter<LabelList, BaseViewHolder> {
    public SelectInterestAdapter(@Nullable List<LabelList> data) {
        super(R.layout.include_select_interst_item_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, LabelList item) {

    }
}
