package com.zhowin.miyou.mine.model;

/**
 * author : zho
 * date  ：2020/9/22
 * desc ： 房间背景列表
 */
public class RoomBackgroundList {


    private int backgroundId;
    private String backgroundImage;

    public RoomBackgroundList(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public int getBackgroundId() {
        return backgroundId;
    }

    public void setBackgroundId(int backgroundId) {
        this.backgroundId = backgroundId;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
}
