package com.comeup.algorithmserial;

/**
 * @auth: qwf
 * @date: 2024年1月28日 0028
 * @description: 打印int的二进制码
 */
public class Zuo0_PrintInt {

    public static void main(String[] args) {
        int num = Integer.MAX_VALUE;
        for (int i = 31; i >= 0; i--) {
            System.out.print((1 << i & num) == 0 ? "0" : "1");
        }
        System.out.println();
        System.out.println(Integer.toHexString(Integer.SIZE));
    }


}
