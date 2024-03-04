package com.comeup.shiying.sizechart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: qiu wanzi
 * @date: 2024年3月4日 0004
 * @version: 1.0
 * @description: TODO
 */
@Slf4j
public class ParseSizeChart {


    public static void main(String[] args) {
        String sizeChartJson = "[\n" +
                "    {\n" +
                "        \"elasticity\": null,\n" +
                "        \"detail\": [\n" +
                "            {\n" +
                "                \"size\": \"XS\",\n" +
                "                \"itemDetail\": [\n" +
                "                    {\n" +
                "                        \"item\": \"Bust\",\n" +
                "                        \"optionDetail\": [\n" +
                "                            {\n" +
                "                                \"option\": \"Relax\",\n" +
                "                                \"length\": \"3\"\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"option\": \"Stretched\",\n" +
                "                                \"length\": \"12\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"size\": \"S\",\n" +
                "                \"itemDetail\": [\n" +
                "                    {\n" +
                "                        \"item\": \"Bust\",\n" +
                "                        \"optionDetail\": [\n" +
                "                            {\n" +
                "                                \"option\": \"Relax\",\n" +
                "                                \"length\": \"2\"\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"option\": \"Stretched\",\n" +
                "                                \"length\": \"22\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"size\": \"M\",\n" +
                "                \"itemDetail\": [\n" +
                "                    {\n" +
                "                        \"item\": \"Bust\",\n" +
                "                        \"optionDetail\": [\n" +
                "                            {\n" +
                "                                \"option\": \"Relax\",\n" +
                "                                \"length\": \"2\"\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"option\": \"Stretched\",\n" +
                "                                \"length\": \"3\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        \"suitPart\": \"TOP\"\n" +
                "    }\n" +
                "]";
        if (StringUtils.isEmpty(sizeChartJson)) {
            return;
        }

        List<SizeChart> sizeChartList = parseSizeChartJson(sizeChartJson);
        if (CollectionUtils.isEmpty(sizeChartList)) {
            return;
        }

        String sizeChartTable = LASOCIETYHtmlSizeChartCreator.getInstance().createSizeChartTable(sizeChartList);


        log.info("sizeChartTable {}", sizeChartTable);

    }

    private static List<SizeChart> parseSizeChartJson(String sizeChartJson) {
        String json = sizeChartJson.startsWith("{") ? String.format("[%s]", sizeChartJson) : sizeChartJson;

        List<SizeChart> sizeChartList = new ArrayList<>();
        try {
            List<JSONObject> sizeChartMapList = JSON.parseObject(json, List.class);
            sizeChartMapList.forEach((JSONObject jsonObject) -> {
                String jsonString = JSON.toJSONString(jsonObject);
                SizeChart parse = JSON.parseObject(jsonString, SizeChart.class);
                sizeChartList.add(parse);
            });
        } catch (Exception e) {
            log.error("非常规size chart,不可转换 {}", sizeChartJson, e);
        }
        return sizeChartList;
    }
}
