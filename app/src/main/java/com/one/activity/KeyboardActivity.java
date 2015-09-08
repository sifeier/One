package com.one.activity;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.one.R;

public class KeyboardActivity extends ActionBarActivity {

    TextView mClear;

    EditText mInputEdit;

    ImageView mDivider;

    int keyBoardHeight = 0;

    Context mContext;

    private LinearLayout dynamicLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_keyboard);
        mClear = (TextView) this.findViewById(R.id.clear_btn);
        mInputEdit = (EditText) this.findViewById(R.id.input_et);
        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputEdit.setText("");
            }
        });

        mDivider = (ImageView) this.findViewById(R.id.divider_iv);
        mDivider.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (keyBoardHeight < 100) {
                            Rect r = new Rect();
                            mDivider.getWindowVisibleDisplayFrame(r);

                            int screenHeight = mDivider.getRootView().getHeight();
                            int heightDiff = screenHeight - (r.bottom - r.top);

                            int resId = getResources()
                                    .getIdentifier("status_bar_height", "dimen", "android");
                            if (resId > 0) {
                                heightDiff -= getResources().getDimensionPixelOffset(resId);
                            }

                            if (heightDiff > 100) {
                                keyBoardHeight = heightDiff;
                            }

                            Toast.makeText(mContext, "kb height: " + keyBoardHeight,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        dynamicLayout = (LinearLayout) findViewById(R.id.dynamic_ly);
        createContentViews();

    }


    private void createContentViews() {
        int itemNum = 6;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        params.weight = 1;

        for (int i = 0; i < itemNum; i++) {
            View vv = LayoutInflater.from(this).inflate(R.layout.chat_input_item, null, true);
            vv.setLayoutParams(params);

            final int ss = i;
            vv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "index: " + ss, Toast.LENGTH_SHORT).show();
                }
            });

            dynamicLayout.addView(vv, i);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.keyboard, menu);
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
