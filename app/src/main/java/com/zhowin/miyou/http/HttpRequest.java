package com.zhowin.miyou.http;


import androidx.lifecycle.LifecycleOwner;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.zhowin.base_library.http.HttpCallBack;
import com.zhowin.base_library.http.RetrofitFactory;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.qiniu.QiNiuYunBean;
import com.zhowin.base_library.utils.RxSchedulers;
import com.zhowin.miyou.BuildConfig;
import com.zhowin.miyou.login.model.DefaultImageList;
import com.zhowin.miyou.login.model.LabelList;
import com.zhowin.miyou.main.model.BannerList;
import com.zhowin.miyou.message.model.SystemMessage;
import com.zhowin.miyou.mine.model.AttentionUserList;
import com.zhowin.miyou.mine.model.KnighthoodMessageInfo;
import com.zhowin.miyou.mine.model.MyWalletBalance;
import com.zhowin.miyou.mine.model.RoomBackgroundList;
import com.zhowin.miyou.mine.model.ShopMallPropsList;
import com.zhowin.miyou.mine.model.VerifiedStatus;
import com.zhowin.miyou.mine.model.VipMessageInfo;
import com.zhowin.miyou.recommend.model.GZBUserList;
import com.zhowin.miyou.recommend.model.GiftList;
import com.zhowin.miyou.recommend.model.GuardUserList;
import com.zhowin.miyou.recommend.model.HomeCategory;
import com.zhowin.miyou.recommend.model.RecommendList;
import com.zhowin.miyou.recommend.model.RoomCategory;
import com.zhowin.miyou.recommend.model.RoomDataInfo;
import com.zhowin.miyou.recommend.model.RoomWaterBean;
import com.zhowin.miyou.recommend.model.ToadyUserList;
import com.zhowin.miyou.recommend.model.WeekStarUserList;
import com.zhowin.miyou.recommend.model.ZABUserList;

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
    public static void getVerificationCode(LifecycleOwner activity, int event, String mobile, final HttpCallBack<Object> callBack) {
        apiRequest.getVerificationCode(ApiRequest.SEND_EMS_CODE, event, mobile)
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
        apiRequest.loginMobileAndPassword(mobile, password)
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
     * 账号 + 密码 登录
     */
    public static void nameAndPasswordLogin(LifecycleOwner activity, String username, String password, final HttpCallBack<UserInfo> callBack) {
        apiRequest.nameAndPasswordLogin(username, password)
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
     * 首页滚动信息
     */
    public static void getHomeNoticeMessageList(LifecycleOwner activity, final HttpCallBack<List<String>> callBack) {
        apiRequest.getHomeNoticeMessageList(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken())
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<List<String>>() {

                    @Override
                    public void onSuccess(List<String> demo) {
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
        apiRequest.forgetPassword(mobileNum, msgCode, newPwd)
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
    public static void createChatRoom(LifecycleOwner activity, HashMap<String, Object> map, final HttpCallBack<RecommendList> callBack) {
        apiRequest.createChatRoom(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), map)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<RecommendList>() {

                    @Override
                    public void onSuccess(RecommendList demo) {
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
        String url = isMyCreate ? ApiRequest.MY_COLLECTION_ROOM_LIST : ApiRequest.GET_MY_CREATE_ROOM_LIST;
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

    /**
     * 市民认证的状态
     */
    public static void getVerifiedStatus(LifecycleOwner activity, final HttpCallBack<VerifiedStatus> callBack) {
        apiRequest.getVerifiedStatus(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken())
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<VerifiedStatus>() {

                    @Override
                    public void onSuccess(VerifiedStatus demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 获取每日魅力和贡献榜单
     */
    public static void getTodayUserList(LifecycleOwner activity, boolean is_MLB, final HttpCallBack<List<ToadyUserList>> callBack) {
        String url = is_MLB ? ApiRequest.GET_MRML_LIST_URL : ApiRequest.GET_MRGX_LIST_URL;
        apiRequest.getTodayUserList(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), url)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<List<ToadyUserList>>() {

                    @Override
                    public void onSuccess(List<ToadyUserList> demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 获取贵族榜单
     */
    public static void getGZBUserList(LifecycleOwner activity, final HttpCallBack<List<GZBUserList>> callBack) {
        apiRequest.getGZBUserList(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken())
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<List<GZBUserList>>() {

                    @Override
                    public void onSuccess(List<GZBUserList> demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });

    }

    /**
     * 守护榜单
     */
    public static void getGuardUserList(LifecycleOwner activity, final HttpCallBack<List<GuardUserList>> callBack) {
        apiRequest.getGuardUserList(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken())
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<List<GuardUserList>>() {

                    @Override
                    public void onSuccess(List<GuardUserList> demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 获取每日魅力和贡献榜单
     */
    public static void getWeekStarUserList(LifecycleOwner activity, boolean is_MLB, final HttpCallBack<WeekStarUserList> callBack) {
        String url = is_MLB ? ApiRequest.GET_MZML_LIST_URL : ApiRequest.GET_MZGX_LIST_URL;
        apiRequest.getWeekStarUserList(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), url)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<WeekStarUserList>() {

                    @Override
                    public void onSuccess(WeekStarUserList demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 真爱榜单
     */
    public static void getZABUserList(LifecycleOwner activity, final HttpCallBack<List<ZABUserList>> callBack) {
        apiRequest.getZABUserList(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken())
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<List<ZABUserList>>() {

                    @Override
                    public void onSuccess(List<ZABUserList> demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 提交认证信息
     */
    public static void submitUserVerifiedInfo(LifecycleOwner activity, HashMap<String, Object> map, final HttpCallBack<Object> callBack) {
        apiRequest.submitUserVerifiedInfo(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), map)
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
     * 添加/移除关注  添加/移除黑名单
     */
    public static void addAttentionOrBlackList(LifecycleOwner activity, int requestType, int target, final HttpCallBack<Object> callBack) {
        String url;
        switch (requestType) {
            case 1://添加关注
                url = ApiRequest.ADD_ATTENTION_LIST_URL;
                break;
            case 2: //移除关注
                url = ApiRequest.REMOVE_ATTENTION_LIST_URL;
                break;
            case 3: //添加黑名单
                url = ApiRequest.ADD_BLACK_LIST_URL;
                break;
            case 4: //移除黑名单
                url = ApiRequest.REMOVE_BLACK_LIST_URL;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + requestType);
        }
        apiRequest.addAttentionOrBlackList(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), url, target)
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
     * 获取关注/粉丝/访客列表
     */
    public static void getAttentionOrFansUserList(LifecycleOwner activity, int requestType, int target, final HttpCallBack<BaseResponse<AttentionUserList>> callBack) {
        String url;
        switch (requestType) {
            case 1: //关注
                url = ApiRequest.GET_ATTENTION_LIST_URL;
                break;
            case 2: //粉丝
                url = ApiRequest.GET_FANS_LIST_URL;
                break;

            case 3: //访客
                url = ApiRequest.GET_VISITOR_LIST_URL;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + requestType);
        }
        apiRequest.getAttentionOrFansUserList(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), url, target)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<BaseResponse<AttentionUserList>>() {

                    @Override
                    public void onSuccess(BaseResponse<AttentionUserList> demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 获取黑名单列表
     */
    public static void getBlackListUserList(LifecycleOwner activity, int target, final HttpCallBack<BaseResponse<AttentionUserList>> callBack) {
        apiRequest.getBlackListUserList(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), target)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<BaseResponse<AttentionUserList>>() {

                    @Override
                    public void onSuccess(BaseResponse<AttentionUserList> demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 商城列表
     */
    public static void getShopMallPropsList(LifecycleOwner activity, int type, final HttpCallBack<List<ShopMallPropsList>> callBack) {
        String url = null;
        switch (type) {
            case 0:
                url = ApiRequest.SHOP_MALL_HEADER_PHOTO_URL;
                break;
            case 1:
                url = ApiRequest.SHOP_MALL_ZJ_PHOTO_URL;
                break;
            case 2:
                url = ApiRequest.SHOP_MALL_DJ_PHOTO_URL;
                break;
        }
        apiRequest.getShopMallPropsList(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), url)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<List<ShopMallPropsList>>() {

                    @Override
                    public void onSuccess(List<ShopMallPropsList> demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 购买道具/座驾
     */
    public static void userBuyProps(LifecycleOwner activity, String goodId, int number, final HttpCallBack<Object> callBack) {
        apiRequest.userBuyProps(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), goodId, number)
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
     * 搜索房间
     */
    public static void searchRoomResultList(LifecycleOwner activity, String keyWord, int currentPage, int pageNumber, final HttpCallBack<BaseResponse<RecommendList>> callBack) {
        apiRequest.searchRoomResultList(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), keyWord, currentPage, pageNumber)
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
     * 搜索用户
     */
    public static void searchUserResultList(LifecycleOwner activity, String keyWord, int currentPage, int pageNumber, final HttpCallBack<BaseResponse<UserInfo>> callBack) {
        apiRequest.searchUserResultList(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), keyWord, currentPage, pageNumber)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<BaseResponse<UserInfo>>() {

                    @Override
                    public void onSuccess(BaseResponse<UserInfo> demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 获取首页分类
     */
    public static void getHomeCategoryList(LifecycleOwner activity, final HttpCallBack<List<HomeCategory>> callBack) {
        apiRequest.getHomeCategoryList(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken())
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<List<HomeCategory>>() {

                    @Override
                    public void onSuccess(List<HomeCategory> demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 获取首页的直播房间
     */
    public static void getHomeRoomList(LifecycleOwner activity, int type, final HttpCallBack<List<RecommendList>> callBack) {
        apiRequest.getHomeRoomList(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), type)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<List<RecommendList>>() {

                    @Override
                    public void onSuccess(List<RecommendList> demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 提交举报房间/用户原因
     */
    public static void submitReportMessage(LifecycleOwner activity, boolean isReportRoom, int target, String type, String content, String pictures, final HttpCallBack<Object> callBack) {
        String url = isReportRoom ? ApiRequest.SUBMIT_REPORT_ROOM_MESSAGE_URL : ApiRequest.SUBMIT_REPORT_USER_MESSAGE_URL;
        apiRequest.submitReportMessage(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), url, target, type, content, pictures)
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
     * 加入直播间
     */
    public static void joinLiveRoom(LifecycleOwner activity, int roomId, String roomPassword, final HttpCallBack<RecommendList> callBack) {
        apiRequest.joinLiveRoom(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), roomId, roomPassword)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<RecommendList>() {

                    @Override
                    public void onSuccess(RecommendList demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 获取vip 信息
     */
    public static void getVipMessageInfo(LifecycleOwner activity, final HttpCallBack<VipMessageInfo> callBack) {
        apiRequest.getVipMessageInfo(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken())
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<VipMessageInfo>() {

                    @Override
                    public void onSuccess(VipMessageInfo demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 获取爵位得信息
     */
    public static void getKnighthoodMessageInfo(LifecycleOwner activity, final HttpCallBack<KnighthoodMessageInfo> callBack) {
        apiRequest.getKnighthoodMessageInfo(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken())
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<KnighthoodMessageInfo>() {

                    @Override
                    public void onSuccess(KnighthoodMessageInfo demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 开通或者续费爵位
     */
    public static void openKnighthoodLevel(LifecycleOwner activity, int rankId, final HttpCallBack<Object> callBack) {
        apiRequest.openKnighthoodLevel(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), rankId)
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
     * 获取系统消息
     */
    public static void getSystemMessageList(LifecycleOwner activity, final HttpCallBack<BaseResponse<SystemMessage>> callBack) {
        apiRequest.getSystemMessageList(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken())
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<BaseResponse<SystemMessage>>() {

                    @Override
                    public void onSuccess(BaseResponse<SystemMessage> demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 查询青少年模式状态
     */
    public static void checkYouthMode(LifecycleOwner activity, final HttpCallBack<Boolean> callBack) {
        apiRequest.checkYouthMode(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken())
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
     * 开启或者关闭青少年模式
     */
    public static void openOrCloseYouthMode(LifecycleOwner activity, boolean isOpenMode, String password, final HttpCallBack<Object> callBack) {
        String url = isOpenMode ? ApiRequest.OPEN_YOUTH_MODE_URL : ApiRequest.CLOSE_YOUTH_MODE_URL;
        apiRequest.openOrCloseYouthMode(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), url, password)
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
     * 找回青少年模式密码
     */
    public static void findYouthModePassword(LifecycleOwner activity, String msgCode, String newPwd, final HttpCallBack<Object> callBack) {
        apiRequest.findYouthModePassword(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), msgCode, newPwd)
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
     * 验证手机号码
     */
    public static void verifyMobileNumber(LifecycleOwner activity, String mobile, String msgCode, final HttpCallBack<String> callBack) {
        apiRequest.verifyMobileNumber(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), mobile, msgCode)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<String>() {

                    @Override
                    public void onSuccess(String demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 换绑手机
     */
    public static void verifyChangeMobileNumber(LifecycleOwner activity, String msgCode, String mobile, String validCode, final HttpCallBack<Object> callBack) {
        apiRequest.verifyChangeMobileNumber(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), msgCode, mobile, validCode)
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
     * 永久注销
     */
    public static void longTimeOutLogin(LifecycleOwner activity, String mobile, String msgCode, final HttpCallBack<Object> callBack) {
        apiRequest.longTimeOutLogin(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken())
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
     * 获取房间信息
     */
    public static void getRoomDataInfo(LifecycleOwner activity, int roomId, final HttpCallBack<RoomDataInfo> callBack) {
        apiRequest.getRoomDataInfo(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), roomId)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<RoomDataInfo>() {

                    @Override
                    public void onSuccess(RoomDataInfo demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }


    /**
     * 获取房间每日魅力和贡献榜单
     */
    public static void getRoomTodayUserList(LifecycleOwner activity, boolean is_MLB, int roomId, final HttpCallBack<List<ToadyUserList>> callBack) {
        String url = is_MLB ? ApiRequest.GET_LIVE_ROOM_MLB_LIST_URL : ApiRequest.GET_LIVE_ROOM_DAY_GXB_LIST_URL;
        apiRequest.getRoomTodayUserList(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), url, roomId)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<List<ToadyUserList>>() {

                    @Override
                    public void onSuccess(List<ToadyUserList> demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }


    /**
     * 获取房间魅力和贡献榜单
     */
    public static void getRoomWeekStarUserList(LifecycleOwner activity, boolean is_MLB, int roomId, final HttpCallBack<WeekStarUserList> callBack) {
        String url = is_MLB ? ApiRequest.GET_LIVE_ROOM_WEEK_MLB_LIST_URL : ApiRequest.GET_LIVE_ROOM_WEEK_GXB_LIST_URL;
        apiRequest.getRoomWeekStarUserList(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), url, roomId)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<WeekStarUserList>() {

                    @Override
                    public void onSuccess(WeekStarUserList demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 根据id 批量查询用户信息
     */
    public static void queryUserMessageList(LifecycleOwner activity, String userIds, final HttpCallBack<BaseResponse<UserInfo>> callBack) {
        apiRequest.queryUserMessageList(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), userIds)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<BaseResponse<UserInfo>>() {

                    @Override
                    public void onSuccess(BaseResponse<UserInfo> demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 房间操作返回object的通用处理
     *
     * @param type
     * @param roomId
     * @param callBack
     */
    public static void roomItemOperating(LifecycleOwner activity, int type, int roomId, final HttpCallBack<Object> callBack) {
        String url = "";
        switch (type) {
            case 1: //清空麦序
                url = ApiRequest.CLEAR_ROW_LIST_URL;
                break;
        }
        apiRequest.roomItemOperating(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), url, roomId)
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
     * 获取房间流水
     */
    public static void getRoomWaterList(LifecycleOwner activity, boolean isTodayWater, int roomId, final HttpCallBack<RoomWaterBean> callBack) {
        String url = isTodayWater ? ApiRequest.GET_LIVE_ROOM_WATER_URL : ApiRequest.GET_LIVE_ROOM_WEEK_WATER_URL;
        apiRequest.getRoomWaterList(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), url, roomId)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe(new ApiObserver<RoomWaterBean>() {

                    @Override
                    public void onSuccess(RoomWaterBean demo) {
                        callBack.onSuccess(demo);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMsg) {
                        callBack.onFail(errorCode, errorMsg);
                    }
                });
    }

    /**
     * 设置房间密码
     */
    public static void setRoomPassword(LifecycleOwner activity, String password, int roomId, final HttpCallBack<Object> callBack) {
        apiRequest.setRoomPassword(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), password, roomId)
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
     * 禁麦 解麦
     */
    public static void lockOrUnLockMicro(LifecycleOwner activity, boolean isLockMicro, int roomId, int target, final HttpCallBack<Object> callBack) {
        String url = isLockMicro ? ApiRequest.PROHIBIT_MICROPHONE_URL : ApiRequest.PROHIBIT_UNLOCK_MICROPHONE_URL;
        apiRequest.lockOrUnLockMicro(ApiRequest.TOKEN_VALUE + UserInfo.getUserToken(), url, roomId, target)
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
}
