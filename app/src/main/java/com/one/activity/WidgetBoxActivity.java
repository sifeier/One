package com.one.activity;

import com.one.R;
import com.one.widget.PullToRefreshView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by sifeier on 15/6/24.
 */
public class WidgetBoxActivity extends Activity {

    private PullToRefreshView mPullToRefreshView;

    private ListView mListView;

    private static final int REFRESH_DELAY = 500;

    private static final String[] items = {"Try Pull down", " :) ", "Monday", "Tuesday",
            "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_widget_box);

        mListView = (ListView) findViewById(R.id.refresh_list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        mListView.setAdapter(adapter);
        mPullToRefreshView = (PullToRefreshView) findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, REFRESH_DELAY);
            }
        });
    }

}
