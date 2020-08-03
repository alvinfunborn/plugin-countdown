package com.sanhenanli.plugin.countdown.client;

import com.sanhenanli.plugin.countdown.client.model.CountdownResult;

/**
 * datetime 2020/8/3 10:09
 * 轮询倒数计时器
 *
 * @author zhouwenxiang
 */
public class PollingInMemoryCountdownTimer extends AbstractInMemoryCountdownTimer {

    private long stopAt;
    private long remainedMillis;

    public PollingInMemoryCountdownTimer(String id, long millis) {
        super(id, millis);
    }

    @Override
    public CountdownResult reset(long millis) {
        if (millis > 0) {
            this.countdownMillis = millis;
            return new CountdownResult(true);
        } else {
            return new CountdownResult(false, "invalid millis");
        }
    }

    @Override
    public CountdownResult start() {
        stopAt = System.currentTimeMillis() + countdownMillis;
        return new CountdownResult(true);
    }

    @Override
    public CountdownResult cancel() {
        stopAt = 0;
        return new CountdownResult(true);
    }

    @Override
    public CountdownResult suspend() {
        remainedMillis = stopAt - System.currentTimeMillis();
        stopAt = 0;
        return new CountdownResult(true);
    }

    @Override
    public CountdownResult resume() {
        stopAt = System.currentTimeMillis() + remainedMillis;
        return new CountdownResult(true);
    }

    public boolean stopped() {
        return stopAt != 0 && stopAt <= System.currentTimeMillis();
    }

}
