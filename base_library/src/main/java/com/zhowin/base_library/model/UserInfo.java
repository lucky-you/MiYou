package com.zhowin.base_library.model;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.base_library.utils.SPUtils;

/**
 * author Z_B
 * date :2020/5/14 8:59
 * description: 用户信息 --> 全局通用
 */
public class UserInfo {


    /**
     * id : 25
     * nickname : 周小川
     * mobile : 13677197786
     * avatar :
     * level : 0
     * gender : 2
     * birthday : null
     * bio : 以“武汉，每天不一样”为主题的武汉城市形象宣传片，2015年9月20日登上美国纽约时报广场“中国屏”
     * money : 0
     * score : 0
     * logintime : 1596093719
     * token :
     * invitationCode : null
     * pid : null
     * expiretime : 1598608717
     * expiresIn : 2592000
     * expertIdentity : null
     * levelText : 普通用户
     * levelLogo :
     * cmoney : 0
     */

    private int id;
    private String nickname;
    private String mobile;
    private String avatar;
    private int level;
    private int gender;
    private String birthday;
    private String bio;
    private double money;
    private int score;
    private int logintime;
    private String token;
    private String invitationCode;
    private String pid;
    private int expiretime;
    private int expiresIn;
    private String levelText;
    private String levelLogo;
    private double cmoney;
    private String imToken;

    public static void setUserInfo(UserInfo data) {
        Gson gson = new Gson();
        String userInfo = gson.toJson(data);
        SPUtils.set(ConstantValue.USER_INFO, userInfo);
        setUserToken(data.getToken());
    }

    public static UserInfo getUserInfo() {
        Gson gson = new Gson();
        UserInfo userInfoBean = gson.fromJson(SPUtils.getString(ConstantValue.USER_INFO), UserInfo.class);
        if (userInfoBean != null) {
            return userInfoBean;
        } else {
            return new UserInfo();
        }
    }

    /**
     * 设置token
     */
    public static void setUserToken(String token) {
        if (!TextUtils.isEmpty(token)) {
            SPUtils.set(ConstantValue.SP_TOKEN, token);
        } else {
            SPUtils.set(ConstantValue.SP_TOKEN, "");
        }
    }

    /**
     * 获取token
     */
    public static String getUserToken() {
        return (String) SPUtils.get(ConstantValue.SP_TOKEN, "");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLogintime() {
        return logintime;
    }

    public void setLogintime(int logintime) {
        this.logintime = logintime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(int expiretime) {
        this.expiretime = expiretime;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getLevelText() {
        return levelText;
    }

    public void setLevelText(String levelText) {
        this.levelText = levelText;
    }

    public String getLevelLogo() {
        return levelLogo;
    }

    public void setLevelLogo(String levelLogo) {
        this.levelLogo = levelLogo;
    }

    public double getCmoney() {
        return cmoney;
    }

    public void setCmoney(int cmoney) {
        this.cmoney = cmoney;
    }

    public String getImToken() {
        return imToken;
    }

    public void setImToken(String imToken) {
        this.imToken = imToken;
    }
}
