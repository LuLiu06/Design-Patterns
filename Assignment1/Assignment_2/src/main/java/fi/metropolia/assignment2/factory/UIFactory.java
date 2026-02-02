package fi.metropolia.assignment2.factory;

import fi.metropolia.assignment2.element.Button;
import fi.metropolia.assignment2.element.TextField;
import fi.metropolia.assignment2.element.Checkbox;

public abstract class UIFactory {

    public abstract Button createButton(String text);
    public abstract TextField createTextField(String text);
    public abstract Checkbox createCheckbox(String text);
}
