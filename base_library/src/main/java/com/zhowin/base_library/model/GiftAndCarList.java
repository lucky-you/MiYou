package com.zhowin.base_library.model;

/**
 * 礼物和座驾的列表
 */
public class GiftAndCarList {


    /**
     * id : 2
     * name : 兔耳朵
     * pictureKey : http://qfah2px93.hn-bkt.clouddn.com//image.backgroundPicture.default.default.jpg
     * number : 0
     */

    private String id;
    private String name;
    private String pictureKey;
    private int number;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureKey() {
        return pictureKey;
    }

    public void setPictureKey(String pictureKey) {
        this.pictureKey = pictureKey;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
