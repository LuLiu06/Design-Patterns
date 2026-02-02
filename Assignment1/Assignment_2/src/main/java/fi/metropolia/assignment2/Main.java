package fi.metropolia.assignment2;

import fi.metropolia.assignment2.factory.*;
import fi.metropolia.assignment2.element.*;

public class Main {

    public static void main(String[] args) {

        UIFactory factory;

        if (args.length > 0 && args[0].equalsIgnoreCase("B")) {
            factory = new BFactory();
        } else {
            factory = new AFactory();
        }

        Button button = factory.createButton("Submit");
        TextField textField = factory.createTextField("username");
        Checkbox checkbox = factory.createCheckbox("Accept terms");

        button.display();
        textField.display();
        checkbox.display();

        System.out.println("\n--- After setText() ---");

        button.setText("Confirm");
        textField.setText("new_user");
        checkbox.setText("Subscribe");

        button.display();
        textField.display();
        checkbox.display();
    }
}
