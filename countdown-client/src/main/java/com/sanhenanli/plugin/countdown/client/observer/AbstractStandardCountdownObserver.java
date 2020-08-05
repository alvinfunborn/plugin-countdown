package com.sanhenanli.plugin.countdown.client.observer;

import com.sanhenanli.plugin.countdown.client.AbstractCountdownTimer;
import com.sanhenanli.plugin.countdown.client.model.CountdownState;
import com.sanhenanli.plugin.countdown.client.subject.AbstractCountdownSubject;
import com.sanhenanli.plugin.countdown.client.subject.AbstractSubject;

import java.util.List;

/**
 * datetime 2020/8/4 11:13
 * 标准倒计时观察者, 处理倒计时所有状态
 *
 * @author zhouwenxiang
 */
public abstract class AbstractStandardCountdownObserver extends AbstractCountdownObserver {

    public AbstractStandardCountdownObserver(String name) {
        super(name);
    }

    @Override
    public void update(AbstractSubject subject) {
        AbstractCountdownSubject countdownSubject = (AbstractCountdownSubject) subject;
        AbstractCountdownTimer timer = countdownSubject.getCountdownTimer();
        List<CountdownState> states = timer.listStates();
        CountdownState lastState = states.get(states.size() - 1);
        switch (lastState.getAction()) {
            case INIT: {
                if (lastState.getActionResult().isOk()) {
                    this.onInit(timer);
                }
            } break;
            case START: {
                if (lastState.getActionResult().isOk()) {
                    this.onStart(timer);
                }
            } break;
            case CANCEL: {
                if (lastState.getActionResult().isOk()) {
                    this.onCancel(timer);
                }
            } break;
            case SUSPEND: {
                if (lastState.getActionResult().isOk()) {
                    this.onSuspend(timer);
                }
            } break;
            case RESUME: {
                if (lastState.getActionResult().isOk()) {
                    this.onResume(timer);
                }
            } break;
            case RESET: {
                if (lastState.getActionResult().isOk()) {
                    this.onReset(timer);
                }
            } break;
            case STOP: {
                this.onStop(timer);
            } break;
            default: break;
        }
    }
}
