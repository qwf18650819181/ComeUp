package com.comeup.algorithm;

/**
 * @auth: qwf
 * @date: 2024年1月15日 0015
 * @description: 希尔排序
 *  time: O(n^2)
 */
public class ShellSort implements ISort {
    @Override
    public void sort(int[] array) {
        for (int gap = array.length >> 1; gap > 0; gap >>= 1) {
            for (int i = gap; i < array.length; i++) {
                int key = array[i];
                int j = i;
                while (j - gap >=0 && array[j - gap] > key) {
                    array[j] = array[j - gap];
                    j -= gap;
                }
                array[j] = key;
            }
        }
    }
    public int[] sort1(int[] array) {
        for (int gap = array.length >> 1; gap > 0; gap >>= 1) {
            for (int i = gap; i < array.length; i++) {
                int key = array[i];
                int j = i;
                while (j >= gap && array[j - gap] > key) {
                    array[j] = array[j - gap];
                    j -= gap;
                }
                array[j] = key;
            }
        }
        return array;
    }
}