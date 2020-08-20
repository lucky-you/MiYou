package com.zhowin.miyou.http;


import androidx.lifecycle.LifecycleOwner;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.zhowin.base_library.http.ApiObserver;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.http.RetrofitFactory;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.utils.RxSchedulers;
import com.zhowin.miyou.BuildConfig;

/**
 * description:
 */
public class HttpRequest {
    private static ApiRequest apiRequest;

    static {
        apiRequest = RetrofitFactory.getInstance().initRetrofit(BuildConfig.API_HOST).create(ApiRequest.class);
    }

    /**
     * 获取用户信息
     */
    public static void getUserInfoMessage(LifecycleOwner activity, final HttpCallBack<UserInfo> callBack) {
        apiRequest.getUserInfoMessage(UserInfo.getUserToken())
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<UserInfo>() {

                    @Override
                    public void onSuccess(UserInfo demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

}
