package com.comeup.shiying;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @auth: qwf
 * @date: 2024年1月9日 0009
 * @description:
 */
public class Main {

    public static void main(String[] args) {

        String[] arr = {
                "P2017#Chestnut",
                "P207#Beige",
                "P1017#Chestnut",
                "P1#Bright White",
                "P101#Snow White",
                "P4#Light Blue",
                "33#33",
                "P2#Black"
        };

        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int num1 = extractNumber(s1);
                int num2 = extractNumber(s2);
                return num1 - num2;
            }

            private int extractNumber(String s) {
                String num = s.replaceAll("\\D", "");  // Remove all non-digit characters
                return Integer.parseInt(num);
            }
        });

        for (String s : arr) {
            System.out.println(s);
        }

        CustomExcelResponse customExcelResponse = new CustomExcelResponse();
        customExcelResponse.setHeaders(NsyExcelUtil.getCommonHeads(FbaReplenishmentSalesPercentageExport.class));
        List<List<Object>> excelData = Arrays.asList("1","2","3").stream().map(dto -> {
            FbaReplenishmentSalesPercentageExport export = new FbaReplenishmentSalesPercentageExport();
            export.setSkc(dto);
            export.setSpu("spu");
            export.setParentCode("p code");
            export.setStoreName("storeName");
            export.setSysSalesPercentage("12312");
            return NsyExcelUtil.getData(FbaReplenishmentSalesPercentageExport.class, export);
        }).collect(Collectors.toList());
        customExcelResponse.setData(excelData);
        DownloadResponse downloadResponse = new DownloadResponse();
        downloadResponse.setTotalCount(200L);
        downloadResponse.setDataJsonStr(JSON.toJSONString(customExcelResponse));
        System.out.println(JSON.toJSONString(downloadResponse));

        System.out.println(isDecimal("123"));
        System.out.println(isDecimal("123.4"));
        System.out.println(isDecimal("0.4"));

    }
    private static final Pattern DECIMAL_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

    private static boolean isDecimal(String str) {
        return !StrUtil.isEmpty(str) && DECIMAL_PATTERN.matcher(str).matches();
    }

}
