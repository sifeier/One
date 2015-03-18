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
        public boolean onTouchUp();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if( gestureDetector != null  && !gestureDetector.onTouchEvent(ev) && (ev.getAction()==MotionEvent.ACTION_UP)){
        }
        int action = ev.getAction();
        if(action == MotionEvent.ACTION_UP && mPanelTouchUpListener != null) {
            mPanelTouchUpListener.onTouchUp();
        }

        return super.dispatchTouchEvent(ev);
    }
}
