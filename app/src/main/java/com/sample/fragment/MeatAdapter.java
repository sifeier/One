package com.sample.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.one.R;

/**
 * Created by sifeier on 14-11-18.
 */
public class MeatAdapter extends BaseAdapter{
    private final LayoutInflater mLayoutInflater;
    private final int mResourceId;

    public MeatAdapter(LayoutInflater inflater, int resourceId) {
        mLayoutInflater = inflater;
        mResourceId = resourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view;
        final ViewHolder holder;

        if( null == convertView) {
            view = mLayoutInflater.inflate(mResourceId, parent, false);
            holder = new ViewHolder();
            assert view != null;
            holder.image = (ImageView)view.findViewById(R.id.image);
            holder.title = (TextView)view.findViewById(R.id.title);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        bindView(holder, position);
        return view;
    }

    @Override
    public long getItemId(int position) {
        return Meat.MEATS[position].resourceId;
    }

    @Override
    public int getCount() {
        return Meat.MEATS.length;
    }

    @Override
    public Meat getItem(int position) {
        return Meat.MEATS[position];
    }

    public void bindView(ViewHolder holder, int position) {
        Meat meat = getItem(position);
        holder.image.setImageResource(meat.resourceId);
        holder.title.setText(meat.title);
    }

    public static class ViewHolder {
        public ImageView image;
        public TextView title;
    }
}
