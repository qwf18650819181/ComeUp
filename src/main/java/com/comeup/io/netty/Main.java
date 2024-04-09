package com.comeup.io.netty;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年4月9日 0009
 * @version: 1.0
 */
public class Main {

    public static void main(String[] args) {
        SelectorThreadGroup boss = new SelectorThreadGroup(3);
        SelectorThreadGroup worker = new SelectorThreadGroup(3);
        boss.setWorker(worker);
        boss.bind(9999);
        boss.bind(8888);
        boss.bind(6666);
        boss.bind(7777);
    }
}
