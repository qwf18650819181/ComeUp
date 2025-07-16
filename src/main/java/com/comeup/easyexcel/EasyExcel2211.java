package com.comeup.easyexcel;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auth: qwf
 * @date: 2024年1月8日 0008
 * @description:
 */
@Slf4j
public class EasyExcel2211 {

    public static void main(String[] args) throws FileNotFoundException {
        long timestampInMilliseconds = 1744823817 * 1000L;

        // 使用时间戳构造 Date 对象
        Date date = new Date(timestampInMilliseconds);

        System.out.println(date);


        ReportReadListener<WalmartInventoryHealthReportHeaderV20250519> listener = new ReportReadListener<>();
        EasyExcel.read(new FileInputStream("D://WFSInventoryHealthReport_2025-05-19T01_19_03.828776873Z.csv"), WalmartInventoryHealthReportHeaderV20250519.class, listener).autoCloseStream(Boolean.TRUE).excelType(ExcelTypeEnum.CSV).doReadAllSync();

        List<ReportWalmartInventoryHealthReportEntity> list = new ArrayList<>();
        listener.getList().forEach(walmartInventoryHealthReportHeader -> {
            ReportWalmartInventoryHealthReportEntity reportEntity = new ReportWalmartInventoryHealthReportEntity();
            BeanUtil.copyProperties(walmartInventoryHealthReportHeader, reportEntity, "predictedOutOfStockDate", "daysOfSupplyBasedOnLast30DaysSales");
            reportEntity.setPublished("Published".equalsIgnoreCase(walmartInventoryHealthReportHeader.getPublishingStatus()) ? 1 : 0);
            if (StrUtil.isNotEmpty(walmartInventoryHealthReportHeader.getPredictedOutOfStockDate()) && !"--".equals(walmartInventoryHealthReportHeader.getPredictedOutOfStockDate())) {
                try {
                    reportEntity.setPredictedOutOfStockDate(DateUtil.parse(walmartInventoryHealthReportHeader.getPredictedOutOfStockDate(), "yyyy-MM-dd"));
                } catch (Exception e) {
                    log.error("setPredictedOutOfStockDate error", e);
                }
            }
            if (NumberUtil.isInteger(walmartInventoryHealthReportHeader.getDaysOfSupplyBasedOnLast30DaysSales())) {
                reportEntity.setDaysOfSupplyBasedOnLast30DaysSales(Integer.valueOf(walmartInventoryHealthReportHeader.getDaysOfSupplyBasedOnLast30DaysSales()));
            } else {
                reportEntity.setDaysOfSupplyBasedOnLast30DaysSales(0);
            }
            list.add(reportEntity);
        });

        System.out.println(list);

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
