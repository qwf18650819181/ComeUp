package com.comeup.algorithm.zuo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述: note: 前缀树
 *
 * @author qiu wanzi
 * @date 2024年3月11日 0011
 */
public class Zuo9_Trie {

    public static void main(String[] args) {
        Node root = new Node();
        add(root, "hello");
        add(root, "helloworld");
        System.out.println(root);
        System.out.println("=====================");

        HashNode rootHash = new HashNode();
        addHashNode(rootHash, "hello");
        addHashNode(rootHash, "helloworld");
        System.out.println(root);

        System.out.println("=====================");
        boolean b = searchHashNode(rootHash, "helloworld");
        System.out.println(b);

        System.out.println("=====================");
        removeHashNode(rootHash, "helloworld");
        System.out.println(b);


    }

    private static void removeHashNode(HashNode root, String word) {
        if (!searchHashNode(root, word)) return;
        HashNode node = root;
        node.passTimes--;
        int[] array = word.chars().toArray();
        for (int i = 0; i < array.length; i++) {
            HashNode next = node.next.get(array[i]);
            next.passTimes--;
            if (next.passTimes == 0) {
                node.next.remove(array[i]);
                return;
            }
            if (i == array.length - 1) {
                next.endTimes--;
                return;
            }
            node = next;
        }



    }

    private static boolean searchHashNode(HashNode root, String word) {
        if (word == null || word.isEmpty()) return true;
        int[] array = word.chars().toArray();
        HashNode node = root;
        for (int i = 0; i < array.length; i++) {
            HashNode next = node.next.get(array[i]);
            if (next == null) return false;
            if (i == array.length - 1) {
                if (next.endTimes == 0) return false;
                return true;
            }
            node = next;
        }
        return false;
    }


    public static void add(Node root, String word) {
        if (word == null || word.isEmpty()) return;
        root.passTimes++;
        Node node = root;
        int[] array = word.chars().toArray();
        for (int i = 0; i < array.length; i++) {
            Node next = node.next[array[i] - 'a'];
            if (next == null) next = new Node();
            next.passTimes++;
            if (i == array.length - 1) {
                next.endTimes++;
            }
            node = next;
        }
    }

    public static void addHashNode(HashNode root, String word) {
        if (word == null || word.isEmpty()) return;
        root.passTimes++;
        HashNode node = root;
        int[] array = word.chars().toArray();
        for (int i = 0; i < array.length; i++) {
            HashNode next = node.next.get(array[i]);
            if (next == null) {
                next = new HashNode();
                node.next.put(array[i], next);
            }
            next.passTimes++;
            if (i == array.length - 1) {
                next.endTimes++;
            }
            node = next;
        }
    }


    @Data
    public static class Node {
        int passTimes;
        int endTimes;
        Node[] next = new Node[26];
    }


    @Data
    public static class HashNode {
        int passTimes;
        int endTimes;
        Map<Integer, HashNode> next = new HashMap<>();
    }


}
