package com.comeup.lru;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @auth: qwf
 * @date: 2023年12月14日 0014
 * @description:
 */
@Data
public class LRUMain {

    @Data
//    @Setter
//    @Getter
    public static class CacheNode {
        private Integer value;
        private CacheNode last;
        private CacheNode next;
    }

    private static class LinkedCacheNode {
        private static final Map<Integer, CacheNode> MAP = new HashMap<>();

        private static CacheNode HEAD;
        private static CacheNode LAST;
        private static int CAP_SIZE;

        @Override
        public String toString() {
            CacheNode cacheNode = HEAD;
            StringBuilder stringBuilder = new StringBuilder();
            while (Objects.nonNull(cacheNode)) {
                stringBuilder.append("[" + cacheNode.value + "],");
                cacheNode = cacheNode.next;
            }
            return stringBuilder.toString();
        }

        public LinkedCacheNode(int capSize) {
            LinkedCacheNode.CAP_SIZE = capSize;
        }

        public void useCache(Integer value) {
            if (MAP.size() == 0) {
                CacheNode cacheNode = new CacheNode();
                cacheNode.setValue(value);
                LinkedCacheNode.HEAD = cacheNode;
                LinkedCacheNode.LAST = cacheNode;
                MAP.put(value, cacheNode);
            } else {
                CacheNode cacheNode = MAP.getOrDefault(value, new CacheNode());
                if (Objects.isNull(cacheNode.getValue())) {
                    cacheNode.value = value;
                } else {
                    if (LinkedCacheNode.HEAD == cacheNode) {
                        cacheNode.next.last = null;
                        LinkedCacheNode.HEAD = cacheNode.next;
                        cacheNode.next = null;
                    } else {
                        cacheNode.last.next = cacheNode.next;
                        cacheNode.next.last = cacheNode.last;
                        cacheNode.next = null;
                    }
                }
                CacheNode last = LinkedCacheNode.LAST;
                last.next = cacheNode;
                cacheNode.last = last;
                LinkedCacheNode.LAST = cacheNode;
                if (MAP.size() == CAP_SIZE) {
                    CacheNode head = LinkedCacheNode.HEAD;
                    head.next.last = null;
                    LinkedCacheNode.HEAD = head.next;
                    MAP.remove(head.value);
                }
                MAP.put(value, cacheNode);
            }
        }
    }


    public static void main(String[] args) {
        LinkedCacheNode linkedCacheNode = new LinkedCacheNode(5);

        linkedCacheNode.useCache(5);
        System.out.println(linkedCacheNode);
        linkedCacheNode.useCache(6);
        System.out.println(linkedCacheNode);
        linkedCacheNode.useCache(1);
        System.out.println(linkedCacheNode);
        linkedCacheNode.useCache(2);
        System.out.println(linkedCacheNode);
        linkedCacheNode.useCache(3);
        System.out.println(linkedCacheNode);
        linkedCacheNode.useCache(4);
        System.out.println(linkedCacheNode);
        linkedCacheNode.useCache(2);
        System.out.println(linkedCacheNode);
        linkedCacheNode.useCache(3);
        System.out.println(linkedCacheNode);

    }


}
