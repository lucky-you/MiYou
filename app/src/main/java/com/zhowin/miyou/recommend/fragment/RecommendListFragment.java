package com.zhowin.miyou.recommend.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhowin.base_library.base.BaseLibFragment;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.base_library.utils.SizeUtils;
import com.zhowin.base_library.widget.GridSpacingItemDecoration;
import com.zhowin.miyou.R;
import com.zhowin.miyou.recommend.adapter.RecommendListAdapter;
import com.zhowin.miyou.recommend.model.RecommendList;

import java.util.ArrayList;
import java.util.List;

/**
 * 推荐列表
 */
public class RecommendListFragment extends BaseLibFragment {


    private RecyclerView recyclerView;
    private RecommendListAdapter recommendListAdapter;
    private int fragmentIndex;

    public static RecommendListFragment newInstance(int index) {
        RecommendListFragment fragment = new RecommendListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.include_recommend_list_fragment;
    }

    @Override
    public void initView() {
        fragmentIndex = getArguments().getInt(ConstantValue.INDEX);
        recyclerView = get(R.id.recyclerView);
    }

    @Override
    public void initData() {
        setListData();
    }

    private void setListData() {

        List<RecommendList> recommendLists = new ArrayList<>();
        recommendLists.add(new RecommendList("娱乐", "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1027245443,3552957153&fm=26&gp=0.jpg"));
        recommendLists.add(new RecommendList("男神", "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1027245443,3552957153&fm=26&gp=0.jpg"));
        recommendLists.add(new RecommendList("女神", "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1027245443,3552957153&fm=26&gp=0.jpg"));
        recommendLists.add(new RecommendList("娱乐", "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1027245443,3552957153&fm=26&gp=0.jpg"));
        recommendLists.add(new RecommendList("FM", "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1027245443,3552957153&fm=26&gp=0.jpg"));
        recommendLists.add(new RecommendList("女神", "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1027245443,3552957153&fm=26&gp=0.jpg"));
        recommendLists.add(new RecommendList("娱乐", "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1027245443,3552957153&fm=26&gp=0.jpg"));
        recommendLists.add(new RecommendList("FM", "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1027245443,3552957153&fm=26&gp=0.jpg"));

        recommendListAdapter = new RecommendListAdapter(recommendLists);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, SizeUtils.dp2px(10), false));
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        recyclerView.setAdapter(recommendListAdapter);
    }
}
