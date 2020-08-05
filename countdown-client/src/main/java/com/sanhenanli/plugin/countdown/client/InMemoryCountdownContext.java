package com.sanhenanli.plugin.countdown.client;

import com.sanhenanli.plugin.countdown.client.model.CountdownState;
import com.sanhenanli.plugin.countdown.client.model.enums.CountdownStateEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * datetime 2020/8/3 16:53
 * 倒计时上下文的内存实现
 *
 * @author zhouwenxiang
 */
public class InMemoryCountdownContext extends AbstractCountdownContext {

    /**
     * 状态列表
     */
    protected List<CountdownState> states = new ArrayList<>(2);

    public InMemoryCountdownContext(String name) {
        super(name);
    }

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

    @Override
    public CountdownStateEnum getCurrentState() {
        return this.states.get(this.states.size() - 1).getState();
    }

    @Override
    public void appendCountdownState(CountdownState state) {
        this.states.add(state);
    }

    @Override
    public List<CountdownState> listStates() {
        return this.states;
    }
}
