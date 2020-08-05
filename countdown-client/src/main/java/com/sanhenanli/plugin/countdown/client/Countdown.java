package com.sanhenanli.plugin.countdown.client;

import com.sanhenanli.plugin.countdown.client.model.CountdownResult;

/**
 * datetime 2020/7/31 17:33
 * 倒计时
 *
 * @author zhouwenxiang
 */
public interface Countdown {

    /**
     * 初始化倒计时
     * @return 操作结果
     */
    CountdownResult init();

    /**
     * 开启倒计时
     * @return 操作结果
     */
    CountdownResult start();

    /**
     * 取消倒计时
     * @return 操作结果
     */
    CountdownResult cancel();

    /**
     * 暂停倒计时
     * @return 操作结果
     */
    CountdownResult suspend();

    /**
     * 继续倒计时
     * @return 操作结果
     */
    CountdownResult resume();

    /**
     * 重置倒计时时间
     * @param millis 毫秒时间
     * @return 操作结果
     */
    CountdownResult reset(long millis);

    /**
     * 获取倒计时日志
     * @return 日志
     */
    String log();
}
