package com.sanhenanli.plugin.countdown.client.subject;

import com.sanhenanli.plugin.countdown.client.InMemoryScheduleCountdownTimer;

/**
 * datetime 2020/8/13 9:36
 * 单机定时任务倒计时subject
 *
 * @author zhouwenxiang
 */
public class InMemoryScheduleCountdownSubject extends InMemoryCountdownSubject {

    public InMemoryScheduleCountdownSubject(String name, long millis) {
        super(new InMemoryScheduleCountdownTimer(name, millis));
        ((InMemoryScheduleCountdownTimer) this.countdownTimer).register(this);
    }
}
