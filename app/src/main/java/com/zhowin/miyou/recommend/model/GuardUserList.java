package com.zhowin.miyou.recommend.model;

import com.zhowin.base_library.model.UserLevelInfo;
import com.zhowin.base_library.model.UserRankInfo;

/**
 * 守护榜单
 */
public class GuardUserList {


    /**
     * giver : {"userId":32,"avatar":"蚂蚁语音","gender":"男","age":0,"profilePictureKey":"image.profilePicture.default.1.jpg","levelObj":{"level":0,"startExpValue":0,"endExpValue":0,"color":"#eeeee"},"rank":{"userId":32,"rankId":1,"rankName":"子爵","rankPictureKey":"2341234tr34r.jpg","level":2,"expireTime":1606339509000,"status":1}}
     * accepter : {"userId":34,"avatar":"周小川","gender":"女","age":19,"profilePictureKey":"miYou/2020/09/19/104141/1600483301592","levelObj":{"level":0,"startExpValue":0,"endExpValue":0,"color":"#eeeee"},"rank":null}
     * score : 343180
     */

    private GiverBean giver;
    private AccepterBean accepter;
    private int score;

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static class GiverBean {
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
