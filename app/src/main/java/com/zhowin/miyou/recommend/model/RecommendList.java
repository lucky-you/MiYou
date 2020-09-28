package com.zhowin.miyou.recommend.model;

/**
 * author : zho
 * date  ：2020/8/24
 * desc ：房间信息
 */
public class RecommendList {


    /**
     * roomId : 30
     * roomNo : 73379
     * title : LOl在线直播
     * coverPictureKey : http://qfah2px93.hn-bkt.clouddn.com/miYou/2020/09/27/210334/1601211814114
     * description : 盘点历年来LOL世界赛中3场最精彩的B05！扣肉让人惋惜！
     * backgroundPictureId : 1
     * backgroundPictureKey : http://qfah2px93.hn-bkt.clouddn.com/image.room.background.1.jpg
     * typeId : 1
     * typeName : 娱乐房
     * heatValue : 0
     * decoratePicture : null
     * existPwd : false
     * owner : 34
     * createTime : 1601211839000
     */

    private int roomId;
    private String roomNo;
    private String title;
    private String coverPictureKey;
    private String description;
    private String backgroundPictureId;
    private String backgroundPictureKey;
    private int typeId;
    private String typeName;
    private int heatValue;
    private String decoratePicture;
    private boolean existPwd;
    private int owner;
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

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
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

    public int getHeatValue() {
        return heatValue;
    }

    public void setHeatValue(int heatValue) {
        this.heatValue = heatValue;
    }

    public String getDecoratePicture() {
        return decoratePicture;
    }

    public void setDecoratePicture(String decoratePicture) {
        this.decoratePicture = decoratePicture;
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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
