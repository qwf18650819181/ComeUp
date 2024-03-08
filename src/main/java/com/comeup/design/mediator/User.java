package com.comeup.design.mediator;

/**
 * @author: qiu wanzi
 * @date: 2024年3月8日 0008
 * @version: 1.0
 * @description: TODO
 */
public class User implements ConsoleCustomer {

    private String name;
    private String password;
    private ConsoleWindow consoleWindow;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String name, String password, ConsoleWindow consoleWindow) {
        this.name = name;
        this.password = password;
        this.consoleWindow = consoleWindow;
    }

    public void sendMessage(String message) {
        consoleWindow.showMessage(this, message);
    }

}
