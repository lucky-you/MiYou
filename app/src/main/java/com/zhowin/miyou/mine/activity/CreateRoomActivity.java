package com.zhowin.miyou.mine.activity;


import android.content.Intent;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.yanzhenjie.permission.runtime.Permission;
import com.yanzhenjie.permission.runtime.PermissionDef;
import com.zhowin.base_library.base.BaseBindActivity;
import com.zhowin.base_library.callback.OnTextChangedListener;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.permission.AndPermissionListener;
import com.zhowin.base_library.permission.AndPermissionUtils;
import com.zhowin.base_library.pickerview.OnSelectConditionsClickListener;
import com.zhowin.base_library.pickerview.PickerViewConditionsUtils;
import com.zhowin.base_library.pickerview.SelectPickerList;
import com.zhowin.base_library.pictureSelect.PictureSelectorUtils;
import com.zhowin.base_library.qiniu.QiNiuYunBean;
import com.zhowin.base_library.qiniu.QinIuUpLoadListener;
import com.zhowin.base_library.qiniu.QinIuUtils;
import com.zhowin.base_library.utils.GlideUtils;
import com.zhowin.base_library.utils.ToastUtils;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.ActivityCreateRoomBinding;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.mine.adapter.RoomBackgroundAdapter;
import com.zhowin.miyou.mine.model.RoomBackgroundList;
import com.zhowin.miyou.recommend.model.RoomCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 创建房间
 */
public class CreateRoomActivity extends BaseBindActivity<ActivityCreateRoomBinding> {


