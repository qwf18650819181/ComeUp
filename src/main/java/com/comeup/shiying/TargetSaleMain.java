package com.comeup.shiying;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.comeup.design.chainofresponsibility.ChainValidator.ChainValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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


        LocalDate thisYearDateLine = LocalDate.parse("2024-02-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println(thisYearDateLine);





        BaseListResponse body = JSON.parseObject("{\"content\":[{\"configSeasonId\":40,\"season\":4,\"seasonName\":\"春秋季\",\"beginDateDto\":{\"month\":\"1\",\"day\":\"1\"},\"endDateDto\":{\"month\":\"9\",\"day\":\"30\"},\"thisYearDateDto\":{\"month\":\"2\",\"day\":\"1\"},\"thisYearDays\":90,\"createBy\":\"SPXT-11251\",\"createDate\":\"2024-01-12 10:00:03\",\"updateBy\":\"admin\",\"updateDate\":\"2024-03-13 17:29:27\"},{\"configSeasonId\":41,\"season\":5,\"seasonName\":\"夏季\",\"beginDateDto\":{\"month\":\"4\",\"day\":\"1\"},\"endDateDto\":{\"month\":\"7\",\"day\":\"1\"},\"thisYearDateDto\":{\"month\":\"6\",\"day\":\"1\"},\"thisYearDays\":90,\"createBy\":\"SPXT-11251\",\"createDate\":\"2024-01-12 10:00:03\",\"updateBy\":\"SPXT-11251\",\"updateDate\":\"2024-01-12 10:00:03\"},{\"configSeasonId\":42,\"season\":7,\"seasonName\":\"冬季\",\"beginDateDto\":{\"month\":\"10\",\"day\":\"1\"},\"endDateDto\":{\"month\":\"12\",\"day\":\"31\"},\"thisYearDateDto\":{\"month\":\"11\",\"day\":\"1\"},\"thisYearDays\":90,\"createBy\":\"SPXT-11251\",\"createDate\":\"2024-01-12 10:00:03\",\"updateBy\":\"SPXT-11251\",\"updateDate\":\"2024-01-12 10:00:03\"},{\"configSeasonId\":43,\"season\":8,\"seasonName\":\"四季\",\"beginDateDto\":{\"month\":\"1\",\"day\":\"1\"},\"endDateDto\":{\"month\":\"12\",\"day\":\"31\"},\"thisYearDateDto\":{\"month\":\"4\",\"day\":\"1\"},\"thisYearDays\":90,\"createBy\":\"SPXT-11251\",\"createDate\":\"2024-01-12 10:00:03\",\"updateBy\":\"SPXT-11251\",\"updateDate\":\"2024-01-12 10:00:03\"},{\"configSeasonId\":44,\"season\":4,\"seasonName\":\"春秋季\",\"beginDateDto\":{\"month\":\"1\",\"day\":\"1\"},\"endDateDto\":{\"month\":\"9\",\"day\":\"30\"},\"thisYearDateDto\":{\"month\":\"2\",\"day\":\"1\"},\"thisYearDays\":90,\"createBy\":\"SPXT-11251\",\"createDate\":\"2024-01-12 10:00:03\",\"updateBy\":\"SPXT-11251\",\"updateDate\":\"2024-01-12 10:00:03\"},{\"configSeasonId\":45,\"season\":5,\"seasonName\":\"夏季\",\"beginDateDto\":{\"month\":\"4\",\"day\":\"1\"},\"endDateDto\":{\"month\":\"7\",\"day\":\"1\"},\"thisYearDateDto\":{\"month\":\"6\",\"day\":\"1\"},\"thisYearDays\":90,\"createBy\":\"SPXT-11251\",\"createDate\":\"2024-01-12 10:00:03\",\"updateBy\":\"SPXT-11251\",\"updateDate\":\"2024-01-12 10:00:03\"},{\"configSeasonId\":46,\"season\":7,\"seasonName\":\"冬季\",\"beginDateDto\":{\"month\":\"10\",\"day\":\"1\"},\"endDateDto\":{\"month\":\"12\",\"day\":\"31\"},\"thisYearDateDto\":{\"month\":\"11\",\"day\":\"1\"},\"thisYearDays\":90,\"createBy\":\"SPXT-11251\",\"createDate\":\"2024-01-12 10:00:03\",\"updateBy\":\"SPXT-11251\",\"updateDate\":\"2024-01-12 10:00:03\"},{\"configSeasonId\":47,\"season\":8,\"seasonName\":\"四季\",\"beginDateDto\":{\"month\":\"1\",\"day\":\"1\"},\"endDateDto\":{\"month\":\"12\",\"day\":\"31\"},\"thisYearDateDto\":{\"month\":\"4\",\"day\":\"1\"},\"thisYearDays\":90,\"createBy\":\"SPXT-11251\",\"createDate\":\"2024-01-12 10:00:03\",\"updateBy\":\"SPXT-11251\",\"updateDate\":\"2024-01-12 10:00:03\"}],\"totalCount\":8}", BaseListResponse.class);

        if (Objects.nonNull(body) && !CollectionUtils.isEmpty(body.getContent())) {
            log.info("[rpc响应] ScmApiService getSeasonConfigsByLocation body: {} timestamp: {}", JSON.toJSONString(body), LocalDateTime.now());
            List<BdReplenishmentSeasonConfigResponse> bdReplenishmentSeasonConfigResponses = Json.listToObject(body.getContent(), BdReplenishmentSeasonConfigResponse.class);
            System.out.println(bdReplenishmentSeasonConfigResponses);
        }






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
