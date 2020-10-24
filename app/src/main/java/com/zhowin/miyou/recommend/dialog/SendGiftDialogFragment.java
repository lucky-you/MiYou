package com.zhowin.miyou.recommend.dialog;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhowin.base_library.base.BaseDialogFragment;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.miyou.R;
import com.zhowin.miyou.http.HttpRequest;
import com.zhowin.miyou.recommend.adapter.GiftListAdapter;
import com.zhowin.miyou.recommend.adapter.ServeWheatListAdapter;
import com.zhowin.miyou.recommend.model.GiftList;
import com.zhowin.miyou.recommend.widget.VpRecyclerView;

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
    private VpRecyclerView vpRecyclerView;
    private GiftListAdapter giftListAdapter;

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
        vpRecyclerView = get(R.id.giftRecyclerView);
        getGiftList();
    }

    @Override
    public void initData() {
        List<String> wheatList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            wheatList.add("");
        }
        serveWheatListAdapter = new ServeWheatListAdapter(wheatList);
        wheatRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        wheatRecyclerView.setAdapter(serveWheatListAdapter);
        giftListAdapter = new GiftListAdapter();
        vpRecyclerView.setAdapter(giftListAdapter);
        vpRecyclerView.setOnpagerChangeListener(new VpRecyclerView.onPagerChangeListener() {
            @Override
            public void onPagerChange(int position) {

            }
        });
        vpRecyclerView.setOnPagerPosition(wheatList.size() - 1);
    }


    private void getGiftList() {
        HttpRequest.getGiftList(this, new HttpCallBack<List<GiftList>>() {
            @Override
            public void onSuccess(List<GiftList> giftLists) {
                if (giftLists != null && !giftLists.isEmpty()) {
                    giftListAdapter.setNewData(giftLists);
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {

            }
        });
    }

    @Override
    public void onViewClick(View view) {

    }
}
