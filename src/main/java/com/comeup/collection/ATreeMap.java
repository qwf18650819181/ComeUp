package com.comeup.collection;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @auth: qwf
 * @date: 2023年12月25日 0025
 * @description:
 */
public class ATreeMap {


    public static void main(String[] args) {
        Map<String, String> map = new TreeMap<>(Comparator.comparingInt(Integer::valueOf));
        map.put("1", "b");
        map.put("2", "a");
        map.put("0", "c");


        System.out.println(map.toString());



    }
}
