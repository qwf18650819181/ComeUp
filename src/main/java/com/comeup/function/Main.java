package com.comeup.function;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月15日 0015
 * @version: 1.0
 */
public class Main {

    public static void main(String[] args) {

        Lazy<Person> zhangsan = Lazy.of(() -> Person.builder().name("zhangsan").build());
        Lazy<List<Person>> zhangsanList1 = Lazy.of(() -> {
            List<Person> personList = new ArrayList<>();
            personList.add(Person.builder().name("zhangsan").build());
            personList.add(Person.builder().name("lisi").build());
            return personList;
        });

        List<Person> personList1 = zhangsanList1.optional().get();

        Person zhangsanPerson = zhangsan.optional().get();

        LazyList<Person> zhangsanList = LazyList.of(() -> {
            List<Person> personList = new ArrayList<>();
            personList.add(Person.builder().name("zhangsan").build());
            personList.add(Person.builder().name("lisi").build());
            return personList;
        });

        List<Person> personList = zhangsanList.optional().get();
        System.out.println(personList);


    }
}
