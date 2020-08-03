package com.sanhenanli.plugin.countdown.client;

import com.sanhenanli.plugin.countdown.client.model.CountdownState;
import com.sanhenanli.plugin.countdown.client.model.enums.CountdownStateEnum;

import java.util.List;

/**
 * datetime 2020/8/3 16:53
 *
 * @author zhouwenxiang
 */
public class InMemoryCountdownContext implements CountdownContext {

    protected List<CountdownState> states;

    @Override
    public long getCurrentMillis() {
        CountdownState currentState = states.get(0);
        for (int i = states.size() - 1; i >= 0; i--) {
            if (states.get(i).getActionResult().isOk()) {
                currentState = states.get(i);
                break;
            }
        }
        switch (currentState.getState()) {
            case STOPPED:
            case CANCELED:
            case SUSPENDED:
                return currentState.getMillis();
            case RUNNING:
                return currentState.getMillis() - System.currentTimeMillis() + currentState.getTimestamp();
            default: return 0;
        }
    }

    /**
     * 获取倒计时钟当前的状态
     * @return 当前状态
     */
    @Override
    public CountdownStateEnum getCurrentState() {
        return this.states.get(this.states.size() - 1).getState();
    }

    /**
     * 追加倒计时器经历的状态
     * @param state 状态
     */
    @Override
    public void appendCountdownState(CountdownState state) {
        this.states.add(state);
    }

    @Override
    public List<CountdownState> listStates() {
        return this.states;
    }
}
