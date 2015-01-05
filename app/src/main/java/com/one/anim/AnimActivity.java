package com.one.anim;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.one.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sifeier on 14-12-5.
 */
public class AnimActivity extends Activity {

    private DragDownLayout dragDownLayout;

    private ListView lv;

    private ImageView mDragLogoImageV;
    private int mImageViewHeight;
    private int mImageViewWidth;
    private int mDynimacHeight = 0;

    private static List<String> ss =  new ArrayList<String>(20);

    static {
        ss.add("monday");
        ss.add("tuesday");
        ss.add("wedesday");
        ss.add("thirsday");
        ss.add("friday");
        ss.add("saturday");
        ss.add("sunday");
        ss.add("one");
        ss.add("two");
        ss.add("three");
        ss.add("four");
        ss.add("five");
        ss.add("six");
        ss.add("seven");
        ss.add("eight");
        ss.add("nine");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_activity);
        initViews();
        initData();
        initLogoImageView();
    }

    private void initData() {
        lv = (ListView)findViewById(R.id.number_lv);
        NumAdapter adapter = new NumAdapter(this, ss);
        lv.setAdapter(adapter);
    }

    private void initViews() {
        dragDownLayout = (DragDownLayout)findViewById(R.id.anim_dragdown_layout);
        dragDownLayout.setOnDragListener(new DragDownLayout.OnDragListener() {
            @Override
            public boolean canChildScrollUp() {
                if(lv.getFirstVisiblePosition() == 0){
                    return false;
                }
                return true;
            }

            @Override
            public void onDrag(int draged, int maxHeight) {
                int dis = Math.abs(draged);
                 int alpha = dis * 255 / maxHeight;
                int progress = dis * 100 / maxHeight;
                //调整Alpha
                mDragLogoImageV.setAlpha(alpha>255 ? 255 : alpha);
                //调整Scale
                float scaleF = (float) progress / 100;
                if(scaleF > 1){
                    scaleF = (float) 1.0;
                }
                setScale(scaleF);
                //调整Margin
                RelativeLayout.LayoutParams layoutParams = (android.widget.RelativeLayout.LayoutParams) mDragLogoImageV
                        .getLayoutParams();
                int viewHeight = getHeight(mDragLogoImageV);
                int margin = (dis - viewHeight) >> 1;
                layoutParams.topMargin = margin;
                mDragLogoImageV.setLayoutParams(layoutParams);
                Log.e("AnimActivity", " alpha: " + alpha + " scale: " + scaleF + " margin" + margin);
            }

            @Override
            public void onDismiss() {
                dragDownLayout.setEnabled(false);
            }
        });

    }

    private void initLogoImageView() {
        mDragLogoImageV = (ImageView) findViewById(R.id.drag_logo_iv);
        mDragLogoImageV.setAlpha(0);
        setScale(0.1f);
        mImageViewHeight = getHeight(mDragLogoImageV);
        mImageViewWidth = getWidth(mDragLogoImageV);
    }

    private int getHeight(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return (view.getMeasuredHeight());
    }

    private int getWidth(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return (view.getMeasuredWidth());
    }

    private void setScale(float scale) {
        mDragLogoImageV.setScaleX(scale);
        mDragLogoImageV.setScaleX(scale);
    }

    @Override
    public void onResume() {
        super.onResume();

        if(dragDownLayout != null && dragDownLayout.getVisibility() != View.VISIBLE) {
            dragDownLayout.setVisibility(View.VISIBLE);
            dragDownLayout.scrollUpOnResume();
        }
    }
}
