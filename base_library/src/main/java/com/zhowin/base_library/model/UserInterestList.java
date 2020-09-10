package com.zhowin.base_library.model;

/**
 * 用户兴趣标签
 */
public class UserInterestList {


    /**
     * labelId : 4
     * labelName : 熬夜大师
     * labelKey : WAKEFUL
     * enable : 1
     * sort : 4
     * createTime : null
     */

    private int labelId;
    private String labelName;
    private String labelKey;
    private int enable;
    private int sort;
    private Object createTime;

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelKey() {
        return labelKey;
    }

    public void setLabelKey(String labelKey) {
        this.labelKey = labelKey;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }
}
