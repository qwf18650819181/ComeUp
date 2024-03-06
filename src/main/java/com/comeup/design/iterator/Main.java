package com.comeup.design.iterator;

import lombok.Data;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author: qiu wanzi
 * @date: 2024年3月6日 0006
 * @version: 1.0
 * @description: TODO
 */
public class Main {


    public static void main(String[] args) {

        People people = new People<Person>();
        people.add(new Person("1"));
        people.add(new Person("2"));
        people.add(new Person("3"));
        people.remove(1);


        Iterator<Person> iterator = people.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getName());
        }
        System.out.println("================");
//        people.forEach(item -> System.out.println(item.getName()));

    }

    @Data
    public static class Person {
        private String name;
        public Person(String name) {
            this.name = name;
        }
    }
    public static class People<T> implements Iterable<T> {
        private Object[] list = new Object[8];
        private int addIndex;
        public void add(T person) {
            if (addIndex < list.length) {
                this.list[addIndex++] = person;
                return;
            }
            Object[] newArray = new Object[(list.length >> 2) + list.length];
            System.arraycopy(list, 0, newArray, 0, list.length);
            list = newArray;
        }
        public void remove(int index) {
            if (index >= addIndex || index < 0) {
                throw new RuntimeException("index out of bounds");
            }
            Object[] newArray = new Object[list.length];
            System.arraycopy(list, 0, newArray, 0, index);
            System.arraycopy(list, index + 1, newArray, index, list.length - index - 1);
            list = newArray;
            addIndex--;
        }
        @Override
        public Iterator<T> iterator() {
            return new Itr();
        }
        @Override
        public Spliterator<T> spliterator() {
            return Iterable.super.spliterator();
        }

        @Override
        public void forEach(Consumer<? super T> action) {
            for (int i = 0; i < list.length; i++) {
                action.accept((T) list[i]);
            }
        }
        @Data
        public class Itr implements Iterator<T> {
            private Object[] items;
            private int size;
            private int index;
            public Itr() {
                this.items = People.this.list;
                this.size = items.length;
            }
            @Override
            public boolean hasNext() {
                return this.items[index] != null;
            }
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) this.items[index++];
            }
        }
    }
}
