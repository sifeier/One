package com.one.design.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * built－in JDK 实现
 * Created by sifeier on 15/3/18.
 */
public class CurrentConditionsDisplay implements DisplayItem, Observer {
    private float mTemperature;
    private float mHumidity;
    private Observable mObservable;


    public CurrentConditionsDisplay(Observable observable) {
        this.mObservable = observable;
        observable.addObserver(this);
    }

    @Override
    public void display() {

    }

    @Override
    public void update(Observable observable, Object data) {

    }
}
