package com.one.activity;

import com.one.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sifeier on 15/6/24.
 */
public class WidgetBox1Activity extends Activity {

    private ImageView mRotateImage;
    private Animation mAnimation;
    private int animIndex = 0;

    List<Animation> mAnimationList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_widget_box1);
        initRotateAnim();
    }

    private void initRotateAnim() {
        mAnimationList.add(AnimationUtils.loadAnimation(this, R.anim.image_rotate));
        mAnimationList.add(AnimationUtils.loadAnimation(this, R.anim.image_rotate_90));
        mAnimationList.add(AnimationUtils.loadAnimation(this, R.anim.image_rotate_180));
        mAnimationList.add(AnimationUtils.loadAnimation(this, R.anim.image_rotate_270));
        mAnimation = mAnimationList.get(animIndex);
        mAnimation.setFillAfter(true);

        mRotateImage = (ImageView)findViewById(R.id.rotate_iv);
        mRotateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRotateImage.startAnimation(mAnimation);
                animIndex++;
                if(animIndex >= mAnimationList.size()) {
                    animIndex = 0;
                }
                mAnimation = mAnimationList.get(animIndex);
                mAnimation.setFillAfter(true);
            }
        });
    }

}
