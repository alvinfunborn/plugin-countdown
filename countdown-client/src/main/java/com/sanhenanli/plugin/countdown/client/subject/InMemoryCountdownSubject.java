package com.sanhenanli.plugin.countdown.client.subject;

import com.sanhenanli.plugin.countdown.client.AbstractInMemoryCountdownTimer;
import com.sanhenanli.plugin.countdown.client.model.CountdownResult;
import com.sanhenanli.plugin.countdown.client.model.CountdownState;
import com.sanhenanli.plugin.countdown.client.model.enums.CountdownActionEnum;
import com.sanhenanli.plugin.countdown.client.model.enums.CountdownStateEnum;

/**
 * datetime 2020/8/12 14:56
 * 单机倒计时subject
 *
 * @author zhouwenxiang
 */
public class InMemoryCountdownSubject extends StandardCountdownSubject {

    public InMemoryCountdownSubject(AbstractInMemoryCountdownTimer countdownTimer) {
        super(countdownTimer);
    }

    /**
     * 停止(非主动, 倒计时结束)
     */
    public void stop() {
        // 记录状态
        countdownTimer.appendCountdownState(new CountdownState(
                countdownTimer.getName(),
                countdownTimer.getCountdownMillis(),
                CountdownActionEnum.STOP,
                new CountdownResult(true),
                CountdownStateEnum.STOPPED,
                System.currentTimeMillis()
        ));
        // 通知观察者
        notifyObservers();
    }
}
