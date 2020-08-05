package com.sanhenanli.plugin.countdown.client.subject;

import com.sanhenanli.plugin.countdown.client.AbstractCountdownTimer;
import com.sanhenanli.plugin.countdown.client.CountdownTimer;

/**
 * datetime 2020/8/4 9:17
 * 倒计时subject
 *
 * @author zhouwenxiang
 */
public abstract class AbstractCountdownSubject extends AbstractSubject implements CountdownTimer {

    /**
     * 倒计时器
     */
    protected AbstractCountdownTimer countdownTimer;

    public AbstractCountdownSubject(AbstractCountdownTimer countdownTimer) {
        this.countdownTimer = countdownTimer;
    }

    public AbstractCountdownTimer getCountdownTimer() {
        return countdownTimer;
    }

    public String getName() {
        return countdownTimer.getName();
    }

    @Override
    public void notifyObservers() {
        observers.forEach(o -> o.update(this));
    }
}
