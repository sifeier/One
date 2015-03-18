package com.one.design.singletion;

/**
 * Singleton Design Pattern: Double check locking
 * Created by sifeier on 14-8-7.
 * @deprecated  in java
 */
@Deprecated
public class Singleton {
    private static volatile Singleton instance = null;

    public static Singleton getInstance() {
        if(instance == null) {
            synchronized (Singleton.class) {
                if(instance == null) {
                    instance = new Singleton();
                    initSomeThing();
                }
            }
        }
        return instance;
    }

    private Singleton() {};  //Singleton

    private static void initSomeThing() {};
}
