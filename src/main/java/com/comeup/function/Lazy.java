package com.comeup.function;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月15日 0015
 * @version: 1.0
 */
public class Lazy<T> {
    private final Supplier<T> supplier;
    private boolean hasGet = false;
    private Optional<T> optional;

    private Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }
    public static <T> Lazy<T> of(Supplier<T> supplier) {
        return new Lazy<>(supplier);
    }
    public Optional<T> optional() {
        if (!hasGet) {
            T newValue = this.supplier.get();
            this.optional = Optional.of(newValue);
            hasGet = true;
        }
        return this.optional;
    }
}
