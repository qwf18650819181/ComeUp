package com.comeup.design.chainofresponsibility;

import java.util.ArrayList;
import java.util.List;

/**
 * @auth: qwf
 * @date: 2023年12月11日 0011
 * @description:
 */
public class ChainOfResponsibilityForServletFilter {

    public static class Chain implements ServletFilter {
        private static List<ServletFilter> list = new ArrayList<>();

        private static Integer INDEX = 0;

        public Chain add(ServletFilter filter) {
            list.add(filter);
            return this;
        }

        public boolean doFilter(StringBuilder req, StringBuilder res, Chain chain) {
            if (INDEX == list.size()) return false;
            list.get(INDEX++).doFilter(req, res, this);
            return true;
        }
    }
    public static class WebsiteFilter implements ServletFilter {
        @Override
        public boolean doFilter(StringBuilder req, StringBuilder res, Chain chain) {
            req.append(" website before");
            chain.doFilter(req, res, chain);
            res.append(" website after");
            return true;
        }

    }

    public static class CodeFilter implements ServletFilter {
        @Override
        public boolean doFilter(StringBuilder req, StringBuilder res, Chain chain) {
            req.append(" code before");
            chain.doFilter(req, res, chain);
            res.append(" code after");
            return true;
        }
    }



    public static void main(String[] args) {
        StringBuilder req = new StringBuilder("hello world request");
        StringBuilder res = new StringBuilder("hello world response");
        Chain chain = new Chain();
        chain.add(new WebsiteFilter()).add(new CodeFilter());
        chain.doFilter(req, res, chain);
        System.out.println(req);
        System.out.println(res);
    }


}