    private RoomBackgroundAdapter roomBackgroundAdapter;
    private List<SelectPickerList> selectPickerLists = new ArrayList<>();
    private int roomCategoryId, backgroundPictureId, roomCategoryPosition = 0;
    private String qiNiuToken, qiNiuCdnUrl, roomHeadImageUrl, roomName, roomDesc;
    private int MAX_NUMBER = 200;

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_room;
    }

    @Override
    public void initView() {
        setOnClick(R.id.tvRoomCategory, R.id.rvRoomBackground, R.id.ivRefreshRoomID, R.id.tvCreateRoom);
        mBinding.tvTextDescNumber.setText("0/" + MAX_NUMBER);
        getRoomBackgroundList();
        getRoomCategory();
        getRoomID();
        getQiNiuToken();
    }

    @Override
    public void initData() {
        roomBackgroundAdapter = new RoomBackgroundAdapter();
        mBinding.roomBgRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mBinding.roomBgRecyclerView.setAdapter(roomBackgroundAdapter);
        roomBackgroundAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                roomBackgroundAdapter.setCurrentPosition(position);
                backgroundPictureId = roomBackgroundAdapter.getItem(position).getId();
            }
        });
    }

    @Override
    public void initListener() {
        mBinding.editRoomDesc.addTextChangedListener(new OnTextChangedListener() {
            @Override
            public void afterTextChanged(Editable editable) {
                roomDesc = editable.toString();
                if (!TextUtils.isEmpty(roomDesc))
                    if (roomDesc.length() <= MAX_NUMBER) {
                        mBinding.tvTextDescNumber.setText(roomDesc.length() + "/" + MAX_NUMBER);
                    }
            }
        });
    }

    /**
     * 获取直播间背景
     */

    private void getRoomBackgroundList() {
        HttpRequest.getRoomBackgroundList(this, new HttpCallBack<List<RoomBackgroundList>>() {
            @Override
            public void onSuccess(List<RoomBackgroundList> roomBackgroundLists) {
                if (roomBackgroundLists != null && !roomBackgroundLists.isEmpty()) {
                    backgroundPictureId = roomBackgroundLists.get(0).getId();
                    roomBackgroundAdapter.setNewData(roomBackgroundLists);
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {

            }
        });
    }

    /**
     * 获取房间类型
     */
    private void getRoomCategory() {
        HttpRequest.getRoomCategory(this, new HttpCallBack<List<RoomCategory>>() {
            @Override
            public void onSuccess(List<RoomCategory> roomCategories) {
                if (roomCategories != null && !roomCategories.isEmpty()) {
                    roomCategoryId = roomCategories.get(0).getTypeId();
                    mBinding.tvRoomCategory.setText(roomCategories.get(0).getTypeName());
                    if (!selectPickerLists.isEmpty()) selectPickerLists.clear();
                    for (RoomCategory itemCategory : roomCategories) {
                        selectPickerLists.add(new SelectPickerList(itemCategory.getTypeId(), itemCategory.getTypeName()));
                    }
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                ToastUtils.showToast(errorMsg);
            }
        });
    }

    /**
     * 获取房间ID
     */
    private void getRoomID() {
        showLoadDialog();
        HttpRequest.getRoomID(this, new HttpCallBack<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                dismissLoadDialog();
                if (integer != 0) {
                    mBinding.tvRoomIdCode.setText(String.valueOf(integer));
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);
            }
        });
    }

    /**
     * 获取七牛云
     */
    private void getQiNiuToken() {
        HttpRequest.getQiNiuYunBean(this, new HttpCallBack<QiNiuYunBean>() {
            @Override
            public void onSuccess(QiNiuYunBean qiNiuYunBean) {
                if (qiNiuYunBean != null) {
                    qiNiuToken = qiNiuYunBean.getToken();
                    qiNiuCdnUrl = qiNiuYunBean.getAddress();
                    QiNiuYunBean.setQiNiuInfo(qiNiuYunBean);
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvRoomCategory:
                showSelectCategoryDialog();
                break;
            case R.id.rvRoomBackground:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermission(Permission.CAMERA, Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE);
                } else {
                    PictureSelectorUtils.selectImageOfOne(mContext, 888, false);
                }
                break;
            case R.id.ivRefreshRoomID:
                getRoomID();
                break;
            case R.id.tvCreateRoom:
                submitCreateRoom();
                break;
        }
    }

    private void submitCreateRoom() {
        roomName = mBinding.editRoomName.getText().toString().trim();
        roomDesc = mBinding.editRoomDesc.getText().toString().trim();
        if (TextUtils.isEmpty(roomName)) {
            ToastUtils.showToast("请输入房间名称");
            return;
        }
        if (TextUtils.isEmpty(roomDesc)) {
            ToastUtils.showToast("请介绍下您的房间");
            return;
        }
        showLoadDialog();
        HashMap<String, Object> map = new HashMap<>();
        map.put("title", roomName);
        map.put("description", roomDesc);
        map.put("coverPictureKey", roomHeadImageUrl);
        map.put("backgroundPictureId", backgroundPictureId);
        map.put("typeId", roomCategoryId);
        map.put("allowMicFree", 1);

        HttpRequest.createChatRoom(this, map, new HttpCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                dismissLoadDialog();
                ToastUtils.showCustomToast(mContext, "创建成功");
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                dismissLoadDialog();
                ToastUtils.showToast(errorMsg);
            }
        });
    }

    /**
     * 显示选择类型的dialog
     */
    private void showSelectCategoryDialog() {
        PickerViewConditionsUtils.selectConditionsView(mContext, selectPickerLists, roomCategoryPosition, new OnSelectConditionsClickListener() {
            @Override
            public void onConditionsSelect(int position, String selectName, int selectId) {
                roomCategoryPosition = position;
                roomCategoryId = selectId;
                mBinding.tvRoomCategory.setText(selectName);
            }
        });
    }

    /**
     * 请求权限
     */
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
                //房间头像
                List<LocalMedia> selectHeadImage = PictureSelector.obtainMultipleResult(data);
                if (selectHeadImage.isEmpty()) return;
                String imageUrl = PictureSelectorUtils.getPhotoPath(selectHeadImage.get(0));
                qinIuUpLoad(imageUrl);
                break;
        }
    }

    /**
     * 上传到七牛云
     */
    private void qinIuUpLoad(String ImageUrl) {
        if (TextUtils.isEmpty(qiNiuToken)) {
            ToastUtils.showToast("上传获取token失败");
            return;
        }
        QinIuUtils.qinIuUpLoad(ImageUrl, qiNiuToken, new QinIuUpLoadListener() {
            @Override
            public void upLoadSuccess(String path) {
                roomHeadImageUrl = path;
                String headUrl = qiNiuCdnUrl + path;
                GlideUtils.loadObjectImage(mContext, headUrl, mBinding.rvRoomBackground);
                mBinding.tvRoomHeadHit.setVisibility(View.GONE);
            }

            @Override
            public void upLoadFail(String errorMessage) {
                ToastUtils.showToast("图片上传失败:" + errorMessage);
            }
        });
    }

}
