package com.sanhenanli.plugin.countdown.powerjob.listener;

import com.sanhenanli.plugin.countdown.client.listener.CountdownListener;
import com.sanhenanli.plugin.countdown.client.CountdownTimer;
import lombok.extern.slf4j.Slf4j;

/**
 * datetime 2020/8/3 14:26
 *
 * @author zhouwenxiang
 */
@Slf4j
public class PowerJobCountdownListener implements CountdownListener {

    @Override
    public void onInit(CountdownTimer timer) {
        log.info("countdown:{}", timer);
    }

    @Override
    public void onReset(CountdownTimer timer) {
        log.info("countdown:{}", timer);
    }

    @Override
    public void onStart(CountdownTimer timer) {
        log.info("countdown:{}", timer);
    }

    @Override
    public void onSuspend(CountdownTimer timer) {
        log.info("countdown:{}", timer);
    }

    @Override
    public void onResume(CountdownTimer timer) {
        log.info("countdown:{}", timer);
    }

    @Override
    public void onCancel(CountdownTimer timer) {
        log.info("countdown:{}", timer);
    }

    @Override
    public void onStop(CountdownTimer timer) {
        log.info("countdown:{}", timer);
    }
}
