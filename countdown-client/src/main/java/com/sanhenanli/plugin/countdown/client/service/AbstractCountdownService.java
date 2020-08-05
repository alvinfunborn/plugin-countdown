package com.sanhenanli.plugin.countdown.client.service;

import com.sanhenanli.plugin.countdown.client.factory.CountdownSubjectFactory;
import com.sanhenanli.plugin.countdown.client.model.CountdownResult;

/**
 * datetime 2020/8/3 17:21
 * 倒计时服务
 *
 * @author zhouwenxiang
 */
public abstract class AbstractCountdownService implements CountdownService {

    protected CountdownSubjectFactory subjectFactory;

    public AbstractCountdownService(CountdownSubjectFactory subjectFactory) {
        this.subjectFactory = subjectFactory;
    }

    @Override
    public CountdownResult start(String name) {
        return subjectFactory.getByName(name).start();
    }

    @Override
    public CountdownResult cancel(String name) {
        return subjectFactory.getByName(name).cancel();
    }

    @Override
    public CountdownResult suspend(String name) {
        return subjectFactory.getByName(name).suspend();
    }

    @Override
    public CountdownResult resume(String name) {
        return subjectFactory.getByName(name).resume();
    }

    @Override
    public CountdownResult reset(String name, long millis) {
        return subjectFactory.getByName(name).reset(millis);
    }

    @Override
    public String log(String name) {
        return subjectFactory.getByName(name).log();
    }
}
