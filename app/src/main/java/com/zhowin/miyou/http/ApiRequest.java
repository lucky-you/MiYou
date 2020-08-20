
package com.zhowin.miyou.http;

import com.zhowin.base_library.http.ApiResponse;
import com.zhowin.base_library.model.UserInfo;

import java.util.HashMap;

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
    String HEADER_URL = "dev-api/";
    String TOKEN = "token";


    //登录
    String LOGIN_URL = HEADER_URL + "api/user/login";
    //注册
    String REGISTER = HEADER_URL + "api/user/register";
    //验证码登录
    String CAPATH_CODE_LOGIN = HEADER_URL + "api/user/mobileLogin";
    //忘记密码
    String RESET_PASSWORD_URL =HEADER_URL + "api/user/password";
    //获取用户信息
    String GET_USER_INFO_MESSAGE_URL = HEADER_URL + "api/ucenter/info";

    /**
     * 返回的是用户信息
     */
    @FormUrlEncoded
    @POST(GET_USER_INFO_MESSAGE_URL)
    Observable<ApiResponse<UserInfo>> getUserInfoMessage(@Field(TOKEN) String token);

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
    Observable<ApiResponse<UserInfo>> mobileVerificationCodeLogin(@FieldMap HashMap<String, Object> map);

    /**
     * 忘记密码
     */
    @FormUrlEncoded
    @POST(RESET_PASSWORD_URL)
    Observable<ApiResponse<Object>> forgetPassword(@FieldMap HashMap<String, Object> map);

}
