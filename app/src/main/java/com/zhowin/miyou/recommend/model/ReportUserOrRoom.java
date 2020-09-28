package com.zhowin.miyou.recommend.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author : zho
 * date  ：2020/9/28
 * desc ：举报用户或者房间
 */
public class ReportUserOrRoom implements Parcelable {
    private int userId;
    private String userNickName;
    private String userHeadPhoto;
    private String userGender;
    private String userMUNumber;
    private int userAge;

    private int roomId;
    private String roomTitle;
    private String roomType;

    public ReportUserOrRoom() {
    }


    protected ReportUserOrRoom(Parcel in) {
        userId = in.readInt();
        userNickName = in.readString();
        userHeadPhoto = in.readString();
        userGender = in.readString();
        userMUNumber = in.readString();
        userAge = in.readInt();
        roomId = in.readInt();
        roomTitle = in.readString();
        roomType = in.readString();
    }

    public static final Creator<ReportUserOrRoom> CREATOR = new Creator<ReportUserOrRoom>() {
        @Override
        public ReportUserOrRoom createFromParcel(Parcel in) {
            return new ReportUserOrRoom(in);
        }

        @Override
        public ReportUserOrRoom[] newArray(int size) {
            return new ReportUserOrRoom[size];
        }
    };

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserHeadPhoto() {
        return userHeadPhoto;
    }

    public void setUserHeadPhoto(String userHeadPhoto) {
        this.userHeadPhoto = userHeadPhoto;
    }

    public String getUserMUNumber() {
        return userMUNumber;
    }

    public void setUserMUNumber(String userMUNumber) {
        this.userMUNumber = userMUNumber;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(userNickName);
        dest.writeString(userHeadPhoto);
        dest.writeString(userGender);
        dest.writeString(userMUNumber);
        dest.writeInt(userAge);
        dest.writeInt(roomId);
        dest.writeString(roomTitle);
        dest.writeString(roomType);
    }

}
