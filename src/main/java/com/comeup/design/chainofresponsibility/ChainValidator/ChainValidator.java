package com.comeup.design.chainofresponsibility.ChainValidator;


import cn.hutool.core.exceptions.ValidateException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author qiu wanzi
 * @description Note: 链式校验器 一个失败中断返回 aop校验????
 * @date 2024年3月7日 0007
 */
@Slf4j
public final class ChainValidator<T> {

    private T target;
    private final List<Validator<T>> validators = new ArrayList<>();

    private ChainValidator() {
    }

    public static <T> ChainValidator<T> newInstance(T target) {
        ChainValidator<T> chainValidator = new ChainValidator<>();
        chainValidator.target = target;
        return chainValidator;
    }

    public ChainValidator<T> condition(Predicate<T> predicate, String message) {
        validators.add(new Validator<>(predicate, message));
        return this;
    }

    public void valid() {
        for (Validator<T> validator : validators) {
            if (validator.predicate.test(target)) {
                log.error("验证失败: " + validator.message);
                throw new ValidateException(validator.message);
            }
        }
    }

    public boolean validSuccess() {
        for (Validator<T> validator : validators) {
            if (validator.predicate.test(target)) {
                log.error("验证失败: " + validator.message);
                return false;
            }
        }
        return true;
    }

    public String validWithMessage() {
        for (Validator<T> validator : validators) {
            if (validator.predicate.test(target)) {
                log.error("验证失败: " + validator.message);
                return validator.message;
            }
        }
        return "";
    }

    private static final class Validator<T> {
        private final Predicate<T> predicate;
        private final String message;

        private Validator(Predicate<T> predicate, String message) {
            this.predicate = predicate;
            this.message = message;
        }
    }

}