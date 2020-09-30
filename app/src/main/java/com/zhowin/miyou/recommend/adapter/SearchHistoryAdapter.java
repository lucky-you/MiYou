package com.zhowin.miyou.recommend.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;
import com.zhowin.miyou.db.model.SearchUserHistory;

import java.util.List;

/**
 * author : zho
 * date  ：2020/9/15
 * desc ： 搜索用户的历史记录
 */
public class SearchHistoryAdapter extends BaseQuickAdapter<SearchUserHistory, BaseViewHolder> {
    public SearchHistoryAdapter() {
        super(R.layout.include_search_history_item_view);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SearchUserHistory item) {
        helper.setText(R.id.tvHistoryTitle, item.getTitle());
    }
}
