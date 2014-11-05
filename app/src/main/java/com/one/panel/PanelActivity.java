package com.one.panel;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;

import com.one.R;

public class PanelActivity extends ActionBarActivity implements View.OnTouchListener, OnGestureListener {

    GestureDetector mGestureDetector;
    private static final int FLING_MIN_DISTANCE = 50;
    Panel mLeftPanel;
    Panel mRightPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);
        mLeftPanel = (Panel) this.findViewById(R.id.panel_ltr);
        mRightPanel = (Panel) this.findViewById(R.id.panel_rtl);
        mLeftPanel.setOnTouchListener(this);
        mLeftPanel.setLongClickable(true);
        mGestureDetector =  new GestureDetector(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.e("onTouch", "onTouch");
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.e("onScroll", " X,Y: " + distanceX + " " + distanceY);
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.e("onFling", "x,Y: " + velocityX + " " + velocityY);
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.panel, menu);
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
}
