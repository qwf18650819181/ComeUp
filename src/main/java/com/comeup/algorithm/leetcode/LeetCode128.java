package com.comeup.algorithm.leetcode;

import lombok.Data;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能描述:
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * <p>
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 * <p>
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *
 * @author qiu wanzi
 * @date 2024年3月12日 0012
 */
public class LeetCode128 {

    public static void main(String[] args) {
        int[] nums = new int[]{9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6};
        int i = longestConsecutive(nums);
        System.out.println(i);
    }

    public static int longestConsecutive1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Integer max = Arrays.stream(nums).boxed().max(Integer::compareTo).get();
        Integer min = Arrays.stream(nums).boxed().min(Integer::compareTo).get();
        int[] bucket = new int[max - min + 1];
        for (int num : nums) {
            bucket[num - min]++;
        }
        int maxCount = 0;
        int tempCount = 0;
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == 0) {
                maxCount = Math.max(maxCount, tempCount);
                tempCount = 0;
            } else if (bucket[i] > 0) {
                tempCount++;
            }
            if (i == bucket.length - 1) {
                maxCount = Math.max(maxCount, tempCount);
            }
        }
        return maxCount;
    }

    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        AtomicInteger maxCount = new AtomicInteger();
        AtomicInteger tempCount = new AtomicInteger(1);
        Arrays.stream(nums).sorted().reduce((a, b) -> {
            if (a == b) {
                return b;
            } else if (a + 1 == b) {
                tempCount.getAndIncrement();
                return b;
            } else {
                maxCount.set(Math.max(maxCount.get(), tempCount.get()));
                tempCount.set(1);
                return b;
            }
        });
        maxCount.set(Math.max(maxCount.get(), tempCount.get()));
        return maxCount.get();
    }



}
