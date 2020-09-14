package com.zhowin.miyou.recommend.dialog;

import android.view.View;
import android.widget.TextView;

import com.zhowin.base_library.base.BaseDialogFragment;
import com.zhowin.miyou.R;
import com.zhowin.miyou.recommend.callback.OnReportAndAttentionListener;

/**
 * author : zho
 * date  ：2020/9/14
 * desc ： 举报或者关注的dialog
 */
public class ReportAndAttentionDialog extends BaseDialogFragment {

    private TextView tvUserNickNameAndId;
    private OnReportAndAttentionListener onReportAndAttentionListener;

    @Override
    public int getLayoutId() {
        return R.layout.include_report_and_attention_dialog;
    }

    public void setOnReportAndAttentionListener(OnReportAndAttentionListener onReportAndAttentionListener) {
        this.onReportAndAttentionListener = onReportAndAttentionListener;
    }

    @Override
    public void initView() {
        tvUserNickNameAndId = get(R.id.tvUserNickNameAndId);
        get(R.id.tvReport).setOnClickListener(this::onViewClick);
        get(R.id.tvAttention).setOnClickListener(this::onViewClick);
        get(R.id.tvPullBlack).setOnClickListener(this::onViewClick);
        get(R.id.tvShare).setOnClickListener(this::onViewClick);
        get(R.id.tvCancel).setOnClickListener(this::onViewClick);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tvReport:
                if (onReportAndAttentionListener != null) {
                    onReportAndAttentionListener.onItemClick(1);
                }
                break;
            case R.id.tvAttention:
                if (onReportAndAttentionListener != null) {
                    onReportAndAttentionListener.onItemClick(2);
                }
                break;
            case R.id.tvPullBlack:
                if (onReportAndAttentionListener != null) {
                    onReportAndAttentionListener.onItemClick(3);
                }
                break;
            case R.id.tvShare:
                if (onReportAndAttentionListener != null) {
                    onReportAndAttentionListener.onItemClick(4);
                }
                break;
            case R.id.tvCancel:
                break;
        }
        dismiss();
    }
}
