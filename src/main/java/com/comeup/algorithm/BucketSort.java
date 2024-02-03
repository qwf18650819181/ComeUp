package com.comeup.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @auth: qwf
 * @date: 2024年1月15日 0015
 * @description:
 */
public class BucketSort implements ISort {
    @Override
    public int[] sort(int[] array) {
        bucketSort1(array, 3);
        return array;
    }

    /**
     * public static void sort(int[] array, int bucketSize) {
     *         if (array.length == 0) {
     *             return;
     *         }
     *
     *         int minValue = array[0];
     *         int maxValue = array[0];
     *         for (int i = 1; i < array.length; i++) {
     *             if (array[i] < minValue) {
     *                 minValue = array[i];
     *             } else if (array[i] > maxValue) {
     *                 maxValue = array[i];
     *             }
     *         }
     *
     *         int bucketCount = (maxValue - minValue) / bucketSize + 1;
     *         ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketCount);
     *         for (int i = 0; i < bucketCount; i++) {
     *             bucketArr.add(new ArrayList<>());
     *         }
     *
     *         for (int i = 0; i < array.length; i++) {
     *             bucketArr.get((array[i] - minValue) / bucketSize).add(array[i]);
     *         }
     *
     *         int currentIndex = 0;
     *         for (int i = 0; i < bucketCount; i++) {
     *             if (bucketSize == 1) {
     *                 for (int j = 0; j < bucketArr.get(i).size(); j++) {
     *                     array[currentIndex++] = bucketArr.get(i).get(j);
     *                 }
     *             } else {
     *                 if (bucketCount == 1) {
     *                     bucketSize--;
     *                 }
     *                 Integer[] temp = new Integer[bucketArr.get(i).size()];
     *                 bucketArr.get(i).toArray(temp);
     *                 sort(Arrays.stream(temp).mapToInt(Integer::intValue).toArray(), bucketSize); ???
     *                 for (int j = 0; j < temp.length; j++) {
     *                     array[currentIndex++] = temp[j];
     *                 }
     *             }
     *         }
     *     }
     * @param array
     * @param bucketRange
     */
    public void bucketSort(int[] array, int bucketRange) {
        if (array.length == 0) {
            return;
        }
        int max = SortUtil.findMax(array);
        int min = SortUtil.findMin(array);
        int bucketCount = (max - min) / bucketRange + 1;
        List<List<Integer>> bucketArray = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            bucketArray.add(new ArrayList<>());
        }
        for (int i = 0; i < array.length; i++) {
            bucketArray.get((array[i] - min) / bucketRange).add(array[i]);
        }

        int currentIndex = 0;
        for (int i = 0; i < bucketCount; i++) {
            if (!bucketArray.get(i).isEmpty()) {
                Collections.sort(bucketArray.get(i));
                for (int j = 0; j < bucketArray.get(i).size(); j++) {
                    array[currentIndex++] = bucketArray.get(i).get(j);
                }
            }
        }

    }
    public void bucketSort1(int[] array, int bucketRange) {
        if (array.length == 0) {
            return;
        }
        int max = SortUtil.findMax(array);
        int min = SortUtil.findMin(array);
        int bucketCount = (max - min) / bucketRange + 1;
        List<List<Integer>> bucketArray = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            bucketArray.add(new ArrayList<>());
        }
        for (int i = 0; i < array.length; i++) {
            bucketArray.get((array[i] - min) / bucketRange).add(array[i]);
        }

        int currentIndex = 0;
        for (int i = 0; i < bucketCount; i++) {
            if (bucketArray.get(i).isEmpty()) continue;
            Collections.sort(bucketArray.get(i));
            for (int j = 0; j < bucketArray.get(i).size(); j++) {
                array[currentIndex++] = bucketArray.get(i).get(j);
            }

        }


    }


}