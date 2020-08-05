package com.sanhenanli.plugin.countdown.client.observer;

/**
 * datetime 2020/8/4 10:03
 * 抽象观察者, 具有name属性
 *
 * @author zhouwenxiang
 */
public abstract class AbstractObserver implements Observer {

    protected String name;

    public AbstractObserver(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
