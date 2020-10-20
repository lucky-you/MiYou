package com.zhowin.miyou.message.activity;


import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivitySystemMessageBinding;
import com.zhowin.miyou.http.BaseResponse;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.message.adapter.SystemMessageAdapter;
import com.zhowin.miyou.message.model.SystemMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统消息/官方公告/公会消息
 */
public class SystemMessageActivity extends BaseBindActivity<ActivitySystemMessageBinding> {

    private int classType;
    private SystemMessageAdapter systemMessageAdapter;


    public static void start(Context context, int type) {
        Intent intent = new Intent(context, SystemMessageActivity.class);
        intent.putExtra(ConstantValue.TYPE, type);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_system_message;
    }

    @Override
    public void initView() {
        classType = getIntent().getIntExtra(ConstantValue.TYPE, -1);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        switch (classType) {
            case 1:
                mBinding.titleView.setTitle("系统消息");
                getSystemMessageList();
                systemMessageAdapter = new SystemMessageAdapter(new ArrayList<>());
                mBinding.recyclerView.setAdapter(systemMessageAdapter);
                break;
            case 2:
                mBinding.titleView.setTitle("官方公告");
                break;
            case 3:
                mBinding.titleView.setTitle("公会消息");
                break;
        }


    }

    @Override
    public void initData() {

    }

    private void getSystemMessageList() {
        showLoadDialog();
        HttpRequest.getSystemMessageList(this, new HttpCallBack<BaseResponse<SystemMessage>>() {
            @Override
            public void onSuccess(BaseResponse<SystemMessage> systemMessageBaseResponse) {
                dismissLoadDialog();
                List<SystemMessage> messageList = systemMessageBaseResponse.getRecords();
                if (messageList != null && !messageList.isEmpty()) {
                    systemMessageAdapter.setNewData(messageList);
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);

            }
        });
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
}
