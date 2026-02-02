package fi.metropolia.assignment2.element.b;

import fi.metropolia.assignment2.element.TextField;

public class TextFieldB extends TextField {

    public TextFieldB(String text) {
        super(text);
    }

    @Override
    public void display() {
        String content = " " + text + " ";
        String border = "#" + "#".repeat(content.length()) + "#";
        System.out.println(border);
        System.out.println("#" + content + "#");
        System.out.println(border);
    }
}
