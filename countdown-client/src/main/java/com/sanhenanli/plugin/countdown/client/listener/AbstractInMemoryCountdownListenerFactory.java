package com.sanhenanli.plugin.countdown.client.listener;

import java.util.HashMap;
import java.util.Map;

/**
 * datetime 2020/8/3 17:33
 *
 * @author zhouwenxiang
 */
public abstract class AbstractInMemoryCountdownListenerFactory implements CountdownListenerFactory {

    protected Map<String, AbstractCountdownListener> listenerMap = new HashMap<>(4);

    @Override
    public void registerListener(AbstractCountdownListener listener) {
        listenerMap.put(listener.getName(), listener);
    }

    @Override
    public AbstractCountdownListener getByName(String name) {
        return listenerMap.get(name);
    }
}
