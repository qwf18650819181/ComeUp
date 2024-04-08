package com.comeup.algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 功能描述: 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长
 * 子串
 *  的长度。
 *
 *  示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * @author qiu wanzi
 * @date 2024年3月12日 0012
 */
public class LeetCode3 {

    int maxLength = 1;

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        if (s.length() == 1) return 1;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer((int) s.charAt(0));
        s.substring(1).chars().forEach(c -> {
            if (queue.contains(c)) {
                this.maxLength = Math.max(this.maxLength, queue.size());
                while (queue.poll() != c);
            }
            queue.offer(c);
        });
        this.maxLength = Math.max(this.maxLength, queue.size());
        return this.maxLength;
    }
}
