package com.zhowin.miyou.recommend.model;

/**
 * author : zho
 * date  ：2020/10/24
 * desc ：房间的基本信息
 */
public class RoomDataInfo {


    /**
     * roomId : 29
     * title : 猫猫的鱼
     * coverPictureKey : http://qfah2px93.hn-bkt.clouddn.com/miYou/2020/09/19/104141/1600483301592
     * decoratePicture : null
     * backgroundPictureKey : http://qfah2px93.hn-bkt.clouddn.com/image.room.background.1.jpg
     * typeId : 1
     * typeName : 娱乐房
     * allowMicFree : 1
     * enableCount : null
     * existPwd : false
     * owner : 34
     * heatValue : 10
     */

    private int roomId;
    private String title;
    private String coverPictureKey;
    private Object decoratePicture;
    private String backgroundPictureKey;
    private int typeId;
    private String typeName;
    private int allowMicFree;
    private Object enableCount;
    private boolean existPwd;
    private int owner;
    private int heatValue;

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

    public Object getDecoratePicture() {
        return decoratePicture;
    }

    public void setDecoratePicture(Object decoratePicture) {
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

    public Object getEnableCount() {
        return enableCount;
    }

    public void setEnableCount(Object enableCount) {
        this.enableCount = enableCount;
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

    public int getHeatValue() {
        return heatValue;
    }

    public void setHeatValue(int heatValue) {
        this.heatValue = heatValue;
    }
}
