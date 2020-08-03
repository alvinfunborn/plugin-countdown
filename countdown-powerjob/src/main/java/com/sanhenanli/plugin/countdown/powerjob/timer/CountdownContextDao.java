package com.sanhenanli.plugin.countdown.powerjob.timer;

import com.sanhenanli.plugin.countdown.client.model.CountdownState;
import com.sanhenanli.plugin.countdown.client.model.enums.CountdownStateEnum;
import com.sanhenanli.plugin.countdown.client.CountdownContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * datetime 2020/8/3 17:01
 * todo 实现repository
 *
 * @author zhouwenxiang
 */
@Service
public class CountdownContextDao implements CountdownContext {
    @Override
    public long getCurrentMillis() {
        return 0;
    }

    @Override
    public CountdownStateEnum getCurrentState() {
        return null;
    }

    @Override
    public void appendCountdownState(CountdownState state) {

    }

    @Override
    public List<CountdownState> listStates() {
        return null;
    }
}
