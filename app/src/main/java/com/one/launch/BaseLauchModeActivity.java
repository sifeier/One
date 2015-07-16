package com.one.launch;

import com.one.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by sifeier on 15/7/16.
 */
public class BaseLauchModeActivity extends Activity {
    private TextView taskInfo;
    private TextView jumpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_launch_mode);
        taskInfo = (TextView)findViewById(R.id.task_tv);
        taskInfo.setText(String.format("current task id: %s, current activity: %s", getTaskId(), toString()));

        jumpBtn = (Button)findViewById(R.id.jump_btn);
    }

    public void jumpBtnClick(View v) {

    }

}
