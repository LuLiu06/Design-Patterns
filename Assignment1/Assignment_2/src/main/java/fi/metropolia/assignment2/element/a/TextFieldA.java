package fi.metropolia.assignment2.element.a;

import fi.metropolia.assignment2.element.TextField;

public class TextFieldA extends TextField {

    public TextFieldA(String text) {
        super(text);
    }

    @Override
    public void display() {
        String content = " " + text + " ";
        String border = "+" + "-".repeat(content.length()) + "+";
        System.out.println(border);
        System.out.println("|" + content + "|");
        System.out.println(border);
    }
}
