package com.zhowin.miyou.recommend.activity;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindActivity;
import com.zhowin.miyou.databinding.ActivitySetUpAdministratorBinding;
import com.zhowin.miyou.recommend.adapter.SetUpAdministratorAdapter;
import com.zhowin.miyou.recommend.callback.OnAdminItemClickListener;
import com.zhowin.miyou.recommend.callback.OnLiveRoomBottomSetListener;
import com.zhowin.miyou.recommend.dialog.LiveRoomBottomSetDialog;
import com.zhowin.miyou.recommend.model.AdminInfoList;

import java.util.ArrayList;
import java.util.List;

/**
 * 设置管理员
 */
public class SetUpAdministratorActivity extends BaseBindActivity<ActivitySetUpAdministratorBinding> implements OnAdminItemClickListener {


    private SetUpAdministratorAdapter setUpAdministratorAdapter;
    private List<AdminInfoList> adminInfoLists = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_up_administrator;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        adminInfoLists.add(new AdminInfoList(1, "接待管理"));
        adminInfoLists.add(new AdminInfoList(2, "接待管理"));
        adminInfoLists.add(new AdminInfoList(2, "接待管理"));

        adminInfoLists.add(new AdminInfoList(1, "普通管理"));
        adminInfoLists.add(new AdminInfoList(2, "接待管理"));
        adminInfoLists.add(new AdminInfoList(2, "接待管理"));

        adminInfoLists.add(new AdminInfoList(1, "普通用户"));
        adminInfoLists.add(new AdminInfoList(2, "接待管理"));
        adminInfoLists.add(new AdminInfoList(2, "接待管理"));

        setUpAdministratorAdapter = new SetUpAdministratorAdapter(adminInfoLists);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(setUpAdministratorAdapter);
        setUpAdministratorAdapter.setOnAdminItemClickListener(this::onItemSetUpAdmin);
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
    public void onItemSetUpAdmin() {
        showSetUpAdminDialog();
    }

    /**
     * 设置管理
     */
    private void showSetUpAdminDialog() {
        LiveRoomBottomSetDialog liveRoomBottomSetDialog = LiveRoomBottomSetDialog.newInstance(false, "设置为接待管理", "取消管理");
        liveRoomBottomSetDialog.show(getSupportFragmentManager(), "admin");
        liveRoomBottomSetDialog.setOnLiveRoomBottomSetListener(new OnLiveRoomBottomSetListener() {
            @Override
            public void onTitleOneClick(int typeOne) {

            }

            @Override
            public void onTitleTwoClick(int typeTwo) {

            }
        });
    }
}
