package com.zhowin.miyou.rongIM.constant;

/**
 * 麦位状态
 */
public enum MicState {

    /**
     * 正常
     */
    NORMAL(0),
    /**
     * 锁麦
     */
    LOCK(1),
    /**
     * 闭麦
     */
    CLOSE(2);

    private int state;

    MicState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
