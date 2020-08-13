package com.sanhenanli.plugin.countdown.client.subject;

import com.sanhenanli.plugin.countdown.client.InMemoryDelayQueueCountdownTimer;

/**
 * datetime 2020/8/13 16:51
 * 单机延时队列倒计时subject
 *
 * @author zhouwenxiang
 */
public class InMemoryDelayQueueCountdownSubject extends InMemoryCountdownSubject {

    public InMemoryDelayQueueCountdownSubject(String name, long millis) {
        super(new InMemoryDelayQueueCountdownTimer(name, millis));
        ((InMemoryDelayQueueCountdownTimer) this.countdownTimer).register(this);
    }
}
