package com.comeup.design.command;

/**
 * @author: qiu wanzi
 * @date: 2024年3月11日 0011
 * @version: 1.0
 * @description: TODO
 */
public class TextEditor implements Receiver {

    private String text;

    public TextEditor(String text) {
        this.text = text;
    }

    public void append(String appendText) {
        this.text = this.text + appendText;
    }

    public void undo(String undoText) {
        this.text = this.text.contains(undoText) ? this.text.substring(0, this.text.indexOf(undoText)) : this.text;
    }

    public String getText() {
        return text;
    }
}
