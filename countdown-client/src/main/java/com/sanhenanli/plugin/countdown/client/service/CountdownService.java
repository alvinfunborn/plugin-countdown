package com.sanhenanli.plugin.countdown.client.service;

import com.sanhenanli.plugin.countdown.client.Countdown;
import com.sanhenanli.plugin.countdown.client.model.CountdownResult;

/**
 * datetime 2020/8/3 17:12
 *
 * @author zhouwenxiang
 */
public interface CountdownService {

    /**
     * 注册一个倒计时
     * @param countdown 倒计时
     */
    void registerCountdown(Countdown countdown);

    /**
     * 根据id取回倒计时器
     * @param id id
     * @return 倒计时
     */
    Countdown getById(String id);

    /**
     * 开启倒计时
     * @return 操作结果
     */
    CountdownResult start(String id);

    /**
     * 取消倒计时
     * @return 操作结果
     */
    CountdownResult cancel(String id);

    /**
     * 暂停倒计时
     * @return 操作结果
     */
    CountdownResult suspend(String id);

    /**
     * 继续倒计时
     * @return 操作结果
     */
    CountdownResult resume(String id);

    /**
     * 重置倒计时时间
     * @param millis 毫秒时间
     * @return 操作结果
     */
    CountdownResult reset(String id, long millis);

    /**
     * 获取倒计时日志
     * @return 日志
     */
    String log(String id);
}
