package com.comeup.algorithm.zuo;

/**
 * @auth: qwf
 * @date: 2024年1月29日 0029
 * @description:
 */
public class Zuo1_Random {

    public static void main(String[] args) {

        double times = 100000;
        double count = 0;
        double max_value = 1000;
        double num = 300;
        for (int i = 0; i <times; i++) {
            double random = Math.random() * max_value;
            if (random <= num) count++;
        }

        System.out.println(count / times);



    }


}
