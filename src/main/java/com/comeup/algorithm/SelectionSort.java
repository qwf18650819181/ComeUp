package com.comeup.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @auth: qwf
 * @date: 2024年1月15日 0015
 * @description:
 *
 * 1. 优化???
 *  大于的数组下标放进一个队列里面??
 *
 *  time: O(n^2)
 */
public class SelectionSort implements ISort {

    public int[] sort1(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int si = IntStream.range(i, array.length).reduce((a, b) -> array[a] > array[b] ? b : a).getAsInt();
            SortUtil.swap(array, si, i);
        }
        return array;
    }
    @Override
    public int[] sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = IntStream.range(i, array.length).reduce((a, b) -> array[a] > array[b] ? b : a).getAsInt();
            SortUtil.swap(array, i, minIndex);
        }
        return array;
    }
}