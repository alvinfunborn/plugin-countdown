package com.sanhenanli.plugin.countdown.client.listener;

import com.sanhenanli.plugin.countdown.client.PollingInMemoryCountdownTimer;

/**
 * datetime 2020/8/3 10:29
 *
 * @author zhouwenxiang
 */
public abstract class AbstractPollingInMemoryCountdownListener extends AbstractCountdownListener {

    protected long pollingGap = 100;

    public AbstractPollingInMemoryCountdownListener(String name, long pollingGap) {
        super(name);
        this.pollingGap = pollingGap;
    }

    public void pollingCountdown(PollingInMemoryCountdownTimer countdownTimer) {
        while (true) {
            if (countdownTimer.stopped()) {
                onStop(countdownTimer);
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
