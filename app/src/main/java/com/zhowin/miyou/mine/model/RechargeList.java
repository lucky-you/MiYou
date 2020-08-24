package com.zhowin.miyou.mine.model;

public class RechargeList {

    private String topTitle;
    private String bottomTitle;

    public RechargeList(String topTitle, String bottomTitle) {
        this.topTitle = topTitle;
        this.bottomTitle = bottomTitle;
    }

    public String getTopTitle() {
        return topTitle;
    }

    public void setTopTitle(String topTitle) {
        this.topTitle = topTitle;
    }

    public String getBottomTitle() {
        return bottomTitle;
    }

    public void setBottomTitle(String bottomTitle) {
        this.bottomTitle = bottomTitle;
    }
}
