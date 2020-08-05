package com.sanhenanli.plugin.countdown.client.factory;

/**
 * datetime 2020/8/4 10:50
 * 简单工厂模式
 *
 * @author zhouwenxiang
 */
public interface Factory<T> {

    /**
     * 根据name获取T
     * @param name name
     * @return T
     */
    T getByName(String name);
}
