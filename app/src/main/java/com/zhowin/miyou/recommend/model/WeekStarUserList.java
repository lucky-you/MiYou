package com.zhowin.miyou.recommend.model;

import com.zhowin.base_library.model.UserLevelInfo;
import com.zhowin.base_library.model.UserRankInfo;

import java.util.List;

/**
 * 周星榜
 */
public class WeekStarUserList {


    /**
     * lastWeekGiftAUser : null
     * lastWeekGiftBUser : null
     * cuurentWeekGiftAName : 高跟鞋
     * cuurentWeekGiftBName : 玫瑰花
     * lastWeekGiftAName : null
     * lastWeekGiftBName : null
     * giftARanking : [{"userInfo":{"userId":34,"avatar":"周小川","gender":"女","age":19,"profilePictureKey":"miYou/2020/09/19/104141/1600483301592","levelObj":{"level":0,"startExpValue":0,"endExpValue":0,"color":"#eeeee"},"rank":null},"score":328000},{"userInfo":{"userId":36,"avatar":"蚂蚁语音2","gender":"女","age":21,"profilePictureKey":"cdefg.jpg","levelObj":{"level":0,"startExpValue":0,"endExpValue":0,"color":"#eeeee"},"rank":null},"score":200000},{"userInfo":{"userId":38,"avatar":"222","gender":"男","age":0,"profilePictureKey":"image.profilePicture.default.5.jpg","levelObj":{"level":0,"startExpValue":0,"endExpValue":0,"color":"#eeeee"},"rank":null},"score":20000}]
     * giftBRanking : [{"userInfo":{"userId":36,"avatar":"蚂蚁语音2","gender":"女","age":21,"profilePictureKey":"cdefg.jpg","levelObj":{"level":0,"startExpValue":0,"endExpValue":0,"color":"#eeeee"},"rank":null},"score":132000},{"userInfo":{"userId":34,"avatar":"周小川","gender":"女","age":19,"profilePictureKey":"miYou/2020/09/19/104141/1600483301592","levelObj":{"level":0,"startExpValue":0,"endExpValue":0,"color":"#eeeee"},"rank":null},"score":15180},{"userInfo":{"userId":38,"avatar":"222","gender":"男","age":0,"profilePictureKey":"image.profilePicture.default.5.jpg","levelObj":{"level":0,"startExpValue":0,"endExpValue":0,"color":"#eeeee"},"rank":null},"score":13200}]
     */

    private Object lastWeekGiftAUser;
    private Object lastWeekGiftBUser;
    private String cuurentWeekGiftAName;
    private String cuurentWeekGiftBName;
    private Object lastWeekGiftAName;
    private Object lastWeekGiftBName;
    private List<GiftARankingBean> giftARanking;
    private List<GiftARankingBean> giftBRanking;

    public Object getLastWeekGiftAUser() {
        return lastWeekGiftAUser;
    }

    public void setLastWeekGiftAUser(Object lastWeekGiftAUser) {
        this.lastWeekGiftAUser = lastWeekGiftAUser;
    }

    public Object getLastWeekGiftBUser() {
        return lastWeekGiftBUser;
    }

    public void setLastWeekGiftBUser(Object lastWeekGiftBUser) {
        this.lastWeekGiftBUser = lastWeekGiftBUser;
    }

    public String getCuurentWeekGiftAName() {
        return cuurentWeekGiftAName;
    }

    public void setCuurentWeekGiftAName(String cuurentWeekGiftAName) {
        this.cuurentWeekGiftAName = cuurentWeekGiftAName;
    }

    public String getCuurentWeekGiftBName() {
        return cuurentWeekGiftBName;
    }

    public void setCuurentWeekGiftBName(String cuurentWeekGiftBName) {
        this.cuurentWeekGiftBName = cuurentWeekGiftBName;
    }

    public Object getLastWeekGiftAName() {
        return lastWeekGiftAName;
    }

    public void setLastWeekGiftAName(Object lastWeekGiftAName) {
        this.lastWeekGiftAName = lastWeekGiftAName;
    }

    public Object getLastWeekGiftBName() {
        return lastWeekGiftBName;
    }

    public void setLastWeekGiftBName(Object lastWeekGiftBName) {
        this.lastWeekGiftBName = lastWeekGiftBName;
    }

    public List<GiftARankingBean> getGiftARanking() {
        return giftARanking;
    }

    public void setGiftARanking(List<GiftARankingBean> giftARanking) {
        this.giftARanking = giftARanking;
    }

    public List<GiftARankingBean> getGiftBRanking() {
        return giftBRanking;
    }

    public void setGiftBRanking(List<GiftARankingBean> giftBRanking) {
        this.giftBRanking = giftBRanking;
    }

    public static class GiftARankingBean {
        /**
         * userInfo : {"userId":34,"avatar":"周小川","gender":"女","age":19,"profilePictureKey":"miYou/2020/09/19/104141/1600483301592","levelObj":{"level":0,"startExpValue":0,"endExpValue":0,"color":"#eeeee"},"rank":null}
         * score : 328000
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
             * userId : 34
             * avatar : 周小川
             * gender : 女
             * age : 19
             * profilePictureKey : miYou/2020/09/19/104141/1600483301592
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
    }


}
