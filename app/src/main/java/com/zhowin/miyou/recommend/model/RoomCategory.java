package com.zhowin.miyou.recommend.model;

/**
 * author : zho
 * date  ：2020/9/22
 * desc ： 房间类型
 */
public class RoomCategory {


    /**
     * typeId : 1
     * typeName : 娱乐房
     * sort : 1
     * enable : 1
     * createTime : 2020-10-01T00:00:00
     */

    private int typeId;
    private String typeName;
    private int sort;
    private int enable;
    private String createTime;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
