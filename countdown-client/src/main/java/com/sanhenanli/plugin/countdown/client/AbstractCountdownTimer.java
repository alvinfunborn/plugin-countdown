package com.sanhenanli.plugin.countdown.client;

/**
 * datetime 2020/7/31 16:28
 * 抽象倒计时器
 *
 * @author zhouwenxiang
 */
public abstract class AbstractCountdownTimer implements CountdownTimer {

    /**
     * 名称
     */
    protected String name;
    /**
     * 初始倒计时毫秒数
     */
    protected long countdownMillis;
    /**
     * 上下文
     */
    protected AbstractCountdownContext countdownContext;

    public AbstractCountdownTimer(String name, long countdownMillis, AbstractCountdownContext countdownContext) {
        this.name = name;
        this.countdownMillis = countdownMillis;
        this.countdownContext = countdownContext;
    }

    public String getName() {
        return name;
    }

    public long getCountdownMillis() {
        return countdownMillis;
    }
}
