package com.sanhenanli.plugin.countdown.persist.jpa.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * datetime 2020/7/16 10:36
 * 通用业务状态码
 *
 * @author zhouwenxiang
 */
@AllArgsConstructor
@Getter
public enum BasicStatusEnum {

    /**
     * 删除的 (不展示的)
     */
    DELETED(0),
    /**
     * 启用的/开放的
     */
    ENABLED(1),
    /**
     * 禁用的/关闭的/完成的 (展示的)
     */
    DISABLED(2),
    ;

    private int code;

    public static BasicStatusEnum getByCode(int code) {
        for (BasicStatusEnum value : BasicStatusEnum.values()) {
            if (value.code == code) {
                return value;
            }
        }
        return null;
    }
}
