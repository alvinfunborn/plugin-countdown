package com.sanhenanli.plugin.countdown.client.factory;

import com.sanhenanli.plugin.countdown.client.model.CountdownResult;
import com.sanhenanli.plugin.countdown.client.subject.AbstractCountdownSubject;

/**
 * datetime 2020/8/4 10:50
 * 倒计时subject工厂
 *
 * @author zhouwenxiang
 */
public interface CountdownSubjectFactory extends Factory<AbstractCountdownSubject> {

    /**
     * 注册subject到工厂
     * @param subject subject
     * @return 注册结果
     */
    CountdownResult register(AbstractCountdownSubject subject);
}
