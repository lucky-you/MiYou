package com.zhowin.miyou.rongIM.model;

import java.io.Serializable;

/**
 * 麦位信息
 */
public class MicBean implements Serializable, Comparable<MicBean> {


    private String userId;
    private int state;//状态
    private int position;//麦位
    private int charmValue;//魅力值
    private String userName;
    private String portrait;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getCharmValue() {
        return charmValue;
    }

    public void setCharmValue(int charmValue) {
        this.charmValue = charmValue;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    @Override
    public String toString() {
        return "MicBean{" +
                "userId='" + userId + '\'' +
                ", state=" + state +
                ", position=" + position +
                ", charmValue=" + charmValue +
                ", userName='" + userName + '\'' +
                ", portrait='" + portrait + '\'' +
                '}';
    }

    @Override
    public int compareTo(MicBean o) {
        return getPosition() - o.getPosition();
    }
}
