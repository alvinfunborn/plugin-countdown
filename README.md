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
3. 注入DDQCountdownServiceImpl
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
