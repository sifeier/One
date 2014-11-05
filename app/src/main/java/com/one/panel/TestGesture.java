package com.one.panel;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.one.R;

public class TestGesture extends Activity {
    GestureDetector mGestureDetector;
    Panel mLeftPanel;
    Button mBtn;
    private static final int FLING_MIN_DISTANCE = 50;
    private static final int FLING_MIN_VELOCITY = 0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gesture);
        mBtn = (Button)findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onCLick", "btn OnClickListener");
            }
        });
        RelativeLayout ll=(RelativeLayout)findViewById(R.id.ll);

        mLeftPanel = (Panel)findViewById(R.id.panel_ltr);
        mLeftPanel.close();
        //mGestureDetector = new GestureDetector(mLeftPanel.getParentOnGestureListener());
        //mGestureDetector = new GestureDetector(mLeftPanel.getmGestureListener());
        ll.setLongClickable(true);
        ll.setOnTouchListener(mLeftPanel.getTouchListener());
    }

}