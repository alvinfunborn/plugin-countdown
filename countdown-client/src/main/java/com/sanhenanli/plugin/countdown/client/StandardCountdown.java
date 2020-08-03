package com.sanhenanli.plugin.countdown.client;

import com.sanhenanli.plugin.countdown.client.listener.CountdownListener;
import com.sanhenanli.plugin.countdown.client.model.CountdownResult;
import com.sanhenanli.plugin.countdown.client.model.CountdownState;
import com.sanhenanli.plugin.countdown.client.model.enums.CountdownActionEnum;
import com.sanhenanli.plugin.countdown.client.model.enums.CountdownStateEnum;

/**
 * datetime 2019/5/9 17:03
 *
 * @author sin5
 */
public class StandardCountdown implements Countdown {

    protected AbstractCountdownTimer countdownTimer;
    protected CountdownListener countdownListener;

    public StandardCountdown(AbstractCountdownTimer countdownTimer, CountdownListener countdownListener) {
        this.countdownTimer = countdownTimer;
        this.countdownListener = countdownListener;
        countdownListener.onInit(countdownTimer);
    }

    @Override
    public CountdownResult reset(long millis) {
        CountdownResult result = countdownTimer.reset(millis);
        CountdownStateEnum currentState = countdownTimer.getCurrentState();
        long currentCountdownMillis;
        if (result.isOk()) {
            currentCountdownMillis = millis;
            countdownListener.onReset(countdownTimer);
        } else {
            currentCountdownMillis = countdownTimer.getCurrentMillis();
        }
        countdownTimer.appendCountdownState(new CountdownState(
                countdownTimer.getId(),
                currentCountdownMillis,
                CountdownActionEnum.RESET,
                result,
                currentState,
                System.currentTimeMillis()
        ));
        return result;
    }

    @Override
    public CountdownResult start() {
        CountdownResult result = countdownTimer.start();
        CountdownStateEnum currentState;
        if (result.isOk()) {
            currentState = CountdownStateEnum.RUNNING;
            countdownListener.onStart(countdownTimer);
        } else {
            currentState = countdownTimer.getCurrentState();
        }
        countdownTimer.appendCountdownState(new CountdownState(
                countdownTimer.getId(),
                countdownTimer.getCurrentMillis(),
                CountdownActionEnum.START,
                result,
                currentState,
                System.currentTimeMillis()
        ));
        return result;
    }

    @Override
    public CountdownResult cancel() {
        CountdownResult result = countdownTimer.cancel();
        CountdownStateEnum currentState;
        if (result.isOk()) {
            currentState = CountdownStateEnum.CANCELED;
            countdownListener.onCancel(countdownTimer);
        } else {
            currentState = countdownTimer.getCurrentState();
        }
        countdownTimer.appendCountdownState(new CountdownState(
                countdownTimer.getId(),
                countdownTimer.getCurrentMillis(),
                CountdownActionEnum.CANCEL,
                result,
                currentState,
                System.currentTimeMillis()
        ));
        return result;
    }

    @Override
    public CountdownResult suspend() {
        CountdownResult result = countdownTimer.suspend();
        CountdownStateEnum currentState;
        if (result.isOk()) {
            currentState = CountdownStateEnum.SUSPENDED;
            countdownListener.onSuspend(countdownTimer);
        } else {
            currentState = countdownTimer.getCurrentState();
        }
        countdownTimer.appendCountdownState(new CountdownState(
                countdownTimer.getId(),
                countdownTimer.getCurrentMillis(),
                CountdownActionEnum.SUSPEND,
                result,
                currentState,
                System.currentTimeMillis()
        ));
        return result;
    }

    @Override
    public CountdownResult resume() {
        CountdownResult result = countdownTimer.resume();
        CountdownStateEnum currentState;
        if (result.isOk()) {
            currentState = CountdownStateEnum.RUNNING;
            countdownListener.onResume(countdownTimer);
        } else {
            currentState = countdownTimer.getCurrentState();
        }
        countdownTimer.appendCountdownState(new CountdownState(
                countdownTimer.getId(),
                countdownTimer.getCurrentMillis(),
                CountdownActionEnum.RESUME,
                result,
                currentState,
                System.currentTimeMillis()
        ));
        return result;
    }

    @Override
    public String log() {
        return countdownTimer.log();
    }
}