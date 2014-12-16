package com.one.anim;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.nineoldandroids.animation.Animator;

/**
 * Created by sifeier on 14-12-3.
 */
public class DragleView extends ImageView {

    public DragleView(Context context) {
        this(context, null);
    }

    public DragleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

}
