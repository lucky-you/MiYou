package com.zhowin.miyou.mine.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;
import com.zhowin.miyou.mine.model.RechargeList;

import java.util.List;

/**
 * 充值选项
 */
public class RechargeListAdapter extends BaseQuickAdapter<RechargeList, BaseViewHolder> {

    private int currentPosition;


    public RechargeListAdapter(@Nullable List<RechargeList> data) {
        super(R.layout.include_recharge_list_item_view, data);
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RechargeList item) {
        helper.setBackgroundRes(R.id.llItemRootLayout, currentPosition == helper.getAdapterPosition() ? R.drawable.shape_recharge_money_choosed_bg :
                R.drawable.shape_recharge_money_not_choose_bg)
                .setText(R.id.tvTopDiamond, item.getTopTitle() + "钻石")
                .setText(R.id.tvBottomMoney, "¥" + item.getBottomTitle());

    }
}
