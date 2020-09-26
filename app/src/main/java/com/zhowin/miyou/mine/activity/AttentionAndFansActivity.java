package com.zhowin.miyou.mine.activity;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.base_library.utils.EmptyViewUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityAttentionAndFansBinding;
import com.zhowin.miyou.http.BaseResponse;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.mine.adapter.AttentionAndFansAdapter;
import com.zhowin.miyou.mine.model.AttentionUserList;

import java.util.List;

/**
 * 关注、粉丝,访客
 */
public class AttentionAndFansActivity extends BaseBindActivity<ActivityAttentionAndFansBinding> {




    private int classType;
    private String emptyHitText;
    private AttentionAndFansAdapter attentionAndFansAdapter;

    public static void start(Context context, int type) {
        Intent intent = new Intent(context, AttentionAndFansActivity.class);
        intent.putExtra(ConstantValue.TYPE, type);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_attention_and_fans;
    }

    @Override
    public void initView() {
        classType = getIntent().getIntExtra(ConstantValue.TYPE, 0);
        switch (classType) {
            case 1:
                mBinding.titleView.setTitle("关注");
                emptyHitText = "没有关注";
                break;
            case 2:
                mBinding.titleView.setTitle("粉丝");
                emptyHitText = "没有粉丝";
                break;
            case 3:
                mBinding.titleView.setTitle("访客");
                emptyHitText = "没有访客";
                break;
        }
        getAttentionOrFansUserList();
    }


    @Override
    public void initData() {
        attentionAndFansAdapter = new AttentionAndFansAdapter();
        attentionAndFansAdapter.setAdapterType(classType);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(attentionAndFansAdapter);

    }

    private void getAttentionOrFansUserList() {
        showLoadDialog();
        HttpRequest.getAttentionOrFansUserList(this, classType, UserInfo.getUserInfo().getUserId(), new HttpCallBack<BaseResponse<AttentionUserList>>() {
            @Override
            public void onSuccess(BaseResponse<AttentionUserList> attentionUserListBaseResponse) {
                dismissLoadDialog();
                if (attentionUserListBaseResponse != null) {
                    List<AttentionUserList> attentionOrFansList = attentionUserListBaseResponse.getRecords();
                    if (attentionOrFansList != null && !attentionOrFansList.isEmpty()) {
                        attentionAndFansAdapter.setNewData(attentionOrFansList);
                        mBinding.tvFansNumber.setVisibility(View.VISIBLE);
                        switch (classType) {
                            case 1:
                                mBinding.tvFansNumber.setText("关注" + attentionOrFansList.size() + "人");
                                break;
                            case 2:
                                mBinding.tvFansNumber.setText("粉丝" + attentionOrFansList.size() + "人");
                                break;
                            case 3:
                                mBinding.tvFansNumber.setText("访客" + attentionOrFansList.size() + "人");
                                break;
                        }
                    } else {
                        mBinding.tvFansNumber.setVisibility(View.GONE);
                        EmptyViewUtils.bindEmptyView(mContext, attentionAndFansAdapter, R.drawable.empty_wufs_icon, emptyHitText);
                    }
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();

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
