package com.zhowin.miyou.message.model;

/**
 * author : zho
 * date  ：2020/10/20
 * desc ： 系统消息
 */
public class SystemMessage {


    /**
     * id : fd551bef-0225-11eb-abfc-0242ac120002
     * content : 测试～
     * target : 34
     * sendTime : 1601364775000
     */

    private String id;
    private String content;
    private int target;
    private long sendTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }
}
