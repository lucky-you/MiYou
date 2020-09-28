package com.zhowin.miyou.recommend.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;


/**
 * author : zho
 * date  ：2020/9/15
 * desc ：管理人员信息
 */
public class AdminInfoList implements MultiItemEntity {

    public static final int ITEM_TYPE_ONE = 1;
    public static final int ITEM_TYPE_TWO = 2;
    private int itemType;

    private String itemTitle;


    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public AdminInfoList(int itemType, String itemTitle) {
        this.itemType = itemType;
        this.itemTitle = itemTitle;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
