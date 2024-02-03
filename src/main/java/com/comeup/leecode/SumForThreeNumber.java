package com.comeup.leecode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @auth: qwf
 * @date: 2023年12月20日 0020
 * @description: 三数之和
 */
public class SumForThreeNumber {


}
class Solution {
    /**
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     * 解释：
     * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
     * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
     * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
     * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
     * Cn3
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) return new ArrayList<>();
        List<List<Integer>> list = new ArrayList<>();
        int length = nums.length;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < length - 2; i++) {
            for (int j = i + 1; j < length - 1; j++) {
                for (int k = j + 1; k < length; k++) {
                    List<Integer> innerList = new ArrayList<>();
                    innerList.add(nums[i]);
                    innerList.add(nums[j]);
                    innerList.add(nums[k]);
                    if (innerList.stream().mapToInt(Integer::intValue).sum() == 0) {
                        List<Integer> sortList = innerList.stream().sorted(Comparator.comparing(Integer::intValue)).collect(Collectors.toList());
                        String setString = sortList.stream().map(String::valueOf).collect(Collectors.joining(","));
                        if (set.contains(setString)) {
                            continue;
                        }
                        set.add(setString);
                        list.add(sortList);
                    }
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(lists);
    }
}
