package com.zhowin.miyou.recommend.model;

/**
 * author : zho
 * date  ：2020/9/28
 * desc ：举报原因
 */
public class ReportList {


    private int id;
    private String  title;
    private boolean  isSelect;


    public ReportList(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
