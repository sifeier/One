package com.one.circle;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Region.Op;
import android.util.Log;

/*
 * 裁剪用功能函数
 * 根据输入的进度(百分比)裁剪区域
 * */
public class ClipCircleArea {
	private static Path mPath = new Path();
	public static double PI = 3.14159;
	
	/*
	 * 参数：
	 *  angle 角度值
	 * 返回值：
	 *  弧度值
	 * 功能：
	 *  根据输入的角度值返回对应的弧度值
	 * */
	public static double getRadian(float angle) {
		return PI*angle/180;
	}

	/*
	 * 参数：
	 * 	progress 当前进度
	 * 	max      最大进度
	 * 返回值:
	 * 	角度
	 * 功能：
	 * 	根据当前进度返回需要剪裁的角度
	 * */
	public static float getAngle(int progress, int max) {
		return (float) 360*(float)progress/(float)max;
	}

	/*
	 * 参数：
	 *	angle  角度
	 * 	r      圆半径
	 * 	canvas 画布
	 * 返回值:
	 * 	void
	 * 功能：
	 * 	根据角度和圆半径裁剪画布
	 * */
	public static void clipArea(float angle, int r, Canvas canvas) {

		mPath.reset();
		canvas.clipPath(mPath);
		if(angle >= 0)  {clipArea1(angle, r, canvas);}
		if(angle > 45)  {clipArea2(angle, r, canvas);}
		if(angle > 90)  {clipArea3(angle, r, canvas);}
		if(angle > 135) {clipArea4(angle, r, canvas);}
		if(angle > 180) {clipArea5(angle, r, canvas);}
		if(angle > 225) {clipArea6(angle, r, canvas);}
		if(angle > 270) {clipArea7(angle, r, canvas);}
		if(angle > 315 &&
			angle <= 360){clipArea8(angle, r, canvas);}

	}

	/*@param{angle} 0~45*/
	protected static void clipArea1(float angle, int r, Canvas canvas) {
		if(angle > 45)angle = 45;
		mPath.reset();
		mPath.moveTo(r, r);
		mPath.lineTo(r, 0);
		mPath.lineTo((int)(r + r*Math.tan(getRadian(angle))), 0);
		Log.d("ClipCircleArea","r:"+r+" dot:"+(int)(r + r*Math.tan(getRadian(angle))));
		mPath.lineTo(r, r);
		canvas.clipPath(mPath,Op.UNION);
	}

	/*@param{angle} 45~90*/
	protected static void clipArea2(float angle, int r, Canvas canvas) {
		if(angle > 90)angle = 90;
		mPath.reset();
		mPath.moveTo(r, r);
		mPath.lineTo(2*r, 0);
		mPath.lineTo(2*r,(int)( r - r*Math.tan(getRadian(90 - angle))));
		mPath.lineTo(r, r);
		canvas.clipPath(mPath, Op.UNION);
	}

	/*@param{angle} 90~135*/
	protected static void clipArea3(float angle, int r, Canvas canvas) {
		if(angle > 135)angle = 135;
		mPath.reset();
		mPath.moveTo(r, r);
		mPath.lineTo(2*r, r);
		mPath.lineTo(2*r, (int)(r + r*Math.tan(getRadian(angle-90))));
		mPath.lineTo(r, r);
		canvas.clipPath(mPath, Op.UNION);
	}

	/*@param{angle} 135~180*/
	protected static void clipArea4(float angle, int r, Canvas canvas) {
		if(angle > 180)angle = 180;
		mPath.reset();
		mPath.moveTo(r, r);
		mPath.lineTo(2*r, 2*r);
	 	mPath.lineTo((int)(r + r * Math.tan(getRadian(180-angle))) , 2*r);
		mPath.lineTo(r, r);
		canvas.clipPath(mPath, Op.UNION);
	}

	/*@param{angle} 180~225*/
	protected static void clipArea5(float angle, int r, Canvas canvas) {
		if(angle > 225)angle = 225;
		mPath.reset();
		mPath.moveTo(r, r);
		mPath.lineTo(r, 2*r);
		mPath.lineTo((int)(r - r*Math.tan(getRadian(angle-180))), 2*r);
		mPath.lineTo(r, r);
		canvas.clipPath(mPath, Op.UNION);
	}

	/*@param{angle} 225~270*/
	protected static void clipArea6(float angle, int r, Canvas canvas) {
		if(angle > 270)angle = 270;
		mPath.reset();
		mPath.moveTo(r, r);
		mPath.lineTo(0, 2*r);
		mPath.lineTo(0, (int)(r + r*Math.tan(getRadian(270-angle))));
		mPath.lineTo(r, r);
		canvas.clipPath(mPath, Op.UNION);
	}

	/*@param{angle} 270~315*/
	protected static void clipArea7(float angle, int r, Canvas canvas) {
		if(angle > 315)angle = 315;
		mPath.reset();
		mPath.moveTo(r, r);
		mPath.lineTo(0, r);
		mPath.lineTo(0, (int)(r - r * Math.tan(getRadian(angle-270))));
		mPath.lineTo(r, r);
		canvas.clipPath(mPath, Op.UNION);
	}

	/*@param{angle} 315~360*/
	protected static void clipArea8(float angle, int r, Canvas canvas) {
		if(angle > 360)angle = 360;
		mPath.reset();
		mPath.moveTo(r, r);
		mPath.lineTo(0, 0);
		mPath.lineTo((int)(r - r * Math.tan(getRadian(360-angle))), 0);
		mPath.lineTo(r, r);
		canvas.clipPath(mPath, Op.UNION);
	}
}