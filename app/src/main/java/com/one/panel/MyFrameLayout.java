package com.one.panel;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by sifeier on 14-11-6.
 */
public class MyFrameLayout extends FrameLayout {

    public MyFrameLayout(Context context, AttributeSet attrs) {
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
        public boolean onTouchUP();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("dispatchTouchEvent", "pos1 Action: " + ev.getAction());
        if(gestureDetector.onTouchEvent(ev)) {
            Log.e("dispatchTouchEvent", "pos2");
            return true;
        } else if(ev.getAction() == MotionEvent.ACTION_UP) {
            Log.e("buke", "callback listener");
            if(mPanelTouchUpListener != null) {
                if(mPanelTouchUpListener.onTouchUP()) {
                    return false;
                }
            }
        }
        Log.e("dispatchTouchEvent", "pos3");
        return super.dispatchTouchEvent(ev);
    }
}
