package com.sanhenanli.plugin.countdown.client;

import com.sanhenanli.plugin.countdown.client.model.CountdownResult;
import com.sanhenanli.plugin.countdown.client.model.CountdownState;
import com.sanhenanli.plugin.countdown.client.model.enums.CountdownActionEnum;
import com.sanhenanli.plugin.countdown.client.model.enums.CountdownStateEnum;

import java.util.List;

/**
 * datetime 2020/8/3 16:33
 *
 * @author zhouwenxiang
 */
public abstract class AbstractInMemoryCountdownTimer extends AbstractCountdownTimer {

    protected CountdownContext countdownContext;

    public AbstractInMemoryCountdownTimer(String id, long countdownMills) {
        super(id, countdownMills);
        this.countdownContext = new InMemoryCountdownContext();
        countdownContext.appendCountdownState(new CountdownState(id, countdownMills, CountdownActionEnum.RESET, new CountdownResult(true), CountdownStateEnum.STOPPED, System.currentTimeMillis()));
    }

    @Override
    public String getId() {
        return id;
    }

    /**
     * 获取倒计时钟当前剩余的时间
     */
    @Override
    public long getCurrentMillis() {
        return countdownContext.getCurrentMillis();
    }

    /**
     * 获取倒计时钟当前的状态
     * @return 当前状态
     */
    @Override
    public CountdownStateEnum getCurrentState() {
        return countdownContext.getCurrentState();
    }

    /**
     * 追加倒计时器经历的状态
     * @param state 状态
     */
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
