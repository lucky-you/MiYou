package com.zhowin.miyou.mine.model;

import com.zhowin.base_library.model.UserLevelInfo;
import com.zhowin.base_library.model.UserRankInfo;

/**
 * 关注的用户列表
 */
public class AttentionUserList {


    /**
     * userId : 38
     * avatar : 222
     * gender : 男
     * age : 0
     * profilePictureKey : http://qfah2px93.hn-bkt.clouddn.com/image.profilePicture.default.5.jpg
     * levelObj : {"level":0,"startExpValue":0,"endExpValue":0,"color":"#eeeee"}
     * rank : null
     * relation : 0
     * operateTime : null
     */

    private int userId;
    private String avatar;
    private String gender;
    private int age;
    private String profilePictureKey;
    private UserLevelInfo levelObj;
    private UserRankInfo rank;
    private int relation;
    private long operateTime;

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

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    public long getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(long operateTime) {
        this.operateTime = operateTime;
    }
}
