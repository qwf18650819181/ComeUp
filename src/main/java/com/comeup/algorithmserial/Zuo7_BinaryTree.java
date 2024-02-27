package com.comeup.algorithmserial;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @auth: qwf
 * @date: 2024年1月28日 0028
 * @description: 二叉树
 */
@Slf4j
public class Zuo7_BinaryTree {



    @Data
    public static class Node {
        int val;
        Node left;
        Node right;
        int height;
        boolean isBalanceNode;
        boolean isSearchNode;
        int leftMinVal;
        int leftMaxVal;
        int rightMinVal;
        int rightMaxVal;

    }

    /**
     * 判断是否平衡二叉树
     */
    public static boolean isBalanced(Node head) {
        if (head == null) {
            return true;
        }
        return judgeBalanceNode(head).isBalanceNode && judgeSearchNode(head).isSearchNode;
    }

    public static Node judgeBalanceNode(Node head) {
        if (head == null) {
            head.isBalanceNode = true;
            head.height = 0;
            return head;

        }
        Node leftNode = judgeBalanceNode(head.left);
        Node rightNode = judgeBalanceNode(head.right);
        head.isBalanceNode = leftNode.isBalanceNode && rightNode.isBalanceNode && Math.abs(leftNode.height - rightNode.height) < 2;
        head.height = Math.max(leftNode.height, rightNode.height) + 1;
        return head;
    }

    public static Node judgeSearchNode(Node head) {
        if (head == null) {
            head.isSearchNode = true;
            head.leftMinVal = Integer.MAX_VALUE;
            head.leftMaxVal = Integer.MIN_VALUE;
            head.rightMinVal = Integer.MAX_VALUE;
            head.rightMaxVal = Integer.MIN_VALUE;
            return head;
        }
        Node leftNode = judgeSearchNode(head.left);
        Node rightNode = judgeSearchNode(head.right);
        head.isSearchNode = leftNode.isSearchNode && rightNode.isSearchNode && head.val > leftNode.rightMaxVal && head.val < rightNode.leftMinVal;
        head.leftMinVal = Math.min(head.val, leftNode.leftMinVal);
        head.leftMaxVal = Math.max(head.val, leftNode.leftMaxVal);
        head.rightMinVal = Math.min(head.val, rightNode.rightMinVal);
        head.rightMaxVal = Math.max(head.val, rightNode.rightMaxVal);
        return head;
    }





}
