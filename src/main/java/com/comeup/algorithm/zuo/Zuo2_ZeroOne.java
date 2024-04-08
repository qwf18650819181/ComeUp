package com.comeup.algorithm.zuo;

import java.util.Arrays;

/**
 * @auth: qwf
 * @date: 2024年1月29日 0029
 * @description:
 */
public class Zuo2_ZeroOne {

    /**
     * [1,2,3,4,5] 等概率返回 -> 如何等概率返回 [1,2,3,4,5,6,7]
     * @param args
     */
    public static void main(String[] args) {
        double times = 100000;
        int[] count = new int[8];
        for (int i = 0; i <times; i++) {
            count[getRandom1_7()]++;
        }
        System.out.println(Arrays.toString(count));
        System.out.println("=====================");
        /**
         * f(0,1)不等概率 -> g(0,1)等概率
         */

        count = new int[2];
        for (int i = 0; i <times; i++) {
            count[getRandom0_1()]++;
        }
        System.out.println(Arrays.toString(count));
        System.out.println("=====================");

    }

    /**
     * 1. 7 - 1 + 1 = 7
     * 2. (0 - 6) + 1
     * 3. 2^3 > 6
     * @return
     */
    public static int getRandom1_7() {
        int i;
        while ((i = (getZeroOne() << 2) + (getZeroOne() << 1) + (getZeroOne())) > 6);
        return i + 1;
    }

    public static int getZeroOne() {
        int num;
        while ((num = getRandom1_5()) == 3);
        return num > 3 ? 1 : 0;
    }

    public static int getRandom1_5() {
        return (int) (Math.random() * 5) + 1;
    }

    public static int getNoRandom0_1() {
        return Math.random() < 0.3 ? 0 : 1;
    }

    public static int getRandom0_1() {

        int r1 = getNoRandom0_1();
        int r2 = getNoRandom0_1();
        while (r1 == r2) {
            r1 = getNoRandom0_1();
            r2 = getNoRandom0_1();
        }
        return r1 == 0 ? 0 : 1;
    }
}
