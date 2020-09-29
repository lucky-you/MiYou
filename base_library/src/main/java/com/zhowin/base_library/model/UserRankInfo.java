package com.zhowin.base_library.model;

/**
 * author : zho
 * date  ：2020/9/28
 * desc ：用户爵位的信息
 */
public class UserRankInfo {


    /**
     * rankId : 2
     * rankName : 男爵
     * rankPictureKey : 231.jpg
     * price : 600
     * bidPrice : 588
     * level : 1
     * enable : 1
     * sort : 0
     * createTime : 1600956617000
     */

    private int rankId;
    private String rankName;
    private String rankPictureKey;
    private int price;
    private int bidPrice;
    private int level;
    private int enable;
    private int sort;
    private long createTime;

    public int getRankId() {
        return rankId;
    }

    public void setRankId(int rankId) {
        this.rankId = rankId;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public String getRankPictureKey() {
        return rankPictureKey;
    }

    public void setRankPictureKey(String rankPictureKey) {
        this.rankPictureKey = rankPictureKey;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
