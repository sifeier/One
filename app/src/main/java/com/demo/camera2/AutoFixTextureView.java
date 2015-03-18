package com.demo.camera2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;

/**
 * Created by sifeier on 14-11-21.
 */
public class AutoFixTextureView extends TextureView {

    private int mRatioWidth = 0;
    private int mRatioHeight = 0;

    public AutoFixTextureView(Context context) {
        this(context, null);
    }

    public AutoFixTextureView(Context context, AttributeSet attr) {
        this(context, attr, 0);
    }

    public AutoFixTextureView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setAspectRatio(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Size cannot be negative");
        }
        this.mRatioHeight = height;
        this.mRatioWidth = width;

        requestLayout();
    }

    @Override
    protected  void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }



}
