package com.sanhenanli.plugin.countdown.persist.jpa.factory;

import com.alibaba.fastjson.JSONObject;
import com.sanhenanli.plugin.countdown.client.AbstractCountdownContext;
import com.sanhenanli.plugin.countdown.client.CountdownContext;
import com.sanhenanli.plugin.countdown.client.factory.Factory;
import com.sanhenanli.plugin.countdown.client.model.CountdownResult;
import com.sanhenanli.plugin.countdown.client.model.CountdownState;
import com.sanhenanli.plugin.countdown.client.model.enums.CountdownActionEnum;
import com.sanhenanli.plugin.countdown.client.model.enums.CountdownStateEnum;
import com.sanhenanli.plugin.countdown.persist.jpa.model.entity.CountdownStatesEntity;
import com.sanhenanli.plugin.countdown.persist.jpa.repository.CountdownStatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * datetime 2020/8/4 15:22
 * 倒计时上下文的工厂
 *
 * @author zhouwenxiang
 */
@Service
public class DbCountdownContextFactory implements Factory<CountdownContext> {

    @Autowired
    private CountdownStatesRepository countdownStatesRepository;

    @Override
    public AbstractCountdownContext getByName(String name) {
        return new AbstractCountdownContext(name) {
            @Override
            public long getCurrentMillis() {
                List<CountdownStatesEntity> entities = countdownStatesRepository.findAllByName(name);
                if (entities == null || entities.isEmpty()) {
                    return 0;
                }
                CountdownStatesEntity currentState = entities.get(0);
                for (int i = entities.size() - 1; i >= 0; i--) {
                    if (JSONObject.parseObject(entities.get(i).getActionResult(), CountdownResult.class).isOk()) {
                        currentState = entities.get(i);
                        break;
                    }
                }
                if (currentState == null || currentState.getState() == null) {
                    return 0;
                }
                CountdownStateEnum stateEnum = CountdownStateEnum.getByCode(currentState.getState());
                if (stateEnum == null) {
                    return 0;
                }
                switch (stateEnum) {
                    case STOPPED:
                    case CANCELED:
                    case SUSPENDED:
                        return currentState.getMillis();
                    case RUNNING:
                        return currentState.getMillis() - System.currentTimeMillis() + currentState.getTimestamp();
                    default: return 0;
                }
            }

            @Override
            public CountdownStateEnum getCurrentState() {
                CountdownStatesEntity entity = countdownStatesRepository.findFirstByNameOrderByIdDesc(name);
                return entity != null ? CountdownStateEnum.getByCode(entity.getState()) : null;
            }

            @Transactional(rollbackFor = Exception.class)
            @Override
            public void appendCountdownState(CountdownState state) {
                CountdownStatesEntity entity = new CountdownStatesEntity();
                entity.setName(name);
                entity.setMillis(state.getMillis());
                entity.setAction(state.getAction().getCode());
                entity.setActionResult(JSONObject.toJSONString(state.getActionResult()));
                entity.setState(state.getState().getCode());
                entity.setTimestamp(state.getTimestamp());
                countdownStatesRepository.save(entity);
            }

            @Override
            public List<CountdownState> listStates() {
                List<CountdownStatesEntity> entities = countdownStatesRepository.findAllByName(name);
                return entities.stream().map(e -> new CountdownState(
                        e.getName(),
                        e.getMillis(),
                        CountdownActionEnum.getByCode(e.getAction()),
                        JSONObject.parseObject(e.getActionResult(), CountdownResult.class),
                        CountdownStateEnum.getByCode(e.getState()),
                        e.getTimestamp())).collect(Collectors.toList());
            }
        };
    }
}
