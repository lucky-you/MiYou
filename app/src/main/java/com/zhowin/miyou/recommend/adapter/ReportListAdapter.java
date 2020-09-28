package com.zhowin.miyou.recommend.adapter;

import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;
import com.zhowin.miyou.recommend.callback.OnReportSelectClickListener;
import com.zhowin.miyou.recommend.model.ReportList;

import java.util.List;

/**
 * author : zho
 * date  ：2020/9/16
 * desc ：举报原因列表
 */
public class ReportListAdapter extends BaseQuickAdapter<ReportList, BaseViewHolder> {
    public ReportListAdapter() {
        super(R.layout.include_report_list_item_view);
    }

    private OnReportSelectClickListener onReportSelectClickListener;

    public void setOnReportSelectClickListener(OnReportSelectClickListener onReportSelectClickListener) {
        this.onReportSelectClickListener = onReportSelectClickListener;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ReportList item) {
        helper.setText(R.id.tvReportItemTitle, item.getTitle())
                .setImageResource(R.id.ivReportSelect, item.isSelect() ? R.drawable.report_click_icon : R.drawable.report_unclick_icon)
                .getView(R.id.llReportItemLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (ReportList reportItem : mData) {
                    if (item.getId() == reportItem.getId()) {
                        item.setSelect(reportItem.isSelect());
                    } else {
                        reportItem.setSelect(false);
                    }
                }
                item.setSelect(!item.isSelect());
                if (onReportSelectClickListener != null) {
                    onReportSelectClickListener.onReportItemSelect(item.getId());
                }
                notifyDataSetChanged();
            }
        });
    }
}
