package com.comeup.hutool;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述: Hutool是一个Java工具包类库，对文件、流、加密解密、转码、正则、线程、XML等JDK方法进行封装，组成各种Util工具类，可以帮助我们提升开发效率。
 *
 * @author: qiu wanzi
 * @date: 2024年3月13日 0013
 * @version: 1.0
 */
public class Main {

    public static void main(String[] args) {

        // Convert
        BigDecimal bigDecimal = Convert.toBigDecimal("12", BigDecimal.ZERO);
        System.out.println(bigDecimal);
        String[] StrArr = { "1", "2", "3", "4" };
        Integer[] intArray = Convert.toIntArray(StrArr);
        System.out.println(Arrays.toString(intArray));

        String a = "20240506";
        String b = "2024-05-06";
        String c = "2024-05-06 12:00:02";
        Date aDate = Convert.toDate(a);
        Date bDate = Convert.toDate(b);
        Date cDate = Convert.toDate(c);
        System.out.println(aDate);
        System.out.println(bDate);
        System.out.println(cDate);

        Object[] arrObj = {"a", "你", "好", "", 1};
        List<?> list = Convert.convert(List.class, arrObj);
        System.out.println(list);
        System.out.println("================");


        // date
        Date date = DateUtil.date();
        //当前时间
        Date date2 = DateUtil.date(Calendar.getInstance());
        System.out.println(date);
        System.out.println(date2);
        Date endOfYear = DateUtil.endOfYear(new Date()).toJdkDate();
        System.out.println(endOfYear);
        Date beginOfMonth = DateUtil.beginOfMonth(new Date()).toJdkDate();
        System.out.println(beginOfMonth);
        DateTime parse = DateUtil.parse("2024-03-13", "yyyy-MM-dd");
        System.out.println(parse);
        System.out.println("================");

        String removeSuffix = StrUtil.removeSuffix("removePrefix", "Prefix");
        System.out.println(removeSuffix);
        String format = StrUtil.format("wo {} ni", "kansi");
        System.out.println(format);
        System.out.println("================");

        Method[] methods = ReflectUtil.getMethods(Main.class);
        System.out.println(Arrays.toString(methods));
        System.out.println("================");

        Snowflake snowflake = IdUtil.getSnowflake();
        System.out.println(snowflake.nextId());
        System.out.println("================");

        SubPerson person = new SubPerson();
        person.setAge(14);
        person.setName("11213232");
        person.setAddress("测试A11");
        person.setSex("sub名字");
        person.setUserAddress("useraddress");
        Map<String, Object> beanToMap = BeanUtil.beanToMap(person);
        System.out.println(beanToMap);
        Map<String, Object> beanToMapNew = new HashMap<>();
        BeanUtil.beanToMap(person, beanToMapNew, true, true);
        System.out.println(beanToMapNew);
        System.out.println("================");


    }

    @Data
    public static class SubPerson {
        private String name;
        private int age;
        private String sex;
        private String address;
        private String userAddress;
    }



}
