package com.zhowin.base_library.callback;

import android.view.View;

/**
 * author Z_B
 * date :2020/5/16 10:30
 * description: 九宫格图片的点击事件
 */
public interface OnNineGridItemClickListener {


    /**
     * 点击添加图片
     */
    void onAddPictureClick();

    /**
     * 点击图片内容
     *
     * @param position
     * @param view
     */
    void onItemClick(int position, View view);
}
