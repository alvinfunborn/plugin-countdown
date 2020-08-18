package com.sanhenanli.plugin.countdown.impl.observer;

import com.sanhenanli.plugin.countdown.client.CountdownTimer;
import com.sanhenanli.plugin.countdown.client.observer.AbstractStandardCountdownObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * datetime 2020/8/4 17:12
 * 测试观察者, 仅输出日志
 *
 * @author zhouwenxiang
 */
public class TestCountdownObserver extends AbstractStandardCountdownObserver {

    private Logger log = LoggerFactory.getLogger(TestCountdownObserver.class);

    public TestCountdownObserver(String name) {
        super(name);
    }

    @Override
    public void onInit(CountdownTimer timer) {
        log.info("timer:{}", timer.log());
    }

    @Override
    public void onReset(CountdownTimer timer) {
        log.info("timer:{}", timer.log());
    }

    @Override
    public void onStart(CountdownTimer timer) {
        log.info("timer:{}", timer.log());
    }

    @Override
    public void onSuspend(CountdownTimer timer) {
        log.info("timer:{}", timer.log());
    }

    @Override
    public void onResume(CountdownTimer timer) {
        log.info("timer:{}", timer.log());
    }

    @Override
    public void onCancel(CountdownTimer timer) {
        log.info("timer:{}", timer.log());
    }

    @Override
    public void onStop(CountdownTimer timer) {
        log.info("timer:{}", timer.log());
    }
}
