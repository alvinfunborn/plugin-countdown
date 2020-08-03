package com.sanhenanli.plugin.countdown.client;

/**
 * datetime 2020/7/31 16:28
 *
 * @author zhouwenxiang
 */
public abstract class AbstractCountdownTimer implements CountdownTimer {

    protected String id;
    protected long countdownMillis;

    public AbstractCountdownTimer(String id, long countdownMillis) {
        this.id = id;
        this.countdownMillis = countdownMillis;
    }

    public String getId() {
        return id;
    }

    public long getCountdownMillis() {
        return countdownMillis;
    }
}
