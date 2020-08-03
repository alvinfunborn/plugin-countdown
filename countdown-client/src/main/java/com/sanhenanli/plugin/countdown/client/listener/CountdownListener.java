package com.sanhenanli.plugin.countdown.client.listener;

import com.sanhenanli.plugin.countdown.client.CountdownTimer;

/**
 * datetime 2020/7/31 15:22
 * 倒计时事件处理器
 *
 * @author zhouwenxiang
 */
public interface CountdownListener {

    /**
     * 初始化倒计时器
     * @param timer 倒数计时器
     */
    void onInit(CountdownTimer timer);

    /**
     * 重置倒计时器, 置为STOPPED状态
     * @param timer 倒数计时器
     */
    void onReset(CountdownTimer timer);

    /**
     * 开启倒计时
     * @param timer 倒数计时器
     */
    void onStart(CountdownTimer timer);

    /**
     * 暂停倒计时
     * @param timer 倒数计时器
     */
    void onSuspend(CountdownTimer timer);

    /**
     * 继续倒计时
     * @param timer 倒数计时器
     */
    void onResume(CountdownTimer timer);

    /**
     * 取消倒计时
     * @param timer 倒数计时器
     */
    void onCancel(CountdownTimer timer);

    /**
     * 倒计时结束
     * @param timer 倒数计时器
     */
    void onStop(CountdownTimer timer);
}
