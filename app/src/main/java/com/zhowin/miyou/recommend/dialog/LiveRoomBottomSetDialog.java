package com.zhowin.miyou.recommend.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.zhowin.base_library.base.BaseDialogFragment;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.recommend.callback.OnLiveRoomBottomSetListener;

/**
 * author : zho
 * date  ：2020/9/15
 * desc ：底部设置的dialog 通用样式
 */
public class LiveRoomBottomSetDialog extends BaseDialogFragment {


    private TextView tvTitleOne, tvTitleTwo;
    private View divideLine;
    private String titleOne, titleTwo;
    private OnLiveRoomBottomSetListener onLiveRoomBottomSetListener;
    private boolean isHideTitleTwo;


    public static LiveRoomBottomSetDialog newInstance(boolean isHideTitleTwo, String titleOne, String titleTwo) {
        LiveRoomBottomSetDialog roomBottomSetDialog = new LiveRoomBottomSetDialog();
        Bundle bundle = new Bundle();
        bundle.putBoolean(ConstantValue.HIDE, isHideTitleTwo);
        bundle.putString(ConstantValue.TITLE, titleOne);
        bundle.putString(ConstantValue.CONTNET, titleTwo);
        roomBottomSetDialog.setArguments(bundle);
        return roomBottomSetDialog;
    }

    public void setOnLiveRoomBottomSetListener(OnLiveRoomBottomSetListener onLiveRoomBottomSetListener) {
        this.onLiveRoomBottomSetListener = onLiveRoomBottomSetListener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_set_up_admin_dialog;
    }

    @Override
    public void initView() {
        divideLine = get(R.id.divideLine);
        tvTitleOne = get(R.id.tvTitleOne);
        tvTitleTwo = get(R.id.tvTitleTwo);
        tvTitleOne.setOnClickListener(this::onViewClick);
        tvTitleTwo.setOnClickListener(this::onViewClick);
        get(R.id.tvCancel).setOnClickListener(this::onViewClick);
    }

    @Override
    public void initData() {
        isHideTitleTwo = getArguments().getBoolean(ConstantValue.HIDE);
        titleOne = getArguments().getString(ConstantValue.TITLE);
        titleTwo = getArguments().getString(ConstantValue.CONTNET);
        if (!TextUtils.isEmpty(titleOne))
            tvTitleOne.setText(titleOne);
        divideLine.setVisibility(isHideTitleTwo ? View.GONE : View.VISIBLE);
        tvTitleTwo.setVisibility(isHideTitleTwo ? View.GONE : View.VISIBLE);
        if (!TextUtils.isEmpty(titleTwo))
            tvTitleTwo.setText(titleTwo);

    }

    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tvTitleOne:
                if (onLiveRoomBottomSetListener != null) {
                    onLiveRoomBottomSetListener.onTitleOneClick(1);
                }
                break;
            case R.id.tvTitleTwo:
                if (onLiveRoomBottomSetListener != null) {
                    onLiveRoomBottomSetListener.onTitleTwoClick(1);
                }
                break;
            case R.id.tvCancel:
                break;
        }
        dismiss();
    }
}
