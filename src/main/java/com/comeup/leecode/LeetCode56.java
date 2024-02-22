package com.comeup.leecode;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 *
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class LeetCode56 {

    public static void main(String[] args) {
        int[][] intervals = {{2, 3}, {5, 5}, {2, 2}, {3, 4}, {3, 4}};
        System.out.println(merge(intervals));
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return intervals;
        }
        Map<Integer, Integer> sortMap = new TreeMap<>();
        for (int[] array : intervals) {
            sortMap.put(array[0], Math.max(array[1], sortMap.getOrDefault(array[0], 0)));
        }
        Map<Integer, Integer> mergeMap = new LinkedHashMap<>();

        AtomicReference<Integer> min = new AtomicReference<>(null);
        AtomicReference<Integer> max = new AtomicReference<>(null);

        sortMap.forEach((k, v) -> {
            if (min.get() == null) {
                min.set(k);
                max.set(v);
                mergeMap.put(min.get(), max.get());
                return;
            }
            if (k > max.get()) {
                mergeMap.put(min.get(), max.get());
                min.set(k);
                max.set(v);
                mergeMap.put(min.get(), max.get());
            } else {
                max.set(Math.max(v, max.get()));
                mergeMap.put(min.get(), max.get());
            }
        });
        int[][] result = new int[mergeMap.size()][2];

        AtomicInteger i = new AtomicInteger(0);
        mergeMap.forEach((k, v) -> {
            result[i.get()][0] = k;
            result[i.getAndIncrement()][1] = v;
        });
        return result;
    }





}
