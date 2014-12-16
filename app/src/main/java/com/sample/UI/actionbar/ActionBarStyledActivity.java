package com.sample.UI.actionbar;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

import com.one.R;
/**
 * Created by sifeier on 14-11-21.
 */
public class ActionBarStyledActivity extends ActionBarActivity implements ActionBar.TabListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actionbar_sample_main);

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ab.addTab(ab.newTab().setText("T 1").setTabListener(this));
        ab.addTab(ab.newTab().setText("T 2").setTabListener(this));
        ab.addTab(ab.newTab().setText("T 3").setTabListener(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
