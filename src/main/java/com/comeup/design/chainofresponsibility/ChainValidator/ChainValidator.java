package com.comeup.design.chainofresponsibility.ChainValidator;


import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author qiu wanzi
 * @description Note: 链式校验器 一个失败中断返回 aop校验????
 * @date 2024年3月7日 0007
 */
public class ChainValidator<T> {

    private T target;
    private final List<Validator<T>> validators = new ArrayList<>();

    public ChainValidator(T target) {
        this.target = target;
    }

    public ChainValidator<T> add(Predicate<T> predicate, String message) {
        validators.add(new Validator<>(predicate, message));
        return this;
    }

    public boolean validate() throws ValidationException {
        for (Validator<T> validator : validators) {
            if (!validator.predicate.test(target)) {
                throw new ValidationException(validator.message);
            }
        }
        return true;
    }

    private static class Validator<T> {
        private Predicate<T> predicate;
        private String message;

        public Validator(Predicate<T> predicate, String message) {
            this.predicate = predicate;
            this.message = message;
        }
    }

    public static class ChainValidationException extends RuntimeException {
        public ChainValidationException(String message) {
            super(message);
        }
    }
}