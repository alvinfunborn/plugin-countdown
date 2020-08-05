package com.sanhenanli.plugin.countdown.client.model;

import com.sanhenanli.plugin.countdown.client.model.enums.CountdownActionEnum;
import com.sanhenanli.plugin.countdown.client.model.enums.CountdownStateEnum;

/**
 * datetime 2020/7/31 15:13
 * 倒计时经历的状态
 *
 * @author zhouwenxiang
 */
public class CountdownState {

    protected String name;
    /**
     * 最近一次记录的倒计时剩余时间
     */
    protected long millis;
    /**
     * 经历的操作
     */
    protected CountdownActionEnum action;
    /**
     * 操作结果
     */
    protected CountdownResult actionResult;
    /**
     * 当前状态
     */
    protected CountdownStateEnum state;
    /**
     * 经历变更的时间戳
     */
    protected long timestamp;

    public CountdownState(String name, long millis, CountdownActionEnum action, CountdownResult actionResult, CountdownStateEnum state, long timestamp) {
        this.name = name;
        this.millis = millis;
        this.action = action;
        this.actionResult = actionResult;
        this.state = state;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public long getMillis() {
        return millis;
    }

    public CountdownActionEnum getAction() {
        return action;
    }

    public CountdownResult getActionResult() {
        return actionResult;
    }

    public CountdownStateEnum getState() {
        return state;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "CountdownState{" +
                "id='" + name + '\'' +
                ", millis=" + millis +
                ", action=" + action +
                ", actionResult=" + actionResult +
                ", state=" + state +
                ", timestamp=" + timestamp +
                '}';
    }
}
