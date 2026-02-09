package org.example.weather;

import org.example.observer.Observer;
import org.example.subject.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeatherStation extends Thread implements Subject {

    private final List<Observer> observers = new ArrayList<>();
    private final Random random = new Random();

    private int temperature;

    private static final int MIN_TEMPERATURE = -20;
    private static final int MAX_TEMPERATURE = 40;

    public WeatherStation() {
        this.temperature =
                random.nextInt(MAX_TEMPERATURE - MIN_TEMPERATURE + 1) + MIN_TEMPERATURE;
        System.out.println("Initial temperature: " + temperature + "°C");
    }

    @Override
    public void run() {
        while (true) {
            try {
                int sleepTime = random.nextInt(5000) + 1000; // 1–5 seconds
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            updateTemperature();
            notifyObservers();
        }
    }

    private void updateTemperature() {
        int change = random.nextBoolean() ? 1 : -1;
        temperature += change;

        if (temperature < MIN_TEMPERATURE) {
            temperature = MIN_TEMPERATURE;
        }
        if (temperature > MAX_TEMPERATURE) {
            temperature = MAX_TEMPERATURE;
        }

        System.out.println("\nWeatherStation updated temperature to " + temperature + "°C");
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }
}
