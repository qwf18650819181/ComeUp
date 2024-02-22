package com.comeup.algorithm;

/**
 * @auth: qwf
 * @date: 2024年1月15日 0015
 * @description:
 *  time: O(n^2)
 */
public class InsertSort implements ISort {
    @Override
    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int insertKey = array[i];
            int j = i - 1;
            while(j >= 0 && array[j] > insertKey) {
                array[j + 1] = array[j--];
            }
            array[j + 1] = insertKey;
        }
    }
    public int[] sort1(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int insertKey = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > insertKey) {
                array[j + 1] = array[j--];
            }
            array[j + 1] = insertKey;
        }
        return array;
    }
}