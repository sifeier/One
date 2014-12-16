package com.one.panel;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.one.R;
import com.one.activity.FrameLayoutActivity;

public class TestGesture extends Activity {
    GestureDetector mGestureDetector;
    Panel mLeftPanel;
    Panel mRightPanel;

    Button mBtn;
    EditText mEt;

    Context context;

    private static final int FLING_MIN_DISTANCE = 50;
    private static final int FLING_MIN_VELOCITY = 0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gesture);
        context = this;

        mBtn = (Button)findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onCLick", "btn OnClickListener");
            }
        });

        mEt = (EditText)findViewById(R.id.input_et);
        mEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onClicl", "EditText");
                mEt.setFocusableInTouchMode(true);
                InputMethodManager dmm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                //dmm.showSoftInput(mEt, 0);

            }
        });

        MyLinearLayout ll=(MyLinearLayout)findViewById(R.id.input_ll);
        int width =  this.getResources().getDisplayMetrics().widthPixels;

        mLeftPanel = (Panel)findViewById(R.id.panel_ltr);
        mLeftPanel.close();
        mLeftPanel.setVisibility(View.GONE);

        mRightPanel = (Panel)findViewById(R.id.panel_rtl);
        mRightPanel.findViewById(R.id.panelContent).setMinimumWidth(width);
        mRightPanel.close();

        ll.setLongClickable(true);
        ll.setGestureDetector(mRightPanel.getGestureDetector());
//        ll.setPanelTouchUpListener(mRightPanel.upListener);
    }

}