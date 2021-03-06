package com.one;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.view.SpinnerActivity;
import com.one.activity.ButterknifeActivity;
import com.one.activity.FrameLayoutActivity;
import com.one.activity.KeyboardActivity;
import com.one.activity.ScreenLockActivity;
import com.one.activity.WidgetBox1Activity;
import com.one.activity.WidgetBoxActivity;
import com.one.anim.AnimActivity;
import com.one.app.AudioRecordTest;
import com.one.app.OtherAppActivity;
import com.one.circle.CircleActivity;
import com.one.draggridview.DragGridviewActivity;
import com.one.favor.FavorActivity;
import com.one.fun.MorseCodeActivity;
import com.one.listview.ExpandLvActivity;
import com.one.material.DrawerActivity;
import com.one.material.MaterialDActivity;
import com.one.media.CameraActivity;
import com.one.net.WebViewTest;
import com.one.view.ActionBaStylerActivity;
import com.one.view.BlurImageActivity;
import com.one.view.GaussBlurActivity;
import com.one.widget.HorizontalActivity;
import com.one.widget.TripleTapActivity;
import com.demo.UI.actionbar.ActionBarShareActivity;
import com.demo.fragment.FragmentTestActivity;
import com.switchlayout.SwitchMainActivity;

import java.lang.ref.WeakReference;


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

    public void onStyleABClick(View v) {
        Intent i = new Intent(mContext, ActionBaStylerActivity.class);
        startActivity(i);
    }

    public void onBlurImageClick(View v) {
        Intent i = new Intent(mContext, BlurImageActivity.class);
        startActivity(i);
    }

    public void onGaussBlurClick(View v) {
        Intent i = new Intent(mContext, GaussBlurActivity.class);
        startActivity(i);
    }

    public void onMorseClick(View v) {
        Intent i = new Intent(mContext, MorseCodeActivity.class);
        startActivity(i);
    }

    public void onSpinnerClick(View v) {
        Intent i = new Intent(mContext, SpinnerActivity.class);
        startActivity(i);
    }

    public void onOtherAppClick(View v) {
        Intent i = new Intent(mContext, OtherAppActivity.class);
        startActivity(i);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements View.OnClickListener{
        private  WeakReference<Activity> activityWeakReference;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_lauch, container, false);

            initOnClick(rootView);

            initTextColor(rootView);

            return rootView;
        }

        private  void initTextColor(View root) {
            try {
                TextView textView = (TextView) root.findViewById(R.id.welcom_msg_tv);
                Spannable sp = new SpannableString("欢迎布可啊");
                sp.setSpan(new ForegroundColorSpan(Color.parseColor("#FFC0CB")), 2,4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.append(sp);
                sp = new SpannableString("再次欢迎布可啊");
                sp.setSpan(new ForegroundColorSpan(Color.parseColor("#F08080")), 4,6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.append("\n");
                textView.append(sp);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        private void initOnClick(View parent) {
            parent.findViewById(R.id.screenlock_btn).setOnClickListener(this);
            parent.findViewById(R.id.framell_tv).setOnClickListener(this);
            parent.findViewById(R.id.circle_btn).setOnClickListener(this);
            parent.findViewById(R.id.softkeyboard_btn).setOnClickListener(this);
            parent.findViewById(R.id.panel_btn).setOnClickListener(this);
            parent.findViewById(R.id.triple).setOnClickListener(this);
            parent.findViewById(R.id.horizontal_sv).setOnClickListener(this);
            parent.findViewById(R.id.fragment).setOnClickListener(this);
            parent.findViewById(R.id.actionbar_base).setOnClickListener(this);
            parent.findViewById(R.id.drag).setOnClickListener(this);
            parent.findViewById(R.id.material).setOnClickListener(this);
            parent.findViewById(R.id.material_drawer).setOnClickListener(this);
            parent.findViewById(R.id.widget_box).setOnClickListener(this);
            parent.findViewById(R.id.widget_box1).setOnClickListener(this);
            parent.findViewById(R.id.switch_layout).setOnClickListener(this);
            parent.findViewById(R.id.drag_gridview).setOnClickListener(this);
            parent.findViewById(R.id.favor_tv).setOnClickListener(this);
            parent.findViewById(R.id.webview_btn).setOnClickListener(this);
            parent.findViewById(R.id.auto_btn).setOnClickListener(this);
            parent.findViewById(R.id.expand_lv_btn).setOnClickListener(this);
            parent.findViewById(R.id.butterknife_btn).setOnClickListener(this);
            parent.findViewById(R.id.audio_btn).setOnClickListener(this);
            parent.findViewById(R.id.video_btn).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.expand_lv_btn:
                    intent = new Intent(mContext, ExpandLvActivity.class);
                    startActivity(intent);
                    break;
                case R.id.video_btn:
                    intent = new Intent(mContext, CameraActivity.class);
                    startActivity(intent);
                    break;
                case R.id.audio_btn:
                    intent = new Intent(mContext, AudioRecordTest.class);
                    startActivity(intent);
                    break;
                case R.id.butterknife_btn:
                    intent = new Intent(mContext, ButterknifeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.webview_btn:
                    intent = new Intent(mContext, WebViewTest.class);
                    startActivity(intent);
                    break;
                case R.id.auto_btn:
                    intent = new Intent(mContext, TaskActivity.class);
                    startActivity(intent);
                    break;
                case R.id.screenlock_btn:
                    intent = new Intent(mContext, ScreenLockActivity.class);
                    startActivity(intent);
                    break;
                case R.id.framell_tv:
                    intent = new Intent(mContext, FrameLayoutActivity.class);
                    startActivity(intent);
                    break;
                case R.id.circle_btn:
                    intent = new Intent(mContext, CircleActivity.class);
                    startActivity(intent);
                    break;
                case R.id.softkeyboard_btn:
                    intent = new Intent(mContext, KeyboardActivity.class);
                    startActivity(intent);
                    break;
                case R.id.triple:
                    intent = new Intent(mContext, TripleTapActivity.class);
                    startActivity(intent);
                    break;
                case R.id.horizontal_sv:
                    intent  =  new Intent(mContext, HorizontalActivity.class);
                    startActivity(intent);
                    break;
                case R.id.fragment:
                    intent  = new Intent(mContext, FragmentTestActivity.class);
                    startActivity(intent);
                    break;
                case R.id.actionbar_base:
                    intent =  new Intent(mContext, ActionBarShareActivity.class);
                    startActivity(intent);
                    break;
                case R.id.drag:
                    intent = new Intent(mContext, AnimActivity.class);
                    startActivity(intent);
                    break;
                case R.id.material:
                    intent = new Intent(mContext, MaterialDActivity.class);
                    startActivity(intent);
                    break;
                case R.id.material_drawer:
                    intent = new Intent(mContext, DrawerActivity.class);
                    startActivity(intent);
                    break;
                case R.id.widget_box:
                    intent  = new Intent(mContext, WidgetBoxActivity.class);
                    startActivity(intent);
                    break;
                case R.id.widget_box1:
                    intent  = new Intent(mContext, WidgetBox1Activity.class);
                    startActivity(intent);
                    break;
                case R.id.switch_layout:
                    intent = new Intent(mContext, SwitchMainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.drag_gridview:
                    intent = new Intent(mContext, DragGridviewActivity.class);
                    startActivity(intent);
                    break;
                case R.id.favor_tv:
                    intent = new Intent(mContext, FavorActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    }

}
