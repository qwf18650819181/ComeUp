package com.comeup.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @auth: qwf
 * @date: 2023年12月28日 0028
 * @description:
 * 最大子数组和
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 */
public class MaxSumFromChildArray {
    class Solution {
        public int maxSubArray(int[] nums) {
            List<Integer> list = new ArrayList<>();
            if (nums.length == 0) return 0;
            if (nums.length == 1) return nums[0];
            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                int max = num;
                if (i < nums.length - 1) {
                    for (int j = i + 1; j < nums.length; j++) {
                        num += nums[j];
                        if (num > max) max = num;
                    }
                }
                list.add(max);
            }
            if (list.size() == 0) return 0;
            return list.stream().mapToInt(Integer::valueOf).max().getAsInt();
        }
    }
}
