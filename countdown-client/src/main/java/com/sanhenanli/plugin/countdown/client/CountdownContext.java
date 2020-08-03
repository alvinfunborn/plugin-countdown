package com.sanhenanli.plugin.countdown.client;

import com.sanhenanli.plugin.countdown.client.model.CountdownState;
import com.sanhenanli.plugin.countdown.client.model.enums.CountdownStateEnum;

import java.util.List;

/**
 * datetime 2020/8/3 16:52
 *
 * @author zhouwenxiang
 */
public interface CountdownContext {

    /**
     * 获取当前剩余的倒计时时间
     * @return 毫秒
     */
    long getCurrentMillis();

    /**
     * 获取当前倒计时状态
     * @return 状态
     */
    CountdownStateEnum getCurrentState();

    /**
     * 记录新状态
     * @param state 新状态
     */
    void appendCountdownState(CountdownState state);

    /**
     * 列出历史状态
     * @return 历史状态列表
     */
    List<CountdownState> listStates();
}
