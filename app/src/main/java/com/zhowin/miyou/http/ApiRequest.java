
package com.zhowin.miyou.http;

import com.zhowin.base_library.http.ApiResponse;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.qiniu.QiNiuYunBean;
import com.zhowin.miyou.login.model.DefaultImageList;
import com.zhowin.miyou.login.model.LabelList;
import com.zhowin.miyou.main.model.BannerList;
import com.zhowin.miyou.recommend.model.RecommendList;
import com.zhowin.miyou.recommend.model.RoomCategory;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;


/**
 * description: 网络请求
 */
public interface ApiRequest {
    String AUTHOR = "Authorization";

    String TOKEN_VALUE = "Bearer ";

    //注册
    String REGISTER = "api/user/register";
    //验证码登录
    String CAPATH_CODE_LOGIN = "/mobile/sms/login";

    //手机号+密码登录
    String MOBILE_AND_PASSWORD_URL = "/mobile/pwd/login";

    //获取用户信息
    String GET_USER_INFO_MESSAGE_URL = "/ant/userInfo/my";

    //获取别人的信息
    String GET_OTHER_USER_INFO_MESSAGE_URL = "/ant/userInfo/getUserInfo";

    //获取七牛云的token
    String GET_QI_NIU_TOKEN_URL = "/qn/getUploadToken";
    //发送验证码
    String SEND_EMS_CODE = "/auth/sendCode";
    //获取默认图像
    String GET_DEFAULT_AVATAR_URL = "/ant/profilePicture/getOptionalProfilePicture";
    //获取默认的标签
    String GET_DEFAULT_LABEL_LIST_URL = "/ant/user/label/getChoiceLabels";
    //提交用户认证信息
    String SUBMIT_USER_INFO_MESSAGE_URL = "/ant/userInfo/initializeUserInfo";
    //更新用户资料
    String UPDATE_USER_INFO_MESSAGE_URL = "/ant/userInfo/updateUserInfo";
    //获取首页标签
    String GET_HOME_BANNER_LIST_URL = "/ant/home/banner/getBanners";
    //获取礼物列表
    String GET_GIFT_LIST_URL = "/ant/gift/getGifts";

    //设置用户密码
    String SET_USER_PASSWORD_URL = "/user/initializePwd";

    //修改用户密码
    String CHANGE_USER_PASSWORD_URL = "/user/modifyPwd";

    //忘记秘密
    String FOR_GET_PASSWORD_URL = "/auth/findPassword";

    //创建房间
    String CREATE_CHAT_ROOM_URL = "/ant/live/create";

    //获取直播间房间类型
    String GET_ROOM_CATEGORY_URL = "/ant/live/roomTypes";

    //获取房间id
    String GET_ROOM_ID_URL = "/ant/live/getNumber";

    //我收藏的直播间
    String MY_COLLECTION_ROOM_LIST = "/ant/live/collectionLiveRooms";

    //获取我创建的直播间
    String GET_MY_CREATE_ROOM_LIST = "/ant/live/myLiveRooms";


    /**
     * 返回的是自己的用户信息
     */
    @POST(GET_USER_INFO_MESSAGE_URL)
    Observable<ApiResponse<UserInfo>> getUserInfoMessage(@Header(AUTHOR) String token);

    /**
     * 七牛云的信息
     */
    @POST(GET_QI_NIU_TOKEN_URL)
    Observable<ApiResponse<QiNiuYunBean>> getQiNiuYunBean(@Header(AUTHOR) String token);

    /**
     * 手机号码+验证码注册
     */
    @FormUrlEncoded
    @POST(REGISTER)
    Observable<ApiResponse<UserInfo>> registerFromPhoneNumber(@FieldMap HashMap<String, Object> map);

    /**
     * 手机号 + 密码 登录
     */
    @FormUrlEncoded
    @POST(MOBILE_AND_PASSWORD_URL)
    Observable<ApiResponse<UserInfo>> loginMobileAndPassword(@Header(AUTHOR) String token, @Field("phone") String phone, @Field("password") String password);

