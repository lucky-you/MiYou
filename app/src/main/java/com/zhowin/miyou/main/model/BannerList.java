package com.zhowin.miyou.main.model;

/**
 * author : zho
 * date  ：2020/8/24
 * desc ：
 */
public class BannerList {


    /**
     * bannerId : 0
     * createTime :
     * enable : 0
     * pictureKey :
     * sort : 0
     * title :
     * url :
     */

    private int bannerId;
    private String createTime;
    private int enable;
    private String pictureKey;
    private int sort;
    private String title;
    private String url;

    public BannerList(String pictureKey) {
        this.pictureKey = pictureKey;
    }

    public int getBannerId() {
        return bannerId;
    }

    public void setBannerId(int bannerId) {
        this.bannerId = bannerId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getPictureKey() {
        return pictureKey;
    }

    public void setPictureKey(String pictureKey) {
        this.pictureKey = pictureKey;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
