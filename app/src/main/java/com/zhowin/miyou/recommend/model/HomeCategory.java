package com.zhowin.miyou.recommend.model;

/**
 * author : zho
 * date  ：2020/9/28
 * desc ：首页分类
 */
public class HomeCategory {


    /**
     * typeId : -1
     * typeName : 热门
     * sort : 0
     * enable : 1
     * createTime : 1601290061401
     */

    private int typeId;
    private String typeName;
    private int sort;
    private int enable;
    private long createTime;

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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
