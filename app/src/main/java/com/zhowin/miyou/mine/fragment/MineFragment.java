package com.zhowin.miyou.mine.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.MineFragmentLayoutBinding;
import com.zhowin.miyou.login.activity.LoginActivity;
import com.zhowin.miyou.mine.adapter.MineIconListAdapter;
import com.zhowin.miyou.mine.model.MineIconList;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的
 */
public class MineFragment extends BaseBindFragment<MineFragmentLayoutBinding> implements BaseQuickAdapter.OnItemClickListener {


    private final Class<?>[] mClasses = {
            LoginActivity.class,
            LoginActivity.class,
            LoginActivity.class,
            LoginActivity.class,
            LoginActivity.class,
            LoginActivity.class,
            LoginActivity.class,
            LoginActivity.class,
            LoginActivity.class
    };


    @Override
    public int getLayoutId() {
        return R.layout.mine_fragment_layout;
    }

    @Override
    public void initView() {
        setOnClick(R.id.llWalletLayout, R.id.llKnighthoodLayout, R.id.llVipLayout);

    }

    @Override
    public void initData() {
        setMineIconList();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llWalletLayout:
                break;
            case R.id.llKnighthoodLayout:
                break;
            case R.id.llVipLayout:
                break;
        }
    }


    private void setMineIconList() {
        List<MineIconList> mineIconList = new ArrayList<>();
        mineIconList.add(new MineIconList(R.drawable.mine_team_icon, "公告"));
        mineIconList.add(new MineIconList(R.drawable.mine_room_icon, "我的房间"));
        mineIconList.add(new MineIconList(R.drawable.mine_authen_icon, "实名认证"));
        mineIconList.add(new MineIconList(R.drawable.mine_shop_icon, "商城"));
        mineIconList.add(new MineIconList(R.drawable.mine_dressup_icon, "个性装扮"));
        mineIconList.add(new MineIconList(R.drawable.mine_in_icon, "签到抽奖"));
        mineIconList.add(new MineIconList(R.drawable.mine_sever_icon, "客服(在线)"));
        mineIconList.add(new MineIconList(R.drawable.mine_help_icon, "帮助与反馈"));
        mineIconList.add(new MineIconList(R.drawable.mine_young_icon, "青少年模式"));
        MineIconListAdapter mineIconListAdapter = new MineIconListAdapter(mineIconList);
        mBinding.mineRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.mineRecyclerView.setAdapter(mineIconListAdapter);
        mineIconListAdapter.setOnItemClickListener(this::onItemClick);

    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .statusBarColor(R.color.color_8E9DFD)
                .statusBarDarkFont(true, 0f)
                .init();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        startActivity(mClasses[position]);
    }
}