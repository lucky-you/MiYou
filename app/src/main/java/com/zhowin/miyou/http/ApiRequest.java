
package com.zhowin.miyou.http;

import com.zhowin.base_library.http.ApiResponse;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.qiniu.QiNiuYunBean;
import com.zhowin.miyou.login.model.DefaultImageList;
import com.zhowin.miyou.login.model.LabelList;

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

    //登录
    String LOGIN_URL = "api/user/login";
    //注册
    String REGISTER = "api/user/register";
    //验证码登录
    String CAPATH_CODE_LOGIN = "/mobile/sms/login";
    //忘记密码
    String RESET_PASSWORD_URL = "api/user/password";
    //获取用户信息
    String GET_USER_INFO_MESSAGE_URL = "api/ucenter/info";

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

    /**
     * 返回的是用户信息
     */
    @FormUrlEncoded
    @POST(GET_USER_INFO_MESSAGE_URL)
    Observable<ApiResponse<UserInfo>> getUserInfoMessage(@Field(AUTHOR) String token);


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
    @POST(LOGIN_URL)
    Observable<ApiResponse<UserInfo>> userLoginFromMobile(@FieldMap HashMap<String, Object> map);

    /**
     * 手机号 + 短信验证码登录
     */
    @FormUrlEncoded
    @POST(CAPATH_CODE_LOGIN)
    Observable<ApiResponse<UserInfo>> mobileVerificationCodeLogin(@Field("phone") String mobile, @Field("smsCode") String smsCode);

    /**
     * 忘记密码
     */
    @FormUrlEncoded
    @POST(RESET_PASSWORD_URL)
    Observable<ApiResponse<Object>> forgetPassword(@FieldMap HashMap<String, Object> map);


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
    @POST
    Observable<ApiResponse<UserInfo>> submitUserInfoMessage(@Header(AUTHOR) String token,@Url String url, @FieldMap HashMap<String, Object> map);

}
