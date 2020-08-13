package com.sanhenanli.plugin.countdown.client;

import com.sanhenanli.plugin.countdown.client.model.CountdownResult;
import com.sanhenanli.plugin.countdown.client.subject.InMemoryCountdownSubject;

import javax.swing.*;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * datetime 2020/8/13 16:27
 * 单机倒计时器, 基于DelayQueue实现
 *
 * @author zhouwenxiang
 */
public class InMemoryDelayQueueCountdownTimer extends AbstractInMemoryCountdownTimer {

    private DelayQueue<CountdownDelayed> queue = new DelayQueue<>();
    private InMemoryCountdownSubject countdownSubject;

    public InMemoryDelayQueueCountdownTimer(String name, long countdownMills) {
        super(name, countdownMills);
    }

    public void register(InMemoryCountdownSubject countdownSubject) {
        this.countdownSubject = countdownSubject;
    }

    static class CountdownDelayed implements Delayed {
        private long stopAt;

        public CountdownDelayed(long millis) {
            this.stopAt = millis + System.currentTimeMillis();
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(stopAt - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (!(o instanceof CountdownDelayed)) {
                return 0;
            }
            CountdownDelayed delayed = (CountdownDelayed) o;
            return Long.compare(getDelay(TimeUnit.MILLISECONDS), delayed.getDelay(TimeUnit.MILLISECONDS));
        }
    }

    @Override
    public CountdownResult init() {
        return new CountdownResult(true);
    }

    @Override
    public CountdownResult start() {
        queue.add(new CountdownDelayed(getCurrentMillis()));
        new Thread(() -> {
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countdownSubject.stop();
        }).start();
        return new CountdownResult(true);
    }

    @Override
    public CountdownResult cancel() {
        queue.clear();
        return new CountdownResult(true);
    }

    @Override
    public CountdownResult suspend() {
        queue.clear();
        return new CountdownResult(true);
    }

    @Override
    public CountdownResult resume() {
        queue.add(new CountdownDelayed(getCurrentMillis()));
        return new CountdownResult(true);
    }

    @Override
    public CountdownResult reset(long millis) {
        if (!queue.isEmpty()) {
            queue.clear();
            queue.add(new CountdownDelayed(getCurrentMillis()));
        }
        return new CountdownResult(true);
    }
}
