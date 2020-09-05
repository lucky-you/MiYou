package com.zhowin.miyou.recommend.callback;

/**
 * author Z_B
 * description: tag 的点击事件
 */
public interface OnTopicTagClickListener {

    void onTagsClick(boolean isTagsData, int tagId, String nickName);
}
