package com.zhowin.miyou.recommend.model;

import com.google.gson.annotations.SerializedName;

/**
 * author : zho
 * date  ：2020/8/24
 * desc ：房间信息
 */
public class RecommendList {


    /**
     * roomId : 30
     * title : LOl在线直播
     * coverPictureKey : miYou/2020/09/27/210334/1601211814114
     * decoratePicture : null
     * backgroundPictureKey : http://qfah2px93.hn-bkt.clouddn.com/image.room.background.1.jpg
     * typeId : 1
     * typeName : 娱乐房
     * allowMicFree : 1
     * existPwd : false
     * owner : 34
     */

    private int roomId;
    private String title;
    private String coverPictureKey;
    private String decoratePicture;
    private String backgroundPictureKey;
    private int typeId;
    private String typeName;
    private int allowMicFree;
    private boolean existPwd;
    private int owner;
    /**
     * roomNo : 73379
     * description : 盘点历年来LOL世界赛中3场最精彩的B05！扣肉让人惋惜！
     * backgroundPictureId : 1
     * decoratePicture : null
     * createTime : 1601211839000
     */

    private String roomNo;
    private String description;
    private String backgroundPictureId;
    private long createTime;


    public RecommendList(String title, String backgroundPictureKey) {
        this.title = title;
        this.backgroundPictureKey = backgroundPictureKey;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverPictureKey() {
        return coverPictureKey;
    }

    public void setCoverPictureKey(String coverPictureKey) {
        this.coverPictureKey = coverPictureKey;
    }

    public String getDecoratePicture() {
        return decoratePicture;
    }

    public void setDecoratePicture(String decoratePicture) {
        this.decoratePicture = decoratePicture;
    }

    public String getBackgroundPictureKey() {
        return backgroundPictureKey;
    }

    public void setBackgroundPictureKey(String backgroundPictureKey) {
        this.backgroundPictureKey = backgroundPictureKey;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getAllowMicFree() {
        return allowMicFree;
    }

    public void setAllowMicFree(int allowMicFree) {
        this.allowMicFree = allowMicFree;
    }

    public boolean isExistPwd() {
        return existPwd;
    }

    public void setExistPwd(boolean existPwd) {
        this.existPwd = existPwd;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackgroundPictureId() {
        return backgroundPictureId;
    }

    public void setBackgroundPictureId(String backgroundPictureId) {
        this.backgroundPictureId = backgroundPictureId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
