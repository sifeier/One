package com.one.favor;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.one.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

/**
 * Created by buke on 15/7/17.
 */
public class FavorLayout extends RelativeLayout implements View.OnClickListener{

    private static final String TAG = "FavorLayout";

    private Random mRandom;

    private int dHeight = 40;  // 爱心的高和宽

    private int dWidth = 40;

    private int mHeight;  //FavorLayout 的高和宽

    private int mWidth;

    private RelativeLayout.LayoutParams mLayoutParams;

    private Drawable red;

    private Drawable yellow;

    private Drawable green;

    private Drawable[] mDrawables;

    public FavorLayout(Context context) {
        this(context, null);
    }

    public FavorLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FavorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setBackgroundColor(getResources().getColor(R.color.Lavender));
        mRandom = new Random();

        mLayoutParams = new LayoutParams(dWidth, dHeight);
        mLayoutParams.addRule(CENTER_HORIZONTAL, TRUE);
        mLayoutParams.addRule(ALIGN_PARENT_BOTTOM, TRUE);

        mDrawables = new Drawable[3];
        red = getResources().getDrawable(R.color.LightPink);
        yellow = getResources().getDrawable(R.color.Gold);
        green = getResources().getDrawable(R.color.SpringGreen);

        mDrawables[0] = red;
        mDrawables[1] = yellow;
        mDrawables[2] = green;
    }

    private AnimatorSet getEnterAnimtor(final View target) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(target,  View.ALPHA.getName(), 0.2f, 1f);
        ObjectAnimator scalex = ObjectAnimator.ofFloat(target, View.SCALE_X.getName(), 0.2f, 1f);
        ObjectAnimator scaley = ObjectAnimator.ofFloat(target, View.SCALE_Y.getName(), 0.2f, 1f);
        AnimatorSet enter = new AnimatorSet();
        enter.setDuration(500);
        enter.setInterpolator(new LinearInterpolator());
        enter.playTogether(alpha, scalex, scaley);
        enter.setTarget(target);
        return enter;
    }

    public void addFavor() {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(mDrawables[mRandom.nextInt(3)]);
        imageView.setLayoutParams(mLayoutParams);

        addView(imageView);

        Animator animator = getEnterAnimtor(imageView);
        animator.start();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // must after onMeasuer
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    public void onClick(View v) {
        addFavor();
    }
}
