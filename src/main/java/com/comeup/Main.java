package com.comeup;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @auth: qwf
 * @date: 2024年1月9日 0009
 * @description:
 */
public class Main {

    public static void main(String[] args) {


        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < args.length; i++) {
            map.merge(args[i], 1, Integer::sum);
        }

        System.out.println(JSON.toJSONString(map));


        int n = 13;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 1;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 2;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 4;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 8;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 16;
        System.out.println(Integer.toBinaryString(n));
        n = (n + 1) << 1;
        System.out.println(Integer.toBinaryString(n));


    }
}
