package com.zhowin.miyou.recommend.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
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
import com.zhowin.base_library.qiniu.QinIuUpLoadListener;
import com.zhowin.base_library.qiniu.QinIuUtils;
import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.base_library.utils.SetDrawableHelper;
import com.zhowin.base_library.utils.SplitUtils;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.base_library.widget.FullyGridLayoutManager;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityReportRoomBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.main.utils.GenderHelper;
import com.zhowin.miyou.recommend.adapter.ReportListAdapter;
import com.zhowin.miyou.recommend.callback.OnReportSelectClickListener;
import com.zhowin.miyou.recommend.model.ReportList;
import com.zhowin.miyou.recommend.model.ReportUserOrRoom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 举报房间 和 举报 用户共用
 * type: 1 举报房间 2 举报用户
 */
public class ReportRoomActivity extends BaseBindActivity<ActivityReportRoomBinding> implements OnReportSelectClickListener {


    public static void start(Context context, int type, ReportUserOrRoom reportUserOrRoom) {
        Intent intent = new Intent(context, ReportRoomActivity.class);
        intent.putExtra(ConstantValue.TYPE, type);
        intent.putExtra(ConstantValue.CONTNET, reportUserOrRoom);
        context.startActivity(intent);
    }

    private List<LocalMedia> selectList = new ArrayList<>();//选中图片的集合
    private List<String> qinIuImages = new ArrayList<>(); //保存从七牛云返回的图片路径的集合
    //选择图片最大数目
    public static final int MAX_NUM = 3;
    private NineGridItemListAdapter nineGridItemListAdapter;
    private String qiNiuToken, qiNiuCdnUrl, backGroundUrlList;
    private int classType, reportInfoId;
    private ReportUserOrRoom reportInfo;


    @Override
    public int getLayoutId() {
        return R.layout.activity_report_room;
    }

    @Override
    public void initView() {
        classType = getIntent().getIntExtra(ConstantValue.TYPE, 1);
        reportInfo = getIntent().getParcelableExtra(ConstantValue.CONTNET);
        mBinding.llReportRoomLayout.setVisibility(1 == classType ? View.VISIBLE : View.GONE);
        mBinding.clReportUserLayout.setVisibility(1 != classType ? View.VISIBLE : View.GONE);
        setOnClick(R.id.tvSubmit);
        getQiNiuToken();
        if (reportInfo != null)
            if (2 == classType) {
                GlideUtils.loadUserPhotoForLogin(mContext, reportInfo.getUserHeadPhoto(), mBinding.ivUserAvatar);
                mBinding.tvUserNickName.setText(reportInfo.getUserNickName());
                mBinding.tvMUNumber.setText(reportInfo.getUserMUNumber());
                mBinding.tvUserSex.setText(String.valueOf(reportInfo.getUserAge()));
                mBinding.tvUserSex.setBackgroundResource(GenderHelper.getSexBackground(reportInfo.getUserGender()));
                int drawable = GenderHelper.getSexDrawable(reportInfo.getUserGender());
                SetDrawableHelper.setLeftDrawable(mContext, mBinding.tvUserSex, true, 2, drawable, drawable);
            } else {

            }
    }

    @Override
    public void initData() {
        List<ReportList> reportList = new ArrayList<>();
        reportList.add(new ReportList(1, "政治敏感"));
        reportList.add(new ReportList(2, "色情暴力"));
        reportList.add(new ReportList(3, "广告骚扰"));
        reportList.add(new ReportList(4, "侵权诈骗"));
        reportList.add(new ReportList(5, "其他"));
        ReportListAdapter reportListAdapter = new ReportListAdapter();
        mBinding.reportListView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mBinding.reportListView.setAdapter(reportListAdapter);
        reportListAdapter.setNewData(reportList);
        reportListAdapter.setOnReportSelectClickListener(this::onReportItemSelect);

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSubmit:

                break;
        }
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

    /**
     * 上传到七牛云
     */
    private void qinIuUpLoad(boolean reportRoom, String ImageUrl) {
        if (TextUtils.isEmpty(qiNiuToken)) {
            ToastUtils.showToast("上传获取token失败");
            return;
        }
        QinIuUtils.qinIuUpLoad(ImageUrl, qiNiuToken, new QinIuUpLoadListener() {
            @Override
            public void upLoadSuccess(String path) {
                qinIuImages.add(path);
                if (qinIuImages.size() == selectList.size()) {
                    backGroundUrlList = SplitUtils.getStringTextId(qinIuImages);
                    submitReportMessageData();
                }
            }

            @Override
            public void upLoadFail(String errorMessage) {
                ToastUtils.showToast("图片上传失败:" + errorMessage);
            }
        });
    }

    private void submitReportMessageData() {
        if (reportInfoId == 0) {
            ToastUtils.showToast("请选择举报类型哦");
            return;
        }
        String reason = mBinding.editReportReason.getText().toString().trim();
        if (TextUtils.isEmpty(reason)) {
            ToastUtils.showToast("请填写原因哦");
            return;
        }
        HashMap<String, Object> map = new HashMap<>();
        showLoadDialog();
        HttpRequest.submitReportMessage(this, map, new HttpCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                dismissLoadDialog();
                ToastUtils.showCustomToast(mContext, "提交成功");
                ActivityManager.getAppInstance().finishActivity();
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);

            }
        });

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

    @Override
    public void onReportItemSelect(int reportId) {
        if (reportId != 0) {
            reportInfoId = reportId;
        }
    }
}
