package com.comeup.shiying;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author: qiu wanzi
 * @date: 2024年3月6日 0006
 * @version: 1.0
 * @description: TODO
 */
public class Validator {

    public static <T> void valid(@Nullable T object, Predicate<T> predicate, String message) {
        Optional.ofNullable(object).filter(item -> !predicate.test(item)).orElseThrow(() -> new RuntimeException(message));
    }
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("1");
        Validator.isValid(list, CollectionUtils::isNotEmpty, "barcode 库存不足,请执行商品任务PreGenBarcodeJob");



    }

    public static <T> void isValid(@Nullable T object, Predicate<T> predicate, String message) {
        if (!predicate.test(object)) {
            throw new RuntimeException(message);
        }
    }
}
