package com.zhowin.miyou.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.base_library.utils.SizeUtils;

import java.util.List;

/**
 * author : zho
 * date  ：2020/9/3
 * desc ： 房间背景
 */
public class RoomBackgroundAdapter extends RecyclerView.Adapter<RoomBackgroundAdapter.ViewHolder> {

    private Context mContext;
    private List<String> roomBgList;

    public RoomBackgroundAdapter(Context mContext, List<String> roomBgList) {
        this.mContext = mContext;
        this.roomBgList = roomBgList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RoundedImageView roundedImageView = new RoundedImageView(mContext);
        roundedImageView.setCornerRadius(SizeUtils.dp2px(5));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(SizeUtils.dp2px(74), SizeUtils.dp2px(112));
        layoutParams.setMargins(SizeUtils.dp2px(0), SizeUtils.dp2px(4), SizeUtils.dp2px(8), SizeUtils.dp2px(4));
        roundedImageView.setLayoutParams(layoutParams);
        return new ViewHolder(roundedImageView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GlideUtils.loadObjectImage(mContext, roomBgList.get(position), holder.roundedImageView);

    }

    @Override
    public int getItemCount() {
        return roomBgList.isEmpty() ? 0 : roomBgList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RoundedImageView roundedImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView = (RoundedImageView) itemView;
        }
    }

}
