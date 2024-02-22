package com.comeup.algorithm;

import cn.hutool.core.date.StopWatch;

/**
 * @auth: qwf
 * @date: 2024年1月15日 0015
 * @description:
 * 选择
 * 冒泡
 * 插入
 * 快速排序
 * 希尔
 * 归并
 * 桶
 * 基数
 */
public class SortTest {

    public static void main(String[] args) {
        int[] number = new int[]{5, 6, 2, 8, 7, 1, 4, 9, 3};
        StopWatch stopWatch = new StopWatch();
        System.out.print("start : ");
        SortUtil.printArray(number);
        stopWatch.start();
//        ISort sort = new SelectionSort();
//        ISort sort = new BubbleSort();
//        ISort sort = new InsertSort();
//        ISort sort = new ShellSort();
        ISort sort = new MergeSort();
//        ISort sort = new QuickSort();
//        ISort sort = new RadixSort();
//        ISort sort = new BucketSort();

        sort.sort(number);
        stopWatch.stop();
        System.out.println("end time: " + stopWatch.getTotalTimeMillis());
        SortUtil.printArray(number);
    }




}
