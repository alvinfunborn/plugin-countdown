package com.sanhenanli.plugin.countdown.client.model;

/**
 * datetime 2020/7/31 17:00
 * 倒计时操作的结果
 *
 * @author zhouwenxiang
 */
public class CountdownResult {

    /**
     * 是否成功
     */
    protected boolean ok;
    /**
     * tip
     */
    protected String msg;

    public CountdownResult(boolean ok, String msg) {
        this.ok = ok;
        this.msg = msg;
    }

    public CountdownResult(boolean ok) {
        this.ok = ok;
    }

    public boolean isOk() {
        return ok;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "CountdownResult{" +
                "ok=" + ok +
                ", msg='" + msg + '\'' +
                '}';
    }
}
