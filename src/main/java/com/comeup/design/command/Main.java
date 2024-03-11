package com.comeup.design.command;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月11日 0011
 * @version: 1.0
 */
public class Main {

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor("Hello World");

        Invoker invoker = new Invoker();

        invoker.executeCommand(new ConcreteCommand(textEditor, " nihao"));
        System.out.println(textEditor.getText());
        invoker.executeCommand(new ConcreteCommand(textEditor, " bielai"));
        System.out.println(textEditor.getText());
        invoker.executeCommand(new ConcreteCommand(textEditor, " qusi"));
        System.out.println(textEditor.getText());


        invoker.undoCommand();
        System.out.println(textEditor.getText());
        invoker.undoCommand();
        System.out.println(textEditor.getText());
        invoker.undoCommand();
        System.out.println(textEditor.getText());



    }

}
