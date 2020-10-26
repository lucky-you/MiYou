package com.zhowin.miyou.recommend.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.recommend.callback.OnRoomMemberItemClickListener;
import com.zhowin.miyou.rongIM.repo.RoomMemberRepo;

import java.util.List;

/**
 * author : zho
 * date  ：2020/10/24
 * desc ： 聊天室排麦列表的adapter
 */
public class AudienceListAdapter extends BaseQuickAdapter<RoomMemberRepo.MemberBean, BaseViewHolder> {
    public AudienceListAdapter(@Nullable List<RoomMemberRepo.MemberBean> data) {
        super(R.layout.include_room_audience_item_view, data);
    }

    private OnRoomMemberItemClickListener onRoomMemberItemClickListener;


    public void setOnRoomMemberItemClickListener(OnRoomMemberItemClickListener onRoomMemberItemClickListener) {
        this.onRoomMemberItemClickListener = onRoomMemberItemClickListener;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RoomMemberRepo.MemberBean item) {

        if (0 == item.getPosition()) {
            helper.setImageResource(R.id.civAudienceHeadImage, R.drawable.room_boss_icon)
                    .setGone(R.id.tvHeatNumber, false)
                    .setText(R.id.AudienceName, "老板位");
        } else {
            loadLiveRoomMember(mContext, item.getPortrait(), helper.getView(R.id.civAudienceHeadImage));
            helper.setText(R.id.AudienceName, item.getPosition() + "号麦");
        }

        helper.getView(R.id.llRoomMemberItemLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRoomMemberItemClickListener != null) {
                    onRoomMemberItemClickListener.onMemberItemClick(item.getPosition(), item.getUserId(), item.getUserName());
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
