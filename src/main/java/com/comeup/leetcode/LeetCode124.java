package com.comeup.leetcode;

import lombok.Data;

import java.util.PriorityQueue;

/**
 * 功能描述: 124. 二叉树中的最大路径和
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 *
 * 路径和 是路径中各节点值的总和。
 *
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 *
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 *
 * @author qiu wanzi
 * @date 2024年3月12日 0012
 */
public class LeetCode124 {


    @Data
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        Integer max = nodeMax(root, queue);
        queue.offer(max);
        return queue.poll();
    }

    private Integer nodeMax(TreeNode node, PriorityQueue<Integer> queue) {
        if (node == null) return null;
        Integer leftMax = nodeMax(node.left, queue);
        Integer rightMax = nodeMax(node.right, queue);
        // 左 中 右放入最大的到queue
        // 传连接中点最大的值
        int max;
        int lineNodeMax;
        if (leftMax == null && rightMax == null) {
            max = node.val;
            lineNodeMax = node.val;
        } else if (leftMax == null) {
            if (node.val >= 0) {
                max = Math.max(Math.max(rightMax, rightMax + node.val), node.val);
            } else {
                max = Math.max(rightMax, node.val);
            }
            lineNodeMax = Math.max(rightMax + node.val, node.val);
        } else if (rightMax == null) {
            if (node.val >= 0) {
                max = Math.max(Math.max(leftMax, leftMax + node.val), node.val);
            } else {
                max = Math.max(leftMax, node.val);
            }
            lineNodeMax = Math.max(leftMax + node.val, node.val);
        } else {
            if (node.val >= 0) {
                max = Math.max(Math.max(Math.max(leftMax + rightMax + node.val, leftMax + node.val), rightMax + node.val), node.val);
            } else {
                max = Math.max(leftMax + rightMax + node.val, Math.max(leftMax, rightMax));
            }
            lineNodeMax = Math.max((leftMax > rightMax ? leftMax + node.val : rightMax + node.val), node.val);
        }
        queue.offer(max);
        return lineNodeMax;
    }


}
