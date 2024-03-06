package com.comeup;

import com.alibaba.fastjson.JSON;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @auth: qwf
 * @date: 2024年1月9日 0009
 * @description:
 */
public class Main {

    public static void main1(String[] args) {


        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < args.length; i++) {
            map.merge(args[i], 1, Integer::sum);
        }

        System.out.println(JSON.toJSONString(map));


        int n = 13;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 1;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 2;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 4;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 8;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 16;
        System.out.println(Integer.toBinaryString(n));
        n = (n + 1) << 1;
        System.out.println(Integer.toBinaryString(n));


    }

    /**
     * @param args:
     * @return null
     * @author qiu wanzi
     * @description TODO 弱引用
     * @date 2024年3月6日 0006
     */
    public static void main(String[] args) {
        WeakReferenceCache<String, Object> cache = new WeakReferenceCache<>();
        Object value = new Object();
        cache.put("key", value);
        // 删除强引用 -> 自动回收
        value = null;
        // 强制垃圾收集
        System.gc();
        // 等待垃圾收集
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // 检查缓存项是否被回收
        if (cache.get("key") == null) {
            System.out.println("Cache item was garbage collected.");
        } else {
            System.out.println("Cache item was not garbage collected.");
        }

        Map<String, Object> cache1 = new HashMap<>();
        Object value1 = new Object();
        cache1.put("key", value1);
        // 删除强引用 -> 自动回收
        value1 = null;
        // 强制垃圾收集
        System.gc();
        // 等待垃圾收集
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // 检查缓存项是否被回收
        if (cache1.get("key") == null) {
            System.out.println("Cache item was garbage collected.");
        } else {
            System.out.println("Cache item was not garbage collected.");
        }
    }

    public static class WeakReferenceCache<K, V> {

        private final Map<K, WeakReference<V>> cache = Collections.synchronizedMap(new HashMap<>());

        public void put(K key, V value) {
            cache.put(key, new WeakReference<>(value));
        }

        public V get(K key) {
            WeakReference<V> weakRef = cache.get(key);
            if (weakRef != null) {
                return weakRef.get();
            }
            return null;
        }
    }


}
