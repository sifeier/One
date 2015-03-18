package com.one.design.observer;

/**
 * Created by sifeier on 15/3/18.
 */
public class CurrentConditionsDisplay2 implements Observer, DisplayItem {
    private float temperature;
    private float humidity;
    private float press;
    private Subject weatherData;

    public CurrentConditionsDisplay2(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float tmp, float press, float humidity) {
        this.temperature = tmp;
        this.humidity = humidity;
        this.press = press;
        display();
    }

    @Override
    public void display() {
        System.out.println("display in Observer");
    }

}
