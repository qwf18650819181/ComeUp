package com.comeup.design.strategy;

/**
 * @auth: qwf
 * @date: 
 * @description:
 */
public class FishSizeComparator implements Comparator<Fish> {
    @Override
    public int compareTo(Fish t1, Fish t2) {
        if (t1.size > t2.size) return 1;
        if (t1.size < t2.size) return -1;
        return 0;
    }
}
