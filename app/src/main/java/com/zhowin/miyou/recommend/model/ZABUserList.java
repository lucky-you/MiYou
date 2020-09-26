package com.zhowin.miyou.recommend.model;

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
        private LevelObjBean levelObj;
        private RankBean rank;

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

        public LevelObjBean getLevelObj() {
            return levelObj;
        }

        public void setLevelObj(LevelObjBean levelObj) {
            this.levelObj = levelObj;
        }

        public RankBean getRank() {
            return rank;
        }

        public void setRank(RankBean rank) {
            this.rank = rank;
        }

        public static class LevelObjBean {
            /**
             * level : 3
             * startExpValue : 301
             * endExpValue : 700
             * color : null
             */

            private int level;
            private int startExpValue;
            private int endExpValue;
            private Object color;

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getStartExpValue() {
                return startExpValue;
            }

            public void setStartExpValue(int startExpValue) {
                this.startExpValue = startExpValue;
            }

            public int getEndExpValue() {
                return endExpValue;
            }

            public void setEndExpValue(int endExpValue) {
                this.endExpValue = endExpValue;
            }

            public Object getColor() {
                return color;
            }

            public void setColor(Object color) {
                this.color = color;
            }
        }

        public static class RankBean {
            /**
             * userId : 32
             * rankId : 1
             * rankName : 子爵
             * rankPictureKey : 2341234tr34r.jpg
             * level : 2
             * expireTime : 1606339509000
             * status : 1
             */

            private int userId;
            private int rankId;
            private String rankName;
            private String rankPictureKey;
            private int level;
            private long expireTime;
            private int status;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

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

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public long getExpireTime() {
                return expireTime;
            }

            public void setExpireTime(long expireTime) {
                this.expireTime = expireTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
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
        private LevelObjBeanX levelObj;
        private RankBean rank;

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

        public LevelObjBeanX getLevelObj() {
            return levelObj;
        }

        public void setLevelObj(LevelObjBeanX levelObj) {
            this.levelObj = levelObj;
        }

        public RankBean getRank() {
            return rank;
        }

        public void setRank(RankBean rank) {
            this.rank = rank;
        }

        public static class LevelObjBeanX {
            /**
             * level : 0
             * startExpValue : 0
             * endExpValue : 0
             * color : #eeeee
             */

            private int level;
            private int startExpValue;
            private int endExpValue;
            private String color;

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getStartExpValue() {
                return startExpValue;
            }

            public void setStartExpValue(int startExpValue) {
                this.startExpValue = startExpValue;
            }

            public int getEndExpValue() {
                return endExpValue;
            }

            public void setEndExpValue(int endExpValue) {
                this.endExpValue = endExpValue;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }
        public static class RankBean {
            /**
             * userId : 32
             * rankId : 1
             * rankName : 子爵
             * rankPictureKey : 2341234tr34r.jpg
             * level : 2
             * expireTime : 1606339509000
             * status : 1
             */

            private int userId;
            private int rankId;
            private String rankName;
            private String rankPictureKey;
            private int level;
            private long expireTime;
            private int status;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

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

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public long getExpireTime() {
                return expireTime;
            }

            public void setExpireTime(long expireTime) {
                this.expireTime = expireTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
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
