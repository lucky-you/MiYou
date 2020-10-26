package com.zhowin.miyou.recommend.callback;

/**
 * author : zho
 * date  ：2020/10/26
 * desc ：房间用户click
 */
public interface OnRoomMemberItemClickListener {


    void onMemberItemClick(int position, String userId,String userName);
}
