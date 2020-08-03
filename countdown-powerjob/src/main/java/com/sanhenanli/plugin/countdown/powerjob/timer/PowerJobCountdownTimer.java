package com.sanhenanli.plugin.countdown.powerjob.timer;

import com.github.kfcfans.powerjob.client.OhMyClient;
import com.github.kfcfans.powerjob.common.ExecuteType;
import com.github.kfcfans.powerjob.common.ProcessorType;
import com.github.kfcfans.powerjob.common.TimeExpressionType;
import com.github.kfcfans.powerjob.common.request.http.SaveJobInfoRequest;
import com.github.kfcfans.powerjob.common.response.ResultDTO;
import com.sanhenanli.plugin.countdown.client.model.CountdownResult;
import com.sanhenanli.plugin.countdown.client.model.CountdownState;
import com.sanhenanli.plugin.countdown.client.model.enums.CountdownStateEnum;
import com.sanhenanli.plugin.countdown.client.AbstractCountdownTimer;
import com.sanhenanli.plugin.countdown.client.CountdownContext;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * datetime 2020/8/3 13:49
 *
 * @author zhouwenxiang
 */
@Slf4j
public class PowerJobCountdownTimer extends AbstractCountdownTimer {

    private static final DateTimeFormatter CRON_FORMATTER = DateTimeFormatter.ofPattern("s m H d M ? yyyy");

    private long powerJobId;
    private CountdownContext countdownContext;
    private OhMyClient powerJobClient;

