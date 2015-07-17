package com.one.common.widget;

import com.one.common.R;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by buke on 15/7/17.
 */
public class ProgressTextBar extends LinearLayout {

    private TextView mTextView;

    public ProgressTextBar(Context context) {
        this(context, null);
    }

    public ProgressTextBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public ProgressTextBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.progress_bar, this);
        mTextView = (TextView) findViewById(R.id.progress_tv);
    }

    public void setText(int resId) {
        mTextView.setText(resId);
    }

    public void setTextColor(int color) {
        mTextView.setTextColor(color);
    }
}
