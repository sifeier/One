package com.one;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.one.activity.FrameLayoutActivity;
import com.one.activity.KeyboardActivity;
import com.one.activity.ScreenLockActivity;
import com.one.anim.AnimActivity;
import com.one.circle.CircleActivity;
import com.one.panel.TestGesture;
import com.one.widget.HorizontalActivity;
import com.one.widget.TripleTapActivity;
import com.sample.UI.actionbar.ActionBarBaseActivity;
import com.sample.UI.actionbar.ActionBarShareActivity;
import com.sample.UI.actionbar.ActionBarStyledActivity;
import com.sample.fragment.FragmentTestActivity;


public class LauchActivity extends ActionBarActivity {
    private static Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_lauch);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lauch, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_lauch, container, false);

            initTextColor(rootView);


            Button screenLock = (Button) rootView.findViewById(R.id.screenlock_btn);
            screenLock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ScreenLockActivity.class);
                    startActivity(intent);
                }
            });

            Button framell = (Button) rootView.findViewById(R.id.framell_tv);
            framell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, FrameLayoutActivity.class);
                    startActivity(intent);
                }
            });

            Button circle = (Button) rootView.findViewById(R.id.circle_btn);
            circle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, CircleActivity.class);
                    startActivity(intent);
                }
            });

            Button softkeyboard = (Button) rootView.findViewById(R.id.softkeyboard_btn);
            softkeyboard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, KeyboardActivity.class);
                    startActivity(intent);
                }
            });

            Button panel = (Button) rootView.findViewById(R.id.panel_btn);
            panel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =  new Intent(mContext, TestGesture.class);
                    startActivity(intent);
                }
            });

            Button triple = (Button) rootView.findViewById(R.id.triple);
            triple.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, TripleTapActivity.class);
                    startActivity(intent);
                }
            });

            Button hScrollView = (Button) rootView.findViewById(R.id.horizontal_sv);
            hScrollView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent  =  new Intent(mContext, HorizontalActivity.class);
                    startActivity(intent);
                }
            });

            Button fragment = (Button)rootView.findViewById(R.id.fragment);
            fragment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent  = new Intent(mContext, FragmentTestActivity.class);
                    startActivity(intent);
                }
            });

            Button actionb = (Button)rootView.findViewById(R.id.actionbar_base);
            actionb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =  new Intent(mContext, ActionBarShareActivity.class);
                    startActivity(intent);
                }
            });

            Button drag = (Button)rootView.findViewById(R.id.drag);
            drag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, AnimActivity.class);
                    startActivity(intent);
                }
            });

            return rootView;
        }


        private  void initTextColor(View root) {
            try {
                TextView textView = (TextView) root.findViewById(R.id.welcom_msg_tv);
                Spannable sp = new SpannableString("欢迎布可啊");
                sp.setSpan(new ForegroundColorSpan(Color.parseColor("#222222")), 2,4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.append(sp);
                sp = new SpannableString("再次欢迎布可啊");
                sp.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), 4,6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.append("\n");
                textView.append(sp);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

}
