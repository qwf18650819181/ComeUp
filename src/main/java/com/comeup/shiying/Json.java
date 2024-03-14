package com.comeup.shiying;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月14日 0014
 * @version: 1.0
 */
public class Json {

    public static <T> List<T> listToObject(List list, Class<T> cls) {
        return JSON.parseArray(JSON.toJSONString(list), cls);
    }

}
