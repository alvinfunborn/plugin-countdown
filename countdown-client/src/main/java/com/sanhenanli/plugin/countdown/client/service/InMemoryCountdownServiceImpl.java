package com.sanhenanli.plugin.countdown.client.service;

import com.sanhenanli.plugin.countdown.client.Countdown;

import java.util.HashMap;
import java.util.Map;

/**
 * datetime 2020/8/3 17:20
 *
 * @author zhouwenxiang
 */
public class InMemoryCountdownServiceImpl extends AbstractCountdownService {

    private Map<String, Countdown> countdownMap = new HashMap<>(4);

    @Override
    public void registerCountdown(Countdown countdown) {
        countdownMap.put(countdown.getId(), countdown);
    }

    @Override
    public Countdown getById(String id) {
        return countdownMap.get(id);
    }
}
