package com.one.design.observer;

/**
 * observer pattern - 2 Created by sifeier on 15/3/18.
 */
public class WeatherData2 implements Subject {

    private float humidity;

    private float pressure;

    private float temperature;

    @Override
    public void registerObserver(Object o) {

    }

    @Override
    public void removeObserver(Object o) {

    }

    @Override
    public void notifyObservers() {

    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}
