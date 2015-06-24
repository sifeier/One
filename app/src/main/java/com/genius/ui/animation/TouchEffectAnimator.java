package com.genius.ui.animation;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

/**
 * Created by sifeier on 15/4/13.
 */
public class TouchEffectAnimator {

    private static final Interpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator(2.8f);

    private static final Interpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();

    private static final int IN_ANIM_DURATION = 250;

    private static final int OUT_ANIM_DURATION = 160;

    private static final int MAX_BACK_ALPHA = 180;

    private static final int MAX_RIPPLE_ALPHA = 255;

    private View mView;

    private float[] f = new float[8];

    private Paint mPaint;

    public TouchEffectAnimator(View view) {
        mView = view;
        mPaint = new Paint(ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        initTouch();
    }

    private void initTouch() {

    }


}
