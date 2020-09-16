package com.zhowin.miyou.recommend.activity;


import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityBroadcastDatingBinding;
import com.zhowin.miyou.recommend.adapter.BroadcastDatingAdapter;
import com.zhowin.miyou.recommend.adapter.KickOutRoomAdapter;
import com.zhowin.miyou.recommend.callback.OnHitCenterClickListener;
import com.zhowin.miyou.recommend.dialog.HitCenterDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * 广播交友
 */
public class BroadcastDatingActivity extends BaseBindActivity<ActivityBroadcastDatingBinding> implements BaseQuickAdapter.OnItemClickListener {


    private BroadcastDatingAdapter broadcastDatingAdapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_broadcast_dating;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            stringList.add("");
        }
        broadcastDatingAdapter = new BroadcastDatingAdapter(stringList);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(broadcastDatingAdapter);
        broadcastDatingAdapter.setOnItemClickListener(this::onItemClick);
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
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        showCenterHitDialog();
    }

    private void showCenterHitDialog() {
        String title = "小喇叭不足，请到商城购买哦";
        HitCenterDialog hitCenterDialog = new HitCenterDialog(mContext);
        hitCenterDialog.setDialogTitle(title);
        hitCenterDialog.setDetermineText("商城");
        hitCenterDialog.show();
        hitCenterDialog.setOnHitCenterClickListener(new OnHitCenterClickListener() {
            @Override
            public void onCancelClick() {

            }

            @Override
            public void onDetermineClick() {

            }
        });


    }
}
