package com.comeup.exception;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月14日 0014
 * @version: 1.0
 */
public enum Exceptions {

    VALID_EXCEPTION(ValidException.class, "参数校验异常"),
    BUSINESS_EXCEPTION(BusinessException.class, "业务异常"),
    ;
    private final Class<? extends BaseException> exceptionClass;
    private final String defaultMessage;

    Exceptions(Class<? extends BaseException> exceptionClass, String defaultMessage) {
        this.exceptionClass = exceptionClass;
        this.defaultMessage = defaultMessage;
    }

    public Class<? extends BaseException> getExceptionClass() {
        return exceptionClass;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
