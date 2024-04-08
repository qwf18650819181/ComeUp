package com.comeup.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述: 76. 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意：
 * <p>
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 * 示例 2：
 * <p>
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 解释：整个字符串 s 是最小覆盖子串。
 * 示例 3:
 * <p>
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 *
 * @author qiu wanzi
 * @date 2024年3月12日 0012
 */
public class LeetCode76 {

    public static void main(String[] args) {
        LeetCode76 leetCode76 = new LeetCode76();
        System.out.println(leetCode76.minWindow("bba", "ab"));
    }

    /**
     * time out
     * @param source
     * @param target
     * @return
     */

    public String minWindow(String source, String target) {
        String minString = "";
        if (cannotFindTarget(source, target)) {
            return minString;
        }
        int[] indexes = substringIndex(source, target);
        if (indexes == null) return minString;
        int leftIndex = indexes[0];
        int rightIndex = indexes[1];
        minString = source.substring(leftIndex, rightIndex + 1);
        String recursiveString = source.substring(leftIndex);
        rightIndex = rightIndex - leftIndex;
        leftIndex = 1;
        int length = recursiveString.length();
        boolean hasTarget = true;
        while (leftIndex < rightIndex && rightIndex < length) {
            if (hasTarget) indexes = substringIndex(recursiveString.substring(leftIndex, rightIndex + 1), target);
            if (indexes == null) {
                rightIndex++;
            } else {
                if ((rightIndex + 1 - leftIndex) < minString.length()) {
                    minString = recursiveString.substring(leftIndex, rightIndex + 1);
                }
                if (target.contains(String.valueOf(recursiveString.charAt(leftIndex)))) {
                    hasTarget = true;
                } else {
                    hasTarget = false;
                }
                leftIndex++;
            }
        }
        return minString;
    }

    private boolean cannotFindTarget(String source, String target) {
        return source == null || target == null || source.isEmpty() || target.isEmpty() || source.length() < target.length();
    }

    private int[] substringIndex(String source, String target) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length(); i++) {
            map.merge(target.charAt(i), 1, Integer::sum);
        }
        int leftIndex = -1;
        int rightIndex = -1;
        boolean hasAll = false;
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            if (map.containsKey(c)) {
                if (leftIndex == -1) {
                    leftIndex = i;
                    rightIndex = i;
                } else {
                    rightIndex = i;
                }
                map.merge(c, -1, Integer::sum);
                if (map.values().stream().filter(item -> item >= 0).mapToInt(Integer::valueOf).sum() == 0) {
                    hasAll = true;
                    break;
                }
            }
        }
        if (!hasAll) return null;
        return new int[]{leftIndex, rightIndex};
    }

}
