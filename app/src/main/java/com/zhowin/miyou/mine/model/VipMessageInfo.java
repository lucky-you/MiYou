package com.zhowin.miyou.mine.model;

import com.zhowin.base_library.model.UserLevelInfo;

import java.util.List;

/**
 * author : zho
 * date  ：2020/9/29
 * desc ：vip 信息
 */
public class VipMessageInfo {


    /**
     * level : {"level":0,"startExpValue":0,"endExpValue":1,"color":"#eeeeee"}
     * exp : 19
     * perms : [{"id":null,"level":1,"permId":1,"name":"访客记录","heightPicture":"http://qfah2px93.hn-bkt.clouddn.com/perm.1.jpg","greyPicture":"http://qfah2px93.hn-bkt.clouddn.com/perm.1.jpg","permKey":"visit_log","type":0,"sort":0,"enable":1,"createTime":1600956313000},{"id":null,"level":1,"permId":7,"name":"全频道发言","heightPicture":"http://qfah2px93.hn-bkt.clouddn.com/perm.4.jpg","greyPicture":"http://qfah2px93.hn-bkt.clouddn.com/perm.4.jpg","permKey":"publish","type":0,"sort":3,"enable":1,"createTime":1601296982000},{"id":null,"level":2,"permId":2,"name":"聊天字体颜色","heightPicture":"http://qfah2px93.hn-bkt.clouddn.com/perm.2.jpg","greyPicture":"http://qfah2px93.hn-bkt.clouddn.com/perm.2.jpg","permKey":"font_color","type":0,"sort":1,"enable":1,"createTime":1600956342000},{"id":null,"level":3,"permId":3,"name":"进入房间隐身","heightPicture":"http://qfah2px93.hn-bkt.clouddn.com/perm.3.jpg","greyPicture":"http://qfah2px93.hn-bkt.clouddn.com/perm.3.jpg","permKey":"stealth","type":0,"sort":2,"enable":1,"createTime":1600956377000}]
     */

    private UserLevelInfo level;
    private int exp;
    private List<VipPrivilegeList> perms;

    public UserLevelInfo getLevel() {
        return level;
    }

    public void setLevel(UserLevelInfo level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public List<VipPrivilegeList> getPerms() {
        return perms;
    }

    public void setPerms(List<VipPrivilegeList> perms) {
        this.perms = perms;
    }
}
