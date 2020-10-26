package com.zhowin.miyou.recommend.dialog;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhowin.base_library.base.BaseDialogFragment;
import com.zhowin.miyou.R;
import com.zhowin.miyou.recommend.adapter.RowWheatListAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * author : zho
 * date  ：2020/10/26
 * desc ：排麦列表
 */
public class RowWheatListDialog extends BaseDialogFragment {

    private RowWheatListAdapter rowWheatListAdapter;
    private TextView tvMicNumber;
    private RecyclerView micSortRecyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.include_row_wheat_list_layout;
    }

    @Override
    public void initView() {
        tvMicNumber = get(R.id.tvMicNumber);
        micSortRecyclerView = get(R.id.micSortRecyclerView);
        get(R.id.tvCancel).setOnClickListener(this::onViewClick);
        get(R.id.tvClearMicSort).setOnClickListener(this::onViewClick);
    }

    @Override
    public void initData() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            stringList.add("");
        }
        rowWheatListAdapter = new RowWheatListAdapter(stringList);
        micSortRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        micSortRecyclerView.setAdapter(rowWheatListAdapter);
    }

    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tvCancel:
                dismiss();
                break;
            case R.id.tvClearMicSort:
                break;
        }
    }
}
