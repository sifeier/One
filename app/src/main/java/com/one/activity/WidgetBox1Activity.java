package com.one.activity;

import com.demo.common.logger.Log;
import com.one.R;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;
import android.net.Uri;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by sifeier on 15/6/24.
 */
public class WidgetBox1Activity extends Activity {

    private static final String TAG = "WidgetBox1Activity";

    private ImageView mRotateImage;

    private Animation mAnimation;

    private int animIndex = 0;

    VideoView mVideoView;

    private static final String VIDEO_PATH = "diandian_lauch.mp4";

    List<Animation> mAnimationList = new ArrayList<>();

    private SurfaceView mSurfaceView;
    private MediaPlayer mMediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_widget_box1);


        initMediaPlayer();
//        initVideoView();
//        initRotateAnim();
    }

    /**
     * 方案2: 使用MediaPlayer＋SurfaceView, 自定义灵活
     */
    private void initMediaPlayer() {
        mSurfaceView = (SurfaceView)findViewById(R.id.surface_vv);
        mMediaPlayer = new MediaPlayer();
        mSurfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        try{
            mMediaPlayer.setDataSource(copyAssetToFile());
            mMediaPlayer.setDisplay(mSurfaceView.getHolder());
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                    onPlayFinish();
                }
            });
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (Exception ex) {
            Log.e(TAG, "mMediaPlayer.setDataSource exception: " + ex.getMessage());
        }


    }

    /**
     * 方案1: 采用VideoPlayer，自带播放控制器，使用方便，不灵活
     */
    private void initVideoView() {
        mVideoView = (VideoView) findViewById(R.id.vv);
        MediaController mc = new MediaController(this);
        mc.hide();
        mVideoView.setMediaController(mc);

        String path = copyAssetToFile();
        mVideoView.setVideoPath(path);
        mVideoView.start();

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
                onPlayFinish();
            }
        });
    }

    private void onPlayFinish() {
        this.finish();
    }

    private String copyAssetToFile() {
        InputStream is;
        FileOutputStream fos;
        byte[] buffer = new byte[1024 * 8];
        try {
            is = getAssets().open("diandian_lauch.mp4");
            String path = Environment.getExternalStorageDirectory() + "/" + VIDEO_PATH;
            Log.e(TAG, "path: " + path);
            fos = new FileOutputStream(path);
            int count = is.read(buffer, 0, 1024 * 8);
            while (count != -1) {
                fos.write(buffer, 0, count);
                count = is.read(buffer, 0, 1024 * 8);
            }
            is.close();
            fos.close();
            return path;
        } catch (IOException ex) {
            Log.e(TAG, "open video mp4 error");
        }
        return null;
    }


    private void initRotateAnim() {
        mAnimationList.add(AnimationUtils.loadAnimation(this, R.anim.image_rotate));
        mAnimationList.add(AnimationUtils.loadAnimation(this, R.anim.image_rotate_90));
        mAnimationList.add(AnimationUtils.loadAnimation(this, R.anim.image_rotate_180));
        mAnimationList.add(AnimationUtils.loadAnimation(this, R.anim.image_rotate_270));
        mAnimation = mAnimationList.get(animIndex);
        mAnimation.setFillAfter(true);

        mRotateImage = (ImageView) findViewById(R.id.rotate_iv);
        mRotateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRotateImage.startAnimation(mAnimation);
                animIndex++;
                if (animIndex >= mAnimationList.size()) {
                    animIndex = 0;
                }
                mAnimation = mAnimationList.get(animIndex);
                mAnimation.setFillAfter(true);
            }
        });
    }

}
