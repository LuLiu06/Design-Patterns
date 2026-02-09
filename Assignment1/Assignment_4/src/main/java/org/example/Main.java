package org.example;

import org.example.display.PhoneDisplay;
import org.example.display.WindowDisplay;
import org.example.observer.Observer;
import org.example.weather.WeatherStation;

public class Main {

    public static void main(String[] args) {

        WeatherStation weatherStation = new WeatherStation();

        Observer phoneDisplay = new PhoneDisplay();
        Observer windowDisplay = new WindowDisplay();

        weatherStation.registerObserver(phoneDisplay);
        weatherStation.registerObserver(windowDisplay);

        weatherStation.start(); // start weather station thread

        try {
            Thread.sleep(15000); // run for 15 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- Removing WindowDisplay observer ---\n");
        weatherStation.removeObserver(windowDisplay);

        try {
            Thread.sleep(15000); // continue simulation
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
