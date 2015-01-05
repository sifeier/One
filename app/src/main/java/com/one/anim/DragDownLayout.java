package com.one.anim;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.Scroller;

import com.one.R;

/**
 * Created by shadow.ly on 2014/11/18.
 */
public class DragDownLayout extends FrameLayout {
    private OnDragListener listener;

    private int touchSlop;
    private int threshold = DEFAULT_THRESHOLD;
    private static final int DEFAULT_THRESHOLD = 3200;

    private Scroller scroller;

    private static final int POINTER_UP_ACTION_DOWN_DISMISS = 0;
    private static final int POINTER_UP_ACTION_UP_RESTORE = 1;
    private int pointerUpAction = POINTER_UP_ACTION_UP_RESTORE;

    public DragDownLayout(Context context) {
        super(context);
        init(context,null);
    }

    public DragDownLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public DragDownLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        scroller = new Scroller(context);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.DragView);
        if (a.hasValue(R.styleable.DragView_max_drag_height)) {
            threshold = a.getDimensionPixelSize(R.styleable.DragView_max_drag_height, DEFAULT_THRESHOLD);
        }
        a.recycle();
        a = null;
    }

    private float downX, downY, lastY;
    private int moved;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
        boolean intercept = false;

        float x = ev.getX();
        float y = ev.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = x;
                lastY = downY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                float deltaX = x - downX;
                float deltaY = y - downY;
                if(deltaY > Math.abs(deltaX) && deltaY >= touchSlop)
                    intercept = true;
                if(listener != null && listener.canChildScrollUp())
                    intercept = false;
                int scrollY = getScrollY();
                if(-scrollY > threshold){
                    intercept = false;
                }
                break;
        }
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);

        float y = ev.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                moved = 0;
                pointerUpAction = POINTER_UP_ACTION_UP_RESTORE;
                lastY = downY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaY = (int) (y - lastY);
                lastY = y;

                if(deltaY < 0 && deltaY + moved < 0) {
                    deltaY = -moved;
                }
                moved += deltaY;
                scrollBy(0, -deltaY/2); //下滑是手势下滑的1/2 防止滑的太快
                if(listener != null)
                    listener.onDrag(getScrollY(), threshold); //传参值改变未最大滑动值
                break;
            case MotionEvent.ACTION_UP:
                int scrollY = getScrollY();
                if(scrollY == 0)
                    break;
                if(-scrollY < threshold) {
                    //up
                    scroller.startScroll(0, scrollY, 0, -scrollY, 500);
                } else {
                    //down
                    scroller.startScroll(0, scrollY, 0, -getHeight() - scrollY, 500);
                    pointerUpAction = POINTER_UP_ACTION_DOWN_DISMISS;
                }
                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE:
                if(getScrollY() != 0) {
                    scroller.startScroll(0, getScrollY(), 0, -getScrollY(), 300);
                    invalidate();
                }
                break;
        }
        return true;
    }

    public void scrollUpOnResume() {
        int scrollY = getScrollY();
        scroller.startScroll(0, scrollY, 0, -scrollY, 1000);
    }
    public void scrollToTop(){
        int scrollY = getScrollY();
        scroller.startScroll(0, scrollY, 0, -scrollY, 0);
    }

    @Override
    public void computeScroll() {
        if(scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        } else {
            if(pointerUpAction == POINTER_UP_ACTION_DOWN_DISMISS) {
                setVisibility(GONE);
                if(listener != null)
                    listener.onDismiss();
                pointerUpAction = POINTER_UP_ACTION_UP_RESTORE;
            }
        }
    }
    public void setOnDragListener(OnDragListener listener) {
        this.listener = listener;
    }

    public interface OnDragListener {
        public boolean canChildScrollUp();
        public void onDrag(int draged, int maxHeight);
        public void onDismiss();
    }
}
