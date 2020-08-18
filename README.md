# 基于redisson延时队列的分布式倒计时组件

- 支持init, start, suspend, resume, reset, cancel动作的倒计时器
- 支持onInit, onStart, onSuspend, onResume, onReset, onCancel, onStop事件

### 使用

1. 按需实现CountdownObserver
```$xslt
@Slf4j
public class TestCountdownObserver extends AbstractStandardCountdownObserver {

    public TestCountdownObserver(String name) {
        super(name);
    }

    @Override
    public void onInit(CountdownTimer timer) {
        log.info("timer:{}", timer.log());
    }

    @Override
    public void onReset(CountdownTimer timer) {
        log.info("timer:{}", timer.log());
    }

    @Override
    public void onStart(CountdownTimer timer) {
        log.info("timer:{}", timer.log());
    }

    @Override
    public void onSuspend(CountdownTimer timer) {
        log.info("timer:{}", timer.log());
    }

    @Override
    public void onResume(CountdownTimer timer) {
        log.info("timer:{}", timer.log());
    }

    @Override
    public void onCancel(CountdownTimer timer) {
        log.info("timer:{}", timer.log());
    }

    @Override
    public void onStop(CountdownTimer timer) {
        log.info("timer:{}", timer.log());
    }
}
```
2. 将Observer注册到ObserverFactory
```$xslt
/**
 * 实现一个CountdownObserverFactory
 */
@Service
public class DefaultCountdownObserverFactory implements CountdownObserverFactory {

    @Bean
    public TestCountdownObserver testCountdownObserver() {
        return new TestCountdownObserver("test");
    }

    @Autowired
    private TestCountdownObserver testCountdownObserver;

    @Override
    public AbstractCountdownObserver getByName(String name) {
        switch (name) {
            case "test": return testCountdownObserver;
            default: return null;
        }
    }
}
```
3. 数据表初始化
```$xslt
CREATE TABLE `countdown_states` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '倒计时的名字',
  `millis` bigint(20) DEFAULT NULL COMMENT '最近记录的剩余时间',
  `action` tinyint(2) DEFAULT NULL COMMENT '操作',
  `action_result` text COMMENT '操作结果',
  `state` tinyint(2) DEFAULT NULL COMMENT '当前状态',
  `timestamp` bigint(20) DEFAULT NULL COMMENT '操作的时间戳',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4 COMMENT='倒计时器状态记录表';

CREATE TABLE `countdown_observers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '倒计时名称',
  `observer` varchar(50) DEFAULT NULL COMMENT '观察者名称',
  `result` text COMMENT '观察者执行结果',
  `status` int(11) DEFAULT NULL COMMENT '观察者启用状态',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='倒计时的观察者记录表';
```
4. 注入DDQCountdownServiceImpl
```$xslt
@Test
public void test1() {
    try {
        String name = "redisson-countdown-test-5";
        // 创建该name的倒计时subject, 并依附上test观察者
        countdownService.create(name, 30000, Collections.singletonList(countdownObserverFactory.getByName("test")));
        countdownService.start(name);
        Thread.sleep(2000);
        countdownService.suspend(name);
        Thread.sleep(1000);
        countdownService.resume(name);
        Thread.sleep(2000);
        countdownService.resume(name);
        Thread.sleep(3000);
        countdownService.suspend(name);
        Thread.sleep(2000);
        countdownService.reset(name, 18000);
        Thread.sleep(2000);
        countdownService.resume(name);
        Thread.sleep(3000);
        Thread.sleep(20000);
        System.out.println(countdownService.log(name));
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```
