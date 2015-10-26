package com.angel.soundrecorder;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaRecorder;
import android.os.Bundle;

import java.io.File;


/**
 * Created by buke on 15/10/26.
 */
public class Recorder implements OnCompletionListener, OnErrorListener {

    static final String SAMPLE_PREFIX = "recording";

    static final String SAMPLE_PATH_KEY = "sample_path";

    static final String SAMPLE_LENGTH_KEY = "sample_length";

    public static final int STATE_IDLE = 0;

    public static final int STATE_RECORDING = 1;

    public static final int STATE_PLAYING = 2;

    int mState = STATE_IDLE;

    public static final int NO_ERROR = 0;

    public static final int SDCARD_ACCESS_ERROR = 1;

    public static final int INTERNAL_ERROR = 2;

    public static final int IN_CALL_RECORD_ERROR = 3;

    public interface OnStateChangedListener {

        public void onStateChange(int state);

        public void onError(int error);

    }

    OnStateChangedListener mOnStateChangedListener = null;


    long mSampleStart;

    int mSampleLength;

    File mSampleFile;

    MediaPlayer mMediaPlayer;

    MediaRecorder mMediaRecorder;

    public Recorder() {

    }

    public int getMaxAmplitute() {
        if (mState != STATE_RECORDING) {
            return 0;
        }
        return mMediaRecorder.getMaxAmplitude();
    }

    public void saveState(Bundle recordState) {
        recordState.putString(SAMPLE_PATH_KEY, mSampleFile.getAbsolutePath());
        recordState.putInt(SAMPLE_LENGTH_KEY, mSampleLength);
    }

    public void restoreState(Bundle recordState) {
        String path = recordState.getString(SAMPLE_PATH_KEY);
        if (path == null) {
            return;
        }

        int lenght = recordState.getInt(SAMPLE_LENGTH_KEY, -1);
        if (lenght == -1) {
            return;
        }

        File file = new File(path);
        if (!file.exists()) {
            return;
        }

        if (mSampleFile != null
                && mSampleFile.getAbsolutePath().compareTo(file.getAbsolutePath()) == 0) {
            return;
        }

        //TODO delete
        delete();

        mSampleFile = file;
        mSampleLength = lenght;

        signalStateChange(STATE_IDLE);
    }

    public void delete() {

    }

    public void clear() {

    }

    public void startRecording() {

    }

    public void stopRecording() {

    }

    public void startPlayback() {

    }

    public void stopPlayback() {

    }

    public void stop() {

    }

    public void setError(int error) {
        if(mOnStateChangedListener != null) {
            mOnStateChangedListener.onError(error);
        }
    }

    private void signalStateChange(int state) {
        if (mOnStateChangedListener != null) {
            mOnStateChangedListener.onStateChange(state);
        }
    }

    public void setOnStateChangedListener(OnStateChangedListener listener) {
        this.mOnStateChangedListener = listener;
    }

    public int getState() {
        return mState;
    }

    public int getSampleLength() {
        return mSampleLength;
    }

    public File getSampleFile() {
        return mSampleFile;
    }

    public int getProgress() {
        if (mState == STATE_RECORDING || mState == STATE_PLAYING) {
            return (int) ((System.currentTimeMillis() - mSampleStart) / 1000);
        }
        return 0;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }
}
