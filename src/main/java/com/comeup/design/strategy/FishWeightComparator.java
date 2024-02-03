package com.comeup.design.strategy;

/**
 * @auth: qwf
 * @date: 
 * @description:
 */
public class FishWeightComparator implements Comparator<Fish> {
    @Override
    public int compareTo(Fish t1, Fish t2) {
        if (t1.weight > t2.weight) return 1;
        if (t1.weight < t2.weight) return -1;
        return 0;
    }
}
