package com.sanhenanli.plugin.countdown.redis.subject;

import com.sanhenanli.plugin.countdown.client.AbstractCountdownTimer;
import com.sanhenanli.plugin.countdown.persist.jpa.factory.AbstractDbCountdownSubjectFactory;
import com.sanhenanli.plugin.countdown.persist.jpa.factory.DbCountdownContextFactory;
import com.sanhenanli.plugin.countdown.redis.config.DistributedDelayedQueue;
import com.sanhenanli.plugin.countdown.redis.timer.DDQCountdownTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * datetime 2020/8/18 11:35
 * 分布式延时队列倒计时subject工厂
 *
 * @author zhouwenxiang
 */
@Service
public class DDQCountdownSubjectFactory extends AbstractDbCountdownSubjectFactory {

    @Autowired
    private DbCountdownContextFactory countdownContextFactory;
    @Autowired
    private DistributedDelayedQueue distributedDelayedQueue;

    @Override
    protected AbstractCountdownTimer rebuildTimer(String name, long millis) {
        return new DDQCountdownTimer(name, millis, countdownContextFactory.getByName(name), distributedDelayedQueue);
    }
}
