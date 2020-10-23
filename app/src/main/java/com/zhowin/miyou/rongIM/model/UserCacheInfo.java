package com.zhowin.miyou.rongIM.model;


import android.net.Uri;
import android.os.Parcel;

import io.rong.imlib.model.UserInfo;

public class UserCacheInfo extends UserInfo {
    private String userAvatar;
    private String userToken;


    public UserCacheInfo(String id, String name, Uri portraitUri) {
        super(id, name, portraitUri);
    }


    public void setUserCacheInfo(UserCacheInfo info) {
        setUserId(info.getUserId());
        setPortraitUri(info.getPortraitUri());
        setName(info.getName());
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
}
