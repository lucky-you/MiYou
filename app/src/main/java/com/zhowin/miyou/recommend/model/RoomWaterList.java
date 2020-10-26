package com.zhowin.miyou.recommend.model;

/**
 * 房间流水
 */
public class RoomWaterList {


    /**
     * date : 0:00 - 0:59
     * amount : 0
     */

    private String date;
    private int amount;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
