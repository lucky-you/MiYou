package com.zhowin.base_library.model;

/**
 * author : zho
 * date  ：2020/9/28
 * desc ：用户爵位的信息
 */
public class UserRankInfo {

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
