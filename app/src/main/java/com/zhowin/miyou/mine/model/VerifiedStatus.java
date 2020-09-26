package com.zhowin.miyou.mine.model;

/**
 * author : zho
 * date  ：2020/9/25
 * desc ：实名认证状态-->返回的是用户实名认证的信息
 */
public class VerifiedStatus {


    /**
     * userId : 34
     * frontPicture : http://qfah2px93.hn-bkt.clouddn.com/miYou/2020/09/19/104141/1600483301592
     * backPicture : http://qfah2px93.hn-bkt.clouddn.com/miYou/2020/09/19/104141/1600483301592
     * realName : 周小川
     * idCardNo : 4209866744783312446
     * status : 0    状态 0：审核中、1：审核通过、2：审核失败（提交时留空）
     * createTime : 1601104710000
     * updateTime : null
     */

    private int userId;
    private String frontPicture;
    private String backPicture;
    private String realName;
    private String idCardNo;
    private int status;
    private long createTime;
    private Object updateTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFrontPicture() {
        return frontPicture;
    }

    public void setFrontPicture(String frontPicture) {
        this.frontPicture = frontPicture;
    }

    public String getBackPicture() {
        return backPicture;
    }

    public void setBackPicture(String backPicture) {
        this.backPicture = backPicture;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }
}
