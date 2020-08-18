package com.sanhenanli.plugin.countdown.persist.jpa.factory;

import com.sanhenanli.plugin.countdown.client.factory.CountdownObserverFactory;
import com.sanhenanli.plugin.countdown.client.observer.AbstractCountdownObserver;
import com.sanhenanli.plugin.countdown.persist.jpa.observer.TestCountdownObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * datetime 2020/8/4 16:18
 * 倒计时观察者工厂
 *
 * @author zhouwenxiang
 */
@Service
public class DefaultCountdownObserverFactory implements CountdownObserverFactory {

    @Bean
    public TestCountdownObserver testCountdownObserver() {
        return new TestCountdownObserver("test");
    }

    @Autowired
    private TestCountdownObserver testCountdownObserver;

    @Override
    public AbstractCountdownObserver getByName(String name) {
        switch (name) {
            case "test": return testCountdownObserver;
            default: return null;
        }
    }
}
