package com.one.recyclerv;

import com.demo.common.logger.Log;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by buke on 15/12/8.
 */
public class FullyGridLayoutManager extends GridLayoutManager {

    private int[] mMeasuredDimension = new int[2];

    public FullyGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public FullyGridLayoutManager(Context context, int spanCount, int orientation,
            boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec,
            int heightSpec) {
        final int widthMode = View.MeasureSpec.getMode(widthSpec);
        final int heightMode = View.MeasureSpec.getMode(heightSpec);
        final int widthSize = View.MeasureSpec.getSize(widthSpec);
        final int heightSize = View.MeasureSpec.getSize(widthSpec);

        int width = 0;
        int height = 0;
        int count = getItemCount();
        int span = getSpanCount();

        for (int i = 0; i < count; i++) {
            measureScrapChild(recycler, i,
                    View.MeasureSpec.makeMeasureSpec(widthSize, widthMode),
                    View.MeasureSpec.makeMeasureSpec(heightSize, heightMode),
                    mMeasuredDimension);

            if (getOrientation() == HORIZONTAL) {
                if (i % span == 0) {
                    width = width + mMeasuredDimension[0];
                }
                if (i == 0) {
                    height = mMeasuredDimension[1];
                }
            } else {
                if (i % span == 0) {
                    height = height + mMeasuredDimension[1];
                }
                if (i == 0) {
                    width = mMeasuredDimension[0];
                }
            }
        }

        switch (widthMode) {
            case View.MeasureSpec.EXACTLY:
                width = widthSize;
            case View.MeasureSpec.AT_MOST:
            case View.MeasureSpec.UNSPECIFIED:
        }

        switch (heightMode) {
            case View.MeasureSpec.EXACTLY:
                height = heightSize;
            case View.MeasureSpec.AT_MOST:
            case View.MeasureSpec.UNSPECIFIED:
        }

        setMeasuredDimension(width, height);
    }

    private void measureScrapChild(RecyclerView.Recycler recycler, int position, int widthSpec,
            int heightSpec, int[] measuredDimension) {
        if (position < getItemCount()) {
            try {
                View view = recycler.getViewForPosition(position); // FIXME: 15/12/8
                if (view != null) {
                    RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view
                            .getLayoutParams();
                    int childWidthSpec = ViewGroup
                            .getChildMeasureSpec(widthSpec, getPaddingLeft() + getPaddingRight(),
                                    p.width);
                    int childHeightSpec = ViewGroup
                            .getChildMeasureSpec(heightSpec, getPaddingTop() + getPaddingBottom(),
                                    p.height);
                    view.measure(childWidthSpec, childHeightSpec);
                    measuredDimension[0] = view.getMeasuredWidth() + p.leftMargin + p.rightMargin;
                    measuredDimension[1] = view.getMeasuredHeight() + p.topMargin + p.bottomMargin;
                    recycler.recycleView(view);
                }
            } catch (Exception ex) {
                Log.e("FullyGridLayoutManager",
                        String.format("onMeasure error: ", ex.getMessage()));
            }

        }
    }
}
