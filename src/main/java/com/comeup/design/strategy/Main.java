package com.comeup.design.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @auth: qwf
 * @date: 
 * @description:
 */
public class Main {
    public static void main(String[] args) {


        List<Fish> fishList = new ArrayList<>();

        fishList.add(new Fish(12, 15));
        fishList.add(new Fish(45, 13));
        fishList.add(new Fish(22, 55));
        System.out.println(sort(fishList, new FishSizeComparator()));
        System.out.println(sort(fishList, new FishWeightComparator()));

    }

    public static List<Fish> sort(List<Fish> fish, Comparator<Fish> comparator) {
        return fish.stream().sorted(comparator::compareTo).collect(Collectors.toList());
    }

}
