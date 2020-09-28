package com.zhowin.miyou.recommend.activity;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.base_library.utils.SizeUtils;
import com.zhowin.base_library.widget.GridSpacingItemDecoration;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityRoomSearchBinding;
import com.zhowin.miyou.mine.adapter.MyRoomListAdapter;
import com.zhowin.miyou.recommend.adapter.SearchHistoryAdapter;
import com.zhowin.miyou.recommend.model.RecommendList;

import java.util.ArrayList;
import java.util.List;

/**
 * 房间/ 好友 搜索
 * <p>
 * type: 1 房间  2 好友
 */
public class RoomSearchActivity extends BaseBindActivity<ActivityRoomSearchBinding> {

    private int classType;

    public static void start(Context context, int type) {
        Intent intent = new Intent(context, RoomSearchActivity.class);
        intent.putExtra(ConstantValue.TYPE, type);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_room_search;
    }

    @Override
    public void initView() {
        setOnClick(R.id.ivBackReturn, R.id.tvSearchText);
        classType = getIntent().getIntExtra(ConstantValue.TYPE, 1);
    }

    @Override
    public void initData() {
        switch (classType) {
            case 1:
                mBinding.editSearch.setHint("请输入房间ID/名称关键字");
                List<RecommendList> recommendLists = new ArrayList<>();
                for (int i = 0; i < 12; i++) {
                    recommendLists.add(new RecommendList("娱乐房" + i, "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1027245443,3552957153&fm=26&gp=0.jpg"));
                }
                MyRoomListAdapter myRoomListAdapter = new MyRoomListAdapter(recommendLists);
                mBinding.recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, SizeUtils.dp2px(10), false));
                mBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
                mBinding.recyclerView.setAdapter(myRoomListAdapter);
                break;
            case 2:
                mBinding.editSearch.setHint("请输入好友ID/昵称关键字");
                List<String> searchHistoryLists = new ArrayList<>();
                searchHistoryLists.add("周树人");
                searchHistoryLists.add("树人教育");
                searchHistoryLists.add("hello树先生");
                SearchHistoryAdapter searchHistoryAdapter = new SearchHistoryAdapter(searchHistoryLists);
                mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                mBinding.recyclerView.setAdapter(searchHistoryAdapter);
                break;
        }
    }

    @Override
    public void initListener() {
        mBinding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mBinding.refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBackReturn:
                ActivityManager.getAppInstance().finishActivity();
                break;
            case R.id.tvSearchText:
                break;
        }
    }
}
