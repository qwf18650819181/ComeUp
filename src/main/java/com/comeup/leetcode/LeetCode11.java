package com.comeup.leetcode;

import java.util.Arrays;

/**
 * @auth: qwf
 * @date: 2024年1月25日 0025
 * @description:
 * 11. 盛最多水的容器
 * 中等
 * 相关标签
 * 相关企业
 * 提示
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 *
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 返回容器可以储存的最大水量。
 *
 * 说明：你不能倾斜容器。
 *
 *
 * 案例:
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 示例 2：
 *
 * 输入：height = [1,1]
 * 输出：1
 */
public class LeetCode11 {


    /**
     * 通过,但是代码太臃肿
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        if (height.length == 0) return 0;
        int[] clone = height.clone();
        Arrays.sort(clone);
        int maxValue1 = clone[height.length - 1];
        int maxValue2 = clone[height.length - 2];
        int leftIndex = 0;
        int rightIndex = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] == maxValue1 || height[i] == maxValue2) {
                leftIndex = i;
                break;
            }
        }
        for (int i = height.length - 1; i >= 0; i--) {
            if (height[i] == maxValue1 || height[i] == maxValue2) {
                rightIndex = i;
                break;
            }
        }
        int max = 0;
        for (int i = 0; i <= leftIndex; i++) {
            for (int j = height.length - 1; j >= rightIndex; j--) {
                int area = Math.min(height[i], height[j]) * (j - i);
                if (area > max) max = area;
            }
        }
        return max;
    }

    /**
     * 超时
     * @param height
     * @return
     */
    public int maxArea1(int[] height) {
        if (height.length == 0) return 0;
        int max = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int area = Math.min(height[i], height[j]) * (j - i);
                if (area > max) max = area;
            }
        }
        return max;
    }

}
