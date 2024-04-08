package com.comeup.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auth: qwf
 * @date: 2024年1月22日 0022
 * @description: 最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
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
 */
public class LongestSerial {

    public static void main(String[] args) {
        int[] array = new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int i = longestConsecutive(array);
        System.out.println(i);
    }

    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = map.getOrDefault(nums[i] - 1, new ArrayList<>());
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (list.isEmpty()) {
                list.add(nums[i]);
                map.put(nums[i], list);
            } else {
                list.add(nums[i]);
                map.put(nums[i], list);
                map.remove(nums[i] - 1);
            }
        }
        return map.values().stream().mapToInt(List::size).max().getAsInt();
    }


}
