package com.zhowin.miyou.mine.model;

import java.util.List;

/**
 * 商城商品列表
 */
public class ShopMallPropsList {


    /**
     * id : 2
     * name : 兔耳朵
     * type : 1
     * pictureKey : http://qfah2px93.hn-bkt.clouddn.com/image.backgroundPicture.default.default.jpg
     * enable : 1
     * sort : 1
     * createTime : 1599362578000
     * goodsTimes : [{"id":"dfasdf34t","goodsId":2,"timeType":0,"price":38,"bidPrice":35,"sort":1,"createTime":1599362949000,"goods":null}]
     */

    private int id;
    private String name;
    private int type;
    private String heightPicture;
    private String greyPicture;
    private int enable;
    private int sort;
    private long createTime;
    private List<GoodsTimesBean> goodsTimes;

    private boolean isSelect;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getHeightPicture() {
        return heightPicture;
    }

    public void setHeightPicture(String heightPicture) {
        this.heightPicture = heightPicture;
    }

    public String getGreyPicture() {
        return greyPicture;
    }

    public void setGreyPicture(String greyPicture) {
        this.greyPicture = greyPicture;
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

    public List<GoodsTimesBean> getGoodsTimes() {
        return goodsTimes;
    }

    public void setGoodsTimes(List<GoodsTimesBean> goodsTimes) {
        this.goodsTimes = goodsTimes;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public static class GoodsTimesBean {
        /**
         * id : dfasdf34t
         * goodsId : 2
         * timeType : 0  物品期限 0：七天、1：一个月、2：三个月、3：一年、4：无限期
         * price : 38
         * bidPrice : 35
         * sort : 1
         * createTime : 1599362949000
         * goods : null
         */

        private String id;
        private int goodsId;
        private int timeType;
        private int price;
        private int bidPrice;
        private int sort;
        private long createTime;
        private Object goods;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getTimeType() {
            return timeType;
        }

        public void setTimeType(int timeType) {
            this.timeType = timeType;
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

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public Object getGoods() {
            return goods;
        }

        public void setGoods(Object goods) {
            this.goods = goods;
        }
    }
}
