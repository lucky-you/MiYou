package com.zhowin.miyou.recommend.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.zhowin.base_library.base.BaseDialogView;
import com.zhowin.miyou.R;
import com.zhowin.miyou.recommend.callback.OnHitCenterClickListener;

/**
 * author : zho
 * date  ：2020/9/16
 * desc ： 居中提示的dialog
 */
public class HitCenterDialog extends BaseDialogView {

    private TextView tvDialogTitle, tvDetermine;
    private OnHitCenterClickListener onHitCenterClickListener;

    public HitCenterDialog(@NonNull Context context ) {
        super(context);
    }

    public void setOnHitCenterClickListener(OnHitCenterClickListener onHitCenterClickListener) {
        this.onHitCenterClickListener = onHitCenterClickListener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_kick_out_dialog_layout;
    }

    @Override
    public void initView() {
        tvDialogTitle = get(R.id.tvDialogTitle);
        get(R.id.tvCancel).setOnClickListener(this::onViewClick);
        tvDetermine = get(R.id.tvDetermine);
        tvDetermine.setOnClickListener(this::onViewClick);
    }

    @Override
    public void initData() {

    }


    public void setDialogTitle(String title) {
        if (!TextUtils.isEmpty(title))
            tvDialogTitle.setText(title);
    }

    public void setDetermineText(String text) {
        if (!TextUtils.isEmpty(text)) {
            tvDetermine.setText(text);
        }
    }

    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tvCancel:
                if (onHitCenterClickListener != null) {
                    onHitCenterClickListener.onCancelClick();
                }
                break;
            case R.id.tvDetermine:
                if (onHitCenterClickListener != null) {
                    onHitCenterClickListener.onDetermineClick();
                }
                break;
        }
        dismiss();
    }
}
