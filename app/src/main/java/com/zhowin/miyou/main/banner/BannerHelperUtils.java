package com.zhowin.miyou.main.banner;

import android.content.Context;

import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.zhowin.miyou.main.model.BannerList;

import java.util.List;

/**
 * author : zho
 * date  ：2020/8/24
 * desc ：
 */
public class BannerHelperUtils {


    public static void showHomeFragmentBanner(Context mContext, Banner banner, List<BannerList> bannerLists) {
        if (banner == null) return;
        banner.setAdapter(new BannerListAdapter(bannerLists, 2))
                .start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object data, int position) {
//                BannerJumpUtils.jump(mContext, bannerLists.get(position));
            }
        });
    }


}
