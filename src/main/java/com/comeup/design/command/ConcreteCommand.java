package com.comeup.design.command;

/**
 * @author: qiu wanzi
 * @date: 2024年3月11日 0011
 * @version: 1.0
 * @description: TODO
 */
public class ConcreteCommand implements Command {

    private final TextEditor textEditor;
    private final String appendText;

    public ConcreteCommand(TextEditor textEditor, String appendText) {
        this.textEditor = textEditor;
        this.appendText = appendText;
    }

    @Override
    public void execute() {
        this.textEditor.append(appendText);
    }

    @Override
    public void undo() {
        this.textEditor.undo(appendText);
    }
}
