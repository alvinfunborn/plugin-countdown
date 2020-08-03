package com.sanhenanli.plugin.countdown.powerjob.task;

import com.github.kfcfans.powerjob.worker.core.processor.ProcessResult;
import com.github.kfcfans.powerjob.worker.core.processor.TaskContext;
import com.github.kfcfans.powerjob.worker.core.processor.sdk.BasicProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * datetime 2020/8/3 14:35
 *
 * @author zhouwenxiang
 */
@Component
public class TestTask implements BasicProcessor {
    @Override
    public ProcessResult process(TaskContext context) throws Exception {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!" + LocalDateTime.now());
        return new ProcessResult(true);
    }
}
