package com.zhowin.miyou.rongIM.model;

import java.io.Serializable;

public class MicBean implements Serializable {
    private String userId;
    private int state;
    private int position;
    private int charmValue;

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

    @Override
    public String toString() {
        return "MicBean{" +
                "userId='" + userId + '\'' +
                ", state=" + state +
                ", position=" + position +
                ", charmValue=" + charmValue +
                '}';
    }
}