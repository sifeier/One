package com.one.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * Created by buke on 15/7/20.
 */
public class CustomGridList extends RelativeLayout {

    private static final int INDEX_TAG = 0x04 << 24;

    private CustomGridAdapter<?> mCustomGridAdapter;

    private LayoutInflater mLayoutInflater;

    private List<View> mViewList;

    private AdapterView.OnItemClickListener mOnItemClickListener;

    public CustomGridList(Context context) {
        this(context, null);
    }

    public CustomGridList(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomGridList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        setLayoutInflater(LayoutInflater.from(context));
    }

    private void initView() {

    }

    public void setLayoutInflater(LayoutInflater inflater) {
        mLayoutInflater = inflater;
    }

    public LayoutInflater getLayoutInflater() {
        return mLayoutInflater;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (null != mCustomGridAdapter) {
            mCustomGridAdapter.registerView(this);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (null != mCustomGridAdapter) {
            mCustomGridAdapter.registerView(null);
        }
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public void setAdapter(CustomGridAdapter<?> adapter) {
        if (null == adapter) {
            throw new IllegalArgumentException("adapter should not be null");
        }
        mCustomGridAdapter = adapter;
        mCustomGridAdapter.registerView(this);
    }

    public BaseAdapter getAdapter() {
        return mCustomGridAdapter;
    }

    public void setColumnNum(int column) {
        mCustomGridAdapter.setColumNum(column);
    }

    /**
     * cool!
     */
    public void onDataListChange() {
        removeAllViews();

        int len = mCustomGridAdapter.getCount();
        int w = mCustomGridAdapter.getBlockWidth();
        int h = mCustomGridAdapter.getBlockHeight();
        int column = mCustomGridAdapter.getColumNum();

        int horizontalSpace = mCustomGridAdapter.getHorizontalSpace();
        int verticalSpace = mCustomGridAdapter.getVerticalSpace();

        boolean blockDecendant = getDescendantFocusability() == ViewGroup.FOCUS_BLOCK_DESCENDANTS;
        boolean needSpecialTreatForThree = (column == 2 && len == 3) ? true : false;

        int left = 0;
        int top = 0;
        int row = 0;
        int col = 0;

        for (int i = 0; i < len; i++) {
            if (needSpecialTreatForThree) {
                left = w >> 1;
                top = 0;

                if (i == 1) {
                    left = 0;
                    top = verticalSpace + 1;
                } else if (i == 2) {
                    left = horizontalSpace + 1;
                    top = verticalSpace + 1;
                }
            } else {
                left = 0;
                top = 0;
                row = len / column;
                col = len % column;

                if (col > 0) {
                    left = (horizontalSpace + w) * col;
                }

                if (row > 0) {
                    top = (verticalSpace + h) * row;
                }
            }

            RelativeLayout.LayoutParams layoutParams = new LayoutParams(w, h);
            layoutParams.setMargins(left, top, 0, 0);
            View convertView = null;
            if (mViewList.size() > i) {
                convertView = mViewList.get(i);
            }

            View view = mCustomGridAdapter.getView(i, convertView, null);
            if (i > mViewList.size()) {
                mViewList.add(view);
            }

            if (!blockDecendant) {
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int index = (Integer) v.getTag(INDEX_TAG);
                        if (null != mOnItemClickListener) {
                            mOnItemClickListener.onItemClick(null, v, index, index);
                        }
                    }
                });
            }

            view.setTag(INDEX_TAG, i);
            addView(view, layoutParams);
        }
    }
}
