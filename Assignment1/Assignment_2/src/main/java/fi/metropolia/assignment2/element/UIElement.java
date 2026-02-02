package fi.metropolia.assignment2.element;

public abstract class UIElement {
    protected String text;

    protected UIElement(String text) {
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public abstract void display();
}

