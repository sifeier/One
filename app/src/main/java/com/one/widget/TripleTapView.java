package com.one.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;
import java.util.jar.Attributes;

/**
 * Created by sifeier on 14-11-4.
 */
public class TripleTapView extends View {
    // Set the tap delay in milliseconds
    protected static final long TAP_MAX_DELAY = 500L;
    // Radius to capture tap within bound
    final static int RADIUS = 30;
    // Store all points with tap count
    public ArrayList<CustomPoint> point = new ArrayList<>();
    // Context to access view
    Context context;
    Paint paint;
    private long thisTime = 0, prevTime = 0;
    private boolean firstTap = true, doubleTap = false;
    float stopX, stopY, startX, startY;
    RectF area_rect;
    TapCounter tapCounter = new TapCounter(TAP_MAX_DELAY, TAP_MAX_DELAY);

    public TripleTapView(Context context) {
        super(context);
        this.context = context;
        initPaint();
    }

    public TripleTapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        for (CustomPoint point_temp : point) {
            // For changing tap circle color based on tap count
            switch (point_temp.count) {
                case 1:
                    paint.setColor(0xFFF0F8FF);
                    break;
                case 2:
                    paint.setColor(0xFFFFE4E1);
                    break;
                case 3:
                    paint.setColor(0xFFFFC0CB);
                    break;
            }
            canvas.drawCircle(point_temp.point.x, point_temp.point.y, 15, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                stopX = event.getX();
                stopY = event.getY();
                if (firstTap) {
                    addFirstTap();
                } else if (doubleTap) {
                    prevTime = thisTime;
                    thisTime = new Date().getTime();
                    if (thisTime > prevTime) {
                        if ((thisTime - prevTime) <= TAP_MAX_DELAY) {
                            if (area_rect.contains(stopX, stopY))
                                doubleTap = false;
                            else {
                                addPoint(1);
                                addFirstTap();
                            }
                        } else {
                            addPoint(1);
                            firstTap = true;
                        }
                    } else {
                        firstTap = true;
                    }
                } else {
                    prevTime = thisTime;
                    thisTime = new Date().getTime();
                    if (thisTime > prevTime) {
                        if ((thisTime - prevTime) <= TAP_MAX_DELAY) {
                            if (area_rect.contains(stopX, stopY)) {
                                addPoint(3);
                                firstTap = true;
                            } else {
                                addPoint(2);
                                addFirstTap();
                            }
                        } else {
                            addPoint(2);
                            firstTap = true;
                        }
                    } else {
                        firstTap = true;
                    }
                }
        }
        return true;
    }

    void addPoint(int tapCount) {
        point.add(new CustomPoint(new PointF(startX, startY), tapCount));
        invalidate();
    }

    void addFirstTap() {
        thisTime = new Date().getTime();
        firstTap = false;
        doubleTap = true;
        startX = stopX;
        startY = stopY;
        area_rect = new RectF(stopX - RADIUS, stopY - RADIUS, stopX + RADIUS,
                stopY + RADIUS);
        tapCounter.resetCounter();
    }

    class TapCounter extends CountDownTimer {
        public TapCounter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            if (doubleTap) {
                prevTime = thisTime;
                thisTime = new Date().getTime();
                if (thisTime > prevTime) {
                    if ((thisTime - prevTime) <= TAP_MAX_DELAY) {
                        doubleTap = false;
                    } else {
                        addPoint(1);
                        firstTap = true;
                    }
                } else {
                    firstTap = true;
                }
            } else if (!firstTap && !doubleTap) {
                prevTime = thisTime;
                thisTime =  new Date().getTime();
                if (thisTime > prevTime) {
                    if ((thisTime - prevTime) <= TAP_MAX_DELAY) {
                        addPoint(2);
                        firstTap = true;
                    }
                } else {
                    firstTap = true;
                }
            }
        }

        @Override
        public void onTick(long millisUntilFinished) {
        }

        public void resetCounter() {
            start();
        }
    }
}
