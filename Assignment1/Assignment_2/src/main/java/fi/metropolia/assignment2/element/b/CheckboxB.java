package fi.metropolia.assignment2.element.b;

import fi.metropolia.assignment2.element.Checkbox;

public class CheckboxB extends Checkbox {

    public CheckboxB(String text) {
        super(text);
    }

    @Override
    public void display() {
        System.out.println("( ) " + text);
    }
}
