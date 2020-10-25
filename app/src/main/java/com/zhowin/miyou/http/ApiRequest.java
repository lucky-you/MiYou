
package com.zhowin.miyou.http;

import com.zhowin.base_library.http.ApiResponse;
import com.zhowin.base_library.model.UserInfo;
import com.zhowin.base_library.qiniu.QiNiuYunBean;
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
import com.zhowin.miyou.recommend.model.ToadyUserList;
import com.zhowin.miyou.recommend.model.WeekStarUserList;
import com.zhowin.miyou.recommend.model.ZABUserList;

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

    //账号 + 密码登录
    String NAME_AND_PASSWORD_LOGIN_URL = "/login";

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

    //获取首页公告
    String GET_HOME_NOTICE_MESSAGE_URL = "/ant/message/scroll/get";

    //获取礼物列表
    String GET_GIFT_LIST_URL = "/ant/live/getGifts";

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

    //获取我的钱包余额信息
    String GET_MY_WALLET_URL = "/ant/user/wallet";

    //获取房间背景
    String GET_ROOM_BACKGROUND_LIST_URL = "/ant/live/getBackgroundPictures";

    //获取实名认证的状态
    String VERIFIED_STATUS_URL = "/ant/realValid/getRealValid";

    //获取每日贡献榜
    String GET_MRGX_LIST_URL = "/ant/rl/dailyContribute";

    //获取每日魅力榜
    String GET_MRML_LIST_URL = "/ant/rl/dailyGlamour";

    //获取贵族榜
    String GET_GZB_LIST_URL = "/ant/rl/gzb";

    //获取守护榜
    String GET_SHB_LIST_URL = "/ant/rl/shb";

    //获取每周贡献榜
    String GET_MZGX_LIST_URL = "/ant/rl/weekContribute";

    //获取每周魅力榜
    String GET_MZML_LIST_URL = "/ant/rl/weekGlamour";

    //获取真爱榜
    String GET_ZAB_LIST_URL = "/ant/rl/zab";

    //提交实名认证信息
    String SUBMIT_USER_VERIFIED_URL = "/ant/realValid/submit";

    //获取关注列表
    String GET_ATTENTION_LIST_URL = "/ant/user/relation/followers";

    //添加关注
    String ADD_ATTENTION_LIST_URL = "/ant/user/relation/follow";

    //移除关注
    String REMOVE_ATTENTION_LIST_URL = "/ant/user/relation/unFollow";

    //获取粉丝列表
    String GET_FANS_LIST_URL = "/ant/user/relation/fans";

    //获取访客列表
    String GET_VISITOR_LIST_URL = "/ant/user/visit/getCurrentUserVisitors";

    //获取黑名单列表
    String GET_BLACK_LIST_URL = "/ant/user/relation/blackList";

    //添加到黑名单
    String ADD_BLACK_LIST_URL = "/ant/user/relation/addToBlackList";

    //移除黑名单
    String REMOVE_BLACK_LIST_URL = "/ant/user/relation/removeFromBlackList";

    //获取爵位信息
    String GET_KNIGHTHOOD_URL = "/ant/rank/rankPage";

    //搜索用户
    String SEARCH_USER_LIST_URL = "/ant/search/searchUser";

    //搜索房间
    String SEARCH_ROOM_lIST_URL = "/ant/search/searchLiveRoom";

    //商城头像框
    String SHOP_MALL_HEADER_PHOTO_URL = "/ant/mall/txkList";

    //商城座驾
    String SHOP_MALL_ZJ_PHOTO_URL = "/ant/mall/cars";

    //商城道具
    String SHOP_MALL_DJ_PHOTO_URL = "/ant/mall/others";

    //购买道具/座驾
    String USER_BUY_PROPS_URL = "/ant/mall/buy";

    //获取首页类型分类
    String GET_HOME_CATEGORY_LIST = "/ant/live/typesView";

    //获取首页直播间列表
    String GET_HOME_LIVE_ROOM_LIST = "/ant/live/list";

    //举报用户
    String SUBMIT_REPORT_USER_MESSAGE_URL = "/ant/inform/report";

    //举报房间
    String SUBMIT_REPORT_ROOM_MESSAGE_URL = "/ant/inform/report";

    //进入直播间
    String JOIN_LIVE_ROOM_URL = "/ant/live/join";

    //获取vip的相关信息
    String GET_VIP_INFO_MESSAGE_URL = "/ant/level/levelPage";

    //获取爵位信息
    String GET_KNIGHTHOOD_INFO_URL = "/ant/rank/rankPage";

    //开通/续费爵位
    String OPEN_KNIGHTHOOD_URL = "/ant/rank/openRank";

    //获取系统消息
    String GET_SYSTEM_LIST_URL = "/ant/msg/system/get";

    //查询青少年模式
    String CHECK_YOUTH_MODE_URL = "/ant/user/younthModel/openStatus";

    //开启青少年模式
    String OPEN_YOUTH_MODE_URL = "/ant/user/younthModel/open";

    //关闭青少年模式
    String CLOSE_YOUTH_MODE_URL = "/ant/user/younthModel/close";

    //获取青少年密码
    String GET_YOUTH_MODE_PASSWORD_URL = "/ant/user/younthModel/findPwd";

    //验证手机号码
    String VERIFY_MOBILE_NUMBER_URL = "/user/validMobileNum";

    //换绑手机号码
    String CHANGE_BIND_MOBILE_NUMBER_URL = "/user/modifyMobileNum";

    //永久注销当前账号
    String LONG_TIME_OUT_LOGIN_URL = "/user/off";

    //获取直播间的信息
    String GET_ROOM_DATA_MESSAGE_URL = "/ant/live/liveRoomInfo";

    //获取房间成员信息
    String GET_ROOM_MEMBER_LIST_URL = "/ant/live/getRoomMembers";

    //获取直播间每日贡献榜
    String GET_LIVE_ROOM_DAY_GXB_LIST_URL = "/ant/live/daily/ranking/contribute";

    //获取直播间每日魅力榜
    String GET_LIVE_ROOM_MLB_LIST_URL = "/ant/live/daily/ranking/glamour";

    //获取直播间每周贡献榜
    String GET_LIVE_ROOM_WEEK_GXB_LIST_URL = "/ant/live/week/ranking/contribute";

    //获取直播间每周魅力榜
    String GET_LIVE_ROOM_WEEK_MLB_LIST_URL = "/ant/live/week/ranking/glamour";

    //获取每日房间流水
    String GET_LIVE_ROOM_WATER_URL = "/ant/live/dailyRoomBill";

    //收藏直播间
    String ADD_COLLECTION_URL = "/ant/live/collection";

    //取消直播间收藏
    String CANCEL_COLLECTION_ROOM_URL = "/ant/live/cancel";

    //取消管理员身份
    String CANCEL_MANAGER_URL = "/ant/live/cancelManager";

    //取消排麦申请
    String CANCEL_ROW_WHEAT_URL = "/ant/live/cancelMicApply";

    //清空排麦列表
    String CLEAR_ROW_LIST_URL = "/ant/live/cleanMicApplyMembers";

    //禁言、解禁用户
    String ADD_OR_CANCEL_Mute_URL = "/ant/live/gag";

    //查询禁言用户列表
    String QUERY_BANNED_LIST_URL = "/ant/live/gag/members";

    //禁麦
    String PROHIBIT_MICROPHONE_URL = "/ant/live/gagMic";

    //查询禁麦用户列表
    String QUERY_PROHIBIT_MIC_LIST_URL = "/ant/live/gagMic/members";

    //获取排麦成员
    String GET_ROW_LIST_URL = "/ant/live/getMicApplyMembers";

    //获取房间管理员
    String GET_ROOM_ADMINISTRATOR_URL = "/ant/live/getRoomManagers";

    //获取房间普通成员
    String GET_ROOM_MEMBER_URL = "/ant/live/getRoomMembers";

    //将用户踢出房间
    String KICK_OUT_USER_FROM_ROOM_URL = "/ant/live/kick";

    //查询踢出用户列表
    String GET_KICK_OUT_USER_LIST_URL = "/ant/live/kick/members";

    //同意上麦
    String AGREE_SERVING_WHEAT_URL = "/ant/live/mic/apply/accept";

    //拒绝上麦
    String REFUSE_SERVING_WHEAT_URL = "/ant/live/mic/apply/reject";

    //踢用户下麦
    String KICK_OUT_USER_WHEAT_URL = "/ant/live/mic/kick";

    //主动下麦
    String USER_DOWN_WHEAT_URL = "/ant/live/mic/quit";

    //麦位状态设置
    String SET_WHEAT_STATUS_URL = "/ant/live/mic/state";

    //接管主持人
    String TAKE_OVER_THE_HOST_URL = "/ant/live/mic/takeOverHost";

    //同意接管主持人
    String AGREE_TAKE_OVER_THE_HOST_URL = "/ant/live/mic/takeOverHost/accept";

    //主持人拒绝接管
    String REFUSE_TAKE_OVER_THE_HOS_TURL = "/ant/live/mic/takeOverHost/reject";

    //转让主持人
    String TRANSFER_HOST_URL = "/ant/live/mic/transferHost";

    //转让主持人同意
    String AGREE_TRANSFER_HOST_URL = "/ant/live/mic/transferHost/accept";

    //转让主持人拒绝
    String REFUSE_TRANSFER_HOST_URL = "/ant/live/mic/transferHost/reject";

    //申请排麦
    String APPLY_FOR_ROW_WHEAT_URL = "/ant/live/roomMicApply";

    //搜索房间内的用户
    String SEARCH_USER_OF_ROOM_URL = "/ant/live/searchRoomUser";

    //赠送礼物
    String SEND_GIFT_URL = "/ant/live/sendGift";

    //设置房间管理员
    String SET_ROOM_MEMBER_URL = "/ant/live/setManager";

    //设置直播间密码
    String SET_ROOM_PASSWORD_URL = "/ant/live/setPwd";

    //修改直播间信息
    String CHANGE_ROOM_MESSAGE_URL = "/ant/live/update";


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
    Observable<ApiResponse<UserInfo>> loginMobileAndPassword(@Field("phone") String phone, @Field("password") String password);

    /**
     * 账号 + 密码 登录
     */
    @FormUrlEncoded
    @POST(NAME_AND_PASSWORD_LOGIN_URL)
    Observable<ApiResponse<UserInfo>> nameAndPasswordLogin(@Field("username") String username, @Field("password") String password);

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
    Observable<ApiResponse<Object>> getVerificationCode(@Url String url, @Field("event") int event, @Field("mobileNum") String mobile);

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
     * 获取首页滚动信息
     */
    @POST(GET_HOME_NOTICE_MESSAGE_URL)
    Observable<ApiResponse<List<String>>> getHomeNoticeMessageList(@Header(AUTHOR) String token);

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
    Observable<ApiResponse<Object>> forgetPassword(@Field("mobileNum") String mobileNum, @Field("msgCode") String msgCode, @Field("newPwd") String newPwd);


    /**
     * 创建房间
     */
    @FormUrlEncoded
    @POST(CREATE_CHAT_ROOM_URL)
    Observable<ApiResponse<RecommendList>> createChatRoom(@Header(AUTHOR) String token, @FieldMap HashMap<String, Object> map);

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
    Observable<ApiResponse<BaseResponse<RecommendList>>> getMyCreateOrCollectionRoomList(@Header(AUTHOR) String token, @Url String url);

    /**
     * 获取我的钱包余额信息
     */
    @POST(GET_MY_WALLET_URL)
    Observable<ApiResponse<MyWalletBalance>> getMyWalletBalance(@Header(AUTHOR) String token);

    /**
     * 获取礼物列表
     */
    @POST(GET_GIFT_LIST_URL)
    Observable<ApiResponse<List<GiftList>>> getGiftList(@Header(AUTHOR) String token);

    /**
     * 获取直播间背景
     */
    @POST(GET_ROOM_BACKGROUND_LIST_URL)
    Observable<ApiResponse<List<RoomBackgroundList>>> getRoomBackgroundList(@Header(AUTHOR) String token);

    /**
     * 获取实名认证状态
     */
    @POST(VERIFIED_STATUS_URL)
    Observable<ApiResponse<VerifiedStatus>> getVerifiedStatus(@Header(AUTHOR) String token);

    /**
     * 获取每日榜
     */
    @POST
    Observable<ApiResponse<List<ToadyUserList>>> getTodayUserList(@Header(AUTHOR) String token, @Url String url);

    /**
     * 获取贵族榜单
     */
    @POST(GET_GZB_LIST_URL)
    Observable<ApiResponse<List<GZBUserList>>> getGZBUserList(@Header(AUTHOR) String token);

    /**
     * 获取守护榜单
     */
    @POST(GET_SHB_LIST_URL)
    Observable<ApiResponse<List<GuardUserList>>> getGuardUserList(@Header(AUTHOR) String token);

    /**
     * 获取周星榜
     */
    @POST
    Observable<ApiResponse<WeekStarUserList>> getWeekStarUserList(@Header(AUTHOR) String token, @Url String url);

    /**
     * 真爱榜单
     */
    @POST(GET_ZAB_LIST_URL)
    Observable<ApiResponse<List<ZABUserList>>> getZABUserList(@Header(AUTHOR) String token);

    /**
     * 提交实名认证
     */
    @FormUrlEncoded
    @POST(SUBMIT_USER_VERIFIED_URL)
    Observable<ApiResponse<Object>> submitUserVerifiedInfo(@Header(AUTHOR) String token, @FieldMap HashMap<String, Object> map);


    //添加/移除关注  添加/移除黑名单
    @FormUrlEncoded
    @POST
    Observable<ApiResponse<Object>> addAttentionOrBlackList(@Header(AUTHOR) String token, @Url String url, @Field("target") int target);

    /**
     * 获取关注/粉丝/访客列表
     */
    @FormUrlEncoded
    @POST
    Observable<ApiResponse<BaseResponse<AttentionUserList>>> getAttentionOrFansUserList(@Header(AUTHOR) String token, @Url String url, @Field("target") int target);

    /**
     * 获取黑名单列表
     */
    @FormUrlEncoded
    @POST(GET_BLACK_LIST_URL)
    Observable<ApiResponse<BaseResponse<AttentionUserList>>> getBlackListUserList(@Header(AUTHOR) String token, @Field("principal") int target);

    /**
     * 商城商品
     */
    @POST
    Observable<ApiResponse<List<ShopMallPropsList>>> getShopMallPropsList(@Header(AUTHOR) String token, @Url String url);

    /**
     * 购买道具/座驾
     */
    @FormUrlEncoded
    @POST(USER_BUY_PROPS_URL)
    Observable<ApiResponse<Object>> userBuyProps(@Header(AUTHOR) String token, @Field("id") String goodId, @Field("number") int number);

    /**
     * 搜索房间
     */
    @FormUrlEncoded
    @POST(SEARCH_ROOM_lIST_URL)
    Observable<ApiResponse<BaseResponse<RecommendList>>> searchRoomResultList(@Header(AUTHOR) String token, @Field("keyWord") String keyWord, @Field("current") int current, @Field("size") int size);

    /**
     * 搜索用户
     */
    @FormUrlEncoded
    @POST(SEARCH_USER_LIST_URL)
    Observable<ApiResponse<BaseResponse<UserInfo>>> searchUserResultList(@Header(AUTHOR) String token, @Field("keyWord") String keyWord, @Field("current") int current, @Field("size") int size);

    /**
     * 获取首页类型分类
     */
    @POST(GET_HOME_CATEGORY_LIST)
    Observable<ApiResponse<List<HomeCategory>>> getHomeCategoryList(@Header(AUTHOR) String token);

    /**
     * 获取首页房间列表
     */
    @FormUrlEncoded
    @POST(GET_HOME_LIVE_ROOM_LIST)
    Observable<ApiResponse<List<RecommendList>>> getHomeRoomList(@Header(AUTHOR) String token, @Field("type") int type);


    /**
     * 提交举报房间/用户原因
     */
    @FormUrlEncoded
    @POST
    Observable<ApiResponse<Object>> submitReportMessage(@Header(AUTHOR) String token, @Url String url, @Field("target") int target, @Field("type") String type, @Field("content") String content, @Field("pictures") String pictures);

    /**
     * 加入直播间
     */
    @FormUrlEncoded
    @POST(JOIN_LIVE_ROOM_URL)
    Observable<ApiResponse<RecommendList>> joinLiveRoom(@Header(AUTHOR) String token, @Field("roomId") int roomId, @Field("pwd") String roomPassword);

    /**
     * 获取vip信息
     */
    @POST(GET_VIP_INFO_MESSAGE_URL)
    Observable<ApiResponse<VipMessageInfo>> getVipMessageInfo(@Header(AUTHOR) String token);

    /**
     * 获取爵位信息
     */
    @POST(GET_KNIGHTHOOD_INFO_URL)
    Observable<ApiResponse<KnighthoodMessageInfo>> getKnighthoodMessageInfo(@Header(AUTHOR) String token);

    /**
     * 开通爵位
     */
    @FormUrlEncoded
    @POST(OPEN_KNIGHTHOOD_URL)
    Observable<ApiResponse<Object>> openKnighthoodLevel(@Header(AUTHOR) String token, @Field("rankId") int rankId);

    /**
     * 获取系统消息
     */
    @POST(GET_SYSTEM_LIST_URL)
    Observable<ApiResponse<BaseResponse<SystemMessage>>> getSystemMessageList(@Header(AUTHOR) String token);

    /**
     * 查询青少年模式
     */
    @POST(CHECK_YOUTH_MODE_URL)
    Observable<ApiResponse<Boolean>> checkYouthMode(@Header(AUTHOR) String token);

    /**
     * 开启/关闭青少年模式
     */
    @FormUrlEncoded
    @POST
    Observable<ApiResponse<Object>> openOrCloseYouthMode(@Header(AUTHOR) String token, @Url String url, @Field("pwd") String pwd);

    /**
     * 找回青少年模式密码
     */
    @FormUrlEncoded
    @POST(GET_YOUTH_MODE_PASSWORD_URL)
    Observable<ApiResponse<Object>> findYouthModePassword(@Header(AUTHOR) String token, @Field("msgCode") String msgCode, @Field("newPwd") String newPwd);


    /**
     * 验证手机号码
     */
    @FormUrlEncoded
    @POST(VERIFY_MOBILE_NUMBER_URL)
    Observable<ApiResponse<String>> verifyMobileNumber(@Header(AUTHOR) String token, @Field("mobileNum") String mobileNum, @Field("msgCode") String msgCode);

    /**
     * 换绑当前手机号码
     */
    @FormUrlEncoded
    @POST(CHANGE_BIND_MOBILE_NUMBER_URL)
    Observable<ApiResponse<Object>> verifyChangeMobileNumber(@Header(AUTHOR) String token, @Field("msgCode") String msgCode, @Field("newMobileNum") String newMobileNum, @Field("validCode") String validCode);

    /**
     * 永久注销
     */
    @FormUrlEncoded
    @POST(LONG_TIME_OUT_LOGIN_URL)
    Observable<ApiResponse<Object>> longTimeOutLogin(@Header(AUTHOR) String token);

    /**
     * 获取房间信息
     */
    @FormUrlEncoded
    @POST(GET_ROOM_DATA_MESSAGE_URL)
    Observable<ApiResponse<RoomDataInfo>> getRoomDataInfo(@Header(AUTHOR) String token, @Field("roomId") int roomId);

    /**
     * 获取房间成员信息
     */
    @FormUrlEncoded
    @POST(GET_ROOM_MEMBER_LIST_URL)
    Observable<ApiResponse<BaseResponse<UserInfo>>> getRoomMemberList(@Header(AUTHOR) String token, @Field("roomId") int roomId);


    /**
     * 获取每日榜
     */
    @FormUrlEncoded
    @POST
    Observable<ApiResponse<List<ToadyUserList>>> getRoomTodayUserList(@Header(AUTHOR) String token, @Url String url, @Field("roomId") int roomId);



    /**
     * 获取周星榜
     */
    @FormUrlEncoded
    @POST
    Observable<ApiResponse<WeekStarUserList>> getRoomWeekStarUserList(@Header(AUTHOR) String token, @Url String url, @Field("roomId") int roomId);

}
