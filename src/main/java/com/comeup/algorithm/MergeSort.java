package com.comeup.algorithm;

/**
 * @auth: qwf
 * @date: 2024年1月15日 0015
 * @description:
 * time: O(n log N)
 */
public class MergeSort implements ISort {
    @Override
    public int[] sort(int[] array) {
        mergeSort(array, 0, array.length - 1);
        return array;
    }

    public void mergeSort(int[] array, Integer left, Integer right) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            merge(array, left, middle, right);
        }
    }

    public void merge(int[] array, int left, int mid, int right) {
        int[] newArray = new int[right - left + 1];
        int index = 0;
        int rightIndex = mid + 1;
        int tempI = left;
        while (left <= mid && rightIndex <= right) {
            newArray[index++] = array[left] > array[rightIndex] ? array[rightIndex++] : array[left++];
        }
        while (left <= mid) newArray[index++] = array[left++];
        while (rightIndex <= right) newArray[index++] = array[rightIndex++];


        for (int i = 0; i < newArray.length; i++) {
            array[tempI++] = newArray[i];
        }


    }

    public void mergeSort1(int[] array, Integer left, Integer right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort1(array, left, mid);
            mergeSort1(array, mid + 1, right);
            merge1(array, left, mid, right);
        }
    }

    public void merge1(int[] array, int left, int mid, int right) {
        int length = right - left + 1;
        int[] temp = new int[length];
        int tempI = left;
        int midP = mid + 1;
        int index = 0;
        while (left <= mid && midP <= right) {
            temp[index++] = array[left] > array[midP] ? array[midP++] : array[left++];
        }
        while (left <= mid) temp[index++] = array[left++];
        while (midP <= right) temp[index++] = array[midP++];
        for (int i = 0; i < temp.length; i++) {
            array[tempI++] = temp[i];
        }
    }


}