package com.sanhenanli.plugin.countdown.client.observer;

import com.sanhenanli.plugin.countdown.client.subject.AbstractSubject;

/**
 * datetime 2020/8/4 10:02
 * 观察者
 *
 * @author zhouwenxiang
 */
public interface Observer {

    /**
     * 更新subject
     * @param subject subject
     */
    void update(AbstractSubject subject);

    /**
     * 获取观察者的name
     * @return name
     */
    String getName();

}
