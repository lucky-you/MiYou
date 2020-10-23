package com.zhowin.miyou.mine.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.utils.EmptyViewUtils;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindActivity;
import com.zhowin.miyou.databinding.ActivityBlackListBinding;
import com.zhowin.miyou.http.BaseResponse;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.mine.adapter.BlackListAdapter;
import com.zhowin.miyou.mine.callback.OnBlackListItemClickListener;
import com.zhowin.miyou.mine.model.AttentionUserList;

import java.util.List;

/**
 * 黑名单
 */
public class BlackListActivity extends BaseBindActivity<ActivityBlackListBinding> implements OnBlackListItemClickListener {

    private BlackListAdapter blackListAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_black_list;
    }

    @Override
    public void initView() {
        getBlackListUserList();
    }

    @Override
    public void initData() {

        blackListAdapter = new BlackListAdapter();
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(blackListAdapter);
        blackListAdapter.setOnBlackListItemClickListener(this::onRemoveBlackList);
    }

    @Override
    public void initListener() {
        mBinding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getBlackListUserList();
                mBinding.refreshLayout.setRefreshing(false);
            }
        });
    }

    /**
     * 获取黑名单列表
     */
    private void getBlackListUserList() {
        showLoadDialog();
        HttpRequest.getBlackListUserList(this, UserInfo.getUserInfo().getUserId(), new HttpCallBack<BaseResponse<AttentionUserList>>() {
            @Override
            public void onSuccess(BaseResponse<AttentionUserList> attentionUserListBaseResponse) {
                dismissLoadDialog();
                if (attentionUserListBaseResponse != null) {
                    List<AttentionUserList> attentionList = attentionUserListBaseResponse.getRecords();
                    if (attentionList != null && !attentionList.isEmpty()) {
                        blackListAdapter.setNewData(attentionList);
                    } else {
                        EmptyViewUtils.bindEmptyView(mContext, blackListAdapter, R.drawable.empty_wufs_icon, "暂无黑名单");
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
    public void onRemoveBlackList(int position, int userId) {
        removeUserFromBlackList(position, userId);
    }

    /**
     * 移除黑名单
     */
    private void removeUserFromBlackList(int position, int userId) {
        showLoadDialog();
        HttpRequest.addAttentionOrBlackList(this, 4, userId, new HttpCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                dismissLoadDialog();
                int dataSize = blackListAdapter.getData().size();
                if (dataSize > 1) {
                    blackListAdapter.remove(position);
                    blackListAdapter.notifyDataSetChanged();
                } else { //最后一个移除之后，就重新获取数据
                    blackListAdapter.remove(position);
                    getBlackListUserList();
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
