package com.zhowin.miyou.recommend.model;

/**
 * author : zho
 * date  ：2020/10/27
 * desc ：麦位列表的数据
 */
public class AudienceList implements Comparable<AudienceList> {

    private int position; //麦位
    private int state; //状态
    private String userId;
    private String userName;
    private String portrait;
    private int charmValue; //魅力值
    private boolean isWheat;//是否上麦


    public AudienceList() {

    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public boolean isWheat() {
        return isWheat;
    }

    public void setWheat(boolean wheat) {
        isWheat = wheat;
    }

    @Override
    public int compareTo(AudienceList o) {
        return getPosition() - o.getPosition();
    }
}
