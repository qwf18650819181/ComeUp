package com.comeup.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @auth: qwf
 * @date: 2023年12月26日 0026
 * @description:
 */
public class Main {


    public static void main(String[] args) {
        Human human = new Human(11, 1);
        Man man = ManConverter.INSTANCE.toMan(human);
        System.out.println(man);
        List<Human> humans = new ArrayList<>();
        humans.add(human);
        humans.add(new Human(22, 2));
        List<Man> mans = ManConverter.INSTANCE.toMans(humans);
        System.out.println(mans);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Human {
        private Integer name;
        private Integer id;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Man {
        private String name;
        private Integer id;
    }

}


