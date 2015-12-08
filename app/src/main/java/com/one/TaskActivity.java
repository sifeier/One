package com.one;

import com.one.widget.CropImageView;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * AsyncTask must be subclassed to be used. The subclass will override at least one method
 * (doInBackground(Params...)), and most often will override a second one (onPostExecute(Result).)
 *
 * Once created, a task is executed very simply: new DownloadFilesTask().execute(url1, url2, url3);
 *
 *
 * Created by buke on 15/9/11.
 */
public class TaskActivity extends Activity {

    private TextView mTextView;

    private CropImageView mTargetImage;

    private int mWidth = -1;
    private int mHeight = -1;
    private int stepLenght = -1;

    private static  final  int MAX_LEVEL = 9;
    private int level = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         *
         * in AndroidManifest.xml, the activity style should be set as follow:
         * android:theme="@android:style/Theme.Wallpaper"
         *
         */
        setContentView(R.layout.translucent_background);

//        setContentView(R.layout.ac_auto);
//        mTextView = (TextView) findViewById(R.id.content_tv);
//
//        mTargetImage = (CropImageView) findViewById(R.id.image_iv);
//        mTargetImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cropImageView();
//            }
//        });



//        new DownloadFilesTask().execute(1, 2, 3);

    }


    private void cropImageView() {
        if(mHeight == -1) {
            mHeight = mTargetImage.getMeasuredHeight();
            mWidth = mTargetImage.getMeasuredWidth();
            stepLenght = mHeight / MAX_LEVEL;
        }

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mTargetImage.getLayoutParams();
        if(level <=1 ) {
            level = MAX_LEVEL;
            params.height = mHeight;
        } else {
            level --;
            params.height = params.height - stepLenght;
        }

        mTargetImage.setLayoutParams(params);
        mTargetImage.invalidate();
    }

    private void animatorImage() {
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.scale_y);
        animator.setTarget(mTargetImage);
        animator.start();
    }

    private void animationImage() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_y);
        mTargetImage.clearAnimation();
        mTargetImage.startAnimation(animation);
    }


    private class DownloadFilesTask extends AsyncTask<Integer, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Integer... params) {
            return false;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            Toast toast = Toast.makeText(TaskActivity.this, "onProgressUpdate", Toast.LENGTH_LONG);
            toast.setGravity(
                    Gravity.TOP, 10, 10);
            toast.show();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            Toast.makeText(TaskActivity.this, "onPostExecute", Toast.LENGTH_LONG).show();
        }
    }

}
