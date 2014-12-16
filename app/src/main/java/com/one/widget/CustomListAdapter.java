package com.one.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import com.one.R;

/**
 * Created by sifeier on 14-11-12.
 */
public class CustomListAdapter extends ArrayAdapter<String> {
    Context mContext;
    ArrayList<String> mList;
    int mResourceId;
    int mCurrentPosition;
    Holder mHolder;
    public View mView;

    public CustomListAdapter(Context context, int resourceId, ArrayList<String> list) {
        super(context, R.layout.horizontal_item, list);
        mContext = context;
        mList = list;
        mResourceId = resourceId;
    }

    class Holder {
        TextView text;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LinearLayout layout;

        if( convertView == null) {
            layout = (LinearLayout) View.inflate(mContext, mResourceId, null);
            mHolder =  new Holder();
            mHolder.text = (TextView) layout.findViewById(R.id.title);
            layout.setTag(mHolder);
        } else {
            layout = (LinearLayout) convertView;
            mView = layout;
            mHolder = (Holder) layout.getTag();
        }

        mHolder.text.setText(getItem(position));
        return layout;
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    public int getCurrentPosition() {
        return mCurrentPosition;
    }

}
