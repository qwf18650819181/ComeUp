package com.comeup.shiying;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.comeup.design.chainofresponsibility.ChainValidator.ChainValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月13日 0013
 * @version: 1.0
 */
@Slf4j
public class TargetSaleMain {

    public static void main(String[] args) {
        String json = "[\n" +
                "    {\n" +
                "        \"0\": \"店铺\",\n" +
                "        \"1\": \"parent code\",\n" +
                "        \"2\": \"打款季节\",\n" +
                "        \"3\": \"2024/3/17\",\n" +
                "        \"4\": \"2024/3/24\",\n" +
                "        \"5\": \"2024/3/31\",\n" +
                "        \"6\": \"2024/4/7\",\n" +
                "        \"7\": \"2024/4/14\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"0\": \"dokotoo-fba补货测试店铺-忽动\",\n" +
                "        \"1\": \"testparent_code0001\",\n" +
                "        \"2\": \"春秋季\",\n" +
                "        \"3\": \"100\",\n" +
                "        \"4\": \"150\",\n" +
                "        \"5\": \"200\",\n" +
                "        \"6\": \"250\",\n" +
                "        \"7\": \"300\"\n" +
                "    }\n" +
                "]";
        List<LinkedHashMap> importList = JSON.parseArray(json, LinkedHashMap.class);
        Map<String, Date> headerDateMap = new HashMap<>();
        validAndParseExcelHeader(importList.get(0), headerDateMap);

    }

    private static String validAndParseExcelHeader(Map<String, String> headerMap, Map<String, Date> headerDateMap) {
        AtomicReference<String> headerErrorMessage = new AtomicReference<>(ChainValidator.newInstance(headerMap)
                .condition(CollectionUtils::isEmpty, "标题不存在")
                .condition(items -> headerMap.values().size() != headerMap.values().stream().distinct().count(), "标题重复,请确认")
                .validWithMessage());
        if (StringUtils.hasText(headerErrorMessage.get())) return headerErrorMessage.get();

        headerMap.forEach((k, v) -> {
            int key = Integer.parseInt(String.valueOf(k));
            String value = String.valueOf(v);
            if (key == 0) {
                Validator.valid(value, "店铺"::equals, "第一列标题应该是[店铺]");
            } else if (key == 1) {
                Validator.valid(value, "parent code"::equals, "第二列标题应该是[parent code]");
            } else if (key == 2) {
                Validator.valid(value, "打款季节"::equals, "第三列标题应该是[打款季节]");
            } else {
                try {
                    headerDateMap.put(String.valueOf(k), Convert.toDate(value));
                } catch (Exception e) {
                    if (!StringUtils.hasText(headerErrorMessage.get())) headerErrorMessage.set(String.format("第[%s]解析日期错误, 正确格式[yyyy/MM/dd]", k));
                    log.error(headerErrorMessage.get(), e);
                }
            }
        });
        return headerErrorMessage.get();
    }

}
