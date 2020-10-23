package com.zhowin.miyou.mine.activity;


import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.yanzhenjie.permission.runtime.Permission;
import com.yanzhenjie.permission.runtime.PermissionDef;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.permission.AndPermissionListener;
import com.zhowin.base_library.permission.AndPermissionUtils;
import com.zhowin.base_library.pictureSelect.PictureSelectorUtils;
import com.zhowin.base_library.qiniu.QiNiuYunBean;
import com.zhowin.base_library.qiniu.QinIuUpLoadListener;
import com.zhowin.base_library.qiniu.QinIuUtils;
import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.base.BaseBindActivity;
import com.zhowin.miyou.databinding.ActivityEditVerifiedBinding;
import com.zhowin.miyou.http.HttpRequest;

import java.util.HashMap;
import java.util.List;

/**
 * 实名认证
 */
public class EditVerifiedActivity extends BaseBindActivity<ActivityEditVerifiedBinding> {

    private String qiNiuToken, qiNiuCdnUrl;
    private boolean isPositive = true;//是否是正面
    private String userPositiveImage, userNegativeImage;//正面/反面

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_verified;
    }

    @Override
    public void initView() {
        setOnClick(R.id.ivPositiveImage, R.id.ivNegativeImage);
        getQiNiuToken();

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mBinding.tvSubmitVerified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSubmitVerified();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivPositiveImage: //正面
                isPositive = true;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermission(isPositive, Permission.CAMERA, Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE);
                } else {
                    PictureSelectorUtils.selectOneImage(mContext, 666, false, false);
                }
                break;
            case R.id.ivNegativeImage: //反面
                isPositive = false;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermission(isPositive, Permission.CAMERA, Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE);
                } else {
                    PictureSelectorUtils.selectOneImage(mContext, 777, false, false);
                }
                break;
        }
    }

    private void requestPermission(boolean isPositive, @PermissionDef String... permissions) {
        AndPermissionUtils.requestPermission(mContext, new AndPermissionListener() {
            @Override
            public void PermissionSuccess(List<String> permissions) {
                if (isPositive) {
                    PictureSelectorUtils.selectOneImage(mContext, 666, false, false);
                } else {
                    PictureSelectorUtils.selectOneImage(mContext, 777, false, false);
                }
            }

            @Override
            public void PermissionFailure(List<String> permissions) {
                ToastUtils.showToast("权限未开启");
            }
        }, permissions);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 666:
                //正面
                List<LocalMedia> userPositiveHeadImage = PictureSelector.obtainMultipleResult(data);
                if (userPositiveHeadImage.isEmpty()) return;
                String imagePositiveUrl = PictureSelectorUtils.getPhotoPath(userPositiveHeadImage.get(0));
                qinIuUpLoad(true, imagePositiveUrl);
                break;
            case 777:
                //反面
                List<LocalMedia> userNegativeHeadImage = PictureSelector.obtainMultipleResult(data);
                if (userNegativeHeadImage.isEmpty()) return;
                String imageNegativeUrl = PictureSelectorUtils.getPhotoPath(userNegativeHeadImage.get(0));
                qinIuUpLoad(false, imageNegativeUrl);
                break;
        }
    }

    private void createSubmitVerified() {
        String cardName = mBinding.editCardName.getText().toString().trim();
        String cardNumber = mBinding.editCardNumber.getText().toString().trim();
        if (TextUtils.isEmpty(cardName)) {
            ToastUtils.showToast("请填写您的真实姓名哦");
            return;
        }
        if (TextUtils.isEmpty(cardNumber)) {
            ToastUtils.showToast("身份证号码不能为空哦");
            return;
        }
        if (TextUtils.isEmpty(userPositiveImage)) {
            ToastUtils.showToast("请上传身份证正面照片");
            return;
        }
        if (TextUtils.isEmpty(userNegativeImage)) {
            ToastUtils.showToast("请上传身份证反面照片");
            return;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("realName", cardName);
        map.put("idCardNo", cardNumber);
        map.put("frontPicture", userPositiveImage);
        map.put("backPicture", userNegativeImage);
        showLoadDialog();
        HttpRequest.submitUserVerifiedInfo(this, map, new HttpCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                dismissLoadDialog();
                VerifiedSuccessActivity.start(mContext, 0);
                ActivityManager.getAppInstance().finishActivity();
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);

            }
        });
    }


    /**
     * 上传到七牛云
     */
    private void qinIuUpLoad(boolean isPositiveUrl, String ImageUrl) {
        if (TextUtils.isEmpty(qiNiuToken)) {
            ToastUtils.showToast("上传获取token失败");
            return;
        }
        QinIuUtils.qinIuUpLoad(ImageUrl, qiNiuToken, new QinIuUpLoadListener() {
            @Override
            public void upLoadSuccess(String path) {
                if (isPositiveUrl) {
                    userPositiveImage = path;
                    String PositiveUrl = qiNiuCdnUrl + path;
                    GlideUtils.loadUserPhotoForLogin(mContext, PositiveUrl, mBinding.ivPositiveImage);
                } else {
                    userNegativeImage = path;
                    String NegativeUrl = qiNiuCdnUrl + path;
                    GlideUtils.loadUserPhotoForLogin(mContext, NegativeUrl, mBinding.ivPositiveImage);
                }
            }

            @Override
            public void upLoadFail(String errorMessage) {
                ToastUtils.showToast("图片上传失败:" + errorMessage);
            }
        });
    }


    /**
     * 获取七牛云token
     */
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
