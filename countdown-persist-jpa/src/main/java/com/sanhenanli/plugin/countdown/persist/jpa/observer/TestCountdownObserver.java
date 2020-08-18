package com.sanhenanli.plugin.countdown.persist.jpa.observer;

import com.sanhenanli.plugin.countdown.client.CountdownTimer;
import com.sanhenanli.plugin.countdown.client.observer.AbstractStandardCountdownObserver;
import lombok.extern.slf4j.Slf4j;

/**
 * datetime 2020/8/4 17:12
 * 测试观察者, 仅输出日志
 *
 * @author zhouwenxiang
 */
@Slf4j
public class TestCountdownObserver extends AbstractStandardCountdownObserver {

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
