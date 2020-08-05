package com.sanhenanli.plugin.countdown.client.subject;

import com.sanhenanli.plugin.countdown.client.AbstractCountdownTimer;
import com.sanhenanli.plugin.countdown.client.model.CountdownResult;
import com.sanhenanli.plugin.countdown.client.model.CountdownState;
import com.sanhenanli.plugin.countdown.client.model.enums.CountdownActionEnum;
import com.sanhenanli.plugin.countdown.client.model.enums.CountdownStateEnum;

import java.util.List;

/**
 * datetime 2020/8/4 11:05
 * 标准倒计时subject
 *
 * @author zhouwenxiang
 */
public class StandardCountdownSubject extends AbstractCountdownSubject {

    public StandardCountdownSubject(AbstractCountdownTimer countdownTimer) {
        super(countdownTimer);
    }

    @Override
    public CountdownResult init() {
        // 操作倒计时
        CountdownResult result = countdownTimer.init();
        CountdownStateEnum currentState;
        if (result.isOk()) {
            currentState = CountdownStateEnum.INITED;
        } else {
            currentState = CountdownStateEnum.DEFINED;
        }
        // 记录状态
        countdownTimer.appendCountdownState(new CountdownState(
                countdownTimer.getName(),
                countdownTimer.getCountdownMillis(),
                CountdownActionEnum.INIT,
                result,
                currentState,
                System.currentTimeMillis()
        ));
        // 通知观察者
        notifyObservers();
        return result;
    }

    @Override
    public CountdownResult start() {
        // 操作倒计时
        CountdownResult result = countdownTimer.start();
        CountdownStateEnum currentState;
        if (result.isOk()) {
            currentState = CountdownStateEnum.RUNNING;
        } else {
            currentState = countdownTimer.getCurrentState();
        }
        // 记录状态
        countdownTimer.appendCountdownState(new CountdownState(
                countdownTimer.getName(),
                countdownTimer.getCountdownMillis(),
                CountdownActionEnum.START,
                result,
                currentState,
                System.currentTimeMillis()
        ));
        // 通知观察者
        notifyObservers();
        return result;
    }

    @Override
    public CountdownResult cancel() {
        // 操作倒计时
        CountdownResult result = countdownTimer.cancel();
        CountdownStateEnum currentState;
        if (result.isOk()) {
            currentState = CountdownStateEnum.CANCELED;
        } else {
            currentState = countdownTimer.getCurrentState();
        }
        // 记录状态
        countdownTimer.appendCountdownState(new CountdownState(
                countdownTimer.getName(),
                countdownTimer.getCurrentMillis(),
                CountdownActionEnum.CANCEL,
                result,
                currentState,
                System.currentTimeMillis()
        ));
        // 通知观察者
        notifyObservers();
        return result;
    }

    @Override
    public CountdownResult suspend() {
        // 操作倒计时
        CountdownResult result = countdownTimer.suspend();
        CountdownStateEnum currentState;
        if (result.isOk()) {
            currentState = CountdownStateEnum.SUSPENDED;
        } else {
            currentState = countdownTimer.getCurrentState();
        }
        // 记录状态
        countdownTimer.appendCountdownState(new CountdownState(
                countdownTimer.getName(),
                countdownTimer.getCurrentMillis(),
                CountdownActionEnum.SUSPEND,
                result,
                currentState,
                System.currentTimeMillis()
        ));
        // 通知观察者
        notifyObservers();
        return result;
    }

    @Override
    public CountdownResult resume() {
        // 操作倒计时
        CountdownResult result = countdownTimer.resume();
        CountdownStateEnum currentState;
        if (result.isOk()) {
            currentState = CountdownStateEnum.RUNNING;
        } else {
            currentState = countdownTimer.getCurrentState();
        }
        // 记录状态
        countdownTimer.appendCountdownState(new CountdownState(
                countdownTimer.getName(),
                countdownTimer.getCurrentMillis(),
                CountdownActionEnum.RESUME,
                result,
                currentState,
                System.currentTimeMillis()
        ));
        // 通知观察者
        notifyObservers();
        return result;
    }

    @Override
    public CountdownResult reset(long millis) {
        // 操作倒计时
        CountdownResult result = countdownTimer.reset(millis);
        CountdownStateEnum currentState = countdownTimer.getCurrentState();
        long currentCountdownMillis;
        if (result.isOk()) {
            currentCountdownMillis = millis;
        } else {
            currentCountdownMillis = countdownTimer.getCurrentMillis();
        }
        // 记录状态
        countdownTimer.appendCountdownState(new CountdownState(
                countdownTimer.getName(),
                currentCountdownMillis,
                CountdownActionEnum.RESET,
                result,
                currentState,
                System.currentTimeMillis()
        ));
        // 通知观察者
        notifyObservers();
        return result;
    }

    @Override
    public String log() {
        return countdownTimer.log();
    }

    @Override
    public long getCurrentMillis() {
        return countdownTimer.getCurrentMillis();
    }

    @Override
    public CountdownStateEnum getCurrentState() {
        return countdownTimer.getCurrentState();
    }

    @Override
    public void appendCountdownState(CountdownState state) {
        countdownTimer.appendCountdownState(state);
    }

    @Override
    public List<CountdownState> listStates() {
        return countdownTimer.listStates();
    }
}
