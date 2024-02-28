package com.comeup.util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: qiu wanzi
 * @date: 2024年2月28日 0028
 * @version: 1.0
 * @description: TODO
 */
public class Lists {


    public static <T, K, V> Map<K, V> collectToMap(List<T> list, Function<T, K> keyMapper, Function<T, V> valueMapper) {
        return list.stream().filter(item -> Objects.nonNull(keyMapper.apply(item))).collect(Collectors.toMap(keyMapper, valueMapper, (k1, k2) -> k1));
    }

    public static <T, K> Map<K, T> collectToMap(List<T> list, Function<T, K> keyMapper) {
        return list.stream().filter(item -> Objects.nonNull(keyMapper.apply(item))).collect(Collectors.toMap(keyMapper, Function.identity(), (k1, k2) -> k1));
    }

    public static <T, K> Map<K, List<T>> collectToGroupBy(List<T> list, Function<T, K> keyMapper) {
        return list.stream().filter(item -> Objects.nonNull(keyMapper.apply(item))).collect(Collectors.groupingBy(keyMapper));
    }


    @Data
    public static class Node {
        Integer key;
        Integer value;

        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        List<Node> list = new ArrayList<>();
        list.add(new Node(1, 2));
        list.add(new Node(null, 3));
        Map<Integer, Integer> map = list.stream().collect(Collectors.toMap(Node::getKey, Node::getValue, (k1, k2) -> k1));

        Map<Integer, Integer> map1 = collectToMap(list, Node::getKey, Node::getValue);
        Map<Integer, Node> integerNodeMap = collectToMap(list, Node::getKey);
        System.out.println(map1);

    }



}
