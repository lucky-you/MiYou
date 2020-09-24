package com.zhowin.miyou.mine.model;

/**
 * author : zho
 * date  ：2020/9/22
 * desc ： 房间背景列表
 */
public class RoomBackgroundList {


    /**
     * id : 1
     * pictureKey : http://qfah2px93.hn-bkt.clouddn.com/image.room.background.1.jpg
     * sort : 1
     * enable : 1
     * createTime : 1600780129000
     * updateTime : 1600780131000
     */

    private int id;
    private String pictureKey;
    private int sort;
    private int enable;
    private long createTime;
    private long updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPictureKey() {
        return pictureKey;
    }

    public void setPictureKey(String pictureKey) {
        this.pictureKey = pictureKey;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
