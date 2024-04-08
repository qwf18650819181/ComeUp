package com.comeup.algorithm.base;

/**
 * @auth: qwf
 * @date: 2024年1月15日 0015
 * @description:
 *  time: O(n^2)
 *  稳定
 */
public class BubbleSort implements ISort {
    @Override
    public void sort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    SortUtil.swap(array, j + 1, j);
                }
            }
        }
    }
    public int[] sort1(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    SortUtil.swap(array, j + 1, j);
                }
            }
        }
        return array;
    }
}
