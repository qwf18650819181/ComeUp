package com.comeup.design.chainofresponsibility;

/**
 * @auth: qwf
 * @date: 2023年12月11日 0011
 * @description:
 */
public interface Filter {

    boolean doFilter(StringBuilder msg);
}
