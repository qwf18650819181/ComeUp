package com.comeup.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @auth: qwf
 * @date: 2024年1月8日 0008
 * @description:
 */
public class DynamicListener extends AnalysisEventListener<LinkedHashMap<String, String>> {
    private List<LinkedHashMap<String, String>> list = new ArrayList<>();

    @Override
    public void invoke(LinkedHashMap<String, String> object, AnalysisContext context) {
        list.add(object);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {}

    public List<LinkedHashMap<String, String>> getList() {
        return list;
    }

    public void setList(List<LinkedHashMap<String, String>> list) {
        this.list = list;
    }
}
