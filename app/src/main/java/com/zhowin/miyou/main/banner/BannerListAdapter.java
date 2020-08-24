package com.zhowin.miyou.main.banner;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.youth.banner.adapter.BannerAdapter;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.base_library.utils.SizeUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.main.model.BannerList;

import java.util.List;

/**
 * author : zho
 * date  ：2020/8/24
 * desc ：
 */
public class BannerListAdapter extends BannerAdapter<BannerList, BannerListAdapter.ViewHolder> {

    protected int imageType;

    public BannerListAdapter(List<BannerList> datas) {
        super(datas);
    }

    public BannerListAdapter(List<BannerList> datas, int imageType) {
        super(datas);
        this.imageType = imageType;
    }

    public void setImageType(int imageType) {
        this.imageType = imageType;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        if (imageType == 1) {
            ImageView imageView = new ImageView(parent.getContext());
            //注意，必须设置为match_parent，这个是viewpager2强制要求的
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params);
            imageView.setBackgroundColor(parent.getResources().getColor(R.color.white));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return new ViewHolder(imageView);
        } else {
            RoundedImageView imageView = new RoundedImageView(parent.getContext());
            imageView.setCornerRadius(SizeUtils.dp2px(10));
            //注意，必须设置为match_parent，这个是viewpager2强制要求的
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return new ViewHolder(imageView);
        }
    }

    @Override
    public void onBindView(ViewHolder holder, BannerList data, int position, int size) {
        GlideUtils.loadObjectImage(holder.imageView.getContext(), data.getBannerImage(), holder.imageView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView;
        }
    }


}
