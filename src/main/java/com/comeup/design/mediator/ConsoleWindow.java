package com.comeup.design.mediator;

/**
 * @author: qiu wanzi
 * @date: 2024年3月8日 0008
 * @version: 1.0
 * @description: TODO
 */
public class ConsoleWindow {

    public void showMessage(ConsoleCustomer consoleCustomer, String message) {
        System.out.println("[" + consoleCustomer.getName() + "]:" + message);
    }


    public static void main(String[] args) {
        ConsoleWindow consoleWindow = new ConsoleWindow();
        User user = new User("user", "123456", consoleWindow);
        Host host = new Host("host", consoleWindow);
        user.sendMessage("hello");
        host.sendMessage("hello");
    }


}
