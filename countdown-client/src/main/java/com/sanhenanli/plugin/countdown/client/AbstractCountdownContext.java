package com.sanhenanli.plugin.countdown.client;

/**
 * datetime 2020/8/4 14:22
 * 抽象倒计时上下文
 *
 * @author zhouwenxiang
 */
public abstract class AbstractCountdownContext implements CountdownContext {

    /**
     * 名称, 与倒计时器名称一致
     */
    protected String name;

    public AbstractCountdownContext(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
