package com.zhowin.miyou.recommend.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;
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

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HomePageCategoryList item) {

        helper.setText(R.id.tvLeftTitle, item.getLeftTitle());
        RecyclerView recyclerView = helper.getView(R.id.itemRecyclerView);
        HomePagerChildAdapter homePagerChildAdapter = new HomePagerChildAdapter(item.getStringList());
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(homePagerChildAdapter);

    }
}
