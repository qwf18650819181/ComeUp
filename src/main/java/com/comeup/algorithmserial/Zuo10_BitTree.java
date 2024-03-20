package com.comeup.algorithmserial;

import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * 功能描述: 二叉树
 * 1. 遍历 与 递归序
 *
 * @author qiu wanzi
 * @date 2024年3月11日 0011
 */
@Slf4j
public class Zuo10_BitTree {

    @Data
    private static class Node {
        Integer val;
        Node left;
        Node right;

        public Node(Integer val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Node node = new Node(3);
        Node node1 = new Node(4);
        Node node2 = new Node(5);
        Node node3 = new Node(6);
        Node node4 = new Node(7);
        Node node5 = new Node(8);
        Node node6 = new Node(9);
        //      3
        //  4       7
        // 5    6 8     9
        node.left = node1;
        node1.left = node2;
        node1.right = node3;
        node.right = node4;
        node4.left = node5;
        node4.right = node6;
        // 3456789
        pre(node);
        System.out.println("=====in====");
        // 5463879
        in(node);
        System.out.println("=====after====");
        // 5648973
        after(node);
        System.out.println("=====stackPre====");
        stackPre(node);
        System.out.println("=====stackIn====");
        stackIn(node);
        System.out.println("=====stackAfter====");
        stackAfter(node);
        System.out.println("=====floorIterator====");
        floorIterator(node);
        System.out.println("=====floor count====");
        floorIteratorAndCount(node);
        System.out.println("=====floor count with hash====");
        HashMap<Integer, List<Node>> map = new HashMap<>();
        floorIteratorAndCountWithHashRecursive(node, 1, map);
        log.info(JSONUtil.toJsonStr(map));
    }


    /**
     * 销量太低
     *
     * @param node
     * @param floor
     * @param map
     */
    private static void floorIteratorAndCountWithHashRecursive(Node node, int floor, Map<Integer, List<Node>> map) {
        if (node == null) return;
        List<Node> nodes = map.getOrDefault(floor, new ArrayList<>());
        nodes.add(node);
        map.put(floor, nodes);
        floorIteratorAndCountWithHashRecursive(node.left, floor + 1, map);
        floorIteratorAndCountWithHashRecursive(node.right, floor + 1, map);
    }

    private static void floorIteratorAndCount(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        Node lastEnd = node;
        Node nextEnd = null;
        Integer floorCount = 0;
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            if (poll.left != null) {
                queue.offer(poll.left);
                nextEnd = poll.left;
            }
            if (poll.right != null) {
                queue.offer(poll.right);
                nextEnd = poll.right;
            }
            floorCount++;
            if (poll == lastEnd) {
                log.info(floorCount.toString());
                floorCount = 0;
                lastEnd = nextEnd;
            }
        }

    }

    private static void floorIterator(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            log.info(poll.val.toString());
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
        }
    }

    /**
     * note: 非递归单个栈 先序
     *
     * @param head
     */
    public static void stackPre(Node head) {
        if (head == null) return;

        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                log.info(head.val.toString());
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                head = head.right;
            }
        }
    }

    /**
     * note: 非递归单个栈 中序
     *
     * @param head
     */
    public static void stackIn(Node head) {
        if (head == null) return;

        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                log.info(head.val.toString());
                head = head.right;
            }
        }
    }

    /**
     * note: 非递归单个栈 后序 单个栈,两个标量
     *          非自己写的
     * @param head
     */
    public static void stackAfter(Node head) {
        if (head == null) return;
        Stack<Node> stack = new Stack<>();
        Node last = null;
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.peek();
                if (head.right != null && head.right != last) {
                    head = head.right;
                } else {
                    log.info(head.val.toString());
                    last = head;
                    stack.pop();
                    head = null;
                }
            }
        }
    }

    public static void pre(Node head) {
        if (head == null) return;
        log.info(String.valueOf(head.val));
        pre(head.left);
        pre(head.right);
    }

    public static void in(Node head) {
        if (head == null) return;
        in(head.left);
        log.info(String.valueOf(head.val));
        in(head.right);
    }

    public static void after(Node head) {
        if (head == null) return;
        after(head.left);
        after(head.right);
        log.info(String.valueOf(head.val));
    }


}
