package com.one.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by buke on 15/7/17.
 */
public class ScrollLayout extends ScrollView {

    private Handler mHandler = new Handler(Looper.getMainLooper());

    public ScrollLayout(Context context) {
        super(context);
    }

    public ScrollLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ScrollLayout(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(2, h, oldw, oldh);
        if (h < oldh) {
            mHandler.postDelayed(new Runnable() { //4.0以下某些手机不做延迟的话没效果
                @Override
                public void run() {
                    fullScroll(ScrollView.FOCUS_DOWN);
                }
            }, 100);
        }
    }
}

