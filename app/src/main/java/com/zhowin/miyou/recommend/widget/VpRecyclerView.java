package com.zhowin.miyou.recommend.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

/**
 * author : zho
 * date  ：2020/9/22
 * desc ：recyclerView 实现 viewpager的滑动效果
 */
public class VpRecyclerView extends RecyclerView {

    private int position = 0;

    public VpRecyclerView(@NonNull Context context) {
        super(context);
        initView();
    }

    public VpRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    private void initView() {
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        this.setLayoutManager(llm);
        SnapHelper snapHelper = new PagerSnapHelper();
//      SnapHelper snapHelper = new LinearSnapHelper(); //一次可滑动多个
        snapHelper.attachToRecyclerView(this);//居中显示RecyclerView
        this.addItemDecoration(new DividerItemDecoration(this.getContext(), DividerItemDecoration.HORIZONTAL));
        this.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    int firs = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                    if (position != firs) {
                        position = firs;
                        if (onpagerChangeListener != null)
                            onpagerChangeListener.onPagerChange(position);
                    }
                }
            }
        });
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        this.requestDisallowInterceptTouchEvent(true); //事件不传递给父布局
        return super.dispatchTouchEvent(ev);
    }

    public void setOnPagerPosition(int position) {
//        this.position = position;
        RecyclerView.LayoutManager layoutManager = this.getLayoutManager();
        layoutManager.scrollToPosition(position);
    }

    public int getOnPagerPosition() {
        RecyclerView.LayoutManager layoutManager = this.getLayoutManager();
        return ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
    }

    interface onPagerChangeListener {
        void onPagerChange(int position);
    }

    private onPagerChangeListener onpagerChangeListener;

    public void setOnpagerChangeListener(onPagerChangeListener onpagerChangeListener) {
        this.onpagerChangeListener = onpagerChangeListener;
    }
}
