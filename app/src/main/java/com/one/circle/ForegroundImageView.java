package com.one.circle;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ForegroundImageView extends ImageView {

	private int progress = 0;  //当前进度
	private int max = 100;     //最大进度
	
	public ForegroundImageView(Context context) {
		super(context);
	}

	public ForegroundImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/*
	 * 参数：
	 *  progress 当前进度
	 * 返回值：
	 * 	void
	 * 功能：
	 * 	设置当前进度
	 * */
	public void setProgress(int progress) {
		this.progress = progress;
	}
	
	/*
	 * 参数：
	 *  void
	 * 返回值：
	 * 	当前进度
	 * 功能：
	 * 	获取当前进度
	 * */
	public int getProgress() {
		return progress;
	}

	/*
	 * 参数：
	 *  max 最大进度
	 * 返回值：
	 * 	void
	 * 功能：
	 * 	设置最大进度
	 * */
	public void setMax(int max) {
		this.max = max;
	}
	
	/*
	 * 参数：
	 *  void
	 * 返回值：
	 * 	最大进度
	 * 功能：
	 * 	获取允许的最大进度
	 * */
	public int getMax() {
		return max;
	}

	/*
	 * 参数：
	 *  canvas 画布
	 * 返回值：
	 * 	void
	 * 功能：
	 * 	重写onDraw函数，根据当前进度和最大进度裁剪图像以显示进度
	 * */
	@Override
	public void onDraw(Canvas canvas) {
		//根据当前进度和最大进度获取需要显示的角度
		float angle = ClipCircleArea.getAngle(progress, max);
		
		int heigth=getHeight();
		int width=getWidth();
		
		canvas.save();
		//求出需要裁剪的区域
		ClipCircleArea.clipArea(angle, (heigth>width?(heigth/2):(width/2)), canvas);
		//将改变后的canvas放入父类onDraw参数中
		super.onDraw(canvas);
		canvas.restore();
	}


}