package com.sanhenanli.plugin.countdown.redis.observer;

import com.sanhenanli.plugin.countdown.client.factory.CountdownSubjectFactory;
import com.sanhenanli.plugin.countdown.client.model.CountdownResult;
import com.sanhenanli.plugin.countdown.client.model.CountdownState;
import com.sanhenanli.plugin.countdown.client.model.enums.CountdownActionEnum;
import com.sanhenanli.plugin.countdown.client.model.enums.CountdownStateEnum;
import com.sanhenanli.plugin.countdown.client.subject.AbstractCountdownSubject;
import com.sanhenanli.plugin.countdown.redis.config.DistributedDelayedQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * datetime 2020/8/18 9:17
 * 延时队列出列事件监听
 *
 * @author zhouwenxiang
 */
@Component
public class DelayedQueuePopEventListener {

    @Autowired
    private CountdownSubjectFactory countdownSubjectFactory;

    @EventListener
    public void listener(DistributedDelayedQueue.DelayedQueuePopEvent event) {
        String name = event.getObj();
        // 重建subject
        AbstractCountdownSubject subject = countdownSubjectFactory.getByName(name);
        subject.appendCountdownState(new CountdownState(name, 0, CountdownActionEnum.STOP, new CountdownResult(true), CountdownStateEnum.STOPPED, System.currentTimeMillis()));
        // 通知subject的所有观察者
        subject.notifyObservers();
    }
}
