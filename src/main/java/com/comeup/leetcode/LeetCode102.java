package com.comeup.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @auth: qwf
 * @date: 2024年1月25日 0025
 * @description: 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * for example:
 * <p>
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
 * <p>
 * 示例 2：
 * <p>
 * 输入：root = [1]
 * 输出：[[1]]
 * 示例 3：
 * <p>
 * 输入：root = []
 * 输出：[]
 */
public class LeetCode102 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(Integer val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * [3,9,20,null,null,15,7]
     * @param args
     */
    public static void main(String[] args) {
        TreeNode node = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));

        List<List<Integer>> lists = levelOrder(node);

        System.out.println(lists.toString());


    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        while (!list.isEmpty()) {
            int size = list.size();
            List<Integer> temList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                temList.add(list.get(i).val);
                if (list.get(i).left != null) list.add(list.get(i).left);
                if (list.get(i).right != null) list.add(list.get(i).right);
            }
            list = list.subList(size, list.size());
            ans.add(temList);
        }
        return ans;
    }

}
