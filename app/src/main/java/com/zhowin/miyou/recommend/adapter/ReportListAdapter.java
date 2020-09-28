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
 * desc ：举报原因列表
 */
public class ReportListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ReportListAdapter(@Nullable List<String> data) {
        super(R.layout.include_report_list_item_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.tvReportItemTitle, item);

    }
}
