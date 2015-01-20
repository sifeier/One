package com.one.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.one.R;
import com.one.util.BitmapUtils;

/**
 * Created by sifeier on 15/1/20.
 */
public class GaussBlurActivity extends Activity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gauss_blur);
        imageView = (ImageView) findViewById(R.id.main_iv);
    }

    public void onBlurImageClick(View v) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.house_main);
        Bitmap newbm = BitmapUtils.blurBitmap(this, bitmap);
        imageView.setImageBitmap(newbm);
    }

    public void onRecoverImageClick(View v) {
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.house_main));
    }

}
