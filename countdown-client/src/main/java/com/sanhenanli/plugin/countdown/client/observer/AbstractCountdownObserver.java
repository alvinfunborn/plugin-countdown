package com.sanhenanli.plugin.countdown.client.observer;

/**
 * datetime 2020/8/4 9:23
 * 倒计时观察者
 *
 * @author zhouwenxiang
 */
public abstract class AbstractCountdownObserver extends AbstractObserver implements CountdownObserver {

    public AbstractCountdownObserver(String name) {
        super(name);
    }
}
