package fi.metropolia.assignment2.factory;

import fi.metropolia.assignment2.element.*;
import fi.metropolia.assignment2.element.a.*;

public class AFactory extends UIFactory {

    @Override
    public Button createButton(String text) {
        return new ButtonA(text);
    }

    @Override
    public TextField createTextField(String text) {
        return new TextFieldA(text);
    }

    @Override
    public Checkbox createCheckbox(String text) {
        return new CheckboxA(text);
    }
}

