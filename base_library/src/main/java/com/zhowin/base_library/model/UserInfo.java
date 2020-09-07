package com.zhowin.base_library.model;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.base_library.utils.SPUtils;

import java.util.List;

/**
 * author Z_B
 * date :2020/5/14 8:59
 * description: 用户信息 --> 全局通用
 */
public class UserInfo {


    /**
     * accountNonExpired : true
     * accountNonLocked : true
     * authorities : [{"authority":"UNDEFINED"}]
     * completed : false
     * credentialsNonExpired : true
     * enabled : true
     * loginType : sms
     * token : eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiIyZmE4OWIwYi1kNDk2LTRkN2UtYWI5Yi02OGUwYzY5MTZkYzMiLCJzdWIiOiIxMjcwOTU2NCIsImV4cCI6MTU5OTQ1NTIyOSwidXNlcmlkIjoxMSwiY3JlYXRlZCI6MTU5OTQ0NDQyOTc1NywiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlVOREVGSU5FRCJ9XX0.7zodyLMI5EABvfNUfs08UzouKqephkohVgm7cYv4pvwrDgib14nZeDytm859zgu6Vdjcf8ln4Sor6VdNKgmzng
     * userId : 11
     * username : 12709564
     */

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean completed;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private String loginType;
    private String token;
    private int userId;
    private String username;
    private List<AuthoritiesBean> authorities;
    /**
     * avatar : 周小川
     * gender : 女
     * birthday : 2000-09-18
     * profilePictureKey : http://qfah2px93.hn-bkt.clouddn.com/image.profilePicture.default.1.jpg
     * backgroundPictureKeys :
     * constellation : 处女座
     * status : 好好学习，天天向上
     * labels : 4
     */

    private String avatar;
    private String gender;
    private String birthday;
    private String profilePictureKey;
    private String backgroundPictureKeys;
    private String constellation;
    private String status;
    private String labels;

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

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<AuthoritiesBean> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthoritiesBean> authorities) {
        this.authorities = authorities;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getProfilePictureKey() {
        return profilePictureKey;
    }

    public void setProfilePictureKey(String profilePictureKey) {
        this.profilePictureKey = profilePictureKey;
    }

    public String getBackgroundPictureKeys() {
        return backgroundPictureKeys;
    }

    public void setBackgroundPictureKeys(String backgroundPictureKeys) {
        this.backgroundPictureKeys = backgroundPictureKeys;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }


    public static class AuthoritiesBean {
        /**
         * authority : UNDEFINED
         */

        private String authority;

        public String getAuthority() {
            return authority;
        }

        public void setAuthority(String authority) {
            this.authority = authority;
        }
    }
}
