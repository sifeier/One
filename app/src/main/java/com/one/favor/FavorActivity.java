package com.one.favor;

import com.one.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by buke on 15/7/17.
 */
public class FavorActivity extends Activity {

    private FavorLayout mFavorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_favor_heart);

        mFavorLayout = (FavorLayout)findViewById(R.id.favor_rly);
        findViewById(R.id.sender).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFavorLayout.addFavor();
            }
        });

    }
}
