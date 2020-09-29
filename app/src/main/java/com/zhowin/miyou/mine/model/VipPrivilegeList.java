package com.zhowin.miyou.mine.model;

/**
 * author : zho
 * date  ：2020/9/29
 * desc ：vip 特权信息
 */
public class VipPrivilegeList {

    /**
     * id : null
     * level : 3
     * permId : 3
     * name : 进入房间隐身
     * heightPicture : http://qfah2px93.hn-bkt.clouddn.com/perm.3.jpg
     * greyPicture : http://qfah2px93.hn-bkt.clouddn.com/perm.3.jpg
     * permKey : stealth
     * type : 0
     * sort : 2
     * enable : 1
     * createTime : 1600956377000
     */

    private Object id;
    private int level;
    private int permId;
    private String name;
    private String heightPicture;
    private String greyPicture;
    private String permKey;
    private int type;
    private int sort;
    private int enable;
    private long createTime;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPermId() {
        return permId;
    }

    public void setPermId(int permId) {
        this.permId = permId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeightPicture() {
        return heightPicture;
    }

    public void setHeightPicture(String heightPicture) {
        this.heightPicture = heightPicture;
    }

    public String getGreyPicture() {
        return greyPicture;
    }

    public void setGreyPicture(String greyPicture) {
        this.greyPicture = greyPicture;
    }

    public String getPermKey() {
        return permKey;
    }

    public void setPermKey(String permKey) {
        this.permKey = permKey;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
