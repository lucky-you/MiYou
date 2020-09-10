package com.zhowin.miyou.recommend.model;

import com.zhowin.base_library.model.GiftAndCarList;

import java.util.List;

/**
 * author : zho
 * date  ：2020/9/5
 * desc ：
 */
public class HomePageCategoryList {

    private String leftTitle;
    private List<GiftAndCarList> stringList;

    public HomePageCategoryList(String leftTitle, List<GiftAndCarList> stringList) {
        this.leftTitle = leftTitle;
        this.stringList = stringList;
    }

    public String getLeftTitle() {
        return leftTitle;
    }

    public void setLeftTitle(String leftTitle) {
        this.leftTitle = leftTitle;
    }

    public List<GiftAndCarList> getStringList() {
        return stringList;
    }

    public void setStringList(List<GiftAndCarList> stringList) {
        this.stringList = stringList;
    }
}
