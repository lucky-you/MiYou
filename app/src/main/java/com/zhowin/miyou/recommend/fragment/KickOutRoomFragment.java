package com.zhowin.miyou.recommend.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindFragment;
import com.zhowin.miyou.databinding.IncludeKickOutRoomFragmentBinding;
import com.zhowin.miyou.recommend.adapter.KickOutRoomAdapter;
import com.zhowin.miyou.recommend.callback.OnHitCenterClickListener;
import com.zhowin.miyou.recommend.dialog.HitCenterDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * author : zho
 * date  ：2020/9/15
 * 踢出房间 ，和  禁言共用
 * type   1:踢出  2: 禁言
 */
public class KickOutRoomFragment extends BaseBindFragment<IncludeKickOutRoomFragmentBinding> implements BaseQuickAdapter.OnItemClickListener {

    private int classType, fragmentIndex;
    private KickOutRoomAdapter kickOutRoomAdapter;

    public static KickOutRoomFragment newInstance(int type, int index) {
        KickOutRoomFragment fragment = new KickOutRoomFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.TYPE, type);
        bundle.putInt(ConstantValue.INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_kick_out_room_fragment;
    }

    @Override
    public void initView() {
        classType = getArguments().getInt(ConstantValue.TYPE);
        fragmentIndex = getArguments().getInt(ConstantValue.INDEX);
    }

    @Override
    public void initData() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            stringList.add("");
        }
        kickOutRoomAdapter = new KickOutRoomAdapter(stringList);
        kickOutRoomAdapter.setClassType(classType, fragmentIndex);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(kickOutRoomAdapter);
        kickOutRoomAdapter.setOnItemClickListener(this::onItemClick);
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
        showKickOutDialog();
    }

    private void showKickOutDialog() {
        String title = 1 == classType ? "确定将小猫猫踢出房间吗？" : "确定要将猫猫的鱼禁麦吗?";
        HitCenterDialog hitCenterDialog = new HitCenterDialog(mContext);
        hitCenterDialog.setDialogTitle(title);
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
