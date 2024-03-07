package com.comeup.design.chainofresponsibility;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: qiu wanzi
 * @date: 2024年3月7日 0007
 * @version: 1.0
 * @description:
 * note: 两种类型的责任链
 *  1. 现后处理
 *  2. 过滤器处理
 */
public class ChainWithTwoType {

    public interface ChainNode {
        boolean doChain(StringBuilder req);
    }

    public static class ChainContainer {
        private final List<ChainNode> chainNodes = new ArrayList<>();

        public ChainContainer add(ChainNode chainNode) {
            chainNodes.add(chainNode);
            return this;
        }

        public boolean doChain(StringBuilder req) {
            for (ChainNode chainNode : chainNodes) {
                if (!chainNode.doChain(req)) {
                    return false;
                }
            }
            return false;
        }
    }

    public interface FilterNode {
        boolean doChain(StringBuilder req, FilterNode filterNode);
    }

    public static class ChainFilterContainer implements FilterNode {
        private final List<FilterNode> chainNodes = new ArrayList<>();

        private int index = 0;

        public ChainFilterContainer add(FilterNode chainNode) {
            chainNodes.add(chainNode);
            return this;
        }

        public boolean doChain(StringBuilder req, FilterNode filterNode) {
            if (index == chainNodes.size()) return true;
            return chainNodes.get(index++).doChain(req, filterNode);
        }
    }

    public static void main(String[] args) {
        ChainContainer container = new ChainContainer();
        container.add((str) -> {
            str.append(" run 1 ");
            return true;
        }).add((str) -> {
            str.append(" run 2 ");
            return true;
        }).add((str) -> {
            str.append(" run 3 ");
            return true;
        });
        StringBuilder stringBuilder = new StringBuilder(" come on ");
        container.doChain(stringBuilder);
        System.out.println(stringBuilder);

        System.out.println("====================");




        FilterNode filterNode1 = (req, filterNode) -> {
            req.append(" run 1 begin");
            if (!filterNode.doChain(req, filterNode)) return false;
            req.append(" run 1 end ");
            return true;
        };

        FilterNode filterNode2 = (req, filterNode) -> {
            req.append(" run 2 begin");
            if (!filterNode.doChain(req, filterNode)) return false;
            req.append(" run 2 end ");
            return false;
        };

        FilterNode filterNode3 = (req, filterNode) -> {
            req.append(" run 3 begin");
            if (!filterNode.doChain(req, filterNode)) return false;
            req.append(" run 3 end ");
            return true;
        };
        ChainFilterContainer chainFilterContainer = new ChainFilterContainer();
        chainFilterContainer.add(filterNode1).add(filterNode2).add(filterNode3);
        StringBuilder appendBuilder = new StringBuilder(" come on ");
        chainFilterContainer.doChain(appendBuilder, chainFilterContainer);
        System.out.println(appendBuilder);




    }



}
