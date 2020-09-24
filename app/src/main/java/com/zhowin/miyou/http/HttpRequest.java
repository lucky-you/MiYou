package com.zhowin.miyou.http;


import androidx.lifecycle.LifecycleOwner;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.zhowin.base_library.http.ApiObserver;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.http.RetrofitFactory;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.qiniu.QiNiuYunBean;
import com.zhowin.base_library.utils.RxSchedulers;
import com.zhowin.miyou.BuildConfig;
import com.zhowin.miyou.login.model.DefaultImageList;
import com.zhowin.miyou.login.model.LabelList;
import com.zhowin.miyou.main.model.BannerList;
import com.zhowin.miyou.mine.model.MyWalletBalance;
import com.zhowin.miyou.mine.model.RoomBackgroundList;
import com.zhowin.miyou.recommend.model.GiftList;
import com.zhowin.miyou.recommend.model.RecommendList;
import com.zhowin.miyou.recommend.model.RoomCategory;

import java.util.HashMap;
import java.util.List;

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
        apiRequest.getUserInfoMessage(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken())
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

    /**
     * 获取七牛云的信息
     */
    public static void getQiNiuYunBean(LifecycleOwner activity, final HttpCallBack<QiNiuYunBean> callBack) {
        apiRequest.getQiNiuYunBean(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken())
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<QiNiuYunBean>() {

                    @Override
                    public void onSuccess(QiNiuYunBean demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 获取验证码
     */
    public static void getVerificationCode(LifecycleOwner activity, String mobile, final HttpCallBack<Object> callBack) {
        apiRequest.getVerificationCode(ApiRequest.SEND_EMS_CODE, mobile)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<Object>() {

                    @Override
                    public void onSuccess(Object demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 手机号 +验证码 登录
     */
    public static void mobileVerificationCodeLogin(LifecycleOwner activity, String mobile, String semCode, final HttpCallBack<UserInfo> callBack) {
        apiRequest.mobileVerificationCodeLogin(mobile, semCode)
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

    /**
     * 手机号 + 密码登录
     */
    public static void loginMobileAndPassword(LifecycleOwner activity, String mobile, String password, final HttpCallBack<UserInfo> callBack) {
        apiRequest.loginMobileAndPassword(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), mobile, password)
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

    /**
     * 获取默认图像
     */
    public static void getDefaultAvatar(LifecycleOwner activity, final HttpCallBack<List<DefaultImageList>> callBack) {
        apiRequest.getDefaultAvatar(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken())
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<List<DefaultImageList>>() {

                    @Override
                    public void onSuccess(List<DefaultImageList> demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 获取默认标签
     */
    public static void getDefaultTagList(LifecycleOwner activity, final HttpCallBack<List<LabelList>> callBack) {
        apiRequest.getDefaultTagList(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken())
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<List<LabelList>>() {

                    @Override
                    public void onSuccess(List<LabelList> demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 提交用户信息
     */
    public static void submitUserInfoMessage(LifecycleOwner activity, HashMap<String, Object> map, final HttpCallBack<UserInfo> callBack) {
        apiRequest.submitUserInfoMessage(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), map)
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

    /**
     * 修改用户信息
     */
    public static void changeUserInfoMessage(LifecycleOwner activity, HashMap<String, Object> map, final HttpCallBack<Object> callBack) {
        apiRequest.changeUserInfoMessage(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), map)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<Object>() {

                    @Override
                    public void onSuccess(Object demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 获取别人的主页信息,传自己id 时，就是获取自己的数据
     */
    public static void getOtherUserInfoMessage(LifecycleOwner activity, int userId, final HttpCallBack<UserInfo> callBack) {
        apiRequest.getOtherInfoMessage(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), userId)
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

    /**
     * 首页banner
     */
    public static void getHomeBannerList(LifecycleOwner activity, final HttpCallBack<List<BannerList>> callBack) {
        apiRequest.getHomeBannerList(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken())
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<List<BannerList>>() {

                    @Override
                    public void onSuccess(List<BannerList> demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 设置用户密码
     */
    public static void setUserPassword(LifecycleOwner activity, String password, final HttpCallBack<Boolean> callBack) {
        apiRequest.setUserPassword(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), password)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<Boolean>() {

                    @Override
                    public void onSuccess(Boolean demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 修改用户密码
     */
    public static void changeUserPassword(LifecycleOwner activity, String oldPwd, String newPwd, final HttpCallBack<Object> callBack) {
        apiRequest.changeUserPassword(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), oldPwd, newPwd)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<Object>() {

                    @Override
                    public void onSuccess(Object demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 忘记 密码
     */
    public static void forgetPassword(LifecycleOwner activity, String mobileNum, String msgCode, String newPwd, final HttpCallBack<Object> callBack) {
        apiRequest.forgetPassword(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), mobileNum, msgCode, newPwd)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<Object>() {

                    @Override
                    public void onSuccess(Object demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }


    /**
     * 获取直播间类型
     */
    public static void getRoomCategory(LifecycleOwner activity, final HttpCallBack<List<RoomCategory>> callBack) {
        apiRequest.getRoomCategory(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken())
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<List<RoomCategory>>() {

                    @Override
                    public void onSuccess(List<RoomCategory> demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 获取房间id
     */
    public static void getRoomID(LifecycleOwner activity, final HttpCallBack<Integer> callBack) {
        apiRequest.getRoomID(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken())
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<Integer>() {

                    @Override
                    public void onSuccess(Integer demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 创建房间
     */
    public static void createChatRoom(LifecycleOwner activity, HashMap<String, Object> map, final HttpCallBack<Object> callBack) {
        apiRequest.createChatRoom(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), map)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<Object>() {

                    @Override
                    public void onSuccess(Object demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 获取我创建或者我收藏的room
     */
    public static void getMyCreateOrCollectionRoomList(LifecycleOwner activity, boolean isMyCreate, final HttpCallBack<BaseResponse<RecommendList>> callBack) {
        String url = isMyCreate ? ApiRequest.GET_MY_CREATE_ROOM_LIST : ApiRequest.MY_COLLECTION_ROOM_LIST;
        apiRequest.getMyCreateOrCollectionRoomList(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), url)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<BaseResponse<RecommendList>>() {

                    @Override
                    public void onSuccess(BaseResponse<RecommendList> demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 获取我的钱包余额
     */
    public static void getMyWalletBalance(LifecycleOwner activity, final HttpCallBack<MyWalletBalance> callBack) {
        apiRequest.getMyWalletBalance(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken())
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<MyWalletBalance>() {

                    @Override
                    public void onSuccess(MyWalletBalance demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 获取礼物列表
     */
    public static void getGiftList(LifecycleOwner activity, final HttpCallBack<List<GiftList>> callBack) {
        apiRequest.getGiftList(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken())
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<List<GiftList>>() {

                    @Override
                    public void onSuccess(List<GiftList> demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 获取直播间背景
     */
    public static void getRoomBackgroundList(LifecycleOwner activity, final HttpCallBack<List<RoomBackgroundList>> callBack) {
        apiRequest.getRoomBackgroundList(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken())
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<List<RoomBackgroundList>>() {

                    @Override
                    public void onSuccess(List<RoomBackgroundList> demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }


}
