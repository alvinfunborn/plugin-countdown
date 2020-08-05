package com.sanhenanli.plugin.countdown.client.model.enums;

/**
 * datetime 2020/7/31 15:14
 * 倒计时的状态枚举
 *
 * @author zhouwenxiang
 */
public enum CountdownStateEnum {

    /**
     * 已声明
     */
    DEFINED(0),
    /**
     * 已创建
     */
    INITED(1),
    /**
     * 倒计时中
     */
    RUNNING(2),
    /**
     * 挂起
     */
    SUSPENDED(3),
    /**
     * 已取消
     */
    CANCELED(5),
    /**
     * 停止状态
     */
    STOPPED(7),
    ;

    private int code;

    CountdownStateEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static CountdownStateEnum getByCode(int code) {
        for (CountdownStateEnum value : CountdownStateEnum.values()) {
            if (value.code == code) {
                return value;
            }
        }
        return null;
    }
}
