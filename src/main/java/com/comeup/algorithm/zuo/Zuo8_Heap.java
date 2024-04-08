package com.comeup.algorithm.zuo;

import com.comeup.algorithm.base.SortUtil;

import java.util.Arrays;

/**
 * 功能描述: note: 大根堆/小根堆
 *              父节点: (i - 1) / 2
 *              子节点(左): 2 * i + 1
 *              子节点(左): 2 * i + 2
 * @author qiu wanzi
 * @date 2024年3月11日 0011
 */
public class Zuo8_Heap {

    public static void main(String[] args) {
        int[] heap = {7, 8, 9, 10, 1, 2, 3, 4, 5, 6};
        heapSort(heap);
        System.out.println(Arrays.toString(Arrays.stream(heap).toArray()));
    }

    private static void heapSort(int[] heap) {
        if (heap == null || heap.length < 2) return;
        for (int i = heap.length / 2 - 1; i >= 0; i--)
            heapify(heap, heap.length - 1, i);
        for (int i = heap.length - 1; i > 0; i--) {
            SortUtil.swap(heap, 0, i);
            heapify(heap, i - 1, 0);
        }
    }
    private static void heapify(int[] heap, int endIndex, int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        while (left <= endIndex) {
            int iValue = heap[i];
            int leftValue = heap[left];
            int maxIndex;
            if (right > endIndex) {
                maxIndex = leftValue > iValue ? left : i;
            } else {
                int rightValue = heap[right];
                int midIndex = leftValue > rightValue ? left : right;
                maxIndex = heap[midIndex] > iValue ? midIndex : i;
            }
            if (maxIndex == i) break;
            SortUtil.swap(heap, maxIndex, i);
            i = maxIndex;
            left = 2 * i + 1;
            right = 2 * i + 2;
        }
    }


}
