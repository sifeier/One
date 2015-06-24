package com.genius.ui.animation;

import android.view.animation.Animation;

/**
 * Created by sifeier on 15/4/13.
 */
public abstract class TouchEffectAnimatorListener implements Animation.AnimationListener {

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public abstract void onAnimationEnd(Animation animation);

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
