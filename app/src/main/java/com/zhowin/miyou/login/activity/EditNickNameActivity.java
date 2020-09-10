package com.zhowin.miyou.login.activity;


import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.yanzhenjie.permission.runtime.Permission;
import com.yanzhenjie.permission.runtime.PermissionDef;
import com.zhowin.base_library.base.BaseBindActivity;
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
import com.zhowin.base_library.utils.KeyboardUtils;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.base_library.utils.ZodiacUtil;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityEditNickNameBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.login.adapter.SelectInterestAdapter;
import com.zhowin.miyou.login.adapter.SelectUserAvatarAdapter;
import com.zhowin.miyou.login.callback.OnLabelSelectListener;
import com.zhowin.miyou.login.callback.OnUserAvatarClickListener;
import com.zhowin.miyou.login.model.DefaultImageList;
import com.zhowin.miyou.login.model.LabelList;
import com.zhowin.miyou.login.model.SubmitUserInfo;
import com.zhowin.miyou.main.activity.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 设置昵称
 */
public class EditNickNameActivity extends BaseBindActivity<ActivityEditNickNameBinding> implements
        RadioGroup.OnCheckedChangeListener, OnUserAvatarClickListener, OnLabelSelectListener {


    public static final int LAYOUT_TYPE_SEX = 1; //性别
    public static final int LAYOUT_TYPE_BIRTHDAY = 2; //生日
    public static final int LAYOUT_TYPE_AVATAR = 3; //头像
    public static final int LAYOUT_TYPE_NICKNAME = 4; //昵称

    public static final int LAYOUT_TYPE_INTEREST = 5; //兴趣
    private int layoutType = LAYOUT_TYPE_SEX;

    private List<DefaultImageList> defaultImageList = new ArrayList<>();
    private SelectUserAvatarAdapter userAvatarAdapterAdapter;

    private List<LabelList> labelLists = new ArrayList<>();
    private SelectInterestAdapter selectInterestAdapter;

    private String qiNiuToken, qiNiuCdnUrl, gender, labels, profilePictureKey;
    private int labelSize;
    private SubmitUserInfo submitUserInfo = new SubmitUserInfo();

    private boolean isUploadHeadUrl; //是否使用了自定义头像

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_nick_name;
    }

    @Override
    public void initView() {
        setOnClick(R.id.tvNextStep);
    }

    @Override
    public void initData() {
        changeUiFromLayoutType(layoutType);
        mBinding.rgUserGenderLayout.setOnCheckedChangeListener(this::onCheckedChanged);
        getQiNiuToken();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvNextStep) {
            switch (layoutType) {
                case LAYOUT_TYPE_SEX:
                    if (TextUtils.isEmpty(gender)) {
                        ToastUtils.showToast("请先选择性别哦");
                        return;
                    }
                    layoutType = LAYOUT_TYPE_BIRTHDAY;
                    changeUiFromLayoutType(layoutType);
                    break;
                case LAYOUT_TYPE_BIRTHDAY:
                    layoutType = LAYOUT_TYPE_AVATAR;
                    changeUiFromLayoutType(layoutType);
                    break;
                case LAYOUT_TYPE_AVATAR:
                    if (TextUtils.isEmpty(profilePictureKey)) {
                        ToastUtils.showToast("请选择合适的图像哦");
                        return;
                    }
                    submitUserInfo.setProfilePictureKey(profilePictureKey);
                    layoutType = LAYOUT_TYPE_NICKNAME;
                    changeUiFromLayoutType(layoutType);
                    break;
                case LAYOUT_TYPE_NICKNAME:
                    String nickName = mBinding.editNickName.getText().toString();
                    if (TextUtils.isEmpty(nickName)) {
                        ToastUtils.showToast("请输入昵称");
                        return;
                    }
                    submitUserInfo.setAvatar(nickName);
                    layoutType = LAYOUT_TYPE_INTEREST;
                    changeUiFromLayoutType(layoutType);
                    KeyboardUtils.hideSoftInput(mContext);
                    break;
                case LAYOUT_TYPE_INTEREST:
                    if (labelSize > 3) {
                        ToastUtils.showToast("最多选三项哦");
                        return;
                    }
                    submitUserInfo.setLabels(labels);
                    submitUserMessageData();
                    break;
            }
        }
    }

    /**
     * 提交用户信息
     */
    private void submitUserMessageData() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("avatar", submitUserInfo.getAvatar());
        map.put("birthday", submitUserInfo.getBirthday());
        map.put("constellation", submitUserInfo.getConstellation());
        map.put("gender", submitUserInfo.getGender());
        map.put("labels", submitUserInfo.getLabels());
        map.put("profilePictureKey", submitUserInfo.getProfilePictureKey());
        showLoadDialog();
        HttpRequest.submitUserInfoMessage(this, map, new HttpCallBack<UserInfo>() {
            @Override
            public void onSuccess(UserInfo userInfo) {
                dismissLoadDialog();
                if (userInfo != null) {
                    UserInfo.setUserInfo(userInfo);
                }
                startActivity(MainActivity.class);
                ActivityManager.getAppInstance().finishActivity();
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);
            }
        });
    }


    private void changeUiFromLayoutType(int layoutType) {
        switch (layoutType) {
            case LAYOUT_TYPE_SEX:
                mBinding.tvTitle.setText("Hi，你的性别是?");
                mBinding.tvContent.setVisibility(View.VISIBLE);
                mBinding.tvContent.setText("只有一次选择性别的机会，我们会根据选择推荐你喜欢的内容,性别选择后不可更改");
                mBinding.llNickNameLayout.setVisibility(View.GONE);
                mBinding.rgUserGenderLayout.setVisibility(View.VISIBLE);
                break;
            case LAYOUT_TYPE_BIRTHDAY:
                mBinding.tvTitle.setText("Hi，你的生日是哪天呢?");
                mBinding.tvContent.setVisibility(View.VISIBLE);
                mBinding.tvContent.setText("选择正确的生辰日期，会受到来自朋友们的祝福哟");
                mBinding.llNickNameLayout.setVisibility(View.GONE);
                mBinding.rgUserGenderLayout.setVisibility(View.GONE);
                mBinding.rlBirthdayLayout.setVisibility(View.VISIBLE);
                setUserBirthday(ZodiacUtil.getCurrentDate());
                showBirthdayDialog();
                break;
            case LAYOUT_TYPE_AVATAR:
                getUserAvatarList();
                mBinding.tvTitle.setText("真棒！选择你喜欢的头像吧?");
                mBinding.tvContent.setVisibility(View.VISIBLE);
                mBinding.tvContent.setText("头像可选择下面你喜欢的，也可以自定义上传哟");
                mBinding.llNickNameLayout.setVisibility(View.GONE);
                mBinding.rlBirthdayLayout.setVisibility(View.GONE);
                mBinding.rgUserGenderLayout.setVisibility(View.GONE);
                mBinding.selectItemRecyclerView.setVisibility(View.VISIBLE);
                userAvatarAdapterAdapter = new SelectUserAvatarAdapter(defaultImageList);
                userAvatarAdapterAdapter.setOnUserAvatarClickListener(this::onAvatarClick);
                mBinding.selectItemRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
                mBinding.selectItemRecyclerView.setAdapter(userAvatarAdapterAdapter);
                break;
            case LAYOUT_TYPE_NICKNAME:
                mBinding.tvTitle.setText("对了,你的昵称是?");
                mBinding.tvContent.setVisibility(View.GONE);
                mBinding.selectItemRecyclerView.setVisibility(View.GONE);
                mBinding.llNickNameLayout.setVisibility(View.VISIBLE);
                break;
            case LAYOUT_TYPE_INTEREST:
                getInterestList();
                mBinding.tvTitle.setText("最后一步啦，\n" + "选择属于你的定制标签吧");
                mBinding.tvContent.setVisibility(View.VISIBLE);
                mBinding.tvContent.setText("兴趣标签选择后也是不可更改的，选择最符合你的三种标签吧");
                mBinding.llNickNameLayout.setVisibility(View.GONE);
                mBinding.rlBirthdayLayout.setVisibility(View.GONE);
                mBinding.rgUserGenderLayout.setVisibility(View.GONE);
                mBinding.selectItemRecyclerView.setVisibility(View.VISIBLE);
                selectInterestAdapter = new SelectInterestAdapter(labelLists);
                selectInterestAdapter.setOnLabelSelectListener(this::labelItemSelect);
                mBinding.selectItemRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
                mBinding.selectItemRecyclerView.setAdapter(selectInterestAdapter);
                break;
        }
    }

    private void showBirthdayDialog() {
        PickerViewTimeUtils.selectTimePickerViewNoDialog(mContext, mBinding.llSelectBirthday, new OnSelectTimeClickListener() {
            @Override
            public void onDateTime(String dateTime) {
                setUserBirthday(dateTime);
            }
        });
    }

    private void setUserBirthday(String birthday) {
        String xinZuo = ZodiacUtil.date2Constellation(birthday);
        mBinding.tvSelectBirthday.setText(birthday + " " + xinZuo);
        submitUserInfo.setBirthday(birthday);
        submitUserInfo.setConstellation(xinZuo);
    }


    private void getUserAvatarList() {
        HttpRequest.getDefaultAvatar(this, new HttpCallBack<List<DefaultImageList>>() {
            @Override
            public void onSuccess(List<DefaultImageList> defaultImageLists) {
                if (defaultImageLists != null && !defaultImageLists.isEmpty()) {
                    defaultImageList.addAll(defaultImageLists);
                    defaultImageList.add(new DefaultImageList(true));
                    userAvatarAdapterAdapter.setNewData(defaultImageList);
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {

            }
        });
    }

    private void getInterestList() {
        HttpRequest.getDefaultTagList(this, new HttpCallBack<List<LabelList>>() {
            @Override
            public void onSuccess(List<LabelList> labelLists) {
                if (labelLists != null && !labelLists.isEmpty()) {
                    selectInterestAdapter.setNewData(labelLists);
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {

            }
        });
    }

    private void getQiNiuToken() {
        HttpRequest.getQiNiuYunBean(this, new HttpCallBack<QiNiuYunBean>() {
            @Override
            public void onSuccess(QiNiuYunBean qiNiuYunBean) {
                if (qiNiuYunBean != null) {
                    QiNiuYunBean.setQiNiuInfo(qiNiuYunBean);
                    qiNiuToken = qiNiuYunBean.getToken();
                    qiNiuCdnUrl = qiNiuYunBean.getAddress();
                    Log.e("xy", "qiNiuToken:" + qiNiuToken);
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {

            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rbManOfGender:
                gender = "男";
                break;
            case R.id.rbWomanOfGender:
                gender = "女";
                break;
        }
        submitUserInfo.setGender(gender);
    }

    @Override
    public void labelItemSelect(int selectSize, String itemLabelId) {
        labelSize = selectSize;
        labels = itemLabelId;
    }

    @Override
    public void onAvatarClick(boolean isAlbumAdd, boolean isSelect, String userAvatar) {
        if (!isAlbumAdd) {
            if (isSelect) {
                profilePictureKey = userAvatar;
            } else {
                profilePictureKey = "";
            }
            Log.e("xy", "profilePictureKey:" + profilePictureKey);
        } else {
            //自定义,弹框选择，请求权限
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermission(Permission.CAMERA, Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE);
            } else {
                PictureSelectorUtils.selectImageOfOne(mContext, 555, false);
            }
        }
    }


    private void requestPermission(@PermissionDef String... permissions) {
        AndPermissionUtils.requestPermission(mContext, new AndPermissionListener() {
            @Override
            public void PermissionSuccess(List<String> permissions) {
                PictureSelectorUtils.selectImageOfOne(mContext, 555, false);
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
            case 555:
                //上传七牛云
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                if (selectList.isEmpty()) return;
                String imageUrl = PictureSelectorUtils.getPhotoPath(selectList.get(0));
                qinIuUpLoad(imageUrl);
                break;
        }
    }

    /**
     * 上传的七牛云
     */
    private void qinIuUpLoad(String ImageUrl) {
        if (TextUtils.isEmpty(qiNiuToken)) {
            ToastUtils.showToast("获取token失败");
            return;
        }
        QinIuUtils.qinIuUpLoad(ImageUrl, qiNiuToken, new QinIuUpLoadListener() {
            @Override
            public void upLoadSuccess(String path) {
                profilePictureKey = path;
                String uploadUrl = qiNiuCdnUrl + path;
                if (!TextUtils.isEmpty(uploadUrl) && !defaultImageList.isEmpty()) {
                    for (DefaultImageList itemInfo : defaultImageList) {
                        if (itemInfo.isAlbumAdd()) {
                            itemInfo.setPictureKey(uploadUrl);
                            itemInfo.setSelect(true);
                        } else {
                            itemInfo.setSelect(false);
                        }
                        itemInfo.setAlbumAdd(false);
                    }
                    isUploadHeadUrl = true;
                    userAvatarAdapterAdapter.setNewData(defaultImageList);
                }
            }

            @Override
            public void upLoadFail(String errorMessage) {
                ToastUtils.showToast("图片上传失败:" + errorMessage);
            }
        });
    }
}
