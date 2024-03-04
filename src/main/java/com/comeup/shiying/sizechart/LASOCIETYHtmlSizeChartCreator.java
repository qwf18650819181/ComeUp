package com.comeup.shiying.sizechart;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LASOCIETYHtmlSizeChartCreator {

    private LASOCIETYHtmlSizeChartCreator() {
    }

    private static class SingletonInstance {
        private static final LASOCIETYHtmlSizeChartCreator INSTANCE = new LASOCIETYHtmlSizeChartCreator();
    }

    public static LASOCIETYHtmlSizeChartCreator getInstance() {
        return SingletonInstance.INSTANCE;
    }
    
    private static final String TABLE_OPEN = "<table class=\"size-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"1\">";
    private static final String TABLE_CLOSE = "</table>";
    private static final String TBODY_OPEN = "<tbody>";
    private static final String TBODY_CLOSE = "</tbody>";
    private static final String TR_HEAD_OPEN = "<tr style=\"text-align:center;\" bgcolor=\"#fff\">";
    private static final String TR_BODY_OPEN = "<tr class=\"size-item size_inch_1\" style=\"\">";
    private static final String TR_CLOSE = "</tr>";
    private static final String TD_HEAD_OPEN = "<td style=\"text-align:center;font-weight:700;\" bgcolor=\"#fff\">";
    private static final String TD_BODY_SIZE_OPEN = "<td bgcolor=\"#ffffff\" style=\"font-weight:700;\" align=\"center\">";
    private static final String TD_BODY_OPEN = "<td bgcolor=\"#ffffff\" align=\"center\">";
    private static final String TD_CLOSE = "</td>";

    private static final String SIZE = "SIZE";
    private static final String HEAD_SPLIT = "-";

    public String createSizeChartTable(List<SizeChart> sizeChartList) {
        StringBuilder sizeChartTable = new StringBuilder(1024);
        sizeChartList.forEach(sizeChart -> {
            if (CollectionUtils.isEmpty(sizeChart.getDetail())) return;

            appendTableBegin(sizeChartTable);

            List<Map<String, String>> rowMapList = parseRowData(sizeChart);

            List<String> headerList = appendHeader(rowMapList, sizeChartTable);

            appendBody(rowMapList, sizeChartTable, headerList);

            appendTableEnd(sizeChartTable);
        });
        return sizeChartTable.toString();
    }

    private void appendTableEnd(StringBuilder sizeChartTable) {
        sizeChartTable.append(TBODY_CLOSE).append(TABLE_CLOSE);
    }

    private void appendTableBegin(StringBuilder sizeChartTable) {
        sizeChartTable.append(TABLE_OPEN).append(TBODY_OPEN).append(TR_HEAD_OPEN);
    }

    private void appendBody(List<Map<String, String>> rowMapList, StringBuilder sizeChartTable, List<String> headerList) {
        rowMapList.forEach(rowMap -> {
            sizeChartTable.append(TR_BODY_OPEN);
            headerList.forEach(header -> {
                if (SIZE.equals(header)) {
                    sizeChartTable.append(TD_BODY_SIZE_OPEN);
                } else {
                    sizeChartTable.append(TD_BODY_OPEN);
                }
                sizeChartTable.append(rowMap.getOrDefault(header, StringConstant.EMPTY_STRING)).append(TD_CLOSE);
            });
            sizeChartTable.append(TR_CLOSE);
        });
    }

    private List<String> appendHeader(List<Map<String, String>> rowMapList, StringBuilder sizeChartTable) {
        List<String> headerList = new ArrayList<>();
        rowMapList.get(0).forEach((header, v) -> {
            sizeChartTable.append(TD_HEAD_OPEN).append(header).append(TD_CLOSE);
            headerList.add(header);
        });
        sizeChartTable.append(TR_CLOSE);
        return headerList;
    }

    private List<Map<String, String>> parseRowData(SizeChart sizeChart) {
        List<Map<String, String>> rowMapList = new ArrayList<>();
        sizeChart.getDetail().forEach(item -> {
            Map<String, String> rowMap = new LinkedHashMap<>();
            rowMapList.add(rowMap);
            rowMap.put(SIZE, item.getSize());
            item.getItemDetail().forEach(itemDetail -> {
                String headName = beautifyConstant(itemDetail.getItem()) + HEAD_SPLIT;
                itemDetail.getOptionDetail().forEach(optionDetail -> {
                    String headDetail = beautifyConstant(optionDetail.getOption());
                    rowMap.put(headName + headDetail, beautifyConstant(optionDetail.getLength()));
                });
            });
        });
        return rowMapList;
    }

    private String beautifyConstant(String value) {
        return StringUtils.isBlank(value) ? StringConstant.EMPTY_STRING : value;
    }


}