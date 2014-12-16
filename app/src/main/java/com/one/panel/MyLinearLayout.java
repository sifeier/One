package com.one.panel;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by sifeier on 14-11-7.
 */
public class MyLinearLayout extends LinearLayout {

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    GestureDetector gestureDetector;

    public void setGestureDetector(GestureDetector gestureDetector) {
        this.gestureDetector = gestureDetector;
    }

    private PanelTouchUpListener mPanelTouchUpListener;
    public void setPanelTouchUpListener(PanelTouchUpListener listener) {
        this.mPanelTouchUpListener = listener;
    }

    public interface PanelTouchUpListener {
        public void onTouchUP();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(gestureDetector.onTouchEvent(ev)) {
            ev.setAction(3);
            return super.dispatchTouchEvent(ev);
        }

        Log.e("action before", " " + ev.getAction());
//
//        if(ev.getAction() == 3) {
//            Log.e("action after2", " " + ev.getAction());
//            super.dispatchTouchEvent(ev);
//            this.setClickable(false);
//            ev.setAction(0);
//            super.dispatchTouchEvent(ev);
//            ev.setAction(2);
//            super.dispatchTouchEvent(ev);
//            ev.setAction(3);
//            this.setClickable(true);
//        }


//        else if(ev.getAction() == MotionEvent.ACTION_UP) {
//            Log.e("buke", "callback listener");
//            if(mPanelTouchUpListener != null) {
//                mPanelTouchUpListener.onTouchUP();
//                this.requestFocus();
//            }
//        }
        boolean ss = super.dispatchTouchEvent(ev);
        Log.e("dispatchTouchEvent", "super return: " + ss);
        return true;
    }
}
