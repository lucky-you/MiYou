package com.zhowin.base_library.qiniu;

import android.text.TextUtils;

import com.zhowin.base_library.utils.ConstantValue;
import com.zhowin.base_library.utils.GsonUtils;
import com.zhowin.base_library.utils.SPUtils;

/**
 * 七牛云的信息
 */
public class QiNiuYunBean {


    /**
     * uploadurl : https://upload-z0.qiniup.com
     * cdnurl : https://qiniu.mwc.mowan.cn/
     * token : be_BUN1Aq8y_MFDQby-sbnx2LwLTiXPBbkXmiej6:mI343NEM2ug0B_OrXIU2d_A9bhk=:eyJzY29wZSI6Im10YmlrZSIsImRlYWRsaW5lIjoxNTk2Njg3MzAyfQ==
     */

    private String uploadurl;
    private String cdnurl;
    private String token;


    public static void setQiNiuInfo(QiNiuYunBean data) {
        String userInfo = GsonUtils.toJson(data);
        SPUtils.set(ConstantValue.QI_NIU_INFO, userInfo);
        setQiNiuToken(data.getToken());
    }

    public static QiNiuYunBean getQiNiuInfo() {
        QiNiuYunBean qiNiuYunBean = GsonUtils.fromJson(SPUtils.getString(ConstantValue.QI_NIU_INFO), QiNiuYunBean.class);
        if (qiNiuYunBean != null) {
            return qiNiuYunBean;
        } else {
            return new QiNiuYunBean();
        }
    }

    /**
     * 设置token
     */
    public static void setQiNiuToken(String token) {
        if (!TextUtils.isEmpty(token)) {
            SPUtils.set(ConstantValue.QI_NIU_TOKEN, token);
        } else {
            SPUtils.set(ConstantValue.QI_NIU_TOKEN, "");
        }
    }

    /**
     * 获取token
     */
    public static String getQiNiuToken() {
        return (String) SPUtils.get(ConstantValue.QI_NIU_TOKEN, "");
    }


    public String getUploadurl() {
        return uploadurl;
    }

    public void setUploadurl(String uploadurl) {
        this.uploadurl = uploadurl;
    }

    public String getCdnurl() {
        return cdnurl;
    }

    public void setCdnurl(String cdnurl) {
        this.cdnurl = cdnurl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
