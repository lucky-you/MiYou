package com.zhowin.miyou.mine.callback;

/**
 * author : zho
 * date  ：2020/9/27
 * desc ： 关注/粉丝的点击事件
 */
public interface OnAttentionOrFansClickListener {

    void onItemHeaderPhotoClick(int userId);

    void onItemAttentionOrFanClick(int position, int relation, int userId);
}
