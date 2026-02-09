package org.example.display;

import org.example.observer.Observer;

public class WindowDisplay implements Observer {

    @Override
    public void update(int temperature) {
        System.out.println("🪟 Window display: Outside temperature is " + temperature + "°C");
    }
}
