package com.comeup.exception;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月13日 0013
 * @version: 1.0
 */
public class MonitorException {


    private static final Map<Class<? extends BaseException>, Exceptions> EXCEPTIONS_MAP = Maps.newHashMap();

    static {
        for (Exceptions exception : Exceptions.values()) {
            EXCEPTIONS_MAP.put(exception.getExceptionClass(), exception);
        }
    }

}
