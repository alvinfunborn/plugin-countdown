package com.sanhenanli.plugin.countdown.powerjob.config;

import com.github.kfcfans.powerjob.client.OhMyClient;
import com.github.kfcfans.powerjob.worker.OhMyWorker;
import com.github.kfcfans.powerjob.worker.common.OhMyConfig;
import com.github.kfcfans.powerjob.worker.common.constants.StoreStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * datetime 2020/7/27 13:46
 * power-job配置初始化
 *
 * @author zhouwenxiang
 */
@Slf4j
@Configuration
public class PowerJobSchedulerConfig {

    private static final String POWERJOB_SERVERS = "192.168.33.47:7700";
    private static final String APP_NAME = "lanxi-hm-dev";
    private static final String PASSWORD = "123456";

    @Bean
    public OhMyWorker initWorker() {
        // 服务器HTTP地址（端口号为 server.port，而不是 ActorSystem port），请勿添加任何前缀（http://）, 例localhost:7700,192.168.33.48:7700
        List<String> serverAddress = Arrays.asList(POWERJOB_SERVERS.split(","));

        // 1. 创建配置文件
        OhMyConfig config = new OhMyConfig();
        // 可以不显式设置，默认值 27777
        config.setPort(27777);
        // appName，需要提前在控制台注册，否则启动报错
        config.setAppName(APP_NAME);
        config.setServerAddress(serverAddress);
        // 如果没有大型 Map/MapReduce 的需求，建议使用内存来加速计算
        // 有大型 Map/MapReduce 需求，可能产生大量子任务（Task）的场景，请使用 DISK，否则妥妥的 OutOfMemory
        config.setStoreStrategy(StoreStrategy.MEMORY);

        // 2. 创建 Worker 对象，设置配置文件
        OhMyWorker ohMyWorker = new OhMyWorker();
        /// nacos配置对应环境的powerjob.servers
        /// powerjob上注册本应用
        ohMyWorker.setConfig(config);
        return ohMyWorker;
    }

    @Bean
    public OhMyClient powerJobClient() {
        List<String> serverAddress = Arrays.asList(POWERJOB_SERVERS.split(","));
        return new OhMyClient(serverAddress, APP_NAME, PASSWORD);
    }
}