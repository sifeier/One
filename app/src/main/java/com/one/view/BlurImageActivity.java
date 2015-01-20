package com.one.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.one.R;

public class BlurImageActivity extends Activity {

    private ImageView mBlurImage;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_blur_image);

        mTextView = (TextView) findViewById(R.id.main_alpha_text);

        mBlurImage = (ImageView) findViewById(R.id.main_blur_bg);
        mBlurImage.setOnTouchListener(new OnTouchListener() {

            private float mLastY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mLastY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float y = event.getY();
                        float alphaDelt = (y - mLastY) / 1000;
                        float alpha = mBlurImage.getAlpha() + alphaDelt;
                        if (alpha > 1.0) {
                            alpha = 1.0f;
                        } else if (alpha < 0.0) {
                            alpha = 0.0f;
                        }
                        mTextView.setText(String.valueOf(alpha));
                        mBlurImage.setAlpha(alpha);
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return true;
            }
        });

    }

}
