package com.comeup.graphql;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年12月23日 星期一
 * @version: 1.0
 */
public class GraphqlUtil {

    private static final Map<String, String> GENERATED_MAP = new HashMap<>();



    public static synchronized String pageQueryContent(String method, Integer limit, String after, Class reqContentClass, List<String> ignoredFields, List<Class> extendClasses) {
        StringBuilder stringBuilder = new StringBuilder(ShopifyGraphqlConstants.QUERY).append(ShopifyGraphqlConstants.START);

        stringBuilder.append(method).append(ShopifyGraphqlConstants.LEFT_KUO);

        stringBuilder.append(ShopifyGraphqlConstants.FIRST).append(limit);

        if (StrUtil.isNotEmpty(after)) {
            stringBuilder.append(ShopifyGraphqlConstants.AFTER).append(after);
        }
        stringBuilder.append(ShopifyGraphqlConstants.RIGHT_KUO);

        stringBuilder.append("{ pageInfo { hasNextPage } edges { cursor } nodes {");

        addClassParam(reqContentClass, stringBuilder, ignoredFields);

        stringBuilder.append(ShopifyGraphqlConstants.END);
        stringBuilder.append(ShopifyGraphqlConstants.END);
        stringBuilder.append(ShopifyGraphqlConstants.END);


        return stringBuilder.toString();
    }

    public static synchronized void addClassParam(Class<?> clazz, StringBuilder query, List<String> ignoredFields) {
        if (GENERATED_MAP.containsKey(clazz.getName())) {
            query.append(GENERATED_MAP.get(clazz.getName()));
            return;
        }
        StringBuilder queryParam = new StringBuilder();
        addFieldsRecursively(clazz, queryParam, ignoredFields);
        GENERATED_MAP.put(clazz.getName(), queryParam.toString());
        query.append(queryParam);
    }


    private static void addFieldsRecursively(Class<?> clazz, StringBuilder query, List<String> ignoredFields) {
        if (clazz == null || clazz == Object.class) {
            return;
        }
        for (Field field : clazz.getDeclaredFields()) {
            // 检查字段是否是基本数据类型或String类型
            if (isPrimitiveOrString(field.getType())) {
                query.append(field.getName()).append(" ");
            } else if (CollUtil.isNotEmpty(ignoredFields) && ignoredFields.contains(field.getName())) {
                return;
            } else {
                // 处理复杂对象类型
                query.append(field.getName()).append(" ");
                query.append("{ ");
                addFieldsRecursively(field.getType(), query, ignoredFields);
                query.append("} ");
            }
        }
    }


    private static boolean isPrimitiveOrString(Class<?> type) {
        return type.isPrimitive() || type == String.class;
    }
}
