package com.sanhenanli.plugin.countdown.powerjob.countdown;

import com.sanhenanli.plugin.countdown.client.StandardCountdown;
import com.sanhenanli.plugin.countdown.powerjob.listener.PowerJobCountdownListener;
import com.sanhenanli.plugin.countdown.powerjob.timer.PowerJobCountdownTimer;

/**
 * datetime 2020/8/3 13:40
 *
 * @author zhouwenxiang
 */
public class PowerJobCountdown extends StandardCountdown {

    public PowerJobCountdown(PowerJobCountdownTimer countdownTimer, PowerJobCountdownListener countdownListener) {
        super(countdownTimer, countdownListener);
    }
}
