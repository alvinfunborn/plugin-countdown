package com.sanhenanli.plugin.countdown.client;

import com.sanhenanli.plugin.countdown.client.model.CountdownState;
import com.sanhenanli.plugin.countdown.client.model.enums.CountdownStateEnum;

import java.util.List;

/**
 * datetime 2020/8/3 16:33
 * 抽象倒计时器的内存模式, 使用倒计时上下文的内存实现
 *
 * @author zhouwenxiang
 */
public abstract class AbstractInMemoryCountdownTimer extends AbstractCountdownTimer {

    public AbstractInMemoryCountdownTimer(String name, long countdownMills) {
        super(name, countdownMills, new InMemoryCountdownContext(name));
    }

    @Override
    public String getName() {
        return name;
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

    @Override
    public String log() {
        return toString();
    }

    @Override
    public String toString() {
        return "AbstractInMemoryCountdownTimer{" +
                "countdownMillis=" + countdownMillis +
                ", currentCountdownMillis=" + getCurrentMillis() +
                ", currentCountdownState=" + getCurrentState() +
                ", states=" + countdownContext.listStates() +
                '}';
    }
}
