package com.one.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class TagViewGroup extends ViewGroup {
	  private static final int SIDE_MARGIN = 16;//左右间距
	  private static final int TEXT_MARGIN = 16;
	  /**
	   * @param context
	   */
	  public TagViewGroup(Context context) {
	    super(context);
	  }
	  
	  /**
	   * @param context
	   * @param attrs
	   * @param defStyle
	   */
	  public TagViewGroup(Context context, AttributeSet attrs, int defStyle) {
	    super(context, attrs, defStyle);
	  }



	  /**
	   * @param context
	   * @param attrs
	   */
	  public TagViewGroup(Context context, AttributeSet attrs) {
	    super(context, attrs);
	  }



	  @Override
	  protected void onLayout(boolean changed, int l, int t, int r, int b) {
	    int childCount = getChildCount();
	    int autualWidth = r - l-getPaddingRight();
	    int x = SIDE_MARGIN+getPaddingLeft();// 横坐标开始
	    int y = 0+getPaddingTop();//纵坐标开始
	    int rows = 1;
	    for(int i=0;i<childCount;i++){
	      View view = getChildAt(i);
	      int width = view.getMeasuredWidth();
	      int height = view.getMeasuredHeight();
	      x += width+TEXT_MARGIN;
	      if(x>autualWidth){
	        x = width+SIDE_MARGIN+getPaddingLeft();
	        rows++;
	      }
	      y = rows*(height+TEXT_MARGIN)+getPaddingTop();
	      if(i==0){
	        view.layout(x-width-TEXT_MARGIN, y-height, x-TEXT_MARGIN, y);
	        x=x-TEXT_MARGIN;
	      }else{
	        view.layout(x-width, y-height, x, y);
	      }
	    }
	  };

	  @Override
	  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	    int x = SIDE_MARGIN;//横坐标
	    int y = 0;//纵坐标
	    int rows = 1;//总行数
	    int specWidth = MeasureSpec.getSize(widthMeasureSpec);
	    int actualWidth = specWidth;//实际宽度
	    int childCount = getChildCount();
	    for(int index = 0;index<childCount;index++){
	      View child = getChildAt(index);
	      child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
	      int width = child.getMeasuredWidth();
	      int height = child.getMeasuredHeight();
	      x += width+TEXT_MARGIN;
	      if(x>actualWidth){//换行
	        x = width+SIDE_MARGIN;
	        rows++;
	      }
	      y = rows*(height+TEXT_MARGIN);
	    }
	    setMeasuredDimension(actualWidth, y+48);
        if(childCount ==0){
            setMeasuredDimension(actualWidth, y+96);
        }
	  }

	}
