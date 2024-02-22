package com.comeup.algorithm;

/**
 * @auth: qwf
 * @date: 2024年1月15日 0015
 * @description:
 * time: O(n log N)
 */
public class QuickSort implements ISort {
    @Override
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public void quickSort(int[] array, int left, int right) {
        if (left < right) {
            int partition = partition(array, left, right);
            quickSort(array, left, partition - 1);
            quickSort(array, partition + 1, right);
        }
    }

    public int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int rightIndex = right - 1;
        while (left < rightIndex) {
            if (array[left] > pivot) {
                SortUtil.swap(array, left, rightIndex--);
            } else {
                left++;
            }
        }
        SortUtil.swap(array, ++rightIndex, right);
        return rightIndex;
    }

    public void quickSort1(int[] array, int left, int right) {
        if (left < right) {
            int partition = partition(array, left, right);
            // 基准不能放进去,不然会死循环
            quickSort(array, left, partition - 1);
            quickSort(array, partition + 1, right);
        }
    }

    public int partition1(int[] array, int left, int right) {
        int pivot = array[right];
        int leftBound = left - 1;
        for (int i = left; i < right; i++) {
            if (array[i] < pivot) {
                SortUtil.swap(array, i, ++leftBound);
            }
        }
        SortUtil.swap(array, right, ++leftBound);
        return leftBound;
    }






}