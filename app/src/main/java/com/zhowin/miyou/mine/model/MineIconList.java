package com.zhowin.miyou.mine.model;

public class MineIconList {

    private int leftDrawable;
    private String leftTitle;

    public MineIconList(int leftDrawable, String leftTitle) {
        this.leftDrawable = leftDrawable;
        this.leftTitle = leftTitle;
    }

    public int getLeftDrawable() {
        return leftDrawable;
    }

    public void setLeftDrawable(int leftDrawable) {
        this.leftDrawable = leftDrawable;
    }

    public String getLeftTitle() {
        return leftTitle;
    }

    public void setLeftTitle(String leftTitle) {
        this.leftTitle = leftTitle;
    }
}
