package com.zhowin.miyou.recommend.model;

import java.util.List;

/**
 * 房间流水
 */
public class RoomWaterBean {


    /**
     * bills : [{"date":"0:00 - 0:59","amount":0},{"date":"1:00 - 1:59","amount":0},{"date":"2:00 - 2:59","amount":0},{"date":"3:00 - 3:59","amount":0},{"date":"4:00 - 4:59","amount":0},{"date":"5:00 - 5:59","amount":0},{"date":"6:00 - 6:59","amount":0},{"date":"7:00 - 7:59","amount":0},{"date":"8:00 - 8:59","amount":0},{"date":"9:00 - 9:59","amount":0},{"date":"10:00 - 10:59","amount":0},{"date":"11:00 - 11:59","amount":0},{"date":"12:00 - 12:59","amount":0},{"date":"13:00 - 13:59","amount":0},{"date":"14:00 - 14:59","amount":0},{"date":"15:00 - 15:59","amount":0},{"date":"16:00 - 16:59","amount":0},{"date":"17:00 - 17:59","amount":0},{"date":"18:00 - 18:59","amount":0},{"date":"19:00 - 19:59","amount":0},{"date":"20:00 - 20:59","amount":0}]
     * total : 0
     */

    private int total;
    private int currentWeekTotal;
    private int lastWeekTotal;
    private List<RoomWaterList> bills;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrentWeekTotal() {
        return currentWeekTotal;
    }

    public void setCurrentWeekTotal(int currentWeekTotal) {
        this.currentWeekTotal = currentWeekTotal;
    }

    public int getLastWeekTotal() {
        return lastWeekTotal;
    }

    public void setLastWeekTotal(int lastWeekTotal) {
        this.lastWeekTotal = lastWeekTotal;
    }

    public List<RoomWaterList> getBills() {
        return bills;
    }

    public void setBills(List<RoomWaterList> bills) {
        this.bills = bills;
    }


}
