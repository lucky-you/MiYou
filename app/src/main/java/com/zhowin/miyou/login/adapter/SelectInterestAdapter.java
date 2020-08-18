package com.zhowin.miyou.login.adapter;

import android.view.View;

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

        helper.setText(R.id.tvLabelTitle, item.getLabelTitle())
                .setBackgroundRes(R.id.tvLabelTitle, item.isSelect() ? R.mipmap.data_interest_click_bg : R.mipmap.data_interest_bg)
                .setTextColor(R.id.tvLabelTitle, item.isSelect() ? getItemColor(R.color.white) : getItemColor(R.color.color_666))
                .getView(R.id.tvLabelTitle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setSelect(!item.isSelect());
                notifyDataSetChanged();
            }
        });
    }


    private int getItemColor(int color) {
        return mContext.getResources().getColor(color);
    }
}
