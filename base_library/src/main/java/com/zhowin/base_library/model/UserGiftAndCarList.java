package com.zhowin.base_library.model;


import com.google.gson.annotations.SerializedName;

/**
 * author : zho
 * date  ：2020/9/9
 * desc ：
 */
public class UserGiftAndCarList {


    /**
     * id : 2
     * name : 兔耳朵
     * pictureKey : http://qfah2px93.hn-bkt.clouddn.com//image.backgroundPicture.default.default.jpg
     * number : 0
     */

    private String id;
    private String name;
    private String pictureKey;
    private Object number;


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

    public Object getNumber() {
        return number;
    }

    public void setNumber(Object number) {
        this.number = number;
    }


}