    /**
     * 手机号 + 短信验证码登录
     */
    @FormUrlEncoded
    @POST(CAPATH_CODE_LOGIN)
    Observable<ApiResponse<UserInfo>> mobileVerificationCodeLogin(@Field("phone") String mobile, @Field("smsCode") String smsCode);


    /**
     * 获取短信验证码
     */
    @FormUrlEncoded
    @POST
    Observable<ApiResponse<Object>> getVerificationCode(@Url String url, @Field("mobileNum") String mobile);

    /**
     * 获取默认图像
     */
    @POST(GET_DEFAULT_AVATAR_URL)
    Observable<ApiResponse<List<DefaultImageList>>> getDefaultAvatar(@Header(AUTHOR) String token);

    /**
     * 获取默认标签
     */
    @POST(GET_DEFAULT_LABEL_LIST_URL)
    Observable<ApiResponse<List<LabelList>>> getDefaultTagList(@Header(AUTHOR) String token);

    /**
     * 提交用户认证信息
     */
    @FormUrlEncoded
    @POST(SUBMIT_USER_INFO_MESSAGE_URL)
    Observable<ApiResponse<UserInfo>> submitUserInfoMessage(@Header(AUTHOR) String token, @FieldMap HashMap<String, Object> map);

    /**
     * 修改用户信息
     */
    @FormUrlEncoded
    @POST(UPDATE_USER_INFO_MESSAGE_URL)
    Observable<ApiResponse<Object>> changeUserInfoMessage(@Header(AUTHOR) String token, @FieldMap HashMap<String, Object> map);


    /**
     * 获取别人的信息
     */
    @FormUrlEncoded
    @POST(GET_OTHER_USER_INFO_MESSAGE_URL)
    Observable<ApiResponse<UserInfo>> getOtherInfoMessage(@Header(AUTHOR) String token, @Field("userId") int userId);


    /**
     * 获取首页banner
     */
    @POST(GET_HOME_BANNER_LIST_URL)
    Observable<ApiResponse<List<BannerList>>> getHomeBannerList(@Header(AUTHOR) String token);

    /**
     * 设置用户密码
     */
    @FormUrlEncoded
    @POST(SET_USER_PASSWORD_URL)
    Observable<ApiResponse<Boolean>> setUserPassword(@Header(AUTHOR) String token, @Field("pwd") String password);

    /**
     * 修改密码
     */
    @FormUrlEncoded
    @POST(CHANGE_USER_PASSWORD_URL)
    Observable<ApiResponse<Object>> changeUserPassword(@Header(AUTHOR) String token, @Field("oldPwd") String oldPwd, @Field("newPwd") String newPwd);

    /**
     * 忘记密码
     */
    @FormUrlEncoded
    @POST(FOR_GET_PASSWORD_URL)
    Observable<ApiResponse<Object>> forgetPassword(@Header(AUTHOR) String token, @Field("mobileNum") String mobileNum, @Field("msgCode") String msgCode, @Field("newPwd") String newPwd);


    /**
     * 创建房间
     */
    @FormUrlEncoded
    @POST(CREATE_CHAT_ROOM_URL)
    Observable<ApiResponse<Object>> createChatRoom(@Header(AUTHOR) String token, @FieldMap HashMap<String, Object> map);

    /**
     * 获取房间类型
     */
    @POST(GET_ROOM_CATEGORY_URL)
    Observable<ApiResponse<List<RoomCategory>>> getRoomCategory(@Header(AUTHOR) String token);

    /**
     * 获取房间ID
     */
    @POST(GET_ROOM_ID_URL)
    Observable<ApiResponse<Integer>> getRoomID(@Header(AUTHOR) String token);

    /**
     * 获取我创建的或者 收藏的直播间
     */
    @POST
    Observable<ApiResponse<List<RecommendList>>> getMyCreateOrCollectionRoomList(@Header(AUTHOR) String token, @Url String url);


}
