package com.zhowin.base_library.model;

/**
 * author : zho
 * date  ：2020/9/28
 * desc ：用户等级信息
 */
public class UserLevelInfo {


    /**
     * level : 1
     * startExpValue : 100
     * endExpValue : 0
     * color : #eeeee
     */

    private int level;
    private int endExpValue;
    private int startExpValue;
    private String color;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getEndExpValue() {
        return endExpValue;
    }

    public void setEndExpValue(int endExpValue) {
        this.endExpValue = endExpValue;
    }

    public int getStartExpValue() {
        return startExpValue;
    }

    public void setStartExpValue(int startExpValue) {
        this.startExpValue = startExpValue;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
