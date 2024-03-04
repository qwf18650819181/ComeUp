package com.comeup.shiying;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @auth: qwf
 * @date: 2024年1月9日 0009
 * @description:
 */
public class Main {

    public static void main(String[] args) {
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
    }
}
