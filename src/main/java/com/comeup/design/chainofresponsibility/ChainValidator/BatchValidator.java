package com.comeup.design.chainofresponsibility.ChainValidator;


import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author qiu wanzi
 * @description Note: 批量校验器 一次校验多个参数
 * @date 2024年3月7日 0007
 */
public class BatchValidator {

    private final List<String> ERROR_LIST = new ArrayList<>();

    private BatchValidator() {
    }

    public static BatchValidator newInstance() {
        return new BatchValidator();
    }

    public <T> BatchValidator valid(@Nullable T object, Predicate<T> predicate, String message) {
        if (predicate.test(object)) {
            ERROR_LIST.add(message);
        }
        return this;
    }

    public boolean hasError() {
        return !ERROR_LIST.isEmpty();
    }

    public List<String> getErrorList() {
        return ERROR_LIST;
    }


}