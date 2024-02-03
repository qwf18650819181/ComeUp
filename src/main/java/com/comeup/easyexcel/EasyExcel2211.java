package com.comeup.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @auth: qwf
 * @date: 2024年1月8日 0008
 * @description:
 */
public class EasyExcel2211 {

    public static void main(String[] args) throws FileNotFoundException {
        UploadDynamicExcelListener syncReadListener = new UploadDynamicExcelListener();
//        ExcelReaderBuilder excelReaderBuilder = EasyExcelFactory.read("D://1.xlsx", syncReadListener).autoCloseStream(true).ignoreEmptyRow(true);
//        excelReaderBuilder.sheet().doRead();
//
//        List<Object> list = syncReadListener.getList();


        List<List<String>> objects = EasyExcel.read(new FileInputStream("D://1.xlsx"), syncReadListener).headRowNumber(-1).sheet().doReadSync();
        System.out.println(JSON.toJSON(syncReadListener));
        System.out.println(JSON.toJSON(objects));


    }





}
