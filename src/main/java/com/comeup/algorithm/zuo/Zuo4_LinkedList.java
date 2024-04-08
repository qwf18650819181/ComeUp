package com.comeup.algorithm.zuo;

import lombok.extern.slf4j.Slf4j;

/**
 * @auth: qwf
 * @date: 2024年1月28日 0028
 * @description: 链表
 */
@Slf4j
public class Zuo4_LinkedList {


    public static class SingleNode {
        public int val;
        public SingleNode next;

        public SingleNode(int val) {
            this.val = val;
        }

        public SingleNode setNext(SingleNode next) {
            this.next = next;
            return this.next;
        }

        @Override
        public String toString() {
            return  "\t" + val +
                    ",\r\n\tnext = " + next;
        }
    }

    public static class DoubleNode {
        public int val;
        public DoubleNode next;
        public DoubleNode last;

        public DoubleNode(int val) {
            this.val = val;
        }

        public DoubleNode next(DoubleNode next) {
            this.next = next;
            this.next.last = this;
            return this;
        }

        @Override
        public String toString() {
            return  "\t" + val +
                    ",\r\n\tnext = " + next;
        }
    }

    public static class StackNode {
        public static SingleNode node;
        public static int size;

        public static void push(int val) {
            if (node == null) {
                node = new SingleNode(val);
            } else {
                SingleNode last = new SingleNode(val);
                last.next = node;
                node = last;
            }
            size++;
        }

        public static SingleNode get() {
            SingleNode tempNode = node;
            node = node.next;
            size--;
            return tempNode;
        }

    }




    public static void main(String[] args) {
        SingleNode node = new SingleNode(5);
        node.setNext(new SingleNode(3)).setNext(new SingleNode(8));
        System.out.println(node);
        System.out.println("=============");
        SingleNode reverseSingleNodeNode = reverseSingleNode(node);
        System.out.println(reverseSingleNodeNode);

        System.out.println("====== double ======");
        DoubleNode node1 = new DoubleNode(5).next(new DoubleNode(3).next(new DoubleNode(8)));
        System.out.println(node1);
        System.out.println("=============");
        DoubleNode reverseSingleNodeNode1 = reverseDoubleNode(node1);
        System.out.println(reverseSingleNodeNode1);

        System.out.println("====== stack ======");

        StackNode.push(5);
        StackNode.push(3);
        StackNode.push(8);
        System.out.println(StackNode.node);
        System.out.println(StackNode.size);
        StackNode.get();
        System.out.println(StackNode.node);
        System.out.println(StackNode.size);
        StackNode.get();
        System.out.println(StackNode.node);
        System.out.println(StackNode.size);

        log.info("======局部轮询反转链表======");

        SingleNode singleNode1 = new SingleNode(5);
        singleNode1.setNext(new SingleNode(3)).setNext(new SingleNode(8)).setNext(new SingleNode(9)).setNext(new SingleNode(4)).setNext(new SingleNode(7)).setNext(new SingleNode(6));
        System.out.println(singleNode1);
        System.out.println("=============");
        SingleNode reversesingleNode = reversePartialSingleNode(singleNode1, 3);
        System.out.println(reversesingleNode);


    }

    private static SingleNode reverseSingleNode(SingleNode node) {
        SingleNode last = null;
        SingleNode mid = node;
        SingleNode next = node;
        while (next != null) {
            next = mid.next;
            mid.next = last;
            last = mid;
            mid = next;
        }
        return last;
    }

    private static SingleNode reversePartialSingleNode(SingleNode node, int k) {
        SingleNode last = null;
        SingleNode mid = node;
        SingleNode next = node;
        while (next != null) {
            next = mid.next;
            mid.next = last;
            last = mid;
            mid = next;
        }
        return last;
    }

    private static DoubleNode reverseDoubleNode(DoubleNode node) {
        DoubleNode last = null;
        DoubleNode mid = node;
        DoubleNode next = node;
        while (next != null) {
            next = mid.next;
            mid.last = next;
            mid.next = last;
            last = mid;
            mid = next;
        }
        return last;
    }


}
