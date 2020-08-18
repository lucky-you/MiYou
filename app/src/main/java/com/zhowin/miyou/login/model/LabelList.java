package com.zhowin.miyou.login.model;


/**
 * 标签
 */
public class LabelList {

    private int labelId;
    private String labelTitle;
    private int labelResources;
    private boolean  isSelect;

    public LabelList() {
    }

    public LabelList(String labelTitle) {
        this.labelTitle = labelTitle;
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public String getLabelTitle() {
        return labelTitle;
    }

    public void setLabelTitle(String labelTitle) {
        this.labelTitle = labelTitle;
    }

    public int getLabelResources() {
        return labelResources;
    }

    public void setLabelResources(int labelResources) {
        this.labelResources = labelResources;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
