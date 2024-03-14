package com.comeup.exception;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月14日 0014
 * @version: 1.0
 */
public enum RPC {
    SUCCESS(1),
    FAIL(2);
    private final Integer code;

    RPC(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public Integer code() {
        return code;
    }
}
