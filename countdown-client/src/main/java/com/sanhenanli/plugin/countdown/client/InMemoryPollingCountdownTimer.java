package com.sanhenanli.plugin.countdown.client;

import com.sanhenanli.plugin.countdown.client.model.CountdownResult;

import java.util.concurrent.atomic.AtomicLong;

/**
 * datetime 2020/8/3 10:09
 * 单机倒计时器, 自标记两个参量方便轮询
 *
 * @author zhouwenxiang
 */
public class InMemoryPollingCountdownTimer extends AbstractInMemoryCountdownTimer {

    /**
     * 记录精确的停止时间
     */
    private AtomicLong stopAt = new AtomicLong(0);
    /**
     * 记录所剩余的倒计时时间
     */
    private AtomicLong remainedMillis = new AtomicLong(0);

    public InMemoryPollingCountdownTimer(String name, long millis) {
        super(name, millis);
    }

    @Override
    public CountdownResult reset(long millis) {
        if (millis > 0) {
            this.countdownMillis = millis;
            remainedMillis.set(millis);
            stopAt.set(System.currentTimeMillis() + millis);
            return new CountdownResult(true);
        } else {
            return new CountdownResult(false, "invalid millis");
        }
    }

    @Override
    public CountdownResult init() {
        return new CountdownResult(true);
    }

    @Override
    public CountdownResult start() {
        stopAt.set(System.currentTimeMillis() + countdownMillis);
        return new CountdownResult(true);
    }

    @Override
    public CountdownResult cancel() {
        stopAt.set(0);
        return new CountdownResult(true);
    }

    @Override
    public CountdownResult suspend() {
        remainedMillis.set(stopAt.get() - System.currentTimeMillis());
        stopAt.set(0);
        return new CountdownResult(true);
    }

    @Override
    public CountdownResult resume() {
        stopAt.set(System.currentTimeMillis() + remainedMillis.get());
        return new CountdownResult(true);
    }

    public boolean isStopped() {
        return stopAt.get() != 0 && stopAt.get() <= System.currentTimeMillis();
    }

}
