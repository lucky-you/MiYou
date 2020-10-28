package com.zhowin.miyou.recommend.callback;

import com.zhowin.miyou.rongIM.model.MicBean;

/**
 * author : zho
 * date  ：2020/10/26
 * desc ：房间用户click
 */
public interface OnRoomMemberItemClickListener {


    void onMemberItemClick(int itemUiPosition, int itemIMPosition, MicBean audienceList);
}
