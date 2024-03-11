package com.comeup.design.command;

import java.util.Stack;

/**
 * @author: qiu wanzi
 * @date: 2024年3月11日 0011
 * @version: 1.0
 * @description: TODO
 */
public class Invoker {

    private Stack<Command> commandLogs = new Stack<>();

    public void executeCommand(Command command) {
        command.execute();
        commandLogs.push(command);
    }

    public void undoCommand() {
        if (!commandLogs.isEmpty()) {
            Command command = commandLogs.pop();
            command.undo();
        }
    }


}
