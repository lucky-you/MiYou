package com.zhowin.miyou.recommend.model;

/**
 * author : zho
 * date  ：2020/8/24
 * desc ：房间信息
 */
public class RecommendList {


    /**
     * roomId : 4
     * title : 测试直播间
     * coverPictureKey : hijklmg.jpg
     * decoratePicture : null
     * typeId : 1
     * typeName : 娱乐房
     * allowMicFree : 0
     * existPwd : true
     * owner : null
     */

    private int roomId;
    private String title;
    private String coverPictureKey;
    private Object decoratePicture;
    private int typeId;
    private String typeName;
    private int allowMicFree;
    private boolean existPwd;
    private Object owner;

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

    public Object getOwner() {
        return owner;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }
}
