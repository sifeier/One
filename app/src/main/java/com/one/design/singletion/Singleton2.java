package com.one.design.singletion;

/**
 * Lazy initialization, use class loader order
 * Created by sifeier on 14-8-7.
 */
public class Singleton2 {

    private static class Singleton2Holder {
        private static final Singleton2 INSTANCE = new Singleton2();
    }

    private Singleton2() {};

    public static final Singleton2 getInstance() {
        return Singleton2Holder.INSTANCE;
    }
}
