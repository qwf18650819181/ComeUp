package com.comeup.design.strategy;

/**
 * @auth: qwf
 * @date: 
 * @description:
 */
public class Fish {
    int weight;
    int size;

    @Override
    public String toString() {
        return "Fish{" +
                "weight=" + weight +
                ", size=" + size +
                '}';
    }

    public Fish(int weight, int size) {
        this.weight = weight;
        this.size = size;
    }
}
