package com.comeup.shiying.json;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年7月30日 星期二
 * @version: 1.0
 */
@Slf4j
public class Main {

    public static void main(String[] args) throws JsonProcessingException {
        String a = "1181x1610";
        String replaceOriginSize = a.replace("x", "\\*");
        System.out.println(replaceOriginSize);

        System.out.println(String.valueOf(new Date().getTime()));

        BigDecimal divide = new BigDecimal("300.00").divide(new BigDecimal("1000"), RoundingMode.HALF_UP);
        System.out.println(divide);

        BigDecimal finalVolumeWeight = null;
        BigDecimal weight = new BigDecimal("428.00");
        ScriptEngine js = new ScriptEngineManager().getEngineByName("JavaScript");
        String formula = "1.3";
        if (!formula.contains("a") && !formula.contains("b")) {
            formula += "*b";
        }
        try {
            String finalFormula = formula.replaceAll("a", Optional.ofNullable(null).orElse(BigDecimal.ZERO).toString())
                    .replaceAll("b", Optional.ofNullable(weight).orElse(BigDecimal.ZERO).toString());
            Number result = (Number) js.eval(finalFormula);
            finalVolumeWeight = BigDecimal.valueOf(result.doubleValue());
        } catch (Exception e) {
            log.error("calculateVolumeWeightByFormula error {}", e.getMessage(), e);
        }
        log.info("[参数] ConfigPriceFormulaService calculateVolumeWeightByFormula volumeWeight: {} timestamp: {}", finalVolumeWeight, LocalDateTime.now());



        TiktokShopPublishProductResponse tiktokShopPublishProductResponse = new TiktokShopPublishProductResponse();
        tiktokShopPublishProductResponse.setCode(null);
        tiktokShopPublishProductResponse.setMessage("fasdf");
        tiktokShopPublishProductResponse.setRequestId("fasdf");
        tiktokShopPublishProductResponse.setData(TiktokShopPublishProductResponse.DataBody.builder().spuCode("123").build());

        TiktokShopPublishProductResponse tiktokShopPublishProductResponse1 = new TiktokShopPublishProductResponse();
        tiktokShopPublishProductResponse1.setCode(1);
        BeanUtils.copyProperties(tiktokShopPublishProductResponse, tiktokShopPublishProductResponse1);

        String jsonString = JSON.toJSONString(tiktokShopPublishProductResponse1);
        System.out.println(jsonString);

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        String collect = list.stream().collect(Collectors.joining());
        System.out.println(collect);


        ObjectMapper mapper = new ObjectMapper();
        // 自定义配置
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String s = mapper.writeValueAsString(tiktokShopPublishProductResponse);
        System.out.println(s);

        String originSize = "1200*1636";

        String[] split = originSize.split("\\*");
        boolean equals = split[0].equals(split[1]);
        System.out.println(equals);

        cropOssImageInCenter("1200*1636", 1350, 1800);


        String s1 = extractContentInsideParentheses("A-line(A型)");
        System.out.println(s1);

        List<String> picShowTypeList = new ArrayList<>();
        picShowTypeList.add("1");
        picShowTypeList.add("2");

        String s2 = picShowTypeList.get(0);
        picShowTypeList.remove(0);




    }


    public static String extractContentInsideParentheses(String input) {
        // 创建一个正则表达式模式，用于匹配括号内的内容
        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(input);

        // 检查是否找到匹配
        if (matcher.find()) {
            return matcher.group(1);  // 返回第一个括号组的内容
        }

        return "";  // 如果没有找到匹配，返回空字符串
    }



    public static void cropOssImageInCenter(String originSize, int width, int height) {
        String[] originSizeSplit = originSize.split("\\*");
        int originWidth = Integer.parseInt(originSizeSplit[0]);
        int originHeight = Integer.parseInt(originSizeSplit[1]);
        int processedWidth;
        int processedHeight;
        if ((originWidth / originHeight) > (width / height)) {
            processedHeight = originHeight;
            processedWidth = originHeight * width / height;
        } else {
            processedWidth = originWidth;
            processedHeight = originWidth * height / width;
        }
        System.out.println(processedWidth);
        System.out.println(processedHeight);
    }
}
