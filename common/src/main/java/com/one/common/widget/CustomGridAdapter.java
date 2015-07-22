package com.one.common.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by buke on 15/7/20.
 */
public class CustomGridAdapter<T> extends BaseAdapter {

    protected List<T> mList;

    protected CustomGridList mView;

    protected Context mContext;

    private int mBlockWidth = 180;

    private int mBlockHeight = 180;

    private int mHorizontalSpace = 2;

    private int mVerticalSpace = 2;

    private int mColumNum = 3;

    protected String imageLoaderKey;

    protected boolean isFromFlow;

    public CustomGridAdapter() {
    }

    public CustomGridAdapter(Context context) {
        mContext = context;
    }

    public void registerView(CustomGridList v) {
        mView = v;
    }

    public void setList(List<T> list) {
        mList = list;
    }

    public List<T> getList() {
        return mList;
    }

    public void displayBlocks() {
        if(null == mView) {
            throw new IllegalArgumentException("Apater has not been atatch to any CustomGridList");
        }

        mView.onDataListChange();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public void setSpace(int w, int h) {
        mHorizontalSpace = w;
        mVerticalSpace = h;
    }

    public int getHorizontalSpace() {
        return mHorizontalSpace;
    }

    public int getVerticalSpace() {
        return mVerticalSpace;
    }

    public void setblockSize(int w, int h) {
        mBlockWidth = w;
        mBlockHeight = h;
    }

    public int getBlockWidth() {
        return mBlockWidth;
    }

    public int getBlockHeight() {
        return mBlockHeight;
    }

    public void setColumNum(int num) {
        mColumNum = num;
    }

    public int getColumNum() {
        return mColumNum;
    }

    public void setImageLoaderKey(String key) {
        imageLoaderKey = key;
    }

    public void setNeedPut(boolean is) {
        isFromFlow = is;
    }
}
