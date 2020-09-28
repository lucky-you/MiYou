package com.zhowin.miyou.recommend.model;

import com.zhowin.base_library.model.UserLevelInfo;
import com.zhowin.base_library.model.UserRankInfo;

/**
 * 真爱榜单
 */
public class ZABUserList {


    /**
     * giver : {"userId":32,"avatar":"蚂蚁语音","gender":"男","age":0,"profilePictureKey":"image.profilePicture.default.1.jpg","levelObj":{"level":3,"startExpValue":301,"endExpValue":700,"color":null},"rank":null}
     * accepter : {"userId":36,"avatar":"蚂蚁语音2","gender":"女","age":21,"profilePictureKey":"cdefg.jpg","levelObj":{"level":0,"startExpValue":0,"endExpValue":0,"color":"#eeeee"},"rank":null}
     * gift : {"giftId":1,"giftName":"玫瑰花","giftPictureKey":"image.gift.xh.png","price":66,"bidPrice":64,"sort":2,"enable":1,"createTime":1600153082000}
     * number : 1000
     * sendTime : 1601068685147
     */

    private GiverBean giver;
    private AccepterBean accepter;
    private GiftBean gift;
    private int number;
    private long sendTime;

    public GiverBean getGiver() {
        return giver;
    }

    public void setGiver(GiverBean giver) {
        this.giver = giver;
    }

    public AccepterBean getAccepter() {
        return accepter;
    }

    public void setAccepter(AccepterBean accepter) {
        this.accepter = accepter;
    }

    public GiftBean getGift() {
        return gift;
    }

    public void setGift(GiftBean gift) {
        this.gift = gift;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public static class GiverBean {
        /**
         * userId : 32
         * avatar : 蚂蚁语音
         * gender : 男
         * age : 0
         * profilePictureKey : image.profilePicture.default.1.jpg
         * levelObj : {"level":3,"startExpValue":301,"endExpValue":700,"color":null}
         * rank : null
         */

        private int userId;
        private String avatar;
        private String gender;
        private int age;
        private String profilePictureKey;
        private UserLevelInfo levelObj;
        private UserRankInfo rank;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getProfilePictureKey() {
            return profilePictureKey;
        }

        public void setProfilePictureKey(String profilePictureKey) {
            this.profilePictureKey = profilePictureKey;
        }

        public UserLevelInfo getLevelObj() {
            return levelObj;
        }

        public void setLevelObj(UserLevelInfo levelObj) {
            this.levelObj = levelObj;
        }

        public UserRankInfo getRank() {
            return rank;
        }

        public void setRank(UserRankInfo rank) {
            this.rank = rank;
        }
    }

    public static class AccepterBean {
        /**
         * userId : 36
         * avatar : 蚂蚁语音2
         * gender : 女
         * age : 21
         * profilePictureKey : cdefg.jpg
         * levelObj : {"level":0,"startExpValue":0,"endExpValue":0,"color":"#eeeee"}
         * rank : null
         */

        private int userId;
        private String avatar;
        private String gender;
        private int age;
        private String profilePictureKey;
        private UserLevelInfo levelObj;
        private UserRankInfo rank;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getProfilePictureKey() {
            return profilePictureKey;
        }

        public void setProfilePictureKey(String profilePictureKey) {
            this.profilePictureKey = profilePictureKey;
        }

        public UserLevelInfo getLevelObj() {
            return levelObj;
        }

        public void setLevelObj(UserLevelInfo levelObj) {
            this.levelObj = levelObj;
        }

        public UserRankInfo getRank() {
            return rank;
        }

        public void setRank(UserRankInfo rank) {
            this.rank = rank;
        }
    }

    public static class GiftBean {
        /**
         * giftId : 1
         * giftName : 玫瑰花
         * giftPictureKey : image.gift.xh.png
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
}
