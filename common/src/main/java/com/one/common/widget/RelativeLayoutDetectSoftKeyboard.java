package com.one.common.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

/**
 * Created by buke on 15/7/17.
 */
public class RelativeLayoutDetectSoftKeyboard extends RelativeLayout{

    private Rect mRect;
    private Activity mActivity;

    private int viewHeight;
    private int statusBarHeight;
    private int screenHeight;
    private int dynamicDiffHeight;

    private static final int KEYBOARD_MIN_HEIGHT = 128;  //assume all soft keyboards are at least 128 pixels high

    public RelativeLayoutDetectSoftKeyboard(Context context) {
        this(context, null);
    }

    public RelativeLayoutDetectSoftKeyboard(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RelativeLayoutDetectSoftKeyboard(Context context, AttributeSet attributeSet,
            int defStyle) {
        super(context, attributeSet, defStyle);
        initView();
    }

    private void initView() {
        mRect = new Rect();
        mActivity = (Activity) getContext();
    }

    public interface SoftKeyboardVisibleListener {
        void onKeyboardShown(boolean isShowing);
    }

    private SoftKeyboardVisibleListener mSoftKeyboardVisibleListener;

    public void setSoftKeyboardVisibleListener(SoftKeyboardVisibleListener listener) {
        this. mSoftKeyboardVisibleListener = listener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            mRect.set(0, 0, 0, 0);
            mActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(mRect);
            statusBarHeight = mRect.top;
            screenHeight = mActivity.getWindowManager().getDefaultDisplay().getHeight();
            viewHeight = MeasureSpec.getSize(heightMeasureSpec);
            dynamicDiffHeight = (screenHeight - statusBarHeight) - viewHeight;

           if(mSoftKeyboardVisibleListener != null) {
               mSoftKeyboardVisibleListener.onKeyboardShown(dynamicDiffHeight > KEYBOARD_MIN_HEIGHT);
           }
        } catch (Exception ex) {
            Log.e("RelativeDetectKeyboard", ex.getMessage());
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
