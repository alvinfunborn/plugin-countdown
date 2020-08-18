package com.sanhenanli.plugin.countdown.redis.service;

import com.sanhenanli.plugin.countdown.CountdownApplicationTest;
import com.sanhenanli.plugin.countdown.client.subject.AbstractCountdownSubject;
import com.sanhenanli.plugin.countdown.persist.jpa.observer.TestCountdownObserver;
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
    private TestCountdownObserver testCountdownObserver;

    @Test
    public void test1() {
        try {
            String name = "redisson-countdown-test-5";
            AbstractCountdownSubject countdown = countdownService.create(name, 30000, Collections.singletonList(testCountdownObserver));
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