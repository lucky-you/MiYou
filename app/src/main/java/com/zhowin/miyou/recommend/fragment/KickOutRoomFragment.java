package com.zhowin.miyou.recommend.fragment;

import android.os.Bundle;

import com.zhowin.base_library.base.BaseBindFragment;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.miyou.R;
import com.zhowin.miyou.databinding.IncludeKickOutRoomFragmentBinding;

/**
 * author : zho
 * date  ：2020/9/15
 * desc ：踢出房间
 */
public class KickOutRoomFragment extends BaseBindFragment<IncludeKickOutRoomFragmentBinding> {

    public static KickOutRoomFragment newInstance(int index) {
        KickOutRoomFragment fragment = new KickOutRoomFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_kick_out_room_fragment;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
