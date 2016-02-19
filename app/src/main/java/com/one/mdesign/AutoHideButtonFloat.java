package com.one.mdesign;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 *
 */
public class AutoHideButtonFloat extends ButtonFloat implements AbsListView.OnScrollListener {
    ListView listView;
    private boolean floatHiding = false, floatShowing = false;
    private int mLastFirstVisibleItem;
    private View view = this;
    private AbsListView.OnScrollListener onScrollListener;

    public AutoHideButtonFloat(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
        this.listView.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        switch (scrollState) {
            case AbsListView.SCROLL_AXIS_NONE:
                floatHiding = false;
                floatShowing = false;
                ViewPropertyAnimator animator = view.animate().translationY(0).setDuration(300);
                animator.start();
                break;
        }
        if (onScrollListener != null)
            onScrollListener.onScrollStateChanged(absListView, scrollState);
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        if (mLastFirstVisibleItem < firstVisibleItem) {
            if (floatShowing) floatShowing = false;
            if (!floatHiding) {
                ViewPropertyAnimator animator = view.animate().translationY(500).setDuration(300);
                animator.start();
                floatHiding = true;
            }
        }
        if (mLastFirstVisibleItem > firstVisibleItem) {
            if (floatHiding) {
                floatHiding = false;
            }
            if (!floatShowing) {

                ViewPropertyAnimator animator = view.animate().translationY(0).setDuration(300);
                animator.start();
                floatShowing = true;
            }
        }
        mLastFirstVisibleItem = firstVisibleItem;
        if (onScrollListener != null)
            onScrollListener.onScroll(absListView, firstVisibleItem, visibleItemCount, totalItemCount);
    }
}
