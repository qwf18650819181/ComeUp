package com.comeup.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @auth: qwf
 * @date: 2024年1月9日 0009
 * @description: 二叉树的中序遍历
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 * root = [1,null,2,3]
 * 输出：[1,3,2]
 *
 * 二叉树的中序遍历：按照访问左子树——根节点——右子树的方式遍历这棵树，而在访问左子树或者右子树的时候我们按照同样的方式遍历，直到遍历完整棵树
 *       4
 *      / \
 *     2   6
 *    / \ / \
 *   1  3 5  7
 *   中序遍历的结果为：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7。
 */
public class BinaryTreeIterator {

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

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        search(root, list);
        return list;
    }

    private void search(TreeNode root, List<Integer> list) {
        if (root == null) return;
        search(root.left, list);
        list.add(root.val);
        search(root.right, list);
    }


}
