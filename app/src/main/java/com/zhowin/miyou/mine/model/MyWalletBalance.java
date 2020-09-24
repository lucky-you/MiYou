package com.zhowin.miyou.mine.model;

/**
 * author : zho
 * date  ：2020/9/24
 * desc ：我的钱包余额
 */
public class MyWalletBalance {


    /**
     * userId : 34
     * diamondNum : 0
     * charmValue : 343180
     * updateTime : null
     */

    private int userId;
    private int diamondNum;
    private int charmValue;
    private Object updateTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDiamondNum() {
        return diamondNum;
    }

    public void setDiamondNum(int diamondNum) {
        this.diamondNum = diamondNum;
    }

    public int getCharmValue() {
        return charmValue;
    }

    public void setCharmValue(int charmValue) {
        this.charmValue = charmValue;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }
}
