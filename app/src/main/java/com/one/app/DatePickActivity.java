package com.one.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.one.R;

/**
 * Created by sifeier on 15/1/26.
 */
public class DatePickActivity extends Activity {

    private static final String TAG = "DatePickActivity";

    Button btn1;
    Button btn2;
    TextView tv1;
    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_date);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);


    }


}
