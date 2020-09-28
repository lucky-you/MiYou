package com.zhowin.miyou.recommend.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;
import com.zhowin.miyou.recommend.callback.OnHomePageMoreGiftListener;
import com.zhowin.miyou.recommend.model.HomePageCategoryList;

import java.util.List;

/**
 * author : zho
 * date  ：2020/9/5
 * desc ： 分类的adapter
 */
public class HomePagerAdapter extends BaseQuickAdapter<HomePageCategoryList, BaseViewHolder> {
    public HomePagerAdapter(@Nullable List<HomePageCategoryList> data) {
        super(R.layout.include_home_pager_item_view, data);
    }

    private OnHomePageMoreGiftListener onHomePageMoreGiftListener;

    public void setOnHomePageMoreGiftListener(OnHomePageMoreGiftListener onHomePageMoreGiftListener) {
        this.onHomePageMoreGiftListener = onHomePageMoreGiftListener;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HomePageCategoryList item) {

        helper.setText(R.id.tvLeftTitle, item.getLeftTitle())
                .getView(R.id.tvSeeMOre).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onHomePageMoreGiftListener != null) {
                    onHomePageMoreGiftListener.onSeeMoreItemClick(helper.getAdapterPosition());
                }
            }
        });
        RecyclerView recyclerView = helper.getView(R.id.itemRecyclerView);
        HomePagerChildAdapter homePagerChildAdapter = new HomePagerChildAdapter(item.getStringList());
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(homePagerChildAdapter);

    }
}
