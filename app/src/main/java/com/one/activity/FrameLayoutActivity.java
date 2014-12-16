package com.one.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.one.R;
import com.one.util.BitmapUtils;

/**
 * Created by sifeier on 14-9-30.
 */
public class FrameLayoutActivity extends Activity {
    ImageView avatar;
    Context context;
    Resources res;
    Bitmap bg;
    Bitmap up;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        res = getResources();
        setContentView(R.layout.activity_center_circle_rl);
        initBitmap();
        avatar = (ImageView) findViewById(R.id.userAvatar);
        initClick();
    }

    private void initBitmap() {
        bg = BitmapFactory.decodeResource(getResources(), R.drawable.avatar_pp);
        up = BitmapFactory.decodeResource(getResources(), R.drawable.here_success_heart);
    }

    private void initClick() {
        avatar.setOnLongClickListener(new View.OnLongClickListener() {

            @SuppressLint("NewApi")
            @Override
            public boolean onLongClick(View v) {
                Bitmap combine  = BitmapUtils.combineBitmap(bg, up);
                avatar.setImageBitmap(combine);
                Toast.makeText(context, "longclick", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
