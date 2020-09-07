package com.zhowin.miyou.login.callback;

/**
 * author : zho
 * date  ：2020/9/7
 * desc ： 选择图像的回调
 */
public interface OnUserAvatarClickListener {

    void onAvatarClick(boolean isAlbumAdd,boolean isSelect,  String userAvatar);
}
