package com.one.circle;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.one.R;

public class CircleProgress extends FrameLayout {
	
	private TextView            mText;         //锟侥憋拷锟斤拷锟斤拷锟斤拷锟斤拷示锟斤拷锟?
	private ImageView           mBackground;   //锟斤拷锟斤拷
	private ForegroundImageView mForeground;   //前锟斤拷锟斤拷锟斤拷示锟矫硷拷锟斤拷慕锟斤拷图片
	
	
	public CircleProgress(Context context) {
		super(context);
		iniLayout(context);
		setProgress(0);
		setBackground(getResources().getDrawable(R.drawable.background));
		setForeground(getResources().getDrawable(R.drawable.foreground));
	}
	
	public CircleProgress(Context context, AttributeSet attrs) {
		super(context,attrs);
		iniLayout(context);
		
		TypedArray a = context.obtainStyledAttributes(attrs,  
                R.styleable.CircleProgress);
		
		setMax(a.getInteger(R.styleable.CircleProgress_imax, 100));
		setProgress(a.getInteger(R.styleable.CircleProgress_iprogress, 0));
		setBackground(a.getDrawable(R.styleable.CircleProgress_ibackground ));
		setForeground(a.getDrawable(R.styleable.CircleProgress_iforeground ));
		setTextSize(a.getDimensionPixelSize(R.styleable.CircleProgress_itextSize, (int) mText.getTextSize()));
		
		a.recycle();
	}
	
	/*
	 * 锟斤拷锟斤拷
	 *  context 锟斤拷锟斤拷锟斤拷
	 * 锟斤拷锟斤拷值锟斤拷
	 * 	void
	 * 锟斤拷锟杰ｏ拷
	 * 	锟斤拷取锟斤拷锟斤拷锟侥硷拷锟斤拷锟斤拷取锟接控硷拷
	 * */
	protected void iniLayout(Context context){
		//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷源锟侥硷拷锟斤拷然锟襟将诧拷锟街革拷锟接碉拷锟斤拷前锟侥控硷拷
        LayoutInflater.from(context).inflate(R.layout.circle_progress, this, true);
		//锟斤拷锟絠d锟斤拷取锟接控硷拷
		mText = (TextView)findViewById(R.id.progress_text);
		mForeground = (ForegroundImageView)findViewById(R.id.foreground);
		mBackground = (ImageView)findViewById(R.id.background);
        
		mText.setText(0+"%");
	}
	
	
	/*
	 * 锟斤拷锟斤拷
	 *  void
	 * 锟斤拷锟斤拷值锟斤拷
	 * 	锟斤拷前锟斤拷锟?
	 * 锟斤拷锟杰ｏ拷
	 * 	锟斤拷取锟斤拷前锟斤拷锟?
	 * */
	public int getProgress() {
		return mForeground.getProgress();
	}

	/*
	 * 锟斤拷锟斤拷
	 *  progress 锟斤拷前锟斤拷锟?
	 * 锟斤拷锟斤拷值锟斤拷
	 * 	void
	 * 锟斤拷锟杰ｏ拷
	 * 	锟斤拷锟矫斤拷龋锟酵憋拷谋锟斤拷锟酵?
	 * */
	public void setProgress(int progress) {
		//锟斤拷炔锟接︼拷锟斤拷锟斤拷远锟斤拷锟斤拷锟斤拷锟斤拷
		if(progress > mForeground.getMax())return ;
		mForeground.setProgress(progress);
		mText.setText( 100*(float)mForeground.getProgress()
			/(float)mForeground.getMax() + "%" );
	}

	/*
	 * 锟斤拷锟斤拷
	 *  max 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 * 锟斤拷锟斤拷值锟斤拷
	 * 	void
	 * 锟斤拷锟杰ｏ拷
	 * 	锟斤拷锟矫斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟?
	 * */
	public void setMax(int max) {
		mForeground.setMax(max);
	}
	
	/*
	 * 锟斤拷锟斤拷
	 *  textSize 锟斤拷锟斤拷锟叫?
	 * 锟斤拷锟斤拷值锟斤拷
	 * 	void
	 * 锟斤拷锟杰ｏ拷
	 * 	锟斤拷锟斤拷TextView锟斤拷锟斤拷锟叫?
	 * */
	public void setTextSize(float textSize){
		mText.setTextSize(textSize);
	}

	/*
	 * 锟斤拷锟斤拷
	 *  void
	 * 锟斤拷锟斤拷值锟斤拷
	 * 	锟斤拷锟斤拷锟叫?
	 * 锟斤拷锟杰ｏ拷
	 * 	锟斤拷取TextView锟斤拷锟斤拷锟叫?
	 * */
	public float getTextSize(){
		return mText.getTextSize();
	}
	/*
	 * 锟斤拷锟斤拷
	 *  drawable 图片锟斤拷源
	 * 锟斤拷锟斤拷值锟斤拷
	 * 	void
	 * 锟斤拷锟杰ｏ拷
	 * 	锟斤拷锟斤拷图片锟斤拷为锟截硷拷锟斤拷锟斤拷
	 * */
	public void setBackground(Drawable drawable){
		mBackground.setImageDrawable(drawable);
	}

	/*
	 * 锟斤拷锟斤拷
	 *  drawable 图片锟斤拷源
	 * 锟斤拷锟斤拷值锟斤拷
	 * 	void
	 * 锟斤拷锟杰ｏ拷
	 * 	锟斤拷锟斤拷图片锟斤拷为锟截硷拷前锟斤拷(锟斤拷锟斤拷锟?)
	 * */
	public void setForeground(Drawable drawable){
		mForeground.setImageDrawable(drawable);
	}

}