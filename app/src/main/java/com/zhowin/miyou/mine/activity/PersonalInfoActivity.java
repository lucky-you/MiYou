package com.zhowin.miyou.mine.activity;


import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.zhowin.base_library.adapter.NineGridItemListAdapter;
import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.callback.OnNineGridItemClickListener;
import com.zhowin.base_library.pictureSelect.GlideEngine;
import com.zhowin.base_library.pictureSelect.PictureSelectorUtils;
import com.zhowin.base_library.widget.FullyGridLayoutManager;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityPersonalInfoBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人资料
 */
public class PersonalInfoActivity extends BaseBindActivity<ActivityPersonalInfoBinding> {


    private List<LocalMedia> selectList = new ArrayList<>();//选中图片的集合
    private List<String> qinIuImages = new ArrayList<>(); //保存从七牛云返回的图片路径的集合
    //选择图片最大数目
    public static final int MAX_NUM = 3;
    private NineGridItemListAdapter nineGridItemListAdapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_info;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL, false);
        nineGridItemListAdapter = new NineGridItemListAdapter(mContext);
        mBinding.backGroundRecyclerView.setLayoutManager(manager);
        mBinding.backGroundRecyclerView.setAdapter(nineGridItemListAdapter);
        nineGridItemListAdapter.setSelectMax(MAX_NUM);
        nineGridItemListAdapter.setOnNineGridItemClickListener(new OnNineGridItemClickListener() {
            @Override
            public void onAddPictureClick() {
                PictureSelectorUtils.selectImageOfMore(mContext, MAX_NUM, true, selectList);
            }

            @Override
            public void onItemClick(int position, View view) {
                PictureSelector.create(mContext)
                        .themeStyle(R.style.picture_default_style)
                        .imageEngine(GlideEngine.createGlideEngine())
                        .openExternalPreview(position, selectList);
            }
        });
    }
}
