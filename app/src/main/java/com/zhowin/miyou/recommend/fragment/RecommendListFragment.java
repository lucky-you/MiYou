package com.zhowin.miyou.recommend.fragment;

import android.os.Bundle;

import com.zhowin.base_library.base.BaseLibFragment;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;

/**
 * 推荐列表
 */
public class RecommendListFragment extends BaseLibFragment {


    public static RecommendListFragment newInstance(int index) {
        RecommendListFragment fragment = new RecommendListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.include_recommend_list_fragment;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
