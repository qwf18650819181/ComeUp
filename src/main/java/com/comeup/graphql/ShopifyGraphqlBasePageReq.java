package com.comeup.graphql;

import cn.hutool.core.util.StrUtil;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年12月23日 星期一
 * @version: 1.0
 */
public class ShopifyGraphqlBasePageReq {


    private Integer limit;
    private String after;
    private String method;
    private Class<T> reqContentClass;

    private ShopifyGraphqlBasePageReq() {
    }

    public ShopifyGraphqlBasePageReq(String method, Integer limit, String after, Class<T> reqContentClass) {
        this.limit = limit;
        this.after = after;
        this.method = method;
        this.reqContentClass = reqContentClass;
    }

    public String query() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(method).append(ShopifyGraphqlConstants.LEFT_KUO);

        stringBuilder.append(ShopifyGraphqlConstants.FIRST).append(limit);

        if (StrUtil.isNotEmpty(after)) {
            stringBuilder.append(ShopifyGraphqlConstants.AFTER).append(after);
        }
        stringBuilder.append(ShopifyGraphqlConstants.RIGHT_KUO);

        stringBuilder.append("{ pageInfo { hasNextPage } edges { cursor } nodes {");

        GraphqlUtil.addClassParam(reqContentClass, stringBuilder, new ArrayList<>());

        stringBuilder.append(ShopifyGraphqlConstants.END);

        return stringBuilder.toString();
    }








    /**
     * query {
     *   products(first: 1, after: "eyJsYXN0X2lkIjo4MjA0MTM2Mjg0NDM2LCJsYXN0X3ZhbHVlIjoiODIwNDEzNjI4NDQzNiJ9") {
     *     pageInfo {
     *       hasNextPage
     *     }
     *     edges {
     *       cursor
     *     }
     *     nodes {
     *       id
     *       vendor
     *       status
     *     }
     *   }
     * }
     */
}
