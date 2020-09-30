package com.zhowin.miyou.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author : zho
 * date  ：2020/9/30
 * desc ： 用户搜索历史
 */
@Entity
public class SearchUserHistory {

    @Id(autoincrement = true)
    public Long id;
    @NotNull
    private String title;
    private String content;

    public SearchUserHistory(String title) {
        this.title = title;
    }

    @Generated(hash = 1372309950)
    public SearchUserHistory(Long id, @NotNull String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Generated(hash = 1380899973)
    public SearchUserHistory() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
