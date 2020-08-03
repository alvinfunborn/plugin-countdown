package com.sanhenanli.plugin.countdown.client.service;

import com.sanhenanli.plugin.countdown.client.model.CountdownResult;

/**
 * datetime 2020/8/3 17:21
 *
 * @author zhouwenxiang
 */
public abstract class AbstractCountdownService implements CountdownService {

    @Override
    public CountdownResult start(String id) {
        return getById(id).start();
    }

    @Override
    public CountdownResult cancel(String id) {
        return getById(id).cancel();
    }

    @Override
    public CountdownResult suspend(String id) {
        return getById(id).suspend();
    }

    @Override
    public CountdownResult resume(String id) {
        return getById(id).resume();
    }

    @Override
    public CountdownResult reset(String id, long millis) {
        return getById(id).reset(millis);
    }

    @Override
    public String log(String id) {
        return getById(id).log();
    }
}
