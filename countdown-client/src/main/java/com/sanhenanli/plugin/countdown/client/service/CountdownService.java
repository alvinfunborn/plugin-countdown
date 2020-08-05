package com.sanhenanli.plugin.countdown.client.service;

import com.sanhenanli.plugin.countdown.client.model.CountdownResult;
import com.sanhenanli.plugin.countdown.client.observer.AbstractCountdownObserver;
import com.sanhenanli.plugin.countdown.client.subject.AbstractCountdownSubject;

import java.util.List;

/**
 * datetime 2020/8/3 17:12
 * 倒计时服务
 *
 * @author zhouwenxiang
 */
public interface CountdownService {

    /**
     * 创建倒计时
     * @param name 倒计时名称
     * @param millis 毫秒数
     * @param observers 初始观察者列表
     * @return 倒计时subject
     * @throws Exception 创建失败
     */
    AbstractCountdownSubject create(String name, long millis, List<AbstractCountdownObserver> observers) throws Exception;

    /**
     * 开启倒计时
     * @return 操作结果
     */
    CountdownResult start(String name);

    /**
     * 取消倒计时
     * @return 操作结果
     */
    CountdownResult cancel(String name);

    /**
     * 暂停倒计时
     * @return 操作结果
     */
    CountdownResult suspend(String name);

    /**
     * 继续倒计时
     * @return 操作结果
     */
    CountdownResult resume(String name);

    /**
     * 重置倒计时时间
     * @param millis 毫秒时间
     * @return 操作结果
     */
    CountdownResult reset(String name, long millis);

    /**
     * 获取倒计时日志
     * @return 日志
     */
    String log(String name);
}
