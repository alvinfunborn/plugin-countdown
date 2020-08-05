package com.sanhenanli.plugin.countdown.client;

import com.sanhenanli.plugin.countdown.client.observer.AbstractObserver;
import com.sanhenanli.plugin.countdown.client.observer.AbstractPollingInMemoryCountdownObserver;
import com.sanhenanli.plugin.countdown.client.subject.StandardCountdownSubject;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

/**
 * datetime 2020/8/3 9:53
 *
 * @author zhouwenxiang
 */
public class StandardCountdownTest {

    @Test
    public void test1() throws InterruptedException {
        InMemoryCountdownTimer countdownTimer = new InMemoryCountdownTimer("countdown-1", 10000);
        AbstractObserver countdownObserver = new AbstractPollingInMemoryCountdownObserver("listener-1", 100) {

            @Override
            public void onInit(CountdownTimer timer) {
                System.out.println("init at " + LocalDateTime.now());
                new Thread(() -> pollingCountdown(countdownTimer)).start();
            }

            @Override
            public void onReset(CountdownTimer timer) {
                System.out.println("reset at " + LocalDateTime.now());
            }

            @Override
            public void onStart(CountdownTimer timer) {
                System.out.println("start at " + LocalDateTime.now());
            }

            @Override
            public void onSuspend(CountdownTimer timer) {
                System.out.println("suspend at " + LocalDateTime.now());
            }

            @Override
            public void onResume(CountdownTimer timer) {
                System.out.println("resume at " + LocalDateTime.now());
            }

            @Override
            public void onCancel(CountdownTimer timer) {
                System.out.println("cancel at " + LocalDateTime.now());
            }

            @Override
            public void onStop(CountdownTimer timer) {
                System.out.println("stop at " + LocalDateTime.now());
            }
        };
        StandardCountdownSubject countdown = new StandardCountdownSubject(countdownTimer);
        countdown.attach(countdownObserver);

        countdown.start();
        System.out.println(countdown.log());
        Thread.sleep(2000);
        countdown.suspend();
        System.out.println(countdown.log());
        Thread.sleep(1000);
        countdown.resume();
        System.out.println(countdown.log());
        Thread.sleep(2000);
        System.out.println(countdown.log());
        countdown.reset(15000);
        System.out.println(countdown.log());
        Thread.sleep(3000);
        countdown.suspend();
        System.out.println(countdown.log());
        Thread.sleep(2000);
        System.out.println(countdown.log());
        countdown.reset(5000);
        System.out.println(countdown.log());
        Thread.sleep(2000);
        countdown.resume();
        System.out.println(countdown.log());
        Thread.sleep(3000);
        System.out.println(countdown.log());
        Thread.sleep(3000);
    }

}