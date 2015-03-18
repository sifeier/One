package com.one.fun;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.one.R;
import com.one.util.MorseCode;
import com.one.util.PinYin;

import java.util.List;

/**
 * Created by sifeier on 15/1/22.
 */
public class MorseCodeActivity extends Activity implements SensorEventListener {

    private static final String TAG = "MorseCodeActivity";

    private int count=0;

    EditText hanzi;
    EditText pinyin;
    EditText morse;

    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_morse);

        hanzi = (EditText) findViewById(R.id.hanzi);
        pinyin = (EditText) findViewById(R.id.pinyin);
        morse = (EditText) findViewById(R.id.morse);
        final TextView txt = (TextView) findViewById(R.id.txt);
        Log.e(TAG, "txt" + txt);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> list = sensorManager.getSensorList(Sensor.TYPE_ALL);

        for(Sensor s : list) {
            String tempString = "\n" + "  设备名称：" + s.getName() + "\n" + "  设备版本：" + s.getVersion() + "\n" + "  供应商："
                    + s.getVendor() + "\n";

            switch (s.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    txt.setText(txt.getText().toString() + s.getType() + " 加速度传感器accelerometer" + tempString);
                    break;
                case Sensor.TYPE_GYROSCOPE:
                    txt.setText(txt.getText().toString() + s.getType() + " 陀螺仪传感器gyroscope" + tempString);
                    break;
                case Sensor.TYPE_LIGHT:
                    txt.setText(txt.getText().toString() + s.getType() + " 环境光线传感器light" + tempString);
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    txt.setText(txt.getText().toString() + s.getType() + " 电磁场传感器magnetic field" + tempString);
                    break;
                case Sensor.TYPE_ORIENTATION:
                    txt.setText(txt.getText().toString() + s.getType() + " 方向传感器orientation" + tempString);
                    break;
                case Sensor.TYPE_PRESSURE:
                    txt.setText(txt.getText().toString() + s.getType() + " 压力传感器pressure" + tempString);
                    break;
                case Sensor.TYPE_PROXIMITY:
                    txt.setText(txt.getText().toString() + s.getType() + " 距离传感器proximity" + tempString);
                    break;
                case Sensor.TYPE_TEMPERATURE:
                    txt.setText(txt.getText().toString() + s.getType() + " 温度传感器temperature" + tempString);
                    break;
                default:
                    txt.setText(txt.getText().toString() + s.getType() + " 未知传感器" + tempString);
                    break;
            }
        }
    }

    public void toPinyinClick(View v) {
        String letter = PinYin.getPinYin(hanzi.getText().toString());
        pinyin.setText(letter);
    }

    public void toMorseClick(View v) {
        String ss = MorseCode.toMorse(pinyin.getText().toString());
        morse.setText(ss);
    }

    @Override
    protected void onPause() {
        //sensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        sensorManager.registerListener(this,
//                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
//                SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
//        float[] values = event.values;
//        Log.d(TAG, "X: " + values[0] + " Y:" + values[1] + " Z:" + values[2]);
//        count ++;
//        if(count % 60 == 0) {
//            StringBuilder sb = new StringBuilder();
//            sb.append("timestamp: " + event.timestamp);
//            sb.append("\n X: " + values[0]);
//            sb.append("\n Y: " + values[1]);
//            sb.append("\n Z: " + values[2]);
//            Log.e(TAG, sb.toString());
//        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//        count ++;
//        if(count % 60 == 0) {
//            Log.e(TAG, "sensor:" + sensor.getName() + "accuracy: " + accuracy);
//        }
    }
}
