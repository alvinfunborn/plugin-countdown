package com.sanhenanli.plugin.countdown.client.subject;

import com.sanhenanli.plugin.countdown.client.InMemoryCountdownTimer;
import com.sanhenanli.plugin.countdown.client.model.CountdownResult;
import com.sanhenanli.plugin.countdown.client.model.CountdownState;
import com.sanhenanli.plugin.countdown.client.model.enums.CountdownActionEnum;
import com.sanhenanli.plugin.countdown.client.model.enums.CountdownStateEnum;

/**
 * datetime 2020/8/12 14:56
 * 单机倒计时subject, 轮询观察倒计时状态
 *
 * @author zhouwenxiang
 */
public class InMemoryCountdownSubject extends StandardCountdownSubject {

    /**
     * 轮询间隔/ms
     */
    protected long pollingGap = 100;

    public InMemoryCountdownSubject(String name, long millis, long pollingGap) {
        super(new InMemoryCountdownTimer(name, millis));
        this.pollingGap = pollingGap;
    }

    @Override
    public CountdownResult start() {
        CountdownResult result = super.start();
        if (result.isOk()) {
            new Thread(this::pollingCountdown).start();
        }
        return result;
    }

    /**
     * 停止(非主动, 倒计时结束)
     */
    private void stop() {
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

    /**
     * 轮询倒计时的结束状态
     */
    private void pollingCountdown() {
        while (true) {
            if (((InMemoryCountdownTimer) this.countdownTimer).isStopped()) {
                stop();
                break;
            }
            try {
                Thread.sleep(pollingGap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
