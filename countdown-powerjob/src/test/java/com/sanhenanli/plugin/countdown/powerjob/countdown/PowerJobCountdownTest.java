package com.sanhenanli.plugin.countdown.powerjob.countdown;

import com.github.kfcfans.powerjob.client.OhMyClient;
import com.sanhenanli.plugin.countdown.powerjob.CountdownPowerJobApplicationTest;
import com.sanhenanli.plugin.countdown.powerjob.listener.PowerJobCountdownListener;
import com.sanhenanli.plugin.countdown.powerjob.timer.CountdownContextDao;
import com.sanhenanli.plugin.countdown.powerjob.timer.PowerJobCountdownTimer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * datetime 2020/8/3 14:33
 *
 * @author zhouwenxiang
 */
public class PowerJobCountdownTest extends CountdownPowerJobApplicationTest {

    @Autowired
    private OhMyClient client;
    @Autowired
    private CountdownContextDao countdownContextDao;

    @Test
    public void test() {
        try {
            PowerJobCountdown countdown = new PowerJobCountdown(
                    new PowerJobCountdownTimer("countdown-1", 10000, countdownContextDao, client, "test_countdown", null, "com.sanhenanli.plugin.countdown.powerjob.task.TestTask"),
                    new PowerJobCountdownListener()
            );
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
            Thread.sleep(30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
