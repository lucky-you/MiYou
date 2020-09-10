package com.zhowin.miyou.recommend.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.internal.FlowLayout;
import com.zhowin.base_library.model.UserInterestList;
import com.zhowin.base_library.utils.SizeUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.recommend.callback.OnTopicTagClickListener;

import java.util.List;

/**
 * author : zho
 * date  ：2020/9/5
 * desc ：
 */
public class SetTagsToViewHelper {


    /**
     * 关注列表tags
     */
    public static void setInterestListTagToView(Context mContext, FlowLayout llInterestItemLayout, List<UserInterestList> tagsList, OnTopicTagClickListener onTopicTagClickListener) {
        if (llInterestItemLayout == null) return;
        if (tagsList == null || tagsList.isEmpty()) return;
        llInterestItemLayout.removeAllViews();
        for (int i = 0; i < tagsList.size(); i++) {
            UserInterestList tagItem = tagsList.get(i);
            TextView radiusTextView = new TextView(mContext);
            radiusTextView.setBackground(mContext.getResources().getDrawable(R.drawable.shape_user_interest_bg));
            radiusTextView.setPadding(SizeUtils.dp2px(6), 0, SizeUtils.dp2px(6), 0);
            radiusTextView.setGravity(Gravity.CENTER);
            radiusTextView.setTextColor(mContext.getResources().getColor(R.color.color_999));
            radiusTextView.setText(tagItem.getLabelName());
            llInterestItemLayout.addView(radiusTextView);
            radiusTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onTopicTagClickListener != null) {
                        onTopicTagClickListener.onTagsClick(true, tagItem.getLabelId(), tagItem.getLabelName());
                    }
                }
            });
        }
    }


}
