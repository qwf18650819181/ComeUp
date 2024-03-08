package com.comeup.design.mediator;

/**
 * @author: qiu wanzi
 * @date: 2024年3月8日 0008
 * @version: 1.0
 * @description: TODO
 */
public class Host implements ConsoleCustomer {

    private String name;
    private ConsoleWindow consoleWindow;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Host(String name, ConsoleWindow consoleWindow) {
        this.name = name;
        this.consoleWindow = consoleWindow;
    }

    public void sendMessage(String message) {
        consoleWindow.showMessage(this, message);
    }

}
