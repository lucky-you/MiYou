package com.zhowin.miyou.recommend.dialog;

import android.view.View;

import com.zhowin.base_library.base.BaseDialogFragment;
import com.zhowin.miyou.R;
import com.zhowin.miyou.recommend.callback.OnReportAndAttentionListener;

/**
 * author : zho
 * date  ：2020/9/14
 * desc ：
 */
public class ShareItemDialog extends BaseDialogFragment {


    @Override
    public int getLayoutId() {

        return R.layout.include_share_item_dialog;
    }

    private OnReportAndAttentionListener onReportAndAttentionListener;

    public void setOnReportAndAttentionListener(OnReportAndAttentionListener onReportAndAttentionListener) {
        this.onReportAndAttentionListener = onReportAndAttentionListener;
    }

    @Override
    public void initView() {
        get(R.id.llShareWeChatFriend).setOnClickListener(this::onViewClick);
        get(R.id.llShareWeChatCircle).setOnClickListener(this::onViewClick);
        get(R.id.llShareQQZeon).setOnClickListener(this::onViewClick);
        get(R.id.llShareQQ).setOnClickListener(this::onViewClick);
        get(R.id.llShareWeiBo).setOnClickListener(this::onViewClick);
        get(R.id.tvCancel).setOnClickListener(this::onViewClick);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.llShareWeChatFriend:
                if (onReportAndAttentionListener != null) {
                    onReportAndAttentionListener.onItemClick(1);
                }
                break;
            case R.id.llShareWeChatCircle:
                if (onReportAndAttentionListener != null) {
                    onReportAndAttentionListener.onItemClick(2);
                }
                break;
            case R.id.llShareQQZeon:
                if (onReportAndAttentionListener != null) {
                    onReportAndAttentionListener.onItemClick(3);
                }
                break;
            case R.id.llShareQQ:
                if (onReportAndAttentionListener != null) {
                    onReportAndAttentionListener.onItemClick(4);
                }
                break;
            case R.id.llShareWeiBo:
                if (onReportAndAttentionListener != null) {
                    onReportAndAttentionListener.onItemClick(5);
                }
                break;
            case R.id.tvCancel:
                break;
        }
        dismiss();
    }
}
