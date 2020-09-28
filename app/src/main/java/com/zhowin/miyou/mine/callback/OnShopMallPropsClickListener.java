package com.zhowin.miyou.mine.callback;

import com.zhowin.miyou.mine.model.ShopMallPropsList;

/**
 * author : zho
 * date  ：2020/9/28
 * desc ：商城选择
 */
public interface OnShopMallPropsClickListener {

    void onPropsItemClick(boolean isSelect, int position, ShopMallPropsList itemPropsInfo);

}
