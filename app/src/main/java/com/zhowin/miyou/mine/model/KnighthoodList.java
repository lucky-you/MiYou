package com.zhowin.miyou.mine.model;

/**
 * author : zho
 * date  ：2020/9/29
 * desc ：爵位特权列表
 */
public class KnighthoodList {


    /**
     * have : 0
     * name : 聊天字体颜色
     * heightPicture : http://qfah2px93.hn-bkt.clouddn.com/perm.2.jpg
     * greyPicture : http://qfah2px93.hn-bkt.clouddn.com/perm.2.jpg
     * permKey : font_color
     * type : 1
     * sort : 1
     * enable : 1
     * createTime : 1600956424000
     */

    private int have;
    private String name;
    private String heightPicture;
    private String greyPicture;
    private String permKey;
    private String description;
    private int type;
    private int sort;
    private int enable;
    private long createTime;

    public int getHave() {
        return have;
    }

    public void setHave(int have) {
        this.have = have;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
