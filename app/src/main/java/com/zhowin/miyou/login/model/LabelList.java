package com.zhowin.miyou.login.model;


/**
 * 标签
 */
public class LabelList {


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
    private int createTime;
    private boolean isSelect;

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

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
