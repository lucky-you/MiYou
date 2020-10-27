package com.zhowin.miyou.recommend.callback;

import com.zhowin.miyou.recommend.model.AudienceList;

/**
 * author : zho
 * date  ：2020/10/26
 * desc ：房间用户click
 */
public interface OnRoomMemberItemClickListener {


    void onMemberItemClick(int position, AudienceList audienceList);
}
