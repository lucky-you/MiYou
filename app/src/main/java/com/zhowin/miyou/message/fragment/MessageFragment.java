package com.zhowin.miyou.message.fragment;

import android.view.View;

import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.MessageFragmentLayoutBinding;
import com.zhowin.miyou.recommend.activity.RoomSearchActivity;

/**
 * 消息
 */
public class MessageFragment extends BaseBindFragment<MessageFragmentLayoutBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.message_fragment_layout;
    }

    @Override
    public void initView() {
        setOnClick(R.id.tvSearchFriend);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSearchFriend:
                RoomSearchActivity.start(mContext, 2);
                break;
        }
    }
}
