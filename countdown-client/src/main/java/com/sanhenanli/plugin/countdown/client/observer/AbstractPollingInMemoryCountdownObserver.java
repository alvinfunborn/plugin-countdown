package com.sanhenanli.plugin.countdown.client.observer;

import com.sanhenanli.plugin.countdown.client.InMemoryCountdownTimer;

/**
 * datetime 2020/8/4 11:35
 * 内存倒计时器的标准轮询观察者
 *
 * @author zhouwenxiang
 */
public abstract class AbstractPollingInMemoryCountdownObserver extends AbstractStandardCountdownObserver {

    /**
     * 轮询时间间隔
     */
    protected long pollingGap = 100;

    public AbstractPollingInMemoryCountdownObserver(String name, long pollingGap) {
        super(name);
        this.pollingGap = pollingGap;
    }

    /**
     * 轮询倒计时的结束状态
     * @param countdownTimer 内存倒计时
     */
    public void pollingCountdown(InMemoryCountdownTimer countdownTimer) {
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
