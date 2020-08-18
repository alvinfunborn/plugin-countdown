package com.sanhenanli.plugin.countdown.persist.jpa.subject;

import com.sanhenanli.plugin.countdown.client.AbstractCountdownTimer;
import com.sanhenanli.plugin.countdown.client.factory.CountdownObserverFactory;
import com.sanhenanli.plugin.countdown.client.observer.AbstractObserver;
import com.sanhenanli.plugin.countdown.client.subject.StandardCountdownSubject;
import com.sanhenanli.plugin.countdown.persist.jpa.model.entity.CountdownObserversEntity;
import com.sanhenanli.plugin.countdown.persist.jpa.model.enums.BasicStatusEnum;
import com.sanhenanli.plugin.countdown.persist.jpa.repository.CountdownObserversRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * datetime 2020/8/4 15:05
 * 倒计时subject
 *
 * @author zhouwenxiang
 */
public class DbStandardCountdownSubject extends StandardCountdownSubject {

    private CountdownObserversRepository countdownObserversRepository;
    private CountdownObserverFactory countdownObserverFactory;

    public DbStandardCountdownSubject(AbstractCountdownTimer countdownTimer, CountdownObserversRepository countdownObserversRepository, CountdownObserverFactory countdownObserverFactory) {
        super(countdownTimer);
        this.countdownObserversRepository = countdownObserversRepository;
        this.countdownObserverFactory = countdownObserverFactory;
    }

    @Override
    public long getCurrentMillis() {
        return super.getCurrentMillis();
    }

    /**
     * 重写attach方法, 记录观察者入库, 便于根据name重建subject
     * @param observer 观察者
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void attach(AbstractObserver observer) {
        super.attach(observer);
        CountdownObserversEntity entity = countdownObserversRepository.findByNameAndObserver(getName(), observer.getName());
        if (entity == null) {
            entity = new CountdownObserversEntity();
            entity.setName(getName());
            entity.setObserver(observer.getName());
            entity.setStatus(BasicStatusEnum.ENABLED.getCode());
            countdownObserversRepository.save(entity);
        } else if (entity.getStatus() != BasicStatusEnum.ENABLED.getCode()) {
            entity.setStatus(BasicStatusEnum.ENABLED.getCode());
            countdownObserversRepository.save(entity);
        }
    }

    /**
     * 重写detach方法, 记录观察者入库, 便于根据name重建subject
     * @param observer 观察者
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void detach(AbstractObserver observer) {
        super.detach(observer);
        CountdownObserversEntity entity = countdownObserversRepository.findByNameAndObserver(getName(), observer.getName());
        if (entity != null && entity.getStatus() == BasicStatusEnum.ENABLED.getCode()) {
            entity.setStatus(BasicStatusEnum.DISABLED.getCode());
            countdownObserversRepository.save(entity);
        }
    }

    /**
     * 读库添加已绑定的观察者到内存列表
     */
    public void retrieveObservers() {
        List<CountdownObserversEntity> entityList = countdownObserversRepository.findAllByName(getName());
        entityList.forEach(e -> super.attach(countdownObserverFactory.getByName(e.getObserver())));
    }
}
