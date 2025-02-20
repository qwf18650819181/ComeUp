package com.comeup.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @auth: qwf
 * @date: 2024年1月8日 0008
 * @description:
 */
public class EasyExcel2211 {

    public static void main(String[] args) throws FileNotFoundException {

        ReportReadListener<WalmartInventoryHealthReportHeaderV20250219> listener = new ReportReadListener<>();
        EasyExcel.read(new FileInputStream("D://inventoryHealth.csv"), WalmartInventoryHealthReportHeaderV20250219.class, listener).autoCloseStream(Boolean.TRUE).excelType(ExcelTypeEnum.CSV).doReadAllSync();

        listener.getList().forEach(item -> {
            System.out.println(JSON.toJSONString(item));
        });

    }


    public static class ReportReadListener<T> implements ReadListener<T> {
        private List<T> list = new ArrayList<>();

        public List<T> getList() {
            return list;
        }

        public void setList(List<T> list) {
            this.list = list;
        }

        @Override
        public void invoke(T data, AnalysisContext context) {
            list.add(data);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {

        }
    }



}
