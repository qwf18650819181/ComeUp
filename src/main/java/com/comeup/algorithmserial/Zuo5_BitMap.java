package com.comeup.algorithmserial;

import lombok.extern.slf4j.Slf4j;

/**
 * @auth: qwf
 * @date: 2024年1月28日 0028
 * @description: 链表
 */
@Slf4j
public class Zuo5_BitMap {

    public static void main(String[] args) {

        BitMap bitMap = new BitMap(33);
        for (int i = 0; i < 33; i++) {
            bitMap.add(i);
        }
        for (int i : bitMap.arr) {
            log.info(Integer.toBinaryString(i));
        }
        for (int i = 0; i < 30; i++) {
            bitMap.remove(i);
        }
        for (int i : bitMap.arr) {
            log.info(Integer.toBinaryString(i));
        }
        log.info(bitMap.contain(31).toString());

        log.info("==================加减乘除==================");
        log.info(add(2, 3).toString());
        log.info(sub(2, 3).toString());
        log.info(multi(2, 3).toString());
        log.info(div(2, 3).toString());



    }


    public static Integer add(int a, int b) {
        int tem1 = a ^ b;
        int tem2 = (a & b) << 1;
        int ans = tem1;
        while (tem2 != 0) {
            ans = tem1 ^ tem2;
            tem2 = (tem1 & tem2) << 1;
            tem1 = ans;
        }
        return ans;
    }
    public static Integer sub(int a, int b) {
        return add(a, add(~b, 1));
    }
    public static Integer multi(int a, int b) {
        int i = 1;
        int ans = 0;
        int num = 0;
        while (i <= a) {
            if ((a & i) != 0) {
                ans = add(ans, b << num);
            }
            num = add(num, 1);
            i <<= 1;
        }
        return ans;
    }

    /**
     * TODO: 未完成
     * @param a
     * @param b
     * @return
     */
    public static Integer div(int a, int b) {
        return 0;
    }





    /**
     * 位图
     */
    public static class BitMap {

        private int[] arr;

        public BitMap(int maxValue) {
            this.arr = new int[(maxValue + 32) >>> 5];
        }

        public void add(int value) {
            arr[value >>> 5] |= (1 << (((1 << 5) - 1) & value));
        }

        public void remove(int value) {
            arr[value >>> 5] &= ~(1 << (((1 << 5) - 1) & value));
        }

        public Boolean contain(int value) {
            int i = 1 << (((1 << 5) - 1) & value);
            log.info(Integer.toBinaryString(i));
            int i1 = arr[value >>> 5];
            log.info(Integer.toBinaryString(i1));
            return (i1 & i) != 0;
        }


    }






}
