package com.demo.common.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.demo.common.logger.Log;
import com.demo.common.logger.LogWrapper;

/**
 * Created by sifeier on 14-11-17.
 */
public class SampleActivityBase extends FragmentActivity {
    public static final String TAG = "SampleActivityBase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializaLogging();
    }

    public void initializaLogging() {
        LogWrapper logWrapper = new LogWrapper();
        Log.setLogNode(logWrapper);
        Log.i(TAG, "Ready");
    }
}
