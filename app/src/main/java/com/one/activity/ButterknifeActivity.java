package com.one.activity;

import com.one.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by buke on 15/11/2.
 */
public class ButterknifeActivity extends Activity {

    @Bind(R.id.view_btn)
    Button mViewBtn;

    @Bind(R.id.view_tv)
    TextView mViewTv;

    @OnClick(R.id.view_btn)
    void btnOnClick() {
        if (mViewTv.getVisibility() == View.VISIBLE) {
            mViewTv.setVisibility(View.GONE);
        } else {
            mViewTv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_butterknife_example);
        ButterKnife.bind(this);
    }

}
