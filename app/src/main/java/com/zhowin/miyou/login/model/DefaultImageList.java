package com.zhowin.miyou.login.model;

/**
 * author : zho
 * date  ：2020/9/7
 * desc ： 默认图像
 */
public class DefaultImageList {


    /**
     * pictureId : 0
     * pictureKey : http://qfah2px93.hn-bkt.clouddn.com/image.profilePicture.default.1.jpg
     * enable : 1
     * sort : 0
     * createTime : 2020-09-07T10:32:39.39
     */

    private String pictureId;
    private String pictureKey;
    private int enable;
    private int sort;
    private String createTime;

    private boolean isSelect; //是否选中
    private boolean isAlbumAdd ; //是否相册添加


    public DefaultImageList(boolean isAlbumAdd) {
        this.isAlbumAdd = isAlbumAdd;
    }

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    public String getPictureKey() {
        return pictureKey;
    }

    public void setPictureKey(String pictureKey) {
        this.pictureKey = pictureKey;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isAlbumAdd() {
        return isAlbumAdd;
    }

    public void setAlbumAdd(boolean albumAdd) {
        isAlbumAdd = albumAdd;
    }
}
