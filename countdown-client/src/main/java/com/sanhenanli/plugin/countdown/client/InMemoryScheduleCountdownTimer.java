package com.sanhenanli.plugin.countdown.client;

import com.sanhenanli.plugin.countdown.client.model.CountdownResult;
import com.sanhenanli.plugin.countdown.client.subject.InMemoryCountdownSubject;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * datetime 2020/8/13 10:33
 * 单机倒计时器, 基于定时任务实现
 *
 * @author zhouwenxiang
 */
public class InMemoryScheduleCountdownTimer extends AbstractInMemoryCountdownTimer {

    private ScheduledExecutorService executorService;
    private ScheduledFuture future;
    private Lock lock = new ReentrantLock(true);
    private InMemoryCountdownSubject countdownSubject;

    public InMemoryScheduleCountdownTimer(String name, long countdownMills) {
        super(name, countdownMills);
        executorService = new ScheduledThreadPoolExecutor(1, r -> new Thread(r, name), new ThreadPoolExecutor.AbortPolicy());
    }

    public void register(InMemoryCountdownSubject countdownSubject) {
        this.countdownSubject = countdownSubject;
    }

    @Override
    public CountdownResult reset(long millis) {
        if (millis > 0) {
            this.countdownMillis = millis;
            if (future != null) {
                if (cancelSchedule()) {
                    schedule();
                    return new CountdownResult(true);
                } else {
                    return new CountdownResult(false, "unable to cancel task");
                }
            }
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
        schedule();
        return new CountdownResult(true);
    }

    @Override
    public CountdownResult cancel() {
        if (cancelSchedule()) {
            return new CountdownResult(true);
        } else {
            return new CountdownResult(false, "unable to cancel task");
        }
    }

    @Override
    public CountdownResult suspend() {
        if (cancelSchedule()) {
            return new CountdownResult(true);
        } else {
            return new CountdownResult(false, "unable to cancel task");
        }
    }

    @Override
    public CountdownResult resume() {
        schedule();
        return new CountdownResult(true);
    }

    public void schedule() {
        lock.lock();
        try {
            future = executorService.schedule(() -> countdownSubject.stop(), this.getCurrentMillis(), TimeUnit.MILLISECONDS);
        } finally {
            lock.unlock();
        }
    }

    public boolean cancelSchedule() {
        lock.lock();
        try {
            boolean res = future.cancel(false);
            if (res) {
                future = null;
            }
            return res;
        } finally {
            lock.unlock();
        }
    }
}
