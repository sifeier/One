package com.one.material;

import com.one.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

/**
 * Created by sifeier on 15/5/21.
 */
public class MaterialDActivity extends Activity {

    private TabLayout mTableLayout;
    private TextInputLayout mTextInputLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        mTableLayout = (TabLayout) findViewById(R.id.widget_tablayout);
        mTableLayout.addTab(mTableLayout.newTab().setText("tab1"));
        mTableLayout.addTab(mTableLayout.newTab().setText("tab2"));
        mTableLayout.addTab(mTableLayout.newTab().setText("tab3"));

        mTableLayout.setupWithViewPager(mViewPager);


        mTextInputLayout = (TextInputLayout) findViewById(R.id.widget_til);
        mTextInputLayout.setHint("Password");

    }
}