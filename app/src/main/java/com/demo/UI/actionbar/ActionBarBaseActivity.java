package com.demo.UI.actionbar;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;

import com.one.R;

/**
 * Created by sifeier on 14-11-21.
 */
public class ActionBarBaseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.actionbar_sample_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_main, menu);

        MenuItem locationItem = menu.add(0, R.id.menu_location, 0, R.string.menu_location);
        locationItem.setIcon(R.drawable.ic_action_location);

        MenuItemCompat.setShowAsAction(locationItem, MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_location:
                Log.e("actionbar", "menu_location");
                return true;
            case R.id.menu_refresh:
                Log.e("actionbar", "menu_refresh");
                return true;
            case R.id.menu_settings:
                Log.e("actionbar", "menu_settings");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
