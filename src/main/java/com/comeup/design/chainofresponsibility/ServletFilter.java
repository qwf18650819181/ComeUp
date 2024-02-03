package com.comeup.design.chainofresponsibility;

/**
 * @auth: qwf
 * @date: 2023年12月11日 0011
 * @description:
 */
public interface ServletFilter {

    boolean doFilter(StringBuilder req, StringBuilder res, ChainOfResponsibilityForServletFilter.Chain chain);
}
