package com.comeup.algorithm;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @auth: qwf
 * @date: 2024年1月15日 0015
 * @description:
 */
public class SortUtil {
    public static void printArray(int[] array) {
        System.out.println(Arrays.stream(array).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    public static int[] getRandomArray(int arraySize, int arrayMax) {
        Random random = new Random();
        int[] array = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(arrayMax);
        }
        return array;
    }

    public static void swap(int[] array, int index1, int index2) {
        if (index2 != index1) {
            int temp = array[index1];
            array[index1] = array[index2];
            array[index2] = temp;
        }
    }

    public static int findMax(int[] array) {
        return Arrays.stream(array).max().getAsInt();
    }

    public static int findMin(int[] array) {
        return Arrays.stream(array).min().getAsInt();
    }

}
