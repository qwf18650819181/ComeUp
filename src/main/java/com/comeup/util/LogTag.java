package com.comeup.util;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月14日 0014
 * @version: 1.0
 */
public enum LogTag {
    RPC_REQ("RPC请求"),
    RPC_RES("RPC响应"),
    REQ("请求参数"),
    RES("返回响应"),
    PARAMETER("参数"),
    ANSWER("结果"),
    VALID("参数校验异常"),
    BUSINESS("业务异常"),
    ERROR("错误");
    private final String type;

    LogTag(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
