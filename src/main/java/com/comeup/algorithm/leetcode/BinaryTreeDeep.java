package com.comeup.algorithm.leetcode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auth: qwf
 * @date: 2024年1月9日 0009
 * @description: 二叉树的最大深度
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：3
 */
public class BinaryTreeDeep {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {
        }
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int maxDepth(TreeNode root) {
        AtomicInteger maxDepth = new AtomicInteger(0);
        searchMaxDepth(root, 1, maxDepth);
        return maxDepth.get();
    }

    private void searchMaxDepth(TreeNode root, int nowDepth, AtomicInteger maxDepth) {
        if (root == null) return;
        if (nowDepth > maxDepth.get()) maxDepth.set(nowDepth);
        nowDepth++;
        searchMaxDepth(root.left, nowDepth, maxDepth);
        searchMaxDepth(root.right, nowDepth, maxDepth);
    }

}
