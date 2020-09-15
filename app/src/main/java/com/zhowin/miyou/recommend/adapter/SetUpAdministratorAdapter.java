package com.zhowin.miyou.recommend.adapter;

import android.view.View;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.miyou.R;
import com.zhowin.miyou.recommend.callback.OnAdminItemClickListener;
import com.zhowin.miyou.recommend.model.AdminInfoList;

import java.util.List;

/**
 * author : zho
 * date  ：2020/9/15
 * desc ： 设置管理员
 */
public class SetUpAdministratorAdapter extends BaseMultiItemQuickAdapter<AdminInfoList, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public SetUpAdministratorAdapter(List<AdminInfoList> data) {
        super(data);
        addItemType(AdminInfoList.ITEM_TYPE_ONE, R.layout.include_admin_item_one_layout);
        addItemType(AdminInfoList.ITEM_TYPE_TWO, R.layout.include_admin_item_two_layout);
    }

    private OnAdminItemClickListener onAdminItemClickListener;

    public void setOnAdminItemClickListener(OnAdminItemClickListener onAdminItemClickListener) {
        this.onAdminItemClickListener = onAdminItemClickListener;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, AdminInfoList item) {

        switch (helper.getItemViewType()) {
            case AdminInfoList.ITEM_TYPE_ONE:
                helper.setText(R.id.tvItemTitle, item.getItemTitle());

                break;
            case AdminInfoList.ITEM_TYPE_TWO:

                helper.getView(R.id.clItemRootLayout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onAdminItemClickListener != null) {
                            onAdminItemClickListener.onItemSetUpAdmin();
                        }
                    }
                });
                break;

        }
    }
}
