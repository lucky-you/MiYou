package com.zhowin.miyou.recommend.model;

import java.util.List;

/**
 * author : zho
 * date  ：2020/9/5
 * desc ：
 */
public class HomePageCategoryList {

    private String leftTitle;
    private List<String> stringList;

    public HomePageCategoryList(String leftTitle, List<String> stringList) {
        this.leftTitle = leftTitle;
        this.stringList = stringList;
    }

    public String getLeftTitle() {
        return leftTitle;
    }

    public void setLeftTitle(String leftTitle) {
        this.leftTitle = leftTitle;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }
}
