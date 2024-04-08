package com.comeup.algorithm.leetcode;

/**
 * @auth: qwf
 * @date: 2024年1月4日 0004
 * @description: 环形链表
 * @example: head = [3,2,0,-4], pos = 1
 */
public class CircleLink {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        ListNode fast = head,slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }
}
