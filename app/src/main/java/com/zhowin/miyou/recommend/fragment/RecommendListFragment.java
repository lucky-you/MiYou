package com.zhowin.miyou.recommend.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhowin.base_library.base.BaseLibFragment;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.base_library.utils.SizeUtils;
import com.zhowin.base_library.widget.GridSpacingItemDecoration;
import com.zhowin.miyou.R;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.mine.dialog.UnlockRoomDialog;
import com.zhowin.miyou.recommend.adapter.RecommendListAdapter;
import com.zhowin.miyou.recommend.model.RecommendList;

import java.util.ArrayList;
import java.util.List;

/**
 * 推荐列表
 */
public class RecommendListFragment extends BaseLibFragment implements BaseQuickAdapter.OnItemClickListener {


    private RecyclerView recyclerView;
    private RecommendListAdapter recommendListAdapter;
    private int fragmentIndex, typeId;

    public static RecommendListFragment newInstance(int index, int typeId) {
        RecommendListFragment fragment = new RecommendListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.INDEX, index);
        bundle.putInt(ConstantValue.ID, typeId);
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
        typeId = getArguments().getInt(ConstantValue.ID);
        recyclerView = get(R.id.recyclerView);
    }

    @Override
    public void initData() {
        getHomeRoomList();
        recommendListAdapter = new RecommendListAdapter();
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, SizeUtils.dp2px(10), false));
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        recyclerView.setAdapter(recommendListAdapter);
        recommendListAdapter.setOnItemClickListener(this::onItemClick);
    }


    private void getHomeRoomList() {
        HttpRequest.getHomeRoomList(this, typeId, new HttpCallBack<List<RecommendList>>() {
            @Override
            public void onSuccess(List<RecommendList> recommendLists) {
                if (recommendLists != null && !recommendLists.isEmpty()) {
                    recommendListAdapter.setNewData(recommendLists);
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {

            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        boolean roomIsLock = recommendListAdapter.getItem(position).isExistPwd();
        if (roomIsLock) {
            showUnLockRoomDialog();
        } else {

        }
    }

    private void showUnLockRoomDialog() {
        UnlockRoomDialog unlockRoomDialog = new UnlockRoomDialog(mContext);
        unlockRoomDialog.show();
        unlockRoomDialog.setOnUnLockRoomListener(new UnlockRoomDialog.OnUnLockRoomListener() {
            @Override
            public void onUnLockRoom(String password) {

            }
        });
    }
}
