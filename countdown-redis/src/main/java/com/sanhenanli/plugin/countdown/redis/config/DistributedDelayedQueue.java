package com.sanhenanli.plugin.countdown.redis.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * datetime 2020/8/17 16:16
 * redisson分布式延时队列
 *
 * @author zhouwenxiang
 */
@Slf4j
@Lazy
@Component
public class DistributedDelayedQueue {

    private RBlockingQueue<String> blockingFairQueue;
    private RDelayedQueue<String> delayedQueue;

    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private Environment environment;
    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    private void init() {
        String applicationName = environment.getProperty("spring.application.name");
        String profilesActive = environment.getProperty("spring.profiles.active");
        // redisson包装主键为redisson_delay_quque:{name}和redisson_delay_quque_timeout:{name}
        blockingFairQueue = redissonClient.getBlockingQueue(applicationName + "-" + profilesActive);
        // 获取或创建delayedQueue, 不需要销毁
        delayedQueue = redissonClient.getDelayedQueue(blockingFairQueue);
        // 开启消费延时队列的线程
        Thread consumer = new Thread(this::consume);
        consumer.setDaemon(true);
        consumer.start();
    }

    /**
     * 插入队列
     * @param obj subject
     * @param millis 毫秒
     */
    public void offer(String obj, long millis) {
        delayedQueue.offer(obj, millis, TimeUnit.MILLISECONDS);
    }

    /**
     * 从队列移除
     * @param obj subject
     * @return true/false
     */
    public boolean remove(String obj) {
        return delayedQueue.remove(obj);
    }

    /**
     * 队列中是否包含
     * @param obj subject
     * @return true/false
     */
    public boolean contains(String obj) {
        return delayedQueue.contains(obj);
    }

    /**
     * 消费队列
     */
    private void consume() {
        while (true) {
            try {
                String obj = blockingFairQueue.take();
                log.info("time:{}, obj:{}", LocalDateTime.now(), obj);
                applicationContext.publishEvent(new DelayedQueuePopEvent(this, obj));
            } catch (InterruptedException e) {
                log.error("", e);
            }
        }
    }

    /**
     * 延时队列出列事件
     */
    public static class DelayedQueuePopEvent extends ApplicationEvent {
        private static final long serialVersionUID = -8436329280574240541L;

        /**
         * subject
         */
        private String obj;

        public DelayedQueuePopEvent(Object source, String obj) {
            super(source);
            this.obj = obj;
        }

        public String getObj() {
            return obj;
        }
    }
}
