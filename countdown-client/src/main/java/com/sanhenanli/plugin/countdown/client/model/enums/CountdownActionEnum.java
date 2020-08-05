package com.sanhenanli.plugin.countdown.client.model.enums;

/**
 * datetime 2020/7/31 16:39
 * 倒计时的操作枚举
 *
 * @author zhouwenxiang
 */
public enum CountdownActionEnum {

    /**
     * 初始化
     */
    INIT(1),
    /**
     * 重置
     */
    RESET(6),
    /**
     * 开启
     */
    START(2),
    /**
     * 暂停
     */
    SUSPEND(3),
    /**
     * 继续
     */
    RESUME(4),
    /**
     * 取消
     */
    CANCEL(5),
    /**
     * 倒计时结束(非主动)
     */
    STOP(7),
    ;

    private int code;

    CountdownActionEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static CountdownActionEnum getByCode(int code) {
        for (CountdownActionEnum value : CountdownActionEnum.values()) {
            if (value.code == code) {
                return value;
            }
        }
        return null;
    }
}
