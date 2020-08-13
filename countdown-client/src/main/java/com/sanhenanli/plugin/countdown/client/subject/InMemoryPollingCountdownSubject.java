package com.sanhenanli.plugin.countdown.client.subject;

import com.sanhenanli.plugin.countdown.client.InMemoryPollingCountdownTimer;
import com.sanhenanli.plugin.countdown.client.model.CountdownResult;

/**
 * datetime 2020/8/13 9:33
 * 单机轮询倒计时subject
 *
 * @author zhouwenxiang
 */
public class InMemoryPollingCountdownSubject extends InMemoryCountdownSubject {

    /**
     * 轮询间隔/ms
     */
    protected long pollingGap = 100;

    public InMemoryPollingCountdownSubject(String name, long millis, long pollingGap) {
        super(new InMemoryPollingCountdownTimer(name, millis));
        this.pollingGap = pollingGap;
    }

    @Override
    public CountdownResult start() {
        CountdownResult result = super.start();
        if (result.isOk()) {
            new Thread(this::pollingCountdown).start();
        }
        return result;
    }

    /**
     * 轮询倒计时的结束状态
     */
    private void pollingCountdown() {
        while (true) {
            if (((InMemoryPollingCountdownTimer) this.countdownTimer).isStopped()) {
                stop();
                break;
            }
            try {
                Thread.sleep(pollingGap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
