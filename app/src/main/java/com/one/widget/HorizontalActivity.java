package com.one.widget;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.one.R;
import java.util.ArrayList;

public class HorizontalActivity extends ActionBarActivity {
    CustomListAdapter mCustomAdapter;
    private TextView mTextView;
    HorizontalScrollView mHscrollView;
    ImageView mImage;

    ArrayList<String> listContent = new ArrayList<String>() {
        {
            add("wisper");
            add("normal");
            add("vip");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal);
        mCustomAdapter =  new CustomListAdapter(this, R.layout.horizontal_item, listContent);
        mHscrollView = (HorizontalScrollView) findViewById(R.id.scrollView_footer);
        LinearLayout linear = (LinearLayout)findViewById(R.id.linear);

        for(int i=0; i < mCustomAdapter.getCount(); i++) {
            linear.addView(mCustomAdapter.getView(i, null, null));
        }

        mImage = (ImageView)findViewById(R.id.dragle_view);
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.horizontal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
