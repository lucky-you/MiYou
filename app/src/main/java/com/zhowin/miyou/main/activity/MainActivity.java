package com.zhowin.miyou.main.activity;

import androidx.fragment.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseLibActivity;
import com.zhowin.miyou.main.entity.TabEntity;
import com.zhowin.miyou.message.fragment.MessageFragment;
import com.zhowin.miyou.mine.fragment.MineFragment;
import com.zhowin.miyou.recommend.fragment.RecommendFragment;

import java.util.ArrayList;

import io.rong.callkit.RongCallKit;

public class MainActivity extends BaseLibActivity {

    private CommonTabLayout commonTabLayout;

    private String[] mTitles = {"房间", "消息", "我的"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private int[] mIconSelectIds = {
            R.drawable.bottom_home_click_icon, R.drawable.bottom_message_click_icon, R.drawable.bottom_mine_click_icon};

    private int[] mIconUnSelectIds = {
            R.drawable.bottom_home_icon, R.drawable.bottom_message_icon, R.drawable.bottom_mine_icon};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        commonTabLayout = get(R.id.commonTabLayout);

        /**
         * 防止 voip 通话页面被会话列表、会话页面或者开发者 app 层页面覆盖。
         * 使用 maven 接入 callkit 的开发者在 app 层主页面的 onCreate 调用此方法即可。
         */
        RongCallKit.onViewCreated();
    }

    @Override
    public void initData() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnSelectIds[i]));
        }
        mFragments.add(new RecommendFragment());
        mFragments.add(new MessageFragment());
        mFragments.add(new MineFragment());

        commonTabLayout.setTabData(mTabEntities, mContext, R.id.flContainer, mFragments);
    }

}
