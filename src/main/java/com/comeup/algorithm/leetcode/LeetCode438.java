package com.comeup.algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 功能描述:438. 找到字符串中所有字母异位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 * 示例 1:
 *
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *  示例 2:
 *
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 * @author qiu wanzi
 * @date 2024年3月12日 0012
 */
public class LeetCode438 {

    public List<Integer> findAnagrams(String s, String p) {
        if (s == null || s.isEmpty() || p == null || p.isEmpty() || s.length() < p.length()) return new ArrayList<>();
        Deque<Integer> container = new ArrayDeque<>(p.length());
        List<Integer> comparator = new ArrayList<>(p.length());
        List<Integer> ans = new ArrayList<>();
        comparator.addAll(p.chars().sorted().boxed().collect(Collectors.toList()));
        AtomicInteger sameIndex = new AtomicInteger(0);
        s.chars().forEach(c -> {
            if (container.size() < p.length()) {
                container.add(c);
                return;
            }
            if (calculateSame(container, comparator)) {
                ans.add(sameIndex.get());
            }
            container.removeFirst();
            container.addLast(c);
            sameIndex.getAndIncrement();
        });
        if (calculateSame(container, comparator)) {
            ans.add(sameIndex.get());
        }
        return ans;
    }

    private boolean calculateSame(Deque<Integer> container, List<Integer> comparator) {
        AtomicBoolean flag = new AtomicBoolean(true);
        AtomicInteger index = new AtomicInteger(0);
        container.stream().sorted().forEach(val -> {
            if (!Objects.equals(comparator.get(index.getAndIncrement()), val)) {
                flag.set(false);
            }
        });
        return flag.get();
    }
}
