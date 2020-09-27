package com.zhowin.miyou.main.model;

/**
 * author : zho
 * date  ：2020/8/24
 * desc ：首页banner
 */
public class BannerList {


    /**
     * bannerId : 1
     * title : 测试Banner
     * pictureKey : http://qfah2px93.hn-bkt.clouddn.com/abc.jpg
     * url : http://www.baidu.com
     * createTime : 1601066500000
     * enable : 1
     * sort : 1
     */

    private int bannerId;
    private String title;
    private String pictureKey;
    private String url;
    private long createTime;
    private int enable;
    private int sort;

    public BannerList(String pictureKey) {
        this.pictureKey = pictureKey;
    }

    public int getBannerId() {
        return bannerId;
    }

    public void setBannerId(int bannerId) {
        this.bannerId = bannerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPictureKey() {
        return pictureKey;
    }

    public void setPictureKey(String pictureKey) {
        this.pictureKey = pictureKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
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
}
