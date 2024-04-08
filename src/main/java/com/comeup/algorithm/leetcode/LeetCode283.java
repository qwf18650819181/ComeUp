package com.comeup.algorithm.leetcode;

/**
 * 功能描述: 283. 移动零
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * 示例 1:
 *
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 示例 2:
 *
 * 输入: nums = [0]
 * 输出: [0]
 * @author qiu wanzi
 * @date 2024年3月12日 0012
 */
public class LeetCode283 {

    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        LeetCode283 leetCode283 = new LeetCode283();
        leetCode283.moveZeroes(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 1) return;
        int zeroIndex = nums[0] == 0 ? 0 : 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) continue;
            if (zeroIndex != i) swap(nums, i, zeroIndex);
            zeroIndex++;
        }
    }

    private void swap(int[] arr, int i, int k) {
        int temp = arr[i];
        arr[i] = arr[k];
        arr[k] = temp;
    }
}
