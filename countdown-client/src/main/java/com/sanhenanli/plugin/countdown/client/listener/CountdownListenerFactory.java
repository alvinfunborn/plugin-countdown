package com.sanhenanli.plugin.countdown.client.listener;

/**
 * datetime 2020/8/3 17:35
 *
 * @author zhouwenxiang
 */
public interface CountdownListenerFactory {

    void registerListener(AbstractCountdownListener listener);

    AbstractCountdownListener getByName(String name);
}
