package com.sanhenanli.plugin.countdown.client.subject;

import com.sanhenanli.plugin.countdown.client.CountdownTimer;
import com.sanhenanli.plugin.countdown.client.observer.AbstractObserver;
import com.sanhenanli.plugin.countdown.client.observer.AbstractStandardCountdownObserver;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

/**
 * datetime 2020/8/5 17:52
 *
 * @author zhouwenxiang
 */
public class StandardCountdownSubjectTest {
    @Test
    public void test1() throws InterruptedException {
        AbstractObserver countdownObserver = new AbstractStandardCountdownObserver("listener-1") {

            @Override
            public void onInit(CountdownTimer timer) {
                System.out.println("init at " + LocalDateTime.now());
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
        StandardCountdownSubject countdown = new InMemoryPollingCountdownSubject("countdown-1", 10000, 100);
        countdown.attach(countdownObserver);
        countdown.init();

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
        Thread.sleep(5000);
    }

    @Test
    public void test2() throws InterruptedException {
        AbstractObserver countdownObserver = new AbstractStandardCountdownObserver("listener-1") {

            @Override
            public void onInit(CountdownTimer timer) {
                System.out.println("init at " + LocalDateTime.now());
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
        StandardCountdownSubject countdown = new InMemoryPollingCountdownSubject("countdown-1", 10000, 100);
        countdown.attach(countdownObserver);

        Thread t1 = new Thread(() -> {
            countdown.init();
        });
        Thread t2 = new Thread(() -> {
            countdown.start();
            System.out.println(countdown.log());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countdown.suspend();
            System.out.println(countdown.log());
        });
        Thread t3 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countdown.resume();
            System.out.println(countdown.log());
        });
        Thread t4 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countdown.suspend();
            System.out.println(countdown.log());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(countdown.log());
            countdown.reset(5000);
        });
        Thread t5 = new Thread(() -> {
            System.out.println(countdown.log());
            try {
                Thread.sleep(9000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countdown.resume();
        });

        t1.start();
        t1.join();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        Thread.sleep(12000);
    }
}
