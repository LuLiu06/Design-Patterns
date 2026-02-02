package fi.metropolia.assignment2.element.a;

import fi.metropolia.assignment2.element.Checkbox;

public class CheckboxA extends Checkbox {

    public CheckboxA(String text) {
        super(text);
    }

    @Override
    public void display() {
        System.out.println("[ ] " + text);
    }
}
