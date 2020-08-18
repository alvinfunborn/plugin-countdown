package com.sanhenanli.plugin.countdown.redis.service;

import com.sanhenanli.plugin.countdown.CountdownApplicationTest;
import com.sanhenanli.plugin.countdown.client.factory.CountdownObserverFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

/**
 * datetime 2020/8/18 11:54
 *
 * @author zhouwenxiang
 */
public class DDQCountdownServiceImplTest extends CountdownApplicationTest {

    @Autowired
    private DDQCountdownServiceImpl countdownService;
    @Autowired
    private CountdownObserverFactory countdownObserverFactory;

    @Test
    public void test1() {
        try {
            String name = "redisson-countdown-test-9";
            // 创建该name的倒计时subject, 并依附上test观察者
            countdownService.create(name, 30000, Collections.singletonList(countdownObserverFactory.getByName("test")));
            countdownService.start(name);
            Thread.sleep(2000);
            countdownService.suspend(name);
            Thread.sleep(1000);
            countdownService.resume(name);
            Thread.sleep(2000);
            countdownService.resume(name);
            Thread.sleep(3000);
            countdownService.suspend(name);
            Thread.sleep(2000);
            countdownService.reset(name, 18000);
            Thread.sleep(2000);
            countdownService.resume(name);
            Thread.sleep(3000);
            Thread.sleep(20000);
            System.out.println(countdownService.log(name));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}