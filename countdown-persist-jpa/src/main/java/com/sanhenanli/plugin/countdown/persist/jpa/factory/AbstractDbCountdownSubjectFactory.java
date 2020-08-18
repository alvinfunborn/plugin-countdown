package com.sanhenanli.plugin.countdown.persist.jpa.factory;

import com.sanhenanli.plugin.countdown.client.AbstractCountdownTimer;
import com.sanhenanli.plugin.countdown.client.factory.CountdownObserverFactory;
import com.sanhenanli.plugin.countdown.client.factory.CountdownSubjectFactory;
import com.sanhenanli.plugin.countdown.client.model.CountdownResult;
import com.sanhenanli.plugin.countdown.client.subject.AbstractCountdownSubject;
import com.sanhenanli.plugin.countdown.persist.jpa.model.entity.CountdownStatesEntity;
import com.sanhenanli.plugin.countdown.persist.jpa.repository.CountdownObserversRepository;
import com.sanhenanli.plugin.countdown.persist.jpa.repository.CountdownStatesRepository;
import com.sanhenanli.plugin.countdown.persist.jpa.subject.DbStandardCountdownSubject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * datetime 2020/8/4 14:10
 * 倒计时subject工厂
 *
 * @author zhouwenxiang
 */
public abstract class AbstractDbCountdownSubjectFactory implements CountdownSubjectFactory {

    @Autowired
    private CountdownStatesRepository countdownStatesDao;
    @Autowired
    private CountdownObserversRepository countdownObserversDao;
    @Autowired
    private CountdownObserverFactory countdownObserverFactory;

    @Override
    public CountdownResult register(AbstractCountdownSubject subject) {
        return subject.init();
    }

    @Override
    public AbstractCountdownSubject getByName(String name) {
        CountdownStatesEntity entity = countdownStatesDao.findFirstByNameOrderByIdAsc(name);
        if (entity == null) {
            return null;
        }
        // 重建subject
        DbStandardCountdownSubject subject = new DbStandardCountdownSubject(rebuildTimer(name, entity.getMillis()), countdownObserversDao, countdownObserverFactory);
        // 重建的subject必须添加上已绑定的观察者
        subject.retrieveObservers();
        return subject;
    }

    /**
     * 根据数据重现timer
     * @param name 名称
     * @param millis 初始时间
     * @return timer
     */
    protected abstract AbstractCountdownTimer rebuildTimer(String name, long millis);
}
