package com.comeup.leetcode;

/**
 * 功能描述: 42. 接雨水
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 *
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 * @author qiu wanzi
 * @date 2024年3月12日 0012
 */
public class LeetCode42 {
    public static void main(String[] args) {

        //[0,1,0,2,1,0,1,3,2,1,2,1]
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int trap = new LeetCode42().trap(height);
        System.out.println(trap);
    }

    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int stepVal = 0;
        int sum = 0;
        while (left < right) {
            while (height[left] <= stepVal && left < height.length - 1) {
                left++;
                if (left >= right) break;
            }
            while (height[right] <= stepVal && right > 1) {
                --right;
            }
            if (left >= right) break;
            int val = Math.min(height[left], height[right]);
            for (int i = left + 1; i < right; i++) {
                if (height[i] < val) {
                    sum += (val - Math.max(stepVal, height[i]));
                }
            }
            stepVal = val;
        }
        return sum;
    }

}
