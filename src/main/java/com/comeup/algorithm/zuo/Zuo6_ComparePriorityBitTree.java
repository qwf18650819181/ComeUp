package com.comeup.algorithm.zuo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * @auth: qwf
 * @date: 2024年1月28日 0028
 * @description: 比较器 优先级队列 二叉树
 */
@Slf4j
public class Zuo6_ComparePriorityBitTree {


    public static void main(String[] args) {
        Node node1 = new Node(3).setNext(new Node(4).setNext(new Node(5)));
        Node node2 = new Node(4).setNext(new Node(6).setNext(new Node(7)));

        log.info(node1.toString());
        log.info(node2.toString());
        List<Node> list = new ArrayList<>();
        list.add(node1);
        list.add(node2);
        Node merge = merge(list);
        log.info(merge.toString());
        log.info("========================");

        /**
         * 二叉树          1
         *         2            3
         *      5     7     7       9
         */
        BitNode bitNode = new BitNode(1)
                .setLeft(new BitNode(2).setLeft(new BitNode(5)).setRight(new BitNode(7)))
                .setRight(new BitNode(3).setLeft(new BitNode(7)).setRight(new BitNode(9)));
        iteratorBitNode(bitNode);
        log.info("===========iteratorBitNode=============");

        BitNode bitNode1 = new BitNode(1)
                .setLeft(new BitNode(2).setLeft(new BitNode(5)).setRight(new BitNode(7)))
                .setRight(new BitNode(3).setLeft(new BitNode(7)).setRight(new BitNode(9)));

        BitNode bitNode2 = new BitNode(1)
                .setLeft(new BitNode(2).setLeft(new BitNode(5)).setRight(new BitNode(7)))
                .setRight(new BitNode(3).setLeft(new BitNode(7)).setRight(new BitNode(9)).setRight(new BitNode(8)));
        boolean b = compareTwoBitTree(bitNode1, bitNode2);
        log.info("bitTree is same [" + b + "]");
        log.info("========================");
        BitNode mirrorNode = new BitNode(1)
                .setLeft(new BitNode(2).setLeft(new BitNode(5)).setRight(new BitNode(7)))
                .setRight(new BitNode(2).setLeft(new BitNode(7)).setRight(new BitNode(5)));
        boolean isMirror = isMirrorTree(mirrorNode, mirrorNode);
        log.info("bitTree is same [" + isMirror + "]");
        log.info("========================");
        BitNode depthNode = new BitNode(1)
                .setLeft(new BitNode(2).setLeft(new BitNode(5)).setRight(new BitNode(7)))
                .setRight(new BitNode(2).setLeft(new BitNode(7)).setRight(new BitNode(5).setLeft(new BitNode(8))));
        int depth = searchTreeDepth(depthNode);
        log.info("bitTree depth [" + depth + "]");
        log.info("========================");

        int[] pre = new int[]{1, 2, 5, 6, 3, 7, 9};
        int[] in  = new int[]{5, 2, 6, 1, 7, 3, 9};
        BitNode formNode = formBitTreeByPreAndIn(pre, in);
        log.info("formNode : \n" + formNode.toString());


    }

    /**
     * 1. 对比两个二叉树是否相等
     */
    public static boolean compareTwoBitTree(BitNode bitNode1, BitNode bitNode2) {
        if (bitNode1 == null ^ bitNode2 == null) return false;
        if (bitNode1 == null) return true;
        return Objects.equals(bitNode1.val, bitNode2.val) && compareTwoBitTree(bitNode1.left, bitNode2.left) && compareTwoBitTree(bitNode1.right, bitNode2.right);
    }

    /**
     * 2. 是否镜面树
     */
    public static boolean isMirrorTree(BitNode bitNode1, BitNode bitNode2) {
        if (bitNode1 == null ^ bitNode2 == null) return false;
        if (bitNode1 == null) return true;
        return Objects.equals(bitNode1.val, bitNode2.val) && isMirrorTree(bitNode1.left, bitNode2.right) && isMirrorTree(bitNode1.right, bitNode2.left);
    }

    /**
     * 3. 返回树最大深度
     */
    public static int searchTreeDepth(BitNode node) {
        if (node == null) return 0;
        return Math.max(searchTreeDepth(node.left), searchTreeDepth(node.right)) + 1;
    }

    /**
     * 4. 有先序,中序,求原二叉树
     *  pre: 1 256 379
     *  in : 526 1 739
     *  二叉树        1
     *         2            3
     *      5     6     7       9
     */
    public static BitNode formBitTreeByPreAndIn(int[] pre, int[] in) {
        if (pre.length != in.length) return null;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        return doFormBitTreeByPreAndIn(pre, 0, pre.length - 1, in, 0, in.length - 1, map);
    }

    /**
     *  pre: 256
     *  in : 526
     */
    public static BitNode doFormBitTreeByPreAndIn(int[] pre, int l1, int r1, int[] in, int l2, int r2, Map<Integer, Integer> map) {
        if (l1 > r1) {
            return null;
        }
        Integer inIndex = map.get(pre[l1]);
        BitNode node = new BitNode(pre[l1]);
        int reeSize = inIndex - l2;
        node.left = doFormBitTreeByPreAndIn(pre, l1 + 1, l1 + reeSize, in, l2, inIndex - 1, map);
        node.right = doFormBitTreeByPreAndIn(pre, l1 + reeSize + 1, r1, in, inIndex + 1, r2, map);
        return node;
    }


    /**
     * 递归序 :
     * note: 学会画递归序 三次来到同一个节点 穷举法
     * @param node
     */
    public static void iteratorBitNode(BitNode node) {
        if (node == null) return;
        // 先序
        iteratorBitNode(node.left);
        log.info(node.val.toString());
        // 中序
        iteratorBitNode(node.right);
        // 后序
    }

    /**
     * 二叉树
     */
    @Data
    private static class BitNode {
        Integer val;
        BitNode left;
        BitNode right;
        public BitNode(int val) {
            this.val = val;
        }

        public BitNode setLeft(BitNode left) {
            this.left = left;
            return this;
        }

        public BitNode setRight(BitNode right) {
            this.right = right;
            return this;
        }

        @Override
        public String toString() {
            return "{" + val + ", left=" + left + ", right=" + right + '}';
        }
    }

    /**
     * 排序好的链表合并成一个排序好的大链表
     *
     * 字符串比较是字典序比较,
     *      1. 长度一样比数字
     *      2. 长度不一样后面补0比数字
     *
     */
    public static Node merge(List<Node> heads) {
        if (heads == null || heads.isEmpty()) return null;
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        queue.addAll(heads);
        Node first = null;
        Node temp = null;
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            if (first == null) {
                first = poll;
                temp = poll;
            } else {
                temp.next = poll;
                temp = poll;
            }
            if (poll.next != null) {
                queue.offer(poll.next);
            }
        }
        return first;
    }


    @Data
    private static class Node {
        int val;
        Node next;
        public Node(int val) {
            this.val = val;
        }

        public Node setNext(Node next) {
            this.next = next;
            return this;
        }
    }




}
