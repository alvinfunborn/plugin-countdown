package com.sanhenanli.plugin.countdown.redis.timer;

import com.sanhenanli.plugin.countdown.client.AbstractCountdownContext;
import com.sanhenanli.plugin.countdown.client.AbstractCountdownTimer;
import com.sanhenanli.plugin.countdown.client.model.CountdownResult;
import com.sanhenanli.plugin.countdown.client.model.CountdownState;
import com.sanhenanli.plugin.countdown.client.model.enums.CountdownStateEnum;
import com.sanhenanli.plugin.countdown.redis.config.DistributedDelayedQueue;

import java.util.List;

/**
 * datetime 2020/8/17 17:11
 * 分布式延时队列倒计时器
 *
 * @author zhouwenxiang
 */
public class DDQCountdownTimer extends AbstractCountdownTimer {

    private DistributedDelayedQueue distributedDelayedQueue;

    public DDQCountdownTimer(String name, long countdownMillis, AbstractCountdownContext countdownContext, DistributedDelayedQueue distributedDelayedQueue) {
        super(name, countdownMillis, countdownContext);
        this.distributedDelayedQueue = distributedDelayedQueue;
    }

    @Override
    public CountdownResult init() {
        return new CountdownResult(true);
    }

    @Override
    public CountdownResult start() {
        distributedDelayedQueue.offer(name, countdownMillis);
        return new CountdownResult(true);
    }

    @Override
    public CountdownResult cancel() {
        if (distributedDelayedQueue.remove(name)) {
            return new CountdownResult(true);
        }
        return new CountdownResult(false, "unable to cancel " + name);
    }

    @Override
    public CountdownResult suspend() {
        if (distributedDelayedQueue.remove(name)) {
            return new CountdownResult(true);
        }
        return new CountdownResult(false, "unable to suspend " + name);
    }

    @Override
    public CountdownResult resume() {
        distributedDelayedQueue.offer(name, getCurrentMillis());
        return new CountdownResult(true);
    }

    @Override
    public CountdownResult reset(long millis) {
        if (distributedDelayedQueue.contains(name)) {
            if (distributedDelayedQueue.remove(name)) {
                distributedDelayedQueue.offer(name, millis);
                return new CountdownResult(true);
            }
            return new CountdownResult(false, "unable to reset " + name);
        }
        return new CountdownResult(true);
    }

    @Override
    public String log() {
        return listStates().toString();
    }

    @Override
    public long getCurrentMillis() {
        return countdownContext.getCurrentMillis();
    }

    @Override
    public CountdownStateEnum getCurrentState() {
        return countdownContext.getCurrentState();
    }

    @Override
    public void appendCountdownState(CountdownState state) {
        countdownContext.appendCountdownState(state);
    }

    @Override
    public List<CountdownState> listStates() {
        return countdownContext.listStates();
    }
}