    public PowerJobCountdownTimer(String id, long millis, CountdownContext countdownContext, OhMyClient client, String name, String cron, String reference) throws Exception {
        super(id, millis);
        this.countdownContext = countdownContext;
        this.powerJobClient = client;
        SaveJobInfoRequest request = create(name, cron, reference);
        ResultDTO result = powerJobClient.saveJob(request);
        if (result.isSuccess() && result.getData() != null) {
            this.powerJobId = (int) result.getData();
        } else {
            throw new Exception(result.getMessage());
        }
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public CountdownResult reset(long millis) {
        try {
            ResultDTO result = powerJobClient.fetchJob(powerJobId);
            if (result.isSuccess() && result.getData() != null) {
                LinkedHashMap<String, String> job = (LinkedHashMap<String, String>) result.getData();
                SaveJobInfoRequest request = create(job.get("jobName"), job.get("timeExpression"), job.get("processorInfo"));
                long stopAt = millis + System.currentTimeMillis();
                request.setId(powerJobId);
                request.setTimeExpression(timestamp2cron(stopAt));
                ResultDTO<Long> resultLong = powerJobClient.saveJob(request);
                if (resultLong.isSuccess()) {
                    return new CountdownResult(true);
                } else {
                    log.error("error update job. id:{}, msg:{}", id, resultLong.getMessage());
                    return new CountdownResult(false, resultLong.getMessage());
                }
            } else {
                log.error("error fetch job. id:{}, msg:{}", id, result.getMessage());
                return new CountdownResult(false, result.getMessage());
            }
        } catch (Exception e) {
            log.error("id:{}", id, e);
            return new CountdownResult(false, e.getMessage());
        }
    }

    @Override
    public CountdownResult start() {
        try {
            ResultDTO result = powerJobClient.fetchJob(powerJobId);
            if (result.isSuccess() && result.getData() != null) {
                LinkedHashMap<String, String> job = (LinkedHashMap<String, String>) result.getData();
                SaveJobInfoRequest request = create(job.get("jobName"), job.get("timeExpression"), job.get("processorInfo"));
                long stopAt = countdownMillis + System.currentTimeMillis();
                request.setId(powerJobId);
                request.setEnable(true);
                request.setTimeExpression(timestamp2cron(stopAt));
                ResultDTO<Long> resultLong = powerJobClient.saveJob(request);
                if (resultLong.isSuccess()) {
                    return new CountdownResult(true);
                } else {
                    log.error("error create job. id:{}, msg:{}", id, resultLong.getMessage());
                    return new CountdownResult(false, resultLong.getMessage());
                }
            } else {
                log.error("error fetch job. id:{}, msg:{}", id, result.getMessage());
                return new CountdownResult(false, result.getMessage());
            }
        } catch (Exception e) {
            log.error("id:{}", id, e);
            return new CountdownResult(false, e.getMessage());
        }
    }

    @Override
    public CountdownResult cancel() {
        try {
            ResultDTO<Void> result = powerJobClient.deleteJob(powerJobId);
            if (result.isSuccess()) {
                return new CountdownResult(true);
            } else {
                log.error("error delete job. id:{}, msg:{}", id, result.getMessage());
                return new CountdownResult(false, result.getMessage());
            }
        } catch (Exception e) {
            log.error("id:{}", id, e);
            return new CountdownResult(false, e.getMessage());
        }
    }

    @Override
    public CountdownResult suspend() {
        try {
            ResultDTO<Void> result = powerJobClient.disableJob(powerJobId);
            if (result.isSuccess()) {
                return new CountdownResult(true);
            } else {
                log.error("error disable job. id:{}, msg:{}", id, result.getMessage());
                return new CountdownResult(false, result.getMessage());
            }
        } catch (Exception e) {
            log.error("id:{}", id, e);
            return new CountdownResult(false, e.getMessage());
        }
    }

    @Override
    public CountdownResult resume() {
        try {
            ResultDTO result = powerJobClient.fetchJob(powerJobId);
            if (result.isSuccess() && result.getData() != null) {
                LinkedHashMap<String, String> job = (LinkedHashMap<String, String>) result.getData();
                SaveJobInfoRequest request = create(job.get("jobName"), job.get("timeExpression"), job.get("processorInfo"));
                long stopAt = getCurrentMillis() + System.currentTimeMillis();
                request.setId(powerJobId);
                request.setEnable(true);
                request.setTimeExpression(timestamp2cron(stopAt));
                ResultDTO<Long> resultLong = powerJobClient.saveJob(request);
                if (resultLong.isSuccess()) {
                    return new CountdownResult(true);
                } else {
                    log.error("error update job. id:{}, msg:{}", id, resultLong.getMessage());
                    return new CountdownResult(false, resultLong.getMessage());
                }
            } else {
                log.error("error fetch job. id:{}, msg:{}", id, result.getMessage());
                return new CountdownResult(false, result.getMessage());
            }
        } catch (Exception e) {
            log.error("id:{}", id, e);
            return new CountdownResult(false, e.getMessage());
        }
    }

    @Override
    public String log() {
        return listStates().toString();
    }

    @Override
    public long getCurrentMillis() {
        return countdownContext.getCurrentMillis();
    }

    @Override
    public CountdownStateEnum getCurrentState() {
        return countdownContext.getCurrentState();
    }

    @Override
    public void appendCountdownState(CountdownState state) {
        countdownContext.appendCountdownState(state);
    }

    @Override
    public List<CountdownState> listStates() {
        return countdownContext.listStates();
    }

    private SaveJobInfoRequest create(String name, String cron, String reference) {
        SaveJobInfoRequest request = new SaveJobInfoRequest();
        request.setId(null);
        request.setJobName(name);
        request.setJobDescription(null);
        request.setJobParams(null);
        request.setTimeExpressionType(TimeExpressionType.CRON);
        request.setTimeExpression(cron == null ? timestamp2cron(System.currentTimeMillis() + 10 * 60 * 1000) : cron);
        request.setExecuteType(ExecuteType.STANDALONE);
        request.setProcessorType(ProcessorType.EMBEDDED_JAVA);
        request.setProcessorInfo(reference);
        request.setMaxInstanceNum(1);
        request.setConcurrency(5);
        request.setInstanceTimeLimit(0L);
        request.setTaskRetryNum(0);
        request.setMinCpuCores(0);
        request.setMinMemorySpace(0);
        request.setMinDiskSpace(0);
        request.setDesignatedWorkers(null);
        request.setMaxWorkerCount(0);
        request.setNotifyUserIds(null);
        request.setEnable(false);
        return request;
    }

    private static String timestamp2cron(long timestamp) {
        LocalDateTime time = LocalDateTime.ofEpochSecond(timestamp / 1000, (int) (timestamp % 1000 * 1000), ZoneOffset.ofHours(8));
        return time.format(CRON_FORMATTER);
    }

}
