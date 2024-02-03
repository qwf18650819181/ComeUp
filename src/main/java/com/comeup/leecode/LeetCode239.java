package com.comeup.leecode;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * [[子串]]
 *
 * 滑动窗口最大值
 *
 *给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回 滑动窗口中的最大值 。
 *
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 *
 *  输入：nums = [1], k = 1
 * 输出：[1]
 *
 */
public class LeetCode239 {

    @Test
    public void test() {
//        int[] nums = {1,3,-1,-3,5,3,6,7};
//        int k = 3;
        int[] nums = {1,-1};
        int k = 1;
        int[] ints = maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 还是超时
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null) return null;
        int length = nums.length;
        if (length < k) return new int[]{ Arrays.stream(nums).max().getAsInt() };

        Deque<Integer> deque = new ArrayDeque(k);
        for (int i = 0; i < k; i++) {
            deque.add(nums[i]);
        }
        int[] ans = new int[length - k + 1];
        ans[0] = deque.stream().mapToInt(Integer::intValue).max().getAsInt();
        for (int i = k; i < length; i++) {
            deque.removeFirst();
            deque.add(nums[i]);
            ans[i - k + 1] = deque.stream().mapToInt(Integer::intValue).max().getAsInt();
        }
        return ans;
    }

    /**
     * fail : 速度太慢
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null) return null;
        int length = nums.length;
        if (length < k) return new int[]{ Arrays.stream(nums).max().getAsInt() };
        int[] ans = new int[length - k + 1];
        for (int i = 0; i <= length - k; i++) {
            int temp = IntStream.rangeClosed(i, i + k - 1).map(item -> nums[item]).max().getAsInt();
            ans[i] = temp;
        }
        return ans;
    }

    /**
     * 算错了, 算成和了
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow1(int[] nums, int k) {
        if (nums == null) return nums;
        int length = nums.length;
        if (length <= k) return nums;

        int[] clone = nums.clone();
        int max = IntStream.rangeClosed(0, k - 1).map(index -> nums[index]).sum();
        int maxIndex = 0;
        for (int i = 1; i <= length - k; i++) {
            clone[i] = IntStream.rangeClosed(i, i + k - 1).map(index -> nums[index]).sum();
            if (clone[i] > max) {
                max = clone[i];
                maxIndex = i;
            }
        }
        int[] ans = new int[k];
        AtomicInteger i = new AtomicInteger(0);
        IntStream.rangeClosed(maxIndex, maxIndex + k - 1).forEach(index -> {
            ans[i.getAndIncrement()] = nums[index];
        });
        return ans;
    }
}
