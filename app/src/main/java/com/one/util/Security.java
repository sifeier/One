package com.one.util;

import android.os.Build;

import java.security.NoSuchAlgorithmException;

import javax.crypto.SecretKeyFactory;

/**
 * Created by sifeier on 14-8-8.
 */
public class Security {

    public static SecretKeyFactory getFactory() {
        SecretKeyFactory factory = null;
        try {
            if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
                factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1And8bit");
            } else {
                factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            }
        } catch ( NoSuchAlgorithmException ex) {
            ex.printStackTrace();

        }
        return factory;
    }




}
