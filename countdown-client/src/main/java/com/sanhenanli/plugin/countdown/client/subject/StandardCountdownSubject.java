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
        CountdownStateEnum currentState = countdownTimer.getCurrentState();
        CountdownResult result;
        if (currentState != CountdownStateEnum.INITED) {
            result = new CountdownResult(false, "illegal lifecycle");
        } else {
            // 操作倒计时
            result = countdownTimer.start();
        }
        if (result.isOk()) {
            currentState = CountdownStateEnum.RUNNING;
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
        CountdownStateEnum currentState = countdownTimer.getCurrentState();
        CountdownResult result;
        if (currentState != CountdownStateEnum.INITED && currentState != CountdownStateEnum.RUNNING && currentState != CountdownStateEnum.SUSPENDED) {
            result = new CountdownResult(false, "illegal lifecycle");
        } else {
            // 操作倒计时
            result = countdownTimer.cancel();
        }
        if (result.isOk()) {
            currentState = CountdownStateEnum.CANCELED;
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
        CountdownStateEnum currentState = countdownTimer.getCurrentState();
        CountdownResult result;
        if (currentState != CountdownStateEnum.RUNNING) {
            result = new CountdownResult(false, "illegal lifecycle");
        } else {
            // 操作倒计时
            result = countdownTimer.suspend();
        }
        if (result.isOk()) {
            currentState = CountdownStateEnum.SUSPENDED;
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
        CountdownStateEnum currentState = countdownTimer.getCurrentState();
        CountdownResult result;
        if (currentState != CountdownStateEnum.SUSPENDED) {
            result = new CountdownResult(false, "illegal lifecycle");
        } else {
            // 操作倒计时
            result = countdownTimer.resume();
        }
        if (result.isOk()) {
            currentState = CountdownStateEnum.RUNNING;
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
        CountdownStateEnum currentState = countdownTimer.getCurrentState();
        CountdownResult result;
        if (currentState != CountdownStateEnum.INITED && currentState != CountdownStateEnum.RUNNING && currentState != CountdownStateEnum.SUSPENDED) {
            result = new CountdownResult(false, "illegal lifecycle");
        } else {
            // 操作倒计时
            result = countdownTimer.reset(millis);
        }
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
