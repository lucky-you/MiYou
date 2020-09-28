package com.zhowin.miyou.recommend.activity;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.zhowin.base_library.adapter.NineGridItemListAdapter;
import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.callback.OnNineGridItemClickListener;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.pictureSelect.PictureSelectorUtils;
import com.zhowin.base_library.qiniu.QiNiuYunBean;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.base_library.widget.FullyGridLayoutManager;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityReportRoomBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.recommend.adapter.ReportListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 举报房间 和 举报 用户共用
 * type: 1 举报房间 2 举报用户
 */
public class ReportRoomActivity extends BaseBindActivity<ActivityReportRoomBinding> {


    private int classType;

    public static void start(Context context, int type) {
        Intent intent = new Intent(context, ReportRoomActivity.class);
        intent.putExtra(ConstantValue.TYPE, type);
        context.startActivity(intent);
    }

    private List<LocalMedia> selectList = new ArrayList<>();//选中图片的集合
    private List<String> qinIuImages = new ArrayList<>(); //保存从七牛云返回的图片路径的集合
    //选择图片最大数目
    public static final int MAX_NUM = 3;
    private NineGridItemListAdapter nineGridItemListAdapter;
    private String qiNiuToken, qiNiuCdnUrl;

    @Override
    public int getLayoutId() {
        return R.layout.activity_report_room;
    }

    @Override
    public void initView() {
        classType = getIntent().getIntExtra(ConstantValue.TYPE, 1);
        mBinding.llReportRoomLayout.setVisibility(1 == classType ? View.VISIBLE : View.GONE);
        mBinding.clReportUserLayout.setVisibility(1 != classType ? View.VISIBLE : View.GONE);
        getQiNiuToken();
    }

    @Override
    public void initData() {
        List<String> reportList = new ArrayList<>();
        reportList.add("政治敏感");
        reportList.add("色情暴力");
        reportList.add("广告骚扰");
        reportList.add("侵权诈骗");
        reportList.add("其他");
        ReportListAdapter reportListAdapter = new ReportListAdapter(reportList);
        mBinding.reportListView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mBinding.reportListView.setAdapter(reportListAdapter);

        FullyGridLayoutManager manager = new FullyGridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL, false);
        nineGridItemListAdapter = new NineGridItemListAdapter(mContext);
        mBinding.backGroundRecyclerView.setLayoutManager(manager);
        mBinding.backGroundRecyclerView.setAdapter(nineGridItemListAdapter);
        nineGridItemListAdapter.setSelectMax(MAX_NUM);
        nineGridItemListAdapter.setOnNineGridItemClickListener(new OnNineGridItemClickListener() {
            @Override
            public void onAddPictureClick() {
                if (!qinIuImages.isEmpty()) qinIuImages.clear();
                PictureSelectorUtils.selectImageOfMore(mContext, MAX_NUM, false, selectList);
            }

            @Override
            public void onItemClick(int position, View view) {
//                PictureSelector.create(mContext)
//                        .themeStyle(R.style.picture_default_style)
//                        .imageEngine(GlideEngine.createGlideEngine())
//                        .openExternalPreview(position, selectList);
            }

            @Override
            public void onItemClickDelete(int position, List<LocalMedia> localMediaList) {
                mBinding.tvBackgroundSize.setText("上传图片(" + localMediaList.size() + "/" + MAX_NUM + ")");
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PictureConfig.CHOOSE_REQUEST:
                //背景
                selectList = PictureSelector.obtainMultipleResult(data);
                if (selectList != null && !selectList.isEmpty()) {
                    nineGridItemListAdapter.setNewDataList(selectList);
                    mBinding.tvBackgroundSize.setText("上传图片(" + selectList.size() + "/" + MAX_NUM + ")");
                }
                break;
        }
    }

    private void getQiNiuToken() {
        HttpRequest.getQiNiuYunBean(this, new HttpCallBack<QiNiuYunBean>() {
            @Override
            public void onSuccess(QiNiuYunBean qiNiuYunBean) {
                if (qiNiuYunBean != null) {
                    qiNiuToken = qiNiuYunBean.getToken();
                    qiNiuCdnUrl = qiNiuYunBean.getAddress();
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {

            }
        });
    }
}
