package org.example.display;

import org.example.observer.Observer;

public class PhoneDisplay implements Observer {

    @Override
    public void update(int temperature) {
        System.out.println("📱 Phone display: Current temperature is " + temperature + "°C");
    }
}
