package com.comeup.algorithm.base;

/**
 * @auth: qwf
 * @date: 2024年1月15日 0015
 * @description: 基数排序 非比较排序
 * time: O(n + k)
 */
public class RadixSort implements ISort {
    @Override
    public void sort(int[] array) {
        int max = SortUtil.findMax(array);
        for (int i = 1; i < max; i *= 10) {
            radixSort(array, i);
        }
    }

    public void radixSort(int[] array, int base) {
        int[] count = new int[10];
        for (int i = 0; i < array.length; i++) {
            int index = (array[i] / base) % 10;
            count[index]++;
        }
        for (int i = 0; i < count.length - 1; i++) {
            count[i + 1] += count[i];
        }
        int[] copyArray = array.clone();
        for (int i = array.length - 1; i >= 0; i--) {
            int index = (copyArray[i] / base) % 10;
            array[count[index] - 1] = copyArray[i];
            count[index]--;
        }
    }
    public int[] sort1(int[] array) {
        int max = SortUtil.findMax(array);
        for (int i = 1; i < max; i *= 10) {
            radixSort1(array, i);
        }
        return array;
    }

    public void radixSort1(int[] array, int base) {
        int[] countArray = new int[10];
        for (int i = 0; i < array.length; i++) {
            countArray[(array[i] / base) % 10]++;
        }
        for (int i = 1; i < 10; i++) {
            countArray[i] += countArray[i - 1];
        }
        int[] copyArray = array.clone();
        for (int i = array.length - 1; i >= 0; i--) {
            int index = (copyArray[i] / base) % 10;
            array[countArray[index] - 1] = copyArray[i];
            countArray[index]--;
        }
    }





}