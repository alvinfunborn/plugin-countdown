package com.sanhenanli.plugin.countdown.client.listener;

/**
 * datetime 2020/8/3 17:28
 *
 * @author zhouwenxiang
 */
public abstract class AbstractCountdownListener implements CountdownListener {

    protected String name;

    public AbstractCountdownListener(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
