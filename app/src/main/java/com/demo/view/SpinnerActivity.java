package com.demo.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.one.R;

/**
 * Created by sifeier on 15/1/26.
 */
public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener{

    private static final String[] COUNTRY = {
            "China",
            "England",
            "France",
            "British",
            "Aus"
    };

    TextView content;
    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_spinner);

        content = (TextView)findViewById(R.id.content);
        sp = (Spinner) findViewById(R.id.sp);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, COUNTRY);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        content.setText(COUNTRY[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
