package com.one.anim;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.one.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sifeier on 14-12-5.
 */
public class AnimActivity extends Activity {

    private DragDownLayout dragDownLayout;

    private ListView lv;

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
            public void onDrag(int draged, int deltaY) {

            }

            @Override
            public void onDismiss() {
                dragDownLayout.setEnabled(false);
            }
        });

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
