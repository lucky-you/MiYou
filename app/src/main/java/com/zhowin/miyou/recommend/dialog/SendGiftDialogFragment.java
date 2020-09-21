package com.zhowin.miyou.recommend.dialog;

import android.os.Bundle;
import android.view.View;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhowin.base_library.base.BaseDialogFragment;
import com.zhowin.miyou.R;
import com.zhowin.miyou.recommend.adapter.ServeWheatListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author : zho
 * date  ：2020/9/21
 * desc ： 赠送礼物的dialog
 */
public class SendGiftDialogFragment extends BaseDialogFragment {

    private ServeWheatListAdapter serveWheatListAdapter;
    private RecyclerView wheatRecyclerView;

    public static SendGiftDialogFragment newInstance() {
        SendGiftDialogFragment fragment = new SendGiftDialogFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_send_gift_dailog_fragment;
    }

    @Override
    public void initView() {
        wheatRecyclerView = get(R.id.wheatRecyclerView);
    }

    @Override
    public void initData() {
        List<String> wheatList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            wheatList.add("");
        }
        serveWheatListAdapter = new ServeWheatListAdapter(wheatList);
        wheatRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        wheatRecyclerView.setAdapter(serveWheatListAdapter);
    }

    @Override
    public void onViewClick(View view) {

    }
}
