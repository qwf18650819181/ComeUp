package com.comeup.design.chainofresponsibility;

import java.util.ArrayList;
import java.util.List;

/**
 * @auth: qwf
 * @date: 2023年12月11日 0011
 * @description:
 */
public class ChainOfResponsibility {

    public static class Chain {
        private static List<Filter> list = new ArrayList<>();

        public Chain add(Filter filter) {
            list.add(filter);
            return this;
        }

        public String chainFilter(StringBuilder str) {
            list.forEach(item -> item.doFilter(str));
            return str.toString();
        }
    }
    public static class WebsiteFilter implements Filter {
        @Override
        public boolean doFilter(StringBuilder msg) {
            msg.append(" website ");
            return true;
        }
    }

    public static class CodeFilter implements Filter {
        @Override
        public boolean doFilter(StringBuilder msg) {
            msg.append(" code ");
            return true;
        }
    }



    public static void main(String[] args) {
        StringBuilder a = new StringBuilder("hello world");
        Chain chain = new Chain();
        chain.add(new WebsiteFilter()).add(new CodeFilter());
        String str = chain.chainFilter(a);
        System.out.println(str);
    }


}
