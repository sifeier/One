package com.one.activity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.PowerManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.one.R;

public class ScreenLockActivity extends ActionBarActivity implements SensorEventListener{
    private static final String TAG = "Activity - ScreenLockActivity";
    private SensorManager mSensorManager;
    private PowerManager mPowerManager;
    private PowerManager.WakeLock mWakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_lock);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL);
        mPowerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        initWakeLock();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.screen_lock, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseSensor();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void releaseSensor() {
        if(mSensorManager != null) {
            mWakeLock.release();
            mSensorManager.unregisterListener(this);
            mWakeLock = null;
            mSensorManager = null;
        }
    }

    private void initWakeLock() {
        try {
            // PROXIMITY_SCREEN_OFF_WAKE_LOCK = 0x00000020 (32)
            mWakeLock = mPowerManager.newWakeLock(32, "myPower");
        } catch (Exception ex) {
            Log.e(TAG, "initWakeLock Exception: " + ex.getMessage());
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] distance = event.values;
        if(distance != null && event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if(distance[0] == 0.0) {
                if (mWakeLock.isHeld()) {
                    return;
                } else {
                    mWakeLock.acquire();
                }
            } else {
                if (mWakeLock.isHeld()) {
                    return;
                } else {
                    mWakeLock.setReferenceCounted(false);
                    mWakeLock.release();
                }
            }

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //TODO auto gennerate
    }
}
