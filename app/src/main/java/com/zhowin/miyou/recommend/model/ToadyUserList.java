package com.zhowin.miyou.recommend.model;

/**
 *  每日榜单用户信息
 */
public class ToadyUserList {


    /**
     * userInfo : {"userId":32,"avatar":"蚂蚁语音","gender":"男","age":0,"profilePictureKey":"image.profilePicture.default.1.jpg","levelObj":{"level":0,"startExpValue":0,"endExpValue":0,"color":"#eeeee"},"rank":{"userId":32,"rankId":1,"rankName":"子爵","rankPictureKey":"2341234tr34r.jpg","level":2,"expireTime":1606339509000,"status":1}}
     * score : 332000
     */

    private UserInfoBean userInfo;
    private int score;

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static class UserInfoBean {
        /**
         * userId : 32
         * avatar : 蚂蚁语音
         * gender : 男
         * age : 0
         * profilePictureKey : image.profilePicture.default.1.jpg
         * levelObj : {"level":0,"startExpValue":0,"endExpValue":0,"color":"#eeeee"}
         * rank : {"userId":32,"rankId":1,"rankName":"子爵","rankPictureKey":"2341234tr34r.jpg","level":2,"expireTime":1606339509000,"status":1}
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
}