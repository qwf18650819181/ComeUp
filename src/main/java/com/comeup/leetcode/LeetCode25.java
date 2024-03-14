package com.comeup.leetcode;

import lombok.Data;

import java.util.Objects;

/**
 * 功能描述:
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * <p>
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * <p>
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 * <p>
 * 进阶: 你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
 *
 * @author qiu wanzi
 * @date 2024年3月12日 0012
 */
public class LeetCode25 {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        ListNode listNode = reverseKGroup(listNode1, 2);


    }
    @Data
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1) return head;
        ListNode cur = head;
        ListNode nextEnd = checkCapacity(cur, k);
        if (Objects.isNull(nextEnd)) return head;
        ListNode finalHead = nextEnd;


        cur = head;
        ListNode next = cur;
        ListNode pre = null;
        ListNode lastEnd = cur;
        while (pre != nextEnd) {
            next = next.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        lastEnd.next = cur;
        while (cur != null) {
            nextEnd = checkCapacity(cur, k);

            if (Objects.isNull(nextEnd)) {
                lastEnd.next = cur;
                cur = null;
            } else {
                lastEnd.next = nextEnd;
                pre = null;
                ListNode thisEnd = cur;
                while (pre != nextEnd) {
                    next = next.next;
                    cur.next = pre;
                    pre = cur;
                    cur = next;
                }
                lastEnd = thisEnd;
            }
        }
        return finalHead;
    }

    private static ListNode checkCapacity(ListNode cur, int k) {
        ListNode end = null;
        int i = 1;
        while (cur != null) {
            cur = cur.next;
            i++;
            if (i == k) {
                end = cur;
                break;
            }
        }
        return end;
    }


}
