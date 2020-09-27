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
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityAttentionAndFansBinding;
import com.zhowin.miyou.http.BaseResponse;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.mine.adapter.AttentionAndFansAdapter;
import com.zhowin.miyou.mine.callback.OnAttentionOrFansClickListener;
import com.zhowin.miyou.mine.model.AttentionUserList;
import com.zhowin.miyou.recommend.activity.HomepageActivity;
import com.zhowin.miyou.recommend.callback.OnHitCenterClickListener;
import com.zhowin.miyou.recommend.dialog.HitCenterDialog;

import java.util.List;

/**
 * 关注、粉丝,访客
 */
public class AttentionAndFansActivity extends BaseBindActivity<ActivityAttentionAndFansBinding> implements OnAttentionOrFansClickListener {


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
        attentionAndFansAdapter.setOnAttentionOrFansClickListener(this);
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
                ToastUtils.showToast(errorMsg);
            }
        });
    }


    @Override
    public void initListener() {
        mBinding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAttentionOrFansUserList();
                mBinding.refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onItemHeaderPhotoClick(int userId) {
        HomepageActivity.start(mContext, false, userId);

    }

    @Override
    public void onItemAttentionOrFanClick(int position, int relation, int userId) {
        // relation 0:未关注 1：已关注 2：互相关注
        switch (classType) {
            case 1: //我关注的时候只能取消关注
                showCancelAttentionUserDialog(position, userId);
                break;
            case 2: //关注我的只能添加关注
                addAttentionOrBlackList(1, position, userId);
                break;
        }
    }

    /**
     * 取消关注
     */
    private void showCancelAttentionUserDialog(int position, int userId) {
        HitCenterDialog hitCenterDialog = new HitCenterDialog(mContext);
        hitCenterDialog.setDialogTitle("确定要取消关注吗?");
        hitCenterDialog.show();
        hitCenterDialog.setOnHitCenterClickListener(new OnHitCenterClickListener() {
            @Override
            public void onCancelClick() {

            }

            @Override
            public void onDetermineClick() {
                addAttentionOrBlackList(2, position, userId);
            }
        });
    }

    /**
     * 关注或者取消
     */
    private void addAttentionOrBlackList(int type, int position, int userId) {
        showLoadDialog();
        HttpRequest.addAttentionOrBlackList(this, type, userId, new HttpCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                dismissLoadDialog();
                switch (classType) {
                    case 1: //我关注的时候只能取消关注,移除item
                        int dataSize = attentionAndFansAdapter.getData().size();
                        if (dataSize > 1) {
                            attentionAndFansAdapter.remove(position);
                            attentionAndFansAdapter.notifyDataSetChanged();
                        } else { //最后一个移除之后，就重新获取数据
                            attentionAndFansAdapter.remove(position);
                            getAttentionOrFansUserList();
                        }
                        break;
                    case 2: //关注我的只能添加关注,刷新item
                        attentionAndFansAdapter.getItem(position).setRelation(2);
                        attentionAndFansAdapter.notifyItemChanged(position);
                        break;
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);
            }
        });
    }

}
