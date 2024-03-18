package com.comeup.function;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月15日 0015
 * @version: 1.0
 */
public class LazyList<T> {
    private final Supplier<List<T>> supplier;
    private boolean hasGet = false;
    private Optional<List<T>> optional;

    private LazyList(Supplier<List<T>> supplier) {
        this.supplier = supplier;
    }

    public static <T> LazyList<T> of(Supplier<List<T>> supplier) {
        return new LazyList<>(supplier);
    }

    public Optional<List<T>> optional() {
        if (!hasGet) {
            List<T> newValue = this.supplier.get();
            this.optional = Optional.ofNullable(newValue);
            hasGet = true;
        }
        return this.optional;
    }

    public List<T> getEmptyListIfNull() {
        if (!hasGet) {
            List<T> newValue = this.supplier.get();
            this.optional = Optional.ofNullable(newValue);
            hasGet = true;
        }
        return this.optional.orElse(Collections.emptyList());
    }
}
