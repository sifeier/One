package com.switchlayout;

import com.one.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

/**
 * SwitchLayout
 */
public class SwitchMainActivity extends Activity {
	private Button btn_ok, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7,
			btn_8, btn_9, btn_10, btn_11, btn_12, btn_13, btn_14;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_switchlayout_main);
		initView();

		SwitchLayout.getSlideFromBottom(this, false,
				BaseEffects.getQuickToSlowEffect());
		initListener();

	}

	private void initListener() {
		btn_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in = new Intent(SwitchMainActivity.this, SwitchSecondActivity.class);
				in.putExtra("key", 0);
				SwitchMainActivity.this.startActivity(in);
			}
		});

		btn_1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in = new Intent(SwitchMainActivity.this, SwitchSecondActivity.class);
				in.putExtra("key", 1);
				SwitchMainActivity.this.startActivity(in);
			}
		});
		btn_2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in = new Intent(SwitchMainActivity.this, SwitchSecondActivity.class);
				in.putExtra("key", 2);
				SwitchMainActivity.this.startActivity(in);
			}
		});
		btn_3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in = new Intent(SwitchMainActivity.this, SwitchSecondActivity.class);
				in.putExtra("key", 3);
				SwitchMainActivity.this.startActivity(in);
			}
		});
		btn_4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in = new Intent(SwitchMainActivity.this, SwitchSecondActivity.class);
				in.putExtra("key", 4);
				SwitchMainActivity.this.startActivity(in);
			}
		});
		btn_5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in = new Intent(SwitchMainActivity.this, SwitchSecondActivity.class);
				in.putExtra("key", 5);
				SwitchMainActivity.this.startActivity(in);
			}
		});
		btn_6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in = new Intent(SwitchMainActivity.this, SwitchSecondActivity.class);
				in.putExtra("key", 6);
				SwitchMainActivity.this.startActivity(in);
			}
		});

		btn_7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in = new Intent(SwitchMainActivity.this, SwitchSecondActivity.class);
				in.putExtra("key", 7);
				SwitchMainActivity.this.startActivity(in);
			}
		});
		btn_8.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in = new Intent(SwitchMainActivity.this, SwitchSecondActivity.class);
				in.putExtra("key", 8);
				SwitchMainActivity.this.startActivity(in);
			}
		});
		btn_9.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in = new Intent(SwitchMainActivity.this, SwitchSecondActivity.class);
				in.putExtra("key", 9);
				SwitchMainActivity.this.startActivity(in);
			}
		});
		btn_10.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in = new Intent(SwitchMainActivity.this, SwitchSecondActivity.class);
				in.putExtra("key", 10);
				SwitchMainActivity.this.startActivity(in);
			}
		});
		btn_11.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in = new Intent(SwitchMainActivity.this, SwitchSecondActivity.class);
				in.putExtra("key", 11);
				SwitchMainActivity.this.startActivity(in);
			}
		});
		btn_12.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in = new Intent(SwitchMainActivity.this, SwitchSecondActivity.class);
				in.putExtra("key", 12);
				SwitchMainActivity.this.startActivity(in);
			}
		});
		btn_13.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in = new Intent(SwitchMainActivity.this, SwitchSecondActivity.class);
				in.putExtra("key", 13);
				SwitchMainActivity.this.startActivity(in);
			}
		});
		btn_14.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in = new Intent(SwitchMainActivity.this, SwitchSecondActivity.class);
				in.putExtra("key", 14);
				SwitchMainActivity.this.startActivity(in);
			}
		});
	}

	private void initView() {
		btn_ok = (Button) this.findViewById(R.id.btn_ok);
		btn_1 = (Button) this.findViewById(R.id.btn_1);
		btn_2 = (Button) this.findViewById(R.id.btn_2);
		btn_3 = (Button) this.findViewById(R.id.btn_3);
		btn_4 = (Button) this.findViewById(R.id.btn_4);
		btn_5 = (Button) this.findViewById(R.id.btn_5);
		btn_6 = (Button) this.findViewById(R.id.btn_6);
		btn_7 = (Button) this.findViewById(R.id.btn_7);
		btn_8 = (Button) this.findViewById(R.id.btn_8);
		btn_9 = (Button) this.findViewById(R.id.btn_9);
		btn_10 = (Button) this.findViewById(R.id.btn_10);
		btn_11 = (Button) this.findViewById(R.id.btn_11);
		btn_12 = (Button) this.findViewById(R.id.btn_12);
		btn_13 = (Button) this.findViewById(R.id.btn_13);
		btn_14 = (Button) this.findViewById(R.id.btn_14);

	}
}
