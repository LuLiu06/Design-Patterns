package fi.metropolia.assignment2.element.b;

import fi.metropolia.assignment2.element.Button;

public class ButtonB extends Button {

    public ButtonB(String text) {
        super(text);
    }

    @Override
    public void display() {
        String content = " " + text + " ";
        String top = "/" + "=".repeat(content.length()) + "\\";
        String mid = "|" + content + "|";
        String bot = "\\" + "=".repeat(content.length()) + "/";
        System.out.println(top);
        System.out.println(mid);
        System.out.println(bot);
    }
}
