package com.comeup.algorithm.zuo;

import java.util.Arrays;

/**
 * @auth: qwf
 * @date: 2024年1月29日 0029
 * @description:
 */
public class Zuo3_BinarySearch {


    public static void main(String[] args) {

        int[] randomSortArr = getRandomSortArr();
        for (int i = 0; i < randomSortArr.length; i++) {
            System.out.print(" " + randomSortArr[i] + "[" + i + "]");
        }
        System.out.println();
        int im = find(randomSortArr, 50);
        System.out.println(im);
        System.out.println("============================");
        int index = findMinIndex(randomSortArr, 50);
        System.out.println(index);
        System.out.println("============================");
        int[] randomArrWithNoContinuous = getRandomArrWithNoContinuous();
        for (int i = 0; i < randomArrWithNoContinuous.length; i++) {
            System.out.print(" " + randomArrWithNoContinuous[i] + "[" + i + "]");
        }
        System.out.println();
        int partialMinIndex = findPartialMinIndex(randomArrWithNoContinuous);
        System.out.println(partialMinIndex);
        System.out.println("============================");


    }

    /**
     * 有序数组找num
     * @param arr
     * @param num
     * @return
     */
    public static int find(int[] arr, int num) {
        int index = -1;
        if (arr == null || arr.length == 0) return index;
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (right + left) / 2;
            if (arr[mid] == num) return mid;
            if (arr[mid] > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return index;
    }

    /**
     * 有序数组找>=num 最小下标
     * @param arr
     * @param num
     * @return
     */
    public static int findMinIndex(int[] arr, int num) {
        int index = -1;
        if (arr == null || arr.length == 0) return index;
        int left = 0;
        int right = arr.length - 1;
        if (arr[left] >= num) return left;
        if (arr[right] < num) return index;

        while (left <= right) {
            int mid = (right + left) / 2;

            if (arr[mid] >= num) {
                index = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }
        return index;
    }
    /**
     * 局部最小值(只要找一个)
     * @param arr
     * @return
     */
    public static int findPartialMinIndex(int[] arr) {
        int index = -1;
        if (arr == null || arr.length == 0) return index;
        if (arr.length == 1) return 0;
        int left = 0;
        int right = arr.length - 1;
        if (arr.length == 2) return arr[left] > arr[right] ? right : left;

        while (left <= right) {
            int mid = (left + right) / 2;
            int midLess = mid - 1;
            if (midLess < 0) midLess = 0;
            if (arr[mid] < arr[mid + 1] && arr[mid] <= arr[midLess]) return mid;

            if (arr[mid] < arr[mid + 1]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return index;

    }

    public static int[] getRandomSortArr() {
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000) + 1;
        }
        Arrays.sort(arr);
        return arr;
    }
    public static int[] getRandomArrWithNoContinuous() {
        int[] arr = new int[50];
        arr[0] = (int) (Math.random() * 50) + 1;
        for (int i = 1; i < arr.length; i++) {
            while ((arr[i] = (int) (Math.random() * 50) + 1) == arr[i - 1]);
        }
        return arr;
    }

}
