package com.zhowin.miyou.main.utils;

import com.zhowin.miyou.R;

/**
 * author : zho
 * date  ：2020/9/8
 * desc ：性别设置的帮助类
 */
public class GenderHelper {

    public static int getSexBackground(String sexText) {
        int background;
        switch (sexText) {
            case "男":
                background = R.drawable.man_box;
                break;
            case "女":
                background = R.drawable.girl_box;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + sexText);
        }
        return background;
    }

    public static int getSexDrawable(String sexText) {
        int drawable;
        switch (sexText) {
            case "男":
                drawable = R.drawable.man_icon;
                break;
            case "女":
                drawable = R.drawable.girl_icon;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + sexText);
        }
        return drawable;
    }

}
