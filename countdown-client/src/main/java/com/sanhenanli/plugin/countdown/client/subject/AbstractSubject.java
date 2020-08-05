package com.sanhenanli.plugin.countdown.client.subject;

import com.sanhenanli.plugin.countdown.client.observer.AbstractObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * datetime 2020/8/4 9:59
 * subject
 *
 * @author zhouwenxiang
 */
public abstract class AbstractSubject {

    /**
     * 观察者列表(内存模式, 云subject重建后一定要添加好已绑定的观察者)
     */
    protected List<AbstractObserver> observers = new ArrayList<>(2);

    /**
     * 绑定观察者
     * @param observer 观察者
     */
    public void attach(AbstractObserver observer) {
        observers.add(observer);
    }

    /**
     * 解绑观察者
     * @param observer 观察者
     */
    public void detach(AbstractObserver observer) {
        observers.remove(observer);
    }

    /**
     * 通知所有观察者
     */
    protected abstract void notifyObservers();
}
