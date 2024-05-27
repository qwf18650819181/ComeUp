package com.comeup.shiying;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Converter;
import cn.hutool.core.convert.ConverterRegistry;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @auth: qwf
 * @date: 2024年1月9日 0009
 * @description:
 */
public class Main {

    private static BigDecimal calculateAccPriceByEstimateDearloverPrice(BigDecimal estimateDearloverPrice) {
        if (estimateDearloverPrice.compareTo(new BigDecimal("3")) >= 0) {
            return estimateDearloverPrice;
        }
        double finalPrice;
        double integerPart = Math.floor(estimateDearloverPrice.doubleValue());
        double fractionPart = estimateDearloverPrice.doubleValue() - integerPart;
        if (fractionPart <= 0.5) {
            finalPrice = integerPart + 0.98;
        } else {
            finalPrice = estimateDearloverPrice.doubleValue() + 1;
        }
        return new BigDecimal(String.valueOf(finalPrice));
    }

    public static void main1(String[] args) {
//        BigDecimal bigDecimal = calculateAccPriceByEstimateDearloverPrice(new BigDecimal("2.66"));
//        System.out.println(bigDecimal);
//        String[] arr = {
//                "P2017#Chestnut",
//                "P207#Beige",
//                "P1017#Chestnut",
//                "P1#Bright White",
//                "P101#Snow White",
//                "P4#Light Blue",
//                "33#33",
//                "P2#Black"
//        };
//
//        Arrays.sort(arr, new Comparator<String>() {
//            @Override
//            public int compare(String s1, String s2) {
//                int num1 = extractNumber(s1);
//                int num2 = extractNumber(s2);
//                return num1 - num2;
//            }
//
//            private int extractNumber(String s) {
//                String num = s.replaceAll("\\D", "");  // Remove all non-digit characters
//                return Integer.parseInt(num);
//            }
//        });
//
//        for (String s : arr) {
//            System.out.println(s);
//        }
//
//        CustomExcelResponse customExcelResponse = new CustomExcelResponse();
//        customExcelResponse.setHeaders(NsyExcelUtil.getCommonHeads(FbaReplenishmentSalesPercentageExport.class));
//        List<List<Object>> excelData = Arrays.asList("1","2","3").stream().map(dto -> {
//            FbaReplenishmentSalesPercentageExport export = new FbaReplenishmentSalesPercentageExport();
//            export.setSkc(dto);
//            export.setSpu("spu");
//            export.setParentCode("p code");
//            export.setStoreName("storeName");
//            export.setSysSalesPercentage("12312");
//            return NsyExcelUtil.getData(FbaReplenishmentSalesPercentageExport.class, export);
//        }).collect(Collectors.toList());
//        customExcelResponse.setData(excelData);
//        DownloadResponse downloadResponse = new DownloadResponse();
//        downloadResponse.setTotalCount(200L);
//        downloadResponse.setDataJsonStr(JSON.toJSONString(customExcelResponse));
//        System.out.println(JSON.toJSONString(downloadResponse));
//
//        System.out.println(isDecimal("123"));
//        System.out.println(isDecimal("123.4"));
//        System.out.println(isDecimal("0.4"));

        ConverterRegistry converterRegistry = ConverterRegistry.getInstance();

        converterRegistry.putCustom(Date.class, new Converter<Object>() {
            @Override
            public Object convert(Object value, Object defaultValue) throws IllegalArgumentException {
                if (value instanceof Date) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    return format.format((Date) value);
                }
                return defaultValue;
            }
        });

        SkuIndex skuIndex = new SkuIndex();
        skuIndex.setId(1);
        skuIndex.setCreateDate(new Date());
        skuIndex.setUpdateDate(new Date());

        System.out.println(JSON.toJSONString(skuIndex));
        System.out.println("----");

        Map<String, Object> map = BeanUtil.beanToMap(skuIndex);

        System.out.println(map.get("createDate").toString());



    }

    public static void main(String[] args) {
        recusive(1);


    }

    private static void recusive(int i) {
        if (i > 1000) {
            System.out.println(111);
            return;
        }
        List<String> newList = new ArrayList<>(100);
        for (int i1 = 0; i1 < 10000; i1++) {
            newList.add(String.valueOf(i1));
        }
        recusive(++i);
    }


    private static final Pattern DECIMAL_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

    private static boolean isDecimal(String str) {
        return !StrUtil.isEmpty(str) && DECIMAL_PATTERN.matcher(str).matches();
    }

}
