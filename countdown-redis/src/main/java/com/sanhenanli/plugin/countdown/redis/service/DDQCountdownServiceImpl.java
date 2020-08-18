package com.sanhenanli.plugin.countdown.redis.service;

import com.sanhenanli.plugin.countdown.client.factory.CountdownObserverFactory;
import com.sanhenanli.plugin.countdown.client.factory.CountdownSubjectFactory;
import com.sanhenanli.plugin.countdown.client.model.CountdownResult;
import com.sanhenanli.plugin.countdown.client.observer.AbstractCountdownObserver;
import com.sanhenanli.plugin.countdown.client.service.AbstractCountdownService;
import com.sanhenanli.plugin.countdown.client.subject.AbstractCountdownSubject;
import com.sanhenanli.plugin.countdown.persist.jpa.factory.DbCountdownContextFactory;
import com.sanhenanli.plugin.countdown.persist.jpa.repository.CountdownObserversRepository;
import com.sanhenanli.plugin.countdown.persist.jpa.subject.DbStandardCountdownSubject;
import com.sanhenanli.plugin.countdown.redis.config.DistributedDelayedQueue;
import com.sanhenanli.plugin.countdown.redis.timer.DDQCountdownTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * datetime 2020/8/18 11:45
 * 分布式延时队列倒计时服务实现
 *
 * @author zhouwenxiang
 */
@Service
public class DDQCountdownServiceImpl extends AbstractCountdownService {

    @Autowired
    private DbCountdownContextFactory countdownContextFactory;
    @Autowired
    private DistributedDelayedQueue distributedDelayedQueue;
    @Autowired
    private CountdownObserverFactory countdownObserverFactory;
    @Autowired
    private CountdownObserversRepository countdownObserversRepository;

    @Autowired
    public DDQCountdownServiceImpl(CountdownSubjectFactory subjectFactory) {
        super(subjectFactory);
    }

    @Override
    public AbstractCountdownSubject create(String name, long millis, List<AbstractCountdownObserver> observers) throws Exception {
        if (subjectFactory.getByName(name) != null) {
            throw new Exception("countdown name existed");
        }
        // 创建subject
        AbstractCountdownSubject subject = new DbStandardCountdownSubject(new DDQCountdownTimer(name, millis, countdownContextFactory.getByName(name), distributedDelayedQueue), countdownObserversRepository, countdownObserverFactory);
        observers.forEach(subject::attach);
        // 注册subject
        CountdownResult result = subjectFactory.register(subject);
        if (result.isOk()) {
            return subject;
        }
        throw new Exception(result.getMsg());
    }
}
