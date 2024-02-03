package com.comeup.shiying;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @auth: qwf
 * @date: 2024年1月9日 0009
 * @description:
 */
public class NsyExcelUtil {

    public static Logger logger = LoggerFactory.getLogger(NsyExcelUtil.class);

    public static List<String> getHeads(Class clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(item -> item.isAnnotationPresent(NsyExcelProperty.class))
                .flatMap(item -> Stream.of(item.getAnnotation(NsyExcelProperty.class).value()))
                .collect(Collectors.toList());
    }

    public static <T> List<Object> getData(Class<T> clazz, T data) {
        List<Object> targetList = new ArrayList<>();
        for (Field declaredField : clazz.getDeclaredFields()) {
            if (declaredField.isAnnotationPresent(NsyExcelProperty.class)) {
                try {
                    declaredField.setAccessible(true);
                    targetList.add(declaredField.get(data));
                } catch (IllegalAccessException e) {
                    logger.error("获取字段值失败!,[{}]", declaredField.getName());
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        }
        return targetList;
    }

    public static List<List<String>> getCommonHeads(Class clazz) {
        List<List<String>> list = new ArrayList<>();
        List<String> heads = getHeads(clazz);

        heads.forEach(head -> {
            List<String> headList = new ArrayList<>();
            headList.add(head);
            list.add(headList);
        });
        return list;
    }

}
