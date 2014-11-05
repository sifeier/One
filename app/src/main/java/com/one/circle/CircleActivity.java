package com.one.circle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.one.R;

public class CircleActivity extends Activity{
	private Button addButton;
	private CircleProgress progressCircle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.circle_test_activity);
		
		addButton = (Button)findViewById(R.id.add_progress);
		progressCircle = (CircleProgress)findViewById(R.id.test_progress_circle);
			
		addButton.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				progressCircle.setProgress(progressCircle.getProgress()+1);
			}
		});
	}

}
