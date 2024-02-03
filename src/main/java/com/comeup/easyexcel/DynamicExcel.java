package com.comeup.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @auth: qwf
 * @date: 2024年1月17日 0017
 * @description:
 */
public class DynamicExcel {


    private static final String FORMAT = "yyyy/MM/dd";

    public static void main(String[] args) throws IOException {

        String a = "[{\"0\":\"店铺\",\"1\":\"parent code\",\"2\":\"打款季节\",\"3\":\"2024/2/4\"},{\"0\":\"dokotoo-fba补货测试店铺-忽动\",\"1\":\"parecode0002\",\"2\":\"夏季\",\"3\":\"12\"}]";

        List<Map> maps = JSON.parseArray(a, Map.class);

        String array = "[[\"店铺\",\"parent code\",\"打款季节\",\"2024/1/14\",\"错误信息\"],[\"dokotoo-fba补货测试店铺-忽动\",\"parecode0002\",\"夏季\",\"12\",\"目标时间非周天/不能小于当天 : 2024/1/14\"]]";

        List<Collection> errorDataList = JSON.parseArray(array, Collection.class);
        FileOutputStream fileOutputStream = new FileOutputStream("D://2.xls");

        WriteSheet writeSheet = EasyExcel.writerSheet(0).build();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        List<List<String>> list = new ArrayList<>();
        List<String> head = new ArrayList<>();
        head.add("123");
        list.add(head);
        List<String> head1 = new ArrayList<>();
        head1.add("3123");
        list.add(head1);
        List<String> head2 = new ArrayList<>();
        head2.add("打款季节");
        list.add(head2);
        List<String> head3 = new ArrayList<>();
        head3.add("错误信息");
        list.add(head3);
        List<String> head4 = new ArrayList<>();
        head4.add("错误信息3");
        list.add(head4);
        ExcelWriter excelWriter = EasyExcel.write(outputStream).head(list)
//                .withTemplate("D://3.xlsx")
                .excelType(ExcelTypeEnum.XLS).build();
        excelWriter.write(errorDataList, writeSheet);
        excelWriter.finish();

        fileOutputStream.write(outputStream.toByteArray());
    }

}
