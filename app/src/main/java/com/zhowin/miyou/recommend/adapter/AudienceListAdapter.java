package com.zhowin.miyou.recommend.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;
import com.zhowin.miyou.recommend.callback.OnRoomMemberItemClickListener;
import com.zhowin.miyou.rongIM.constant.MicState;
import com.zhowin.miyou.rongIM.model.MicBean;

import java.util.List;

/**
 * author : zho
 * date  ：2020/10/24
 * desc ： 聊天室排麦列表的adapter
 */
public class AudienceListAdapter extends BaseQuickAdapter<MicBean, BaseViewHolder> {
    public AudienceListAdapter(@Nullable List<MicBean> data) {
        super(R.layout.include_room_audience_item_view, data);
    }

    private OnRoomMemberItemClickListener onRoomMemberItemClickListener;


    public void setOnRoomMemberItemClickListener(OnRoomMemberItemClickListener onRoomMemberItemClickListener) {
        this.onRoomMemberItemClickListener = onRoomMemberItemClickListener;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MicBean item) {
        if (0 == helper.getAdapterPosition()) {
            helper.setImageResource(R.id.civAudienceHeadImage, R.drawable.room_boss_icon)
                    .setGone(R.id.tvHeatNumber, false)
                    .setText(R.id.AudienceName, "老板位");
        } else {
            loadLiveRoomMember(mContext, item.getPortrait(), helper.getView(R.id.civAudienceHeadImage));
            helper.setText(R.id.AudienceName, helper.getAdapterPosition() + "号麦")
                    .setGone(R.id.tvHeatNumber, !TextUtils.isEmpty(item.getUserId()))
                    .setText(R.id.tvHeatNumber, item.getCharmValue() + "");
        }
        helper.getView(R.id.llRoomMemberItemLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRoomMemberItemClickListener != null) {
                    //第一个是在界面中的position，第二个是融云返回的position
                    onRoomMemberItemClickListener.onMemberItemClick(helper.getAdapterPosition(), item.getPosition(), item);
                }
            }
        });
    }


    public static void loadLiveRoomMember(Context context, Object photoUrl, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.room_put_icon)
                .error(R.drawable.room_put_icon)
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(context)
                .load(photoUrl)
                .apply(options)
                .into(imageView);
    }
}
