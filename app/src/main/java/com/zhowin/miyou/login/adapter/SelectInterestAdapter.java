package com.zhowin.miyou.login.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;
import com.zhowin.miyou.login.callback.OnLabelSelectListener;
import com.zhowin.miyou.login.model.LabelList;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择兴趣
 */
public class SelectInterestAdapter extends BaseQuickAdapter<LabelList, BaseViewHolder> {


    private OnLabelSelectListener onLabelSelectListener;

    public SelectInterestAdapter(@Nullable List<LabelList> data) {
        super(R.layout.include_select_interst_item_view, data);
    }

    public void setOnLabelSelectListener(OnLabelSelectListener onLabelSelectListener) {
        this.onLabelSelectListener = onLabelSelectListener;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, LabelList item) {
        helper.setText(R.id.tvLabelTitle, item.getLabelName())
                .setBackgroundRes(R.id.tvLabelTitle, item.isSelect() ? R.mipmap.data_interest_click_bg : R.mipmap.data_interest_bg)
                .setTextColor(R.id.tvLabelTitle, item.isSelect() ? getItemColor(R.color.white) : getItemColor(R.color.color_666))
                .getView(R.id.tvLabelTitle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setSelect(!item.isSelect());
                if (onLabelSelectListener != null) {
                    onLabelSelectListener.labelItemSelect(getSelectLabelList().size(), getSelectLabelID());
                }
                notifyDataSetChanged();
            }
        });
    }

    /**
     * @return 选中id的字符串
     */
    public String getSelectLabelID() {
        StringBuffer stringBuffer = new StringBuffer();
        String selectID = null;
        if (mData != null) {
            for (int i = 0; i < mData.size(); i++) {
                if (mData.get(i).isSelect()) {
                    stringBuffer.append(mData.get(i).getLabelId() + ",");
                }
            }
            if (stringBuffer.length() > 1) {
                selectID = stringBuffer.substring(0, stringBuffer.length() - 1);
            }
        }
        return selectID;
    }

    /**
     * @return 选中id的字符串
     */
    public List<String> getSelectLabelList() {
        List<String> labelIds = new ArrayList<>();
        if (mData != null) {
            for (int i = 0; i < mData.size(); i++) {
                if (mData.get(i).isSelect()) {
                    labelIds.add(String.valueOf(mData.get(i).getLabelId()));
                }
            }
        }
        return labelIds;
    }


    private int getItemColor(int color) {
        return mContext.getResources().getColor(color);
    }
}
