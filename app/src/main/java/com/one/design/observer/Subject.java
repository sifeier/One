package com.one.design.observer;

import java.util.Objects;

/**
 * observer pattern - 2
 * Created by sifeier on 15/3/18.
 */
public interface Subject {
    public void registerObserver(Object o);
    public void removeObserver(Object o);
    public void notifyObservers();
}
