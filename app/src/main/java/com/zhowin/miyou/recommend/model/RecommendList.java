package com.zhowin.miyou.recommend.model;

/**
 * author : zho
 * date  ：2020/8/24
 * desc ：房间信息
 */
public class RecommendList {


    /**
     * roomId : 29
     * roomNo : 15071
     * title : 猫猫的鱼
     * coverPictureKey : http://qfah2px93.hn-bkt.clouddn.com/miYou/2020/09/19/104141/1600483301592
     * description : 创建一个房间
     * backgroundPictureId : 1
     * backgroundPictureKey : http://qfah2px93.hn-bkt.clouddn.com/image.room.background.1.jpg
     * decoratePicture : null
     * typeId : 1
     * pwd : null
     * allowMicFree : 1
     * owner : 34
     * createTime : 1601020033000
     */

    private int roomId;
    private int roomNo;
    private String title;
    private String coverPictureKey;
    private String description;
    private String backgroundPictureId;
    private String backgroundPictureKey;
    private Object decoratePicture;
    private int typeId;
    private Object pwd;
    private int allowMicFree;
    private int owner;
    private long createTime;

    public RecommendList(String title, String coverPictureKey) {
        this.title = title;
        this.coverPictureKey = coverPictureKey;
    }


    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
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

    public Object getDecoratePicture() {
        return decoratePicture;
    }

    public void setDecoratePicture(Object decoratePicture) {
        this.decoratePicture = decoratePicture;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public Object getPwd() {
        return pwd;
    }

    public void setPwd(Object pwd) {
        this.pwd = pwd;
    }

    public int getAllowMicFree() {
        return allowMicFree;
    }

    public void setAllowMicFree(int allowMicFree) {
        this.allowMicFree = allowMicFree;
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
