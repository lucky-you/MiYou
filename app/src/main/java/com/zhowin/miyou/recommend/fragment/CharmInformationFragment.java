package com.zhowin.miyou.recommend.fragment;

import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gyf.immersionbar.ImmersionBar;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindFragment;
import com.zhowin.miyou.databinding.IncludeCharmInformationFragmentBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.recommend.adapter.CharmInformationAdapter;
import com.zhowin.miyou.recommend.model.ToadyUserList;
import com.zhowin.miyou.recommend.model.WeekStarUserList;

import java.util.Arrays;
import java.util.List;


/**
 * author : zho
 * date  ：2020/9/17
 * desc ：  贡献榜单 / 魅力榜单  的 信息
 */
public class CharmInformationFragment extends BaseBindFragment<IncludeCharmInformationFragmentBinding> {


    private int roomID, fragmentType, fragmentIndex;
    private CharmInformationAdapter charmInformationAdapter;
    private boolean isRefresh;

    public static CharmInformationFragment newInstance(int roomId, int type, int index) {
        CharmInformationFragment fragment = new CharmInformationFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.ID, roomId);
        bundle.putInt(ConstantValue.TYPE, type);
        bundle.putInt(ConstantValue.INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_charm_information_fragment;
    }

    @Override
    public void initView() {
        roomID = getArguments().getInt(ConstantValue.ID);
        fragmentType = getArguments().getInt(ConstantValue.TYPE);
        fragmentIndex = getArguments().getInt(ConstantValue.INDEX);
        Log.e("xy", "fragmentType:" + fragmentType + "---fragmentIndex:" + fragmentIndex);
    }

    @Override
    public void initData() {
        if (0 == fragmentType) {
            getRoomTodayUserList();
        } else {
            getRoomWeekStarUserList();
        }
        charmInformationAdapter = new CharmInformationAdapter(Arrays.asList("212", "14545", "ff", "dfd", "dfd"));
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(charmInformationAdapter);
    }

    /**
     * 房间日榜
     */
    private void getRoomTodayUserList() {
        if (isRefresh)
            showLoadDialog();
        HttpRequest.getRoomTodayUserList(this, 1 == fragmentIndex, roomID, new HttpCallBack<List<ToadyUserList>>() {
            @Override
            public void onSuccess(List<ToadyUserList> toadyUserLists) {
                dismissLoadDialog();
                if (toadyUserLists != null) {

                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
            }
        });
    }

    /**
     * 获取周榜
     */
    private void getRoomWeekStarUserList() {
        if (isRefresh)
            showLoadDialog();
        HttpRequest.getRoomWeekStarUserList(this, 1 == fragmentIndex, roomID, new HttpCallBack<WeekStarUserList>() {
            @Override
            public void onSuccess(WeekStarUserList weekStarUserList) {
                dismissLoadDialog();

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

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .statusBarColor(R.color.color_8234FC)
                .keyboardEnable(true)
                .statusBarDarkFont(true, 0f)
                .init();
    }
}
