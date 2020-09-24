package com.zhowin.miyou.mine.activity;


import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.yanzhenjie.permission.runtime.Permission;
import com.yanzhenjie.permission.runtime.PermissionDef;
import com.zhowin.base_library.adapter.NineGridItemListAdapter;
import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.callback.OnNineGridItemClickListener;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.permission.AndPermissionListener;
import com.zhowin.base_library.permission.AndPermissionUtils;
import com.zhowin.base_library.pickerview.OnSelectTimeClickListener;
import com.zhowin.base_library.pickerview.PickerViewTimeUtils;
import com.zhowin.base_library.pictureSelect.PictureSelectorUtils;
import com.zhowin.base_library.qiniu.QiNiuYunBean;
import com.zhowin.base_library.qiniu.QinIuUpLoadListener;
import com.zhowin.base_library.qiniu.QinIuUtils;
import com.zhowin.base_library.utils.ActivityManager;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.base_library.utils.SetDrawableHelper;
import com.zhowin.base_library.utils.SplitUtils;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.base_library.utils.ZodiacUtil;
import com.zhowin.base_library.widget.FullyGridLayoutManager;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityPersonalInfoBinding;
import com.zhowin.miyou.http.HttpRequest;

import java.util.ArrayList;
import java.util.HashMap;
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
    private String qiNiuToken, qiNiuCdnUrl;
    private boolean isChangeHead, isChangeBackground;
    private String headImageUrl, userNickName, userBirthday, userXinZuo, userSignText, backGroundUrlList = "";


    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_info;
    }

    @Override
    public void initView() {
        setOnClick(R.id.civUserHeadPhoto, R.id.ivDeleteHead, R.id.clSelectAgeLayout);
//        qiNiuToken = QiNiuYunBean.getQiNiuToken();
//        qiNiuCdnUrl = QiNiuYunBean.getQiNiuInfo().getAddress();
//        if (TextUtils.isEmpty(qiNiuToken))
        getQiNiuToken();
    }

    @Override
    public void initData() {
        getUserInfoMessage();
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
                isChangeBackground = true;
                backGroundUrlList = "";
                mBinding.tvBackgroundSize.setText("背景墙(" + localMediaList.size() + "/" + MAX_NUM + ")");
            }
        });
    }


    @Override
    public void initListener() {
        mBinding.titleView.getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadBackgroundUrl();
            }
        });
    }

    /**
     * 获取用户信息
     */
    private void getUserInfoMessage() {
        showLoadDialog();
        HttpRequest.getUserInfoMessage(this, new HttpCallBack<UserInfo>() {
            @Override
            public void onSuccess(UserInfo userInfo) {
                dismissLoadDialog();
                if (userInfo != null) {
                    setDataToViews(userInfo);
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);
            }
        });
    }

    private void setDataToViews(UserInfo userInfo) {
        headImageUrl = userInfo.getProfilePictureKey();
        GlideUtils.loadUserPhotoForLogin(mContext, headImageUrl, mBinding.civUserHeadPhoto);
        mBinding.editUserNickName.setText(userInfo.getAvatar());
        mBinding.editUserNickName.setSelection(userInfo.getAvatar().length());
        mBinding.tvUserSex.setText(userInfo.getGender());
        userBirthday = userInfo.getBirthday();
//        userBirthday = DateHelpUtils.getStringDateOfDayTwo(userInfo.getBirthday());
        mBinding.tvAgeText.setText(userBirthday);
        userXinZuo = ZodiacUtil.date2Constellation(userBirthday);
        List<String> userBackgroundList = userInfo.getBackgroundPictureKeys();
        if (userBackgroundList != null && !userBackgroundList.isEmpty())
            backGroundUrlList = SplitUtils.getStringTextId(userBackgroundList);
        SetDrawableHelper.setLeftDrawable(mContext, mBinding.tvUserSex, TextUtils.equals("男", userInfo.getGender()), 6, R.drawable.data_man_icon, R.drawable.data_girl_icon);
        if (!TextUtils.isEmpty(userInfo.getStatus())) {
            mBinding.editSignature.setText(userInfo.getStatus());
            mBinding.editSignature.setSelection(userInfo.getStatus().length());
        }
        List<String> backgroundList = userInfo.getBackgroundPictureKeys();
        if (backgroundList != null && !backgroundList.isEmpty()) {
//            if (!selectList.isEmpty()) selectList.clear();
            List<LocalMedia> mediaList = new ArrayList<>();
            for (String item : backgroundList) {
                LocalMedia localMedia = new LocalMedia();
                localMedia.setPath(item);
                mediaList.add(localMedia);
            }
            mBinding.tvBackgroundSize.setText("背景墙(" + mediaList.size() + "/" + MAX_NUM + ")");
            nineGridItemListAdapter.setNewDataList(mediaList);
        }
    }


    /**
     * 上传图片
     */
    private void uploadBackgroundUrl() {
        if (TextUtils.isEmpty(headImageUrl)) {
            ToastUtils.showToast("请先上传图像哦");
            return;
        }
        if (selectList.isEmpty()) {
            //没有选择背景，直接提交
            submitUserMessageData();
        } else {
            //选择了背景，先上传图片，后提交
            for (int i = 0; i < selectList.size(); i++) {
                qinIuUpLoad(false, PictureSelectorUtils.getPhotoPath(selectList.get(i)));
            }
        }
    }

    /**
     * 提交用户信息
     */
    private void submitUserMessageData() {
        userNickName = mBinding.editUserNickName.getText().toString().trim();
        userSignText = mBinding.editSignature.getText().toString().trim();
        HashMap<String, Object> map = new HashMap<>();
        map.put("avatar", userNickName);
        map.put("birthday", userBirthday);
        map.put("constellation", userXinZuo);
        map.put("profilePictureKey", headImageUrl);
        map.put("backgroundPictureKeys", backGroundUrlList);
        map.put("status", userSignText);
        showLoadDialog();
        HttpRequest.changeUserInfoMessage(this, map, new HttpCallBack<Object>() {
            @Override
            public void onSuccess(Object object) {
                dismissLoadDialog();
                ActivityManager.getAppInstance().finishActivity();
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.civUserHeadPhoto:
                //自定义,弹框选择，请求权限
                if (isChangeHead)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermission(Permission.CAMERA, Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE);
                    } else {
                        PictureSelectorUtils.selectImageOfOne(mContext, 888, false);
                    }
                break;

            case R.id.ivDeleteHead:
                //点击删除头像按钮
                GlideUtils.loadUserPhotoForLogin(mContext, R.mipmap.data_update_pic, mBinding.civUserHeadPhoto);
                mBinding.ivDeleteHead.setVisibility(View.GONE);
                isChangeHead = true;
                headImageUrl = "";
                break;
            case R.id.clSelectAgeLayout:
                showSelectAgeDialog();
                break;
        }
    }

    private void showSelectAgeDialog() {
        PickerViewTimeUtils.selectTimePickerView(mContext, new OnSelectTimeClickListener() {
            @Override
            public void onDateTime(String dateTime) {
                mBinding.tvAgeText.setText(dateTime);
                userBirthday = dateTime;
                userXinZuo = ZodiacUtil.date2Constellation(dateTime);
            }
        });
    }

    private void requestPermission(@PermissionDef String... permissions) {
        AndPermissionUtils.requestPermission(mContext, new AndPermissionListener() {
            @Override
            public void PermissionSuccess(List<String> permissions) {
                PictureSelectorUtils.selectImageOfOne(mContext, 888, false);
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
            case 888:
                //头像
                List<LocalMedia> selectHeadImage = PictureSelector.obtainMultipleResult(data);
                if (selectHeadImage.isEmpty()) return;
                String imageUrl = PictureSelectorUtils.getPhotoPath(selectHeadImage.get(0));
                qinIuUpLoad(true, imageUrl);
                break;
            case PictureConfig.CHOOSE_REQUEST:
                //背景
                selectList = PictureSelector.obtainMultipleResult(data);
                if (selectList != null && !selectList.isEmpty()) {
                    nineGridItemListAdapter.setNewDataList(selectList);
                }
                break;
        }
    }

    /**
     * 上传到七牛云
     */
    private void qinIuUpLoad(boolean isUploadHead, String ImageUrl) {
        if (TextUtils.isEmpty(qiNiuToken)) {
            ToastUtils.showToast("上传获取token失败");
            return;
        }
        QinIuUtils.qinIuUpLoad(ImageUrl, qiNiuToken, new QinIuUpLoadListener() {
            @Override
            public void upLoadSuccess(String path) {
                if (isUploadHead) {
                    headImageUrl = path;
                    String headUrl = qiNiuCdnUrl + path;
                    System.out.println("headUrl:" + headUrl);
                    GlideUtils.loadUserPhotoForLogin(mContext, headUrl, mBinding.civUserHeadPhoto);
                    mBinding.ivDeleteHead.setVisibility(View.VISIBLE);
                    isChangeHead = false;
                } else {
                    qinIuImages.add(path);
                    if (qinIuImages.size() == selectList.size()) {
                        backGroundUrlList = SplitUtils.getStringTextId(qinIuImages);
                        submitUserMessageData();
                    }
                }
            }

            @Override
            public void upLoadFail(String errorMessage) {
                ToastUtils.showToast("图片上传失败:" + errorMessage);
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
}
