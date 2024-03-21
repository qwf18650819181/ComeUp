package com.comeup.leetcode;

import lombok.Data;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 功能描述: 合并 K 个升序链表
 *给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * 示例 1：
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 * 输入：lists = [[]]
 * 输出：[]
 * @author qiu wanzi
 * @date 2024年3月12日 0012
 */
public class LeetCode23 {

    @Data
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }


    /**
     * 使用堆排序
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode listNode = null;
        if (lists == null || lists.length == 0) return listNode;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode list : lists) {
            if (list != null) {
                queue.offer(list);
            }
        }
        if (queue.isEmpty()) return listNode;
        listNode = queue.poll();
        if (listNode.next != null) {
            queue.offer(listNode.next);
        }
        ListNode last = listNode;
        while (!queue.isEmpty()) {
            ListNode poll = queue.poll();
            last.next = poll;
            if (poll.next != null) {
                queue.offer(poll.next);
            }
            last = poll;
        }
        return listNode;
    }




}
