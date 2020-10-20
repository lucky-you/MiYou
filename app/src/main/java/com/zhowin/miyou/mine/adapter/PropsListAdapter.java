package com.zhowin.miyou.mine.adapter;

import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.base_library.utils.SizeUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.mine.callback.OnShopMallPropsClickListener;
import com.zhowin.miyou.mine.model.ShopMallPropsList;

import java.util.List;

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

    private OnShopMallPropsClickListener onShopMallPropsClickListener;

    public void setOnShopMallPropsClickListener(OnShopMallPropsClickListener onShopMallPropsClickListener) {
        this.onShopMallPropsClickListener = onShopMallPropsClickListener;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ShopMallPropsList item) {
        GlideUtils.loadObjectImage(mContext, item.getGreyPicture(), helper.getView(R.id.ivPropsPhoto));
        helper.setText(R.id.tvPropsName, item.getName());
        List<ShopMallPropsList.GoodsTimesBean> itemGoodList = item.getGoodsTimes();
        if (itemGoodList != null && !itemGoodList.isEmpty()) {
            ShopMallPropsList.GoodsTimesBean itemGoodInfo = itemGoodList.get(0);
            helper.setText(R.id.tvPropsPrice, itemGoodInfo.getPrice() + "");
        }
        ConstraintLayout clPropsBackground = helper.getView(R.id.clPropsBackground);
        LinearLayout.LayoutParams layoutParams;
        switch (fragmentIndex) {
            case 0:
            case 2:
                layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, SizeUtils.dp2px(108));
                break;
            case 1:
                layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, SizeUtils.dp2px(135));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + fragmentIndex);
        }
        clPropsBackground.setLayoutParams(layoutParams);
        if (item.isSelect()) {
            clPropsBackground.setBackground(mContext.getResources().getDrawable(R.drawable.shape_shop_mall_props_check_bg));
        } else {
            clPropsBackground.setBackground(mContext.getResources().getDrawable(R.drawable.shape_shop_mall_props_bg));
        }
        clPropsBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (ShopMallPropsList itemInfo : mData) {
                    if (item.getId() == itemInfo.getId()) {
                        item.setSelect(itemInfo.isSelect());
                    } else {
                        itemInfo.setSelect(false);
                    }
                }
                item.setSelect(!item.isSelect());
                if (onShopMallPropsClickListener != null) {
                    onShopMallPropsClickListener.onPropsItemClick(item.isSelect(), helper.getAdapterPosition(), item);
                }
                notifyDataSetChanged();
            }
        });
    }

}
