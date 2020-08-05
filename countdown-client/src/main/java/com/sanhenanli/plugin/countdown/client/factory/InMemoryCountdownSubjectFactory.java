package com.sanhenanli.plugin.countdown.client.factory;

import com.sanhenanli.plugin.countdown.client.model.CountdownResult;
import com.sanhenanli.plugin.countdown.client.subject.AbstractCountdownSubject;

import java.util.HashMap;
import java.util.Map;

/**
 * datetime 2020/8/3 17:20
 * 简单工厂的内存实现
 *
 * @author zhouwenxiang
 */
public class InMemoryCountdownSubjectFactory implements CountdownSubjectFactory {

    private Map<String, AbstractCountdownSubject> map = new HashMap<>(4);

    @Override
    public AbstractCountdownSubject getByName(String name) {
        return map.get(name);
    }

    @Override
    public CountdownResult register(AbstractCountdownSubject subject) {
        map.put(subject.getName(), subject);
        return subject.init();
    }
}
