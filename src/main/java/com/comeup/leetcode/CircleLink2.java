package com.comeup.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @auth: qwf
 * @date: 2024年1月4日 0004
 * @description: 环形链表
 * @example: head = [3,2,0,-4], pos = 1
 */
public class CircleLink2 {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode temp = head;
        while (temp != null) {
            if (set.contains(temp)) {
                return temp;
            }
            set.add(temp);
            temp = temp.next;
        }
        return null;
    }
}
