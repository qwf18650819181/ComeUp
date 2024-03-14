package com.comeup.exception;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月14日 0014
 * @version: 1.0
 */
public enum RPCERROR {
    SUCCESS(400, "成功"),
    VALID(401, "校验错误"),
    BUSINESS(402, "业务异常"),
    FAIL(500, "错误");
    private final Integer code;
    private final String msg;

    RPCERROR(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }

    public Integer code() {
        return code;
    }


}
