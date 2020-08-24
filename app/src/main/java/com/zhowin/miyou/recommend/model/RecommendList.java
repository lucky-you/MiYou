package com.zhowin.miyou.recommend.model;

/**
 * author : zho
 * date  ：2020/8/24
 * desc ：
 */
public class RecommendList {

    private String title;
    private String image;

    public RecommendList(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
