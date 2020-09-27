package com.zhowin.miyou.mine.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;
import com.zhowin.miyou.mine.model.ShopMallPropsList;

/**
 * 道具
 */
public class PropsListAdapter extends BaseQuickAdapter<ShopMallPropsList, BaseViewHolder> {

    private int fragmentIndex;

    public PropsListAdapter() {
        super(R.layout.include_pro_list_item_view);
    }

    public void setFragmentIndex(int fragmentIndex) {
        this.fragmentIndex = fragmentIndex;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ShopMallPropsList item) {
        helper.setText(R.id.tvPropsName, item.getName());
    }
}
