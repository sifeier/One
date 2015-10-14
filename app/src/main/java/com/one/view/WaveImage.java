package com.one.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by buke on 15/9/18.
 */
public class WaveImage extends View {

    public WaveImage(Context context) {
        this(context, null);
    }

    public WaveImage(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public WaveImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }}
