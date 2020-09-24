package com.zhowin.miyou.recommend.model;

/**
 * 礼物列表
 */
public class GiftList {


    /**
     * giftId : 1
     * giftName : 玫瑰花
     * giftPictureKey : http://qfah2px93.hn-bkt.clouddn.com/image.gift.xh.png
     * price : 66
     * bidPrice : 64
     * sort : 2
     * enable : 1
     * createTime : 1600153082000
     */

    private int giftId;
    private String giftName;
    private String giftPictureKey;
    private int price;
    private int bidPrice;
    private int sort;
    private int enable;
    private long createTime;

    public int getGiftId() {
        return giftId;
    }

    public void setGiftId(int giftId) {
        this.giftId = giftId;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getGiftPictureKey() {
        return giftPictureKey;
    }

    public void setGiftPictureKey(String giftPictureKey) {
        this.giftPictureKey = giftPictureKey;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(int bidPrice) {
        this.bidPrice = bidPrice;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
