package com.comeup.easyexcel;

import cn.hutool.core.map.MapUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class UploadDynamicExcelListener extends AnalysisEventListener<LinkedHashMap<Integer, String>>  {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadDynamicExcelListener.class);
    /**
     * 每隔100条存储数据库
     */
    private static final int BATCH_COUNT = 3;
    /**
     * 总数
     */
    private Integer totalCount = 0;
    /**
     * 错误总数
     */
    private Integer errorCount = 0;
    private final LinkedHashMap<Integer, String> header = new LinkedHashMap<>();
    private final LinkedHashMap<Integer, String> errorHeader = new LinkedHashMap<>();
    // 存放读取excel的数据
    private final List<LinkedHashMap<Integer, String>> list = new ArrayList<>();
    // 存放错误数据
    private final List<LinkedHashMap<Integer, String>> errorList = new ArrayList<>();
    private final ExcelWriter excelWriter;
    private final WriteSheet writeSheet;
    private final OutputStream outputStream;

    public UploadDynamicExcelListener() {
        try {
            this.outputStream = new FileOutputStream("D://error.xlsx");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.excelWriter = EasyExcel.write(outputStream).excelType(ExcelTypeEnum.XLSX).build();
        this.writeSheet = EasyExcel.writerSheet(0).build();
    }
    /**
     * 每条数据解析都会来调用
     */
    @Override
    public void invoke(LinkedHashMap<Integer, String> data, AnalysisContext context) {
        String dataJson = JSON.toJSONString(data);
        LOGGER.info("UploadExcelListener 解析到一条数据:{}", dataJson);
        if ("{}".equals(dataJson)) {
            return;
        }
        if (MapUtil.isEmpty(header)) {
            data.forEach((k, v) -> {
                header.put(k, v);
            });
        } else {
            list.add(data);
        }
        if (list.size() >= BATCH_COUNT) {
            this.handleData();
        }
        if (errorList.size() >= BATCH_COUNT) {
            this.handleValidationFailData();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        try {
            if (this.totalCount == 0 && this.list.isEmpty() && this.errorList.isEmpty()) {
                LOGGER.info("upload excel not data！");
                return;
            }
            this.handleData();
            this.handleValidationFailData();
        } finally {
            if (this.excelWriter != null) {
                this.excelWriter.finish();
            }
        }
        LOGGER.info("upload excel handle completed！");
    }

    private void handleData() {
        if (this.list.isEmpty()) {
            return;
        }
        this.errorList.addAll(this.list);

        this.errorList.forEach(item -> item.put(header.size(), "错误的信息"));
    }


    /**
     * 处理验证失败的数据
     */
    private void handleValidationFailData() {
        if (this.errorList.isEmpty()) {
            return;
        }
        List<LinkedHashMap<Integer, String>> batchErrorList;
        if (MapUtil.isEmpty(errorHeader)) {
            header.forEach((k, v) -> {
                errorHeader.put(k, v);
            });
            errorHeader.put(header.size(), "错误信息");
            batchErrorList = new ArrayList<>(this.errorList.size() + 1);
            batchErrorList.add(errorHeader);
            batchErrorList.addAll(this.errorList);
        } else {
            batchErrorList = this.errorList;
        }
        this.totalCount = this.totalCount + this.errorList.size();
        this.errorCount = this.errorCount + this.errorList.size();
        this.excelWriter.write(batchErrorList, this.writeSheet);
        this.errorList.clear();
    }

}
