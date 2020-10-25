package com.zhowin.miyou.recommend.model;

/**
 * 房间设置信息
 */
public class LiveRoomSet {

    private int itemId;
    private String itemTitle;
    private boolean isCheck;

    public LiveRoomSet(int itemId, String itemTitle) {
        this.itemId = itemId;
        this.itemTitle = itemTitle;
    }

    public LiveRoomSet(int itemId, String itemTitle, boolean isCheck) {
        this.itemId = itemId;
        this.itemTitle = itemTitle;
        this.isCheck = isCheck;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
